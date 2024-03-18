package model;

public class ChatModelManager implements ChatModel {
  private Conversation conversation;
  private UserList userList;

  public ChatModelManager(Conversation conversation, UserList userList) {
    this.conversation = conversation;
    this.userList = userList;
  }

  @Override
  public void send(String username, String message) {
    if (conversation != null) {
      MessagePackage messagePackage = new MessagePackage(username, message, "");
      conversation.addPackage(messagePackage);
    }
  }

  @Override
  public String getWholeConversation() {
    if (conversation != null) {
      return conversation.getConversationContent();
    }
    return null;
  }

  @Override
  public void createUser(String username, String password) {
    if (userList != null) {
      userList.addUser(username, password);
    }
  }

  public void setConversation(Conversation conversation) {
    this.conversation = conversation;
  }

  public void setUserList(UserList userList) {
    this.userList = userList;
  }
}
