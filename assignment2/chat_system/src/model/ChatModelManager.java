package model;

public class ChatModelManager implements ChatModel
{
  private Conversation conversation;
  private UserList userList;
  @Override public void send(String username, String message) {
    MessagePackage messagePackage = new MessagePackage(username, message, null);
    conversation.addPackage(messagePackage);
  }

  @Override public String getWholeConversation()
  {
    return conversation.getConversationContent();
  }

  @Override public void createUser(String username, String password)
  {
    userList.addUser(username, password);
  }

  public void setConversation(Conversation conversation)
  {
    this.conversation = conversation;
  }
}
