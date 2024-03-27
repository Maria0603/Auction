package mediator;

import model.ChatModel;

public class UserPackageFactory extends PackageFactory
{

  public UserPackageFactory(ChatModel model)
  {
    super(model);
  }
  @Override
  protected Package createPackage(String type, String username, String password, String something)
  {
    if(type.equals("Create"))
    {
      try
      {
        getModel().createUser(username, password);
      }
      catch (IllegalArgumentException e)
      {
        return new UserPackage("UserError", e.getMessage());
      }
      return new UserPackage(type, username, password);
    }
    else throw new IllegalStateException("Error");
  }


}
