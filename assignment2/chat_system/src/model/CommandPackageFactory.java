package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CommandPackageFactory extends PackageFactory {

  private static final String[] COMMANDS = {"/list", "/number", "/last"};


  public CommandPackageFactory(Conversation conversation){
    super(conversation);
  }
  @Override
  protected Package createPackage(String sender, String command, UserList list)
  {
      switch (command)
      {
        case "/list" ->
        {
          return new CommandPackage(sender, command, list.toString());
        }
        case "/number" ->
        {
          return new CommandPackage(sender, command, list.getSize());
        }
        case "/last" ->
        {
          return new CommandPackage(sender, command, list.getLast());
        }
        default ->
        {
          throw new IllegalArgumentException("Invalid command");
        }
      }
  }

}
