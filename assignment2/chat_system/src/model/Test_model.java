package model;

public class Test_model {
  public static void main(String[] args) {
    Conversation conversation = new Conversation();
    UserList userList = new UserList("MyUsername", "123456789");
    userList.addUser("User1", "password1");
    userList.addUser("User2", "password2");

    ChatModelManager chatModelManager = new ChatModelManager(conversation, userList);

    // Send a message from User1
    chatModelManager.send("Nastya Jacson", "Hello, Bella!");

    // Send a message from User2
    chatModelManager.send("Bella Hadid", "Hi there!");

    // Get the whole conversation
    String wholeConversation = chatModelManager.getWholeConversation();

    System.out.println("Whole conversation:\n" + wholeConversation);
  }
}
