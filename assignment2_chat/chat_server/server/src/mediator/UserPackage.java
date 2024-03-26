package mediator;

public class UserPackage {
  String type, message, sender;

  // UserPackage is created for User requests, can also be used as logIn request
  //sender = sender, and message = password
  //or
  public UserPackage(String type, String sender, String message) {
    this.type = type;
    this.message = message;
    this.sender = sender;
  }

  public UserPackage(String type, String errorMessage){
    this.type = type;
    this.message = errorMessage;
    this.sender = null;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getType() {
    return type;
  }

  @Override public String toString() {
    return "Sendable{" + "message='" + message + '\'' + ", sender='" + sender
        + '\'' + '}';
  }
}
