package model;

public abstract class PackageFactory {
  protected Conversation conversation;

  public PackageFactory(Conversation conversation){
    this.conversation = conversation;
  }
  public Package getPackage(String sender, String request, UserList list)
  {
    return createPackage(sender, request, list);
  }
  protected abstract Package createPackage(String sender, String request, UserList list);

}