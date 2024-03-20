package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

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
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat(
        "dd/MM/yyyy HH:mm");
    if(reply==null)
      return sdf.format(date)+ " " + getSender() + ": " + textContent;
    else throw new IllegalArgumentException(reply);
  }

  public String getTextContent()
  {
    return textContent;
  }
}
