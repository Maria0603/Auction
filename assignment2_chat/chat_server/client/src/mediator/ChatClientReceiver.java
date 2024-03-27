package mediator;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientReceiver implements Runnable
{
  private BufferedReader in;
  private boolean running;
  private ChatClient client;
  public ChatClientReceiver(ChatClient client, BufferedReader in)
  {
    this.in=in;
    this.client=client;
    running=true;
  }

  //Always expect packages from the server
  @Override public void run()
  {
    running=true;
    while(running)
    {
      try
      {
        String line = in.readLine();
        client.receive(line);
      }
      catch (IOException e)
      {
        //
      }
    }
  }

  public void close()
  {
    try
    {
      running = false;
      in.close();
    }
    catch (IOException e)
    {
      //
    }
  }
}
