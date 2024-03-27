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
      System.out.println("Received in client handler: " + request);

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
        System.out.println("Sent: " + toJson);
      }
      else
      {
        CommunicationPackageFactory factory = new CommunicationPackageFactory(
            model);
        CommunicationPackage receivedPackageUnchecked = gson.fromJson(request,
            CommunicationPackage.class);
        System.out.println("Client handler run method: " + receivedPackageUnchecked.getType() +  " " + receivedPackageUnchecked.getSender() + " " + receivedPackageUnchecked.getRequest() + " " + receivedPackageUnchecked.getReply() + '\n');

        CommunicationPackage sendPackage = (CommunicationPackage) factory.getPackage(
            receivedPackageUnchecked.getType(), receivedPackageUnchecked.getSender(),
            receivedPackageUnchecked.getRequest(), receivedPackageUnchecked.getReply());
        String toJson = gson.toJson(sendPackage);


        if(sendPackage.getType().equals("Message"))
        {
          model.send(sendPackage.getSender(), sendPackage.getRequest());
          Logger.getInstance().addLog("IP: " + clientIP +"; " + sendPackage);
          System.out.println("Sent through model: " + sendPackage);
        }
        else
        {
          out.println(toJson);
          System.out.println("Sent: " + toJson);
        }
      }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    System.out.println(evt.getPropertyName()  + " received in the ClientHandler");
    CommunicationPackage broadcast=new CommunicationPackage(evt.getPropertyName(), ((CommunicationPackage)evt.getNewValue()).getSender(), ((CommunicationPackage)evt.getNewValue()).getRequest(), ((CommunicationPackage)evt.getNewValue()).getReply());
    String toJson=gson.toJson(broadcast);
    out.println(toJson);
  }
}
