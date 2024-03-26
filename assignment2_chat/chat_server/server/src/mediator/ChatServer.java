package mediator;

import model.ChatModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable
{
  private int PORT=3138;
  private boolean running;
  private ServerSocket welcomeSocket;
  private ChatModel model;
  public ChatServer(ChatModel model)
  {
    this.model=model;
    running=true;
  }
  public void close()
  {
    try
    {
      running=false;
      welcomeSocket.close();
    }
    catch(Exception e)
    {
      //
    }
  }

  @Override public void run()
  {
    try
    {
      running=true;
      welcomeSocket=new ServerSocket(PORT);
      while(running)
      {
        Socket socket = welcomeSocket.accept();
        ChatClientHandler clientHandler = new ChatClientHandler(socket, model);
        Thread clientThread = new Thread(clientHandler);
        clientThread.setDaemon(true);
        clientThread.start();
      }
    }
    catch (IOException e)
    {
      System.out.println("Error in ChatServer run method");
    }
  }
}
