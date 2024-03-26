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

  @Override public void run()
  {
    running=true;
    while(running)
    {
      try
      {
        String line = in.readLine();
        System.out.println("Received in reader: " + line);
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
