package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//problem of using logger class is that it should be independent of rest classes
//so it should take in the string and do smth with it
public class Logger {
  private static Logger instance;
  private static final Object lock = new Object(); // lock for thread safety

  private static String output;

  private Logger() {
    output = "";
  }

  public static Logger getInstance() {
    if (instance == null) {
      synchronized (lock){
        if(instance == null){

          instance = new Logger();
        }
      }
    }
    return instance;
  }
  public void addLog(String text)
  {
    output+=text+'\n';
    addToFile(text +'\n');
    //System.out.println(text);
  }
  public String getOutput()
  {
    return output;
  }
  public String getSortableDate()
  {
    DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return LocalDateTime.now().format(dtf);
  }
  private void addToFile(String line)
  {
    if (line == null)
    {
      return;
    }
    BufferedWriter out = null;
    try
    {
      String filename= getSortableDate()+".txt";
      out = new BufferedWriter(new FileWriter(filename, true));
      out.write(line + "\n");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        out.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  public void extractLastMessageAndReply(String conversation) {
    //splitting conversation string into messages with newline character as the delimiter
    String[] messages = conversation.split("\\r?\\n");
    String lastMessage = "";
    String lastReply = "";

    //going up from the last message and stopping
    for (int i = messages.length - 1; i >= 0; i--) {
      String message = messages[i];
      if (message.contains(":")) {
        if (lastReply.isEmpty()) {
          lastReply = message;
        } else {
          lastMessage = message;
          break;
        }
      }
    }

    System.out.println("Last Message: " + lastMessage);
    System.out.println("Last Reply: " + lastReply);
  }

  public String extractOnlyMessages(String conversation){//add date and time for each message
    // Split the conversation string into messages with newline character as the delimiter
    String[] lines = conversation.split("\\r?\\n");
    List<String> messages = new ArrayList<>();

    // Iterate over the lines and filter out the replies
    for (String line : lines) {
      if (line.contains(":") && !line.startsWith("Reply:")) {
        messages.add(line);
      }
    }

    String output = "";
    // Print the extracted messages
    System.out.println("Extracted Messages:");
    for (String message : messages) {
      output += message + "\n";
      System.out.println(message);
    }

    return output;
  }

}