package model;

public class MessagePackage extends Package
{
  private String textContent, reply;

  public MessagePackage(String sender, String textContent, String reply)
  {
    super(sender);
    this.textContent = textContent;
    this.reply = reply;
  }

  @Override public String toString()
  {
    return "Message from " + getSender() + ": " + textContent + "\nReply: " + reply;
  }

  public String getTextContent()
  {
    return textContent;
  }
}
