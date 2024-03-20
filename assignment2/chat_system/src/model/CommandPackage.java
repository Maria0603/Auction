package model;

public class CommandPackage extends Package
{
  private String command, reply;
  public CommandPackage(String sender, String command, String reply)
  {
    super(sender);
    this.command = command;

    if (reply != null && !reply.isEmpty()) {
      this.reply = "Error: " + reply;
    } else {
      this.reply = reply;
    }
  }

  @Override public String toString()
  {
     return "Command from " + getSender() + ": " + command + "\nReply: " + reply;
  }

  public String getCommand()
  {
    return command;
  }
}
