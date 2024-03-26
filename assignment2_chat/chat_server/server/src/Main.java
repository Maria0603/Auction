import javafx.application.Application;
import mediator.ChatServer;

public class Main {
  public static void main(String[] args) {

    int port = 3138;

    ChatServer server = new ChatServer(port);
    Application.launch(MyApplication.class, args);
    server.run();
  }
}
