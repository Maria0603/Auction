package model;

public abstract class Package {
  private String sender;

  public Package(String sender) {
    this.sender = sender;
  }

  public String getSender() {
    return sender;
  }

  public abstract String toString();
}
