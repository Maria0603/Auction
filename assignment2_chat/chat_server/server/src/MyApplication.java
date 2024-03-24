import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ChatServer;
import model.ChatModel;
import model.ChatModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application {
  private ChatServer server;
  @Override public void start(Stage primaryStage) throws Exception {
    ChatModel model = new ChatModelManager();
    server=new ChatServer(model);
    new Thread(server).start();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);

    view.start(primaryStage);
  }
}