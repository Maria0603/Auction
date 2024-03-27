package mediator;

import model.ChatModel;

public class CommunicationPackageFactory extends PackageFactory
{
  public CommunicationPackageFactory(ChatModel model)
  {
    super(model);
  }

  @Override protected Package createPackage(String type, String sender, String request, String reply)
  {
    //if the type is "Create", then the package is wrong
    if(!type.equals("Create"))
    {
      //if it is "Message", we send it back
      if (type.equals("Message"))
      {
          return new CommunicationPackage("Message", sender, request, null);
      }
      //is it is "Command", we send it back with the requested information
      else if (type.equals("Command"))
      {
        if (request.startsWith("/"))
        {
          switch (request)
          {
            //the list of previously connected chatters
            case "/list" ->
            {
              return new CommunicationPackage(type, sender, request,
                  getModel().getUsers().toString()+'\n');
            }

            //the number of previously connected chatters
            case "/number" ->
            {
              return new CommunicationPackage(type, sender, request,
                  getModel().getUsers().getSize() +'\n');
            }
            //the last connected chatter
            case "/last" ->
            {
              return new CommunicationPackage(type, sender, request,
                  getModel().getUsers().getLast()+'\n');
            }
            //invalid command
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
