import mediator.ChatClient;
import java.util.Scanner;

public class TestClient {
  private static final String SERVER_HOST = "localhost"; // Change this to your server's IP or hostname
  private static final int SERVER_PORT = 3138; // Change this to your server's port

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get username and password from the user
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    // Create a new ChatClient instance with username and password
    ChatClient client = new ChatClient(SERVER_HOST, SERVER_PORT, username, password);

    int maxAttempts = 3;
    int attempt = 0;

    boolean isConnected = false;

    while (attempt < maxAttempts) {
      try {
        client.connect();
        // Connection successful, set isConnected to true and break out of the loop
        isConnected = true;
        break;
      } catch (RuntimeException e) {
        System.err.println("Failed to connect to server (attempt " + (attempt + 1) + "/" + maxAttempts + "): " + e.getMessage());
        attempt++;
        try {
          Thread.sleep(1000); // Wait for a while before retrying
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
      }
    }

    // If connection was successful, start conversation
    if (isConnected) {
      System.out.println("Connected to server. Start typing messages to chat (type 'exit' to quit):");
      // Inside the conversation loop
      // Inside the conversation loop
      while (true) {
        String message = scanner.nextLine();
        if ("exit".equalsIgnoreCase(message)) {
          break; // Exit loop if user types 'exit'
        }
        // Send message to server
        client.send(username, message); // Pass recipient username and message

        // Receive messages from the server
        // Example of calling receive() method with parameters
        String receivedMessage = client.receive();

        System.out.println("Received message from server: " + receivedMessage);
      }

    } else {
      System.err.println("Failed to connect to the server. Exiting...");
    }

    // Close scanner after use
    scanner.close();
  }
}
