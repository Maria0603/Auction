package model;


public class CommandPackageCreator extends PackageCreator {

  private static final String[] COMMANDS = {"/list", "/number", "/last"};
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
          return new CommandPackage(sender, command, users.getSize());
        }
        case "/last" ->
        {
          return new CommandPackage(sender, command, users.getLast());
        }
        default ->
        {
          throw new IllegalArgumentException("Invalid command");
        }
      }
    }
    return null;
  }
}
