package mediator;

import model.ChatModel;
import model.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable
{
  private int PORT =3138;
  private boolean running;
  private ServerSocket welcomeSocket;
  private ChatModel model;
  public ChatServer(ChatModel model)
  {
    this.model=model;
    running=true;

  }

  public ChatServer(int port)
  {this.PORT = port;
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
      System.err.println("Error closing server socket: " + e.getMessage());
    }
    }


  @Override public void run()
  {
    try
    {
      running = true;
      welcomeSocket = new ServerSocket(PORT);
      System.out.println("Server started on port " + PORT);

      while (running) {
        Socket socket = welcomeSocket.accept();
        System.out.println("New client connected: " + socket.getInetAddress());

        // Handle the client in a separate thread
        ChatClientHandler clientHandler = new ChatClientHandler(socket, model);
        Thread clientThread = new Thread(clientHandler);
        clientThread.setDaemon(true);
        clientThread.start();
      }
    } catch (IOException e) {
      System.err.println("Error in ChatServer run method: " + e.getMessage());
    } finally {
      close();
    }
  }
}