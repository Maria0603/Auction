package mediator;

public class Sendable {
  String message, sender;

  public Sendable(String message, String sender) {
    this.message = message;
    this.sender = sender;
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

  @Override public String toString() {
    return "Sendable{" + "message='" + message + '\'' + ", sender='" + sender
        + '\'' + '}';
  }
}
