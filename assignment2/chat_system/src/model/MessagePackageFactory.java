package model;

public class MessagePackageFactory extends PackageFactory {

  public MessagePackageFactory(Conversation conversation){
    super(conversation);
  }

  @Override protected Package createPackage(String sender, String request) {

    if (request.isEmpty()) {
      throw new IllegalArgumentException("No input");
    }
    else {
      return new MessagePackage(sender, request);
    }

  }
}
