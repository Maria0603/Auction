package model;

public class ChatModelManager implements ChatModel
{
  private Conversation conversation;
  private UserList userList;

  public ChatModelManager() {
    this.userList = new UserList("","");
  }
  @Override public void send(String username, String message) {
    MessagePackage messagePackage = new MessagePackage(username, message, "");
    conversation.addPackage(messagePackage);
  }

  @Override public String getWholeConversation()
  {
    return conversation.getConversationContent();
  }

  @Override public void createUser(String username, String password)
  {
    userList.addUser(username, password);
    System.out.println(userList.toString());
  }

  public void setConversation(Conversation conversation)
  {
    this.conversation = conversation;
  }


}
