import mediator.ChatClient;

public class TestClient {
  public static void main(String[] args) {
    // Create a ChatClient instance
    ChatClient chatClient = new ChatClient();

    // Connect to the server
    chatClient.connect();

    // Test sending a message
    System.out.println("Sending message...");
    chatClient.send("TestUser", "Hello, world!");

    // Test receiving a message (simulating a broadcast)
    System.out.println("Simulating message received...");
    chatClient.receive("Broadcast message: Hello from the server!");

    // Test getting the whole conversation
    System.out.println("Getting whole conversation...");
    String conversation = chatClient.getWholeConversation();
    System.out.println("Conversation:\n" + conversation);

    // Test creating a user
    System.out.println("Creating user...");
    try {
      chatClient.createUser("newUser", "newPassword");
      System.out.println("User created successfully.");
    } catch (IllegalArgumentException e) {
      System.err.println("Error: " + e.getMessage());
    }

    // Disconnect from the server
    chatClient.disconnect();
  }
}