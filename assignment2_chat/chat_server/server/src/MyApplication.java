import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ChatServer;
import model.ChatModel;
import model.ChatModelManager;


public class MyApplication extends Application {
  private ChatServer server;
  @Override public void start(Stage primaryStage)
  {
    ChatModel model = new ChatModelManager();
    server=new ChatServer(model);
    new Thread(server).start();
  }
}
