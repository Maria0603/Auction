package model;

public class PackageFactorySelector {
  public static PackageFactory getFactory(String request, Conversation conversation){
    if (request.startsWith("/")){
      return new CommandPackageFactory(conversation);
    } else {
      return new MessagePackageFactory(conversation);
    }
  }
}