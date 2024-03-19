package model;

public class Test_model {
  public static void main(String[] args) {

    Conversation conversation = new Conversation();

    ChatModelManager chatModelManager = new ChatModelManager();
    chatModelManager.setConversation(conversation);

    chatModelManager.send("User1", "Hello, World!");

    String wholeConversation = chatModelManager.getWholeConversation();
    System.out.println("Whole conversation:\n" + wholeConversation);
    chatModelManager.createUser("User2", "password\n");

    Logger.getInstance().extractLastMessageAndReply(wholeConversation);

  }
}
