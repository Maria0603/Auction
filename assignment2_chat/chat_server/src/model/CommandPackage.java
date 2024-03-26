package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class CommandPackage extends Package
{
  private String command, reply;
  public CommandPackage(String sender, String command, String reply)
  {
    super(sender);
    this.command = command;
    this.reply = reply;
  }

  @Override public String toString()
  {
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat(
        "dd/MM/yyyy HH:mm");
    return sdf.format(date)+  " " + getSender() + ": " + command + "\n" + reply;
  }

  public String getCommand()
  {
    return command;
  }
}
