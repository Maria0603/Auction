package model;

public class CommandPackageFactory extends PackageFactory {

  private static final String[] COMMANDS = {"/list", "/number", "/last"};


  public CommandPackageFactory(Conversation conversation){
    super(conversation);
  }
  @Override
  protected Package createPackage(String sender, String command, UserList list)
  {
    if(!command.startsWith("/"))
      throw new IllegalStateException("Error");
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