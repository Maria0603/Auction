/*package model;

public class ChatModelManager implements ChatModel
{
  private String conversationContent;
  private PackageCreator messagePackageCreator;
  private PackageCreator commandPackageCreator;
  private UserList users;
  public ChatModelManager ()
  {
    this.conversationContent="";
    messagePackageCreator=new MessagePackageCreator();
    commandPackageCreator=new CommandPackageCreator();
  }
  public void send(String sender, String request, String reply)
  {
    if(request.startsWith("/"))
    {
      commandPackageCreator.getPackage(sender, request, reply);
    }
    else
    {
      messagePackageCreator.getPackage(sender, request, reply);
    }

  }
  public String getConversationContent()
  {
    return conversationContent;
  }
}
 */

