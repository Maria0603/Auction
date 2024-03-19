package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;


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

}
