package mediator;

import com.google.gson.Gson;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class ChatClientHandler implements Runnable, PropertyChangeListener
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private ChatModel model;
  private Gson gson;
  private String clientIP;
  public ChatClientHandler(Socket socket, ChatModel model, String clientIP)
  {
    this.socket=socket;
    this.model=model;
    gson=new Gson();
    running=true;
    this.clientIP=clientIP;
    model.addListener("Message", this);
  }

  //Always wait for requests from the clients
  @Override public void run()
  {
    try
    {
      in=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
      out=new PrintWriter(this.socket.getOutputStream(), true);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  while(running)
  {
    try
    {
      String request = in.readLine();

      //if it is related to the account creation window
      if (gson.fromJson(request, Map.class).get("type").equals("Create"))
      {
        UserPackage receivedPackage = gson.fromJson(request, UserPackage.class);
        UserPackage sendPackage;
        try
        {
          model.createUser(receivedPackage.getUsername(), receivedPackage.getPassword());
          sendPackage = new UserPackage(receivedPackage.getType(),
              receivedPackage.getUsername(), receivedPackage.getPassword());
        }
        catch (IllegalArgumentException ex)
        {
          sendPackage = new UserPackage("UserError", ex.getMessage());
        }
        String toJson = gson.toJson(sendPackage);
        out.println(toJson);
      }
      //or to the chat window
      else
      {
        CommunicationPackageFactory factory = new CommunicationPackageFactory(
            model);
        CommunicationPackage receivedPackageUnchecked = gson.fromJson(request,
            CommunicationPackage.class);

        //create the answer for the client
        CommunicationPackage sendPackage = (CommunicationPackage) factory.getPackage(
            receivedPackageUnchecked.getType(), receivedPackageUnchecked.getSender(),
            receivedPackageUnchecked.getRequest(), receivedPackageUnchecked.getReply());
        String toJson = gson.toJson(sendPackage);

        //if we receive a message
        if(sendPackage.getType().equals("Message"))
        {
          //we send it through model to fire an event
          model.send(sendPackage.getSender(), sendPackage.getRequest());

          //and we add a log to the file
          Logger.getInstance().addLog("IP: " + clientIP +"; " + sendPackage);
        }
        //if it is a command, we send the reply to the client
        else
        {
          out.println(toJson);
        }
      }
      }
      catch (Exception e)
      {
        close();
      }
    }

  }

  public void close()
  {
    try
    {
      in.close();
      out.close();
    }
    catch (IOException e)
    {
      //
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    //if we receive a message, we send it to all clients
    CommunicationPackage broadcast=new CommunicationPackage(evt.getPropertyName(), evt.getOldValue()+"", evt.getNewValue()+"", null);
    String toJson=gson.toJson(broadcast);
    out.println(toJson);
  }
}
