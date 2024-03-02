import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    VinylLibraryModel model = new VinylLibraryModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);

    view.start(primaryStage);
  }
}
