package mediator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//for transporting data
public class CommunicationPackage
{
  private String type, sender, request, reply;

  public CommunicationPackage(String type, String sender, String request, String reply)
  {
    this.type=type;
    this.sender=sender;
    this.request=request;
    this.reply=reply;
  }

  public String toString()
  {
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat(
        "dd/MM/yyyy HH:mm");
    if(reply!=null)
      return sdf.format(date)+ " " + getSender() + ": " + '\n' + request + '\n' + reply;
    return sdf.format(date)+ " " + getSender() + ": " + '\n' + request + '\n';
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
