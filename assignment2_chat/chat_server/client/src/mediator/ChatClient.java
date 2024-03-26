package mediator;

import com.google.gson.Gson;

import model.*;
import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import model.Package;

public class ChatClient implements ChatModel, NamedPropertyChangeSubject
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;
  private PropertyChangeSupport property;
  private CommunicationPackage receivedCommunication;
  private ArrayList<CommunicationPackage> receivedCommunications;
  private UserPackage receivedUser;
  private String ip;
  public ChatClient(String host, int port) throws IOException
  {
    receivedCommunication=null;
    receivedUser=null;
    receivedCommunications=new ArrayList<>();
    socket=new Socket(host, port);
    ip=socket.getInetAddress().getHostAddress();
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    property = new PropertyChangeSupport(this);
    gson = new Gson();
    Thread t=new Thread(new ChatClientReceiver(this, in));
    t.setDaemon(true);
    t.start();
  }

  public synchronized void receive(String s)
  {
    if(gson.fromJson(s, Map.class).get("type").equals("Create") || gson.fromJson(s, Map.class).get("type").equals("UserError"))
    {
        receivedUser=gson.fromJson(s, UserPackage.class);
        System.out.println("In the receive method - user: " + receivedUser);
    }
    else
    {
      CommunicationPackage received=gson.fromJson(s, CommunicationPackage.class);
      receivedCommunications.add(received);

      if(received.getType().equals("Message"))
      {
        property.firePropertyChange("Message", null, received);
        System.out.println("In the receive method - broadcast: " + received);
      }
    }
    notify();
    System.out.println("Notified");
  }

  private synchronized String waitForReply(String type, String username)
  {
    boolean found=false;

    CommunicationPackage received=null;
    while(!found)
    {
      while (!receivedCommunications.isEmpty())
      {
        received = receivedCommunications.remove(
            receivedCommunications.size() - 1);
        found = (received.getType().equals("Error") || type.equals(received.getType())) && username.equals(received.getSender());
        if (found)
          break;
      }
      if(!found)
      {
        try
        {
          System.out.println("Wait1...");
          wait();
          System.out.println("Wait2...");
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
      //found=true;
      //System.out.println("Found");

    }
    if(received.getType().equals("Error"))
    {
      String error=received.getReply();
      throw new IllegalArgumentException(error);
    }
    else return received.toString();

  }

  @Override public String send(String username, String message)
  {
    if(message.startsWith("/"))
    {
      CommunicationPackage pack=new CommunicationPackage("Command", username, message, null);
      out.println(gson.toJson(pack));
      System.out.println("Sent - command: " + pack);
      return waitForReply("Command", username);
    }
    else
    {
      CommunicationPackage pack=new CommunicationPackage("Message", username, message, null);
      out.println(gson.toJson(pack));
      System.out.println("Sent - message: " + pack);

      Logger.getInstance().addLog(ip + " " + pack);
      return waitForReply("Message", username);
    }

  }

  @Override public String getWholeConversation(String username)
  {
    CommunicationPackage pack=new CommunicationPackage("Conversation", username, null, null);
    out.println(gson.toJson(pack));
    System.out.println("Sent - conversation: " + pack);

    return waitForReply("Conversation", username);
  }


  @Override public synchronized void createUser(String username, String password)
  {
    UserPackage pack=new UserPackage("Create", username, password);
    out.println(gson.toJson(pack));
    System.out.println("Sent: " + pack.toString());
    try
    {
      wait();
    }
    catch(InterruptedException e)
    {
      //
    }
    if(receivedUser.getType().equals("UserError"))
    {
      String error=receivedUser.getError();
      receivedUser=null;
      throw new IllegalArgumentException(error);
    }

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
}
