import javafx.application.Application;
import javafx.stage.Stage;
import model.ChatModel;
import model.ChatModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application {
  @Override public void start(Stage primaryStage) throws Exception {
    ChatModel model = new ChatModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);

    view.start(primaryStage);
  }
}
