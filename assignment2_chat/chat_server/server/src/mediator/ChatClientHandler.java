package mediator;

import com.google.gson.Gson;
import model.ChatModel;
import model.CommandPackage;
import model.MessagePackage;
import model.Package;
import model.PackageFactorySelector;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientHandler implements Runnable, PropertyChangeListener
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private ChatModel model;
  private Gson gson;
  public ChatClientHandler(Socket socket, ChatModel model)
  {
    this.socket=socket;
    this.model=model;
    gson=new Gson();
    running=true;
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

    try
    {
      String request=in.readLine();
      System.out.println("Received: " + request);
      try
      {
        CommandPackage receivedPackage=gson.fromJson(request, CommandPackage.class);
        CommandPackage sendPackage=(CommandPackage)model.send(receivedPackage.getSender(), receivedPackage.getCommand());
        String toJson=gson.toJson(sendPackage);
        out.println(toJson);
        System.out.println("Sent: " + toJson);
      }
      catch(IllegalStateException e)
      {
        MessagePackage receivedPackage=gson.fromJson(request, MessagePackage.class);
        MessagePackage sendPackage=(MessagePackage) model.send(receivedPackage.getSender(), receivedPackage.getTextContent());
        String toJson=gson.toJson(sendPackage);
        out.println(toJson);
        System.out.println("Sent: " + toJson);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }


    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    System.out.println(evt.getPropertyName()  + " received in the ClientHandler");
    MessagePackage broadcast=new MessagePackage(evt.getOldValue()+"", evt.getNewValue()+"");
    String toJson=gson.toJson(broadcast);
    out.println(toJson);
  }
}