package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommandPackage extends Package
{
  private String textContent, reply;
  public CommandPackage(String sender, String command, String reply)
  {
    super(sender);
    this.textContent = command;
    this.reply = reply;
  }

  @Override public String toString()
  {
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat(
        "dd/MM/yyyy HH:mm");
    return sdf.format(date)+  " " + getSender() + ": " + '\n' + textContent + '\n' + reply;
  }


  public String getCommand() {
    return textContent;
  }

  public String getReply(){
    return reply;
  }

}