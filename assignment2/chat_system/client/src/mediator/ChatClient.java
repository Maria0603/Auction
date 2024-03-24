package mediator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Package;
import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient implements ServerModel, NamedPropertyChangeSubject
{

  private String HOST;
  private int PORT;

  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;

  String receivedText;
  private PropertyChangeSupport property;

  public ChatClient(String HOST, int PORT)
  {
    this.HOST = HOST;
    this.PORT = PORT;
    this.property = new PropertyChangeSupport(this);
  }

  public ChatClient()
  {
    this("localhost", 1234);
  }   //  Default constructor

  //  TODO: send(+), getWholeConversation(+), createUser(String username, String password)
  //        and logic behind broadcast +
  @Override public void send(String username, String message)
  {
    try {
      JsonObject messageObject = new JsonObject();
      messageObject.addProperty("username", username);
      messageObject.addProperty("message", message);
      out.println(messageObject.toString());

      out.flush(); // Flush the output stream to ensure immediate sending
    } catch (Exception e) {
      handleClientError("Error sending message: " + e.getMessage());
    }
  }

  public void receive(String c)
  {
    //  Might want to fire broadcast here for model to listen to
    property.firePropertyChange("broadcast", null, c);

  }

  @Override public String getWholeConversation()
  {

    StringBuilder conversationBuilder = new StringBuilder();

    try
    {
      out.println("GET_CONVERSATION");
      String line;
      while ((line = in.readLine()) != null)
      {
        conversationBuilder.append(line).append("\n");
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return conversationBuilder.toString();
  }

  @Override public void createUser(String username, String password)
      throws IllegalArgumentException
  {
    try {
    // Construct the JSON request for creating a user
    JsonObject createUserRequest = new JsonObject();

    createUserRequest.addProperty("operation", "createUser");
    createUserRequest.addProperty("username", username);
    createUserRequest.addProperty("password", password);

    out.println(createUserRequest.toString());
    out.flush(); // immediate sending


    boolean success = waitingForReply(); //waiting

    // If the user creation was successful, broadcast the event
    if (success) {
      property.firePropertyChange("UserCreated", null, username);
    }
  } catch (Exception e) {
    handleClientError("Error creating user: " + e.getMessage());
  }
  }

  @Override public void connect()
  {
    try
    {
      Socket socket = new Socket(HOST, PORT);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      gson = new Gson();

      //  Starting the receiver thread here
      ChatClientReceiver receiver = new ChatClientReceiver(this, in);
      Thread thread = new Thread(receiver);
      thread.setDaemon(true);
      thread.start();

    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void disconnect()
  {
    try
    {
      in.close();
      out.close();
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  public void handleClientError(String errorMessage)
  {
    System.err.println("Error: " + errorMessage);
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(propertyName, listener);
  }

  // waiting for server reply
  private boolean waitingForReply()
  {
    try
    {
      long startTime = System.currentTimeMillis();
      long timeout = 5000;
      while (!in.ready())
      {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime >= timeout)
        {
          handleClientError(
              "Timeout: Server did not respond within the specified time.");
          return false; // if fail
        }
        Thread.sleep(100);
      }

      // If ready - read the server response
      String response = in.readLine();
      if (response != null)
      {
        // Parse the response as JSON
        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
        // success?
        boolean success = jsonResponse.get("success").getAsBoolean();
        if (!success)
        {
          // !success = Error
          String errorMessage = jsonResponse.get("errorMessage").getAsString();
          handleClientError(errorMessage);
        }
        return success;
      }
      else
      {
        handleClientError("Error: Server response was null.");
        return false;
      }
    }
    catch (IOException | InterruptedException e)
    {
      handleClientError("Error occurred while waiting for server reply: " + e.getMessage());
      return false;
    }
  }
}
