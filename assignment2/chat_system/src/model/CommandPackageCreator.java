package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class CommandPackageCreator extends PackageCreator {

  private static final String[] COMMANDS = {"/list", "/number", "/date", "/time"};
  @Override
  protected Package createPackage(String sender, String command, Object reply)
  {
    if (reply.getClass().equals(UserList.class))
    {
      UserList users = (UserList) reply;
      switch (command)
      {
        case "/list" ->
        {
          return new CommandPackage(sender, command, users.toString());
        }
        case "/number" ->
        {
          return new CommandPackage(sender, command, users.getSize() + "");
        }
        case "/date" ->
        {
          return new CommandPackage(sender, command, LocalDate.now().toString());
        }
        case "/time" ->
        {
          return new CommandPackage(sender, command, LocalTime.now().toString());
        }
        default ->
        {
          return new CommandPackage(sender, command, "Invalid command");
        }
      }
    }
    return null;
  }
}
