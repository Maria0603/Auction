package mediator;

import model.ChatModel;

public abstract class PackageFactory
{
  private ChatModel model;
  public PackageFactory(ChatModel model)
  {
    this.model=model;
  }
  public ChatModel getModel()
  {
    return model;
  }
  public Package getPackage(String type, String sender, String request, String reply)
  {
    return createPackage(type, sender, request, reply);
  }
  protected abstract Package createPackage(String type, String sender, String request, String reply);

}
