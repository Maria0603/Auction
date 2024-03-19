package model;

public class ChatModelManager implements ChatModel {
  private Conversation conversation;
  private String conversationContent;
  private UserList userList;
  private PackageCreator messagePackageCreator;
  private PackageCreator commandPackageCreator;

  public ChatModelManager(Conversation conversation, UserList userList) {
    this.conversation = conversation;
    this.userList = userList;
  }

  public ChatModelManager() {
    this.conversationContent = "";
    messagePackageCreator = new MessagePackageCreator();
    commandPackageCreator = new CommandPackageCreator();
  }

 /* @Override public void send(String username, String message) {
    if (conversation != null) {
      MessagePackage messagePackage = new MessagePackage(username, message, "");
      conversation.addPackage(messagePackage);
    }
  }*/

  @Override public String getWholeConversation() {
    if (conversation != null) {
      return conversation.getConversationContent();
    }
    return null;
  }

  @Override public void createUser(String username, String password) {
    if (userList != null) {
      userList.addUser(username, password);
    }
  }

  public void send(String sender, String request, String reply) {
    if (request.startsWith("/")) {
      commandPackageCreator.getPackage(sender, request, reply);
    }
    else {
      messagePackageCreator.getPackage(sender, request, reply);
    }

  }

  public String getConversationContent() {
    return conversationContent;
  }

  public void setConversation(Conversation conversation) {
    this.conversation = conversation;
  }

  public void setUserList(UserList userList) {
    this.userList = userList;
  }
}

