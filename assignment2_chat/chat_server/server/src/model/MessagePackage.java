package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MessagePackage extends Package
{
  private String textContent;

  public MessagePackage(String sender, String textContent)
  {
    super(sender);
    this.textContent = textContent;
  }

  @Override public String toString()
  {
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat(
        "dd/MM/yyyy HH:mm");
    return sdf.format(date)+ " " + getSender() + ": " + '\n' + textContent;
  }

  public String getTextContent()
  {
    return textContent;
  }
}