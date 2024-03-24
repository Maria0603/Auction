package mediator;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientReceiver implements Runnable
{

  private ChatClient client;
  private BufferedReader in;
  private Gson gson;

  public ChatClientReceiver(ChatClient client, BufferedReader in)
  {
    this.client = client;
    this.in = in;
    this.gson = new Gson();
  }

  //  For receiving stuff
  @Override public void run()
  {
    try
    {
      String receivedMessage;
      while ((receivedMessage = in.readLine()) != null)
      {
        client.receive(receivedMessage);
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}