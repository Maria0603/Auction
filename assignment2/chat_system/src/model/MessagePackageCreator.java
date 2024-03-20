package model;

public class MessagePackageCreator extends PackageCreator {

  @Override
  protected Package createPackage(String sender, String request, Object reply)
  {
    if (reply == null)
    {
      if (request.isEmpty())
      {
        return new MessagePackage(sender, request, "No input");
      }
      else
      {
        return new MessagePackage(sender, request, null);
      }
    }
    return null;
  }
}
