package model;

public abstract class PackageFactory {
  protected Conversation conversation;

  public PackageFactory(Conversation conversation){
    this.conversation = conversation;
  }

  protected abstract Package createPackage(String sender, String request);

}
