package model;

public class MessagePackageFactory extends PackageFactory {

  public MessagePackageFactory(Conversation conversation){
    super(conversation);
  }

  @Override protected Package createPackage(String sender, String request, UserList list)
  {
    if (request.isEmpty()) {
      throw new IllegalArgumentException("No input");
    }
    else {
      MessagePackage pack=new MessagePackage(sender, request);
      Logger.getInstance().addLog(pack.toString());
      return pack;
    }

  }
}