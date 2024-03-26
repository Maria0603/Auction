package model;

public class CommunicationPackageFactory extends PackageFactory {
  public CommunicationPackageFactory(ChatModel model)
  {
    super(model);
  }

  @Override protected Package createPackage(String type, String sender, String request, String reply)
  {
    if(!type.equals("Create"))
    {
      if (type.equals("Conversation"))
        return new CommunicationPackage("Conversation", sender, null, getModel().getWholeConversation(sender));
      else if (type.equals("Message"))
      {
        if (request.isEmpty())
          return new CommunicationPackage("Error", sender, request, "No input");
        else
          return new CommunicationPackage("Message", sender, request, null);
      }
      else if (type.equals("Command"))
      {
        if (request.startsWith("/"))
        {
          switch (request)
          {
            case "/list" ->
            {
              return new CommunicationPackage(type, sender, request,
                  getModel().getUsers().toString()+'\n');
            }
            case "/number" ->
            {
              return new CommunicationPackage(type, sender, request,
                  getModel().getUsers().getSize() + ""+'\n');
            }
            case "/last" ->
            {
              return new CommunicationPackage(type, sender, request,
                  getModel().getUsers().getLast()+'\n');
            }
            default ->
            {
              return new CommunicationPackage("Error", sender, request,
                  "Invalid command");
            }
          }
        }
        else
          throw new IllegalArgumentException("Error");
      }
      else
        throw new IllegalStateException("Error");
    }
    else throw new IllegalStateException("Error");
  }
}
