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
    if(reply==null)
      return "Message from " + getSender() + ": " + textContent + "\n";
    else throw new IllegalArgumentException(reply);
  }

  public String getTextContent()
  {
    return textContent;
  }
}
