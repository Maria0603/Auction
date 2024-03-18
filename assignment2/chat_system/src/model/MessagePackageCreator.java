package model;

public class MessagePackageCreator extends PackageCreator {

  @Override
  protected void createPackage(String sender, String request, String reply) {
    if (request.isEmpty()) {
      reply = "No input";
    } else {
      reply = null;
    }
    if (reply != null) {
      new MessagePackage(sender, request, reply);
    } else {
      new MessagePackage(sender, request, null);
    }
  }
}
