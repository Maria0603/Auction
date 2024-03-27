package mediator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommunicationPackage extends Package
{
  private String type, sender, request, reply;

  public CommunicationPackage(String type, String sender, String request, String reply)
  {
    super();
    this.type=type;
    this.sender=sender;
    this.request=request;
    this.reply=reply;
  }

  @Override public String toString()
  {
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat(
        "dd/MM/yyyy HH:mm");
    if(reply!=null)
      return sdf.format(date)+ " " + getSender() + ": " + '\n' + request + '\n' + reply;
    return sdf.format(date)+ " " + getSender() + ": " + '\n' + request;

  }

  public String getType()
  {
    return type;
  }

  public String getReply()
  {
    return reply;
  }

  public String getRequest()
  {
    return request;
  }

  public String getSender()
  {
    return sender;
  }
}
