package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CommandPackageFactory extends PackageFactory {

  private static final String[] COMMANDS = {"/list", "/number", "/time"};


  public CommandPackageFactory(Conversation conversation){
    super(conversation);
  }
  @Override
  protected Package createPackage(String sender, String command)
  {
      switch (command)
      {
        case "/characters" ->
        {
          return new CommandPackage(sender, command, "Number of characters: " + conversation.getConversationContent().length());
        }
        case "/messages" ->
        {
          return new CommandPackage(sender, command, "Number of messages: " + conversation.getNumberOdMessages());
        }
        case "/date" ->
        {
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
          LocalDateTime now = LocalDateTime.now();
          return new CommandPackage(sender, command, "Date and time now: " + dtf.format(now));
        }
        default ->
        {
          throw new IllegalArgumentException("Invalid command");
        }
      }
  }

}
