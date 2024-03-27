package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;
  private ChatViewController chatViewController;
  private LogInViewController logInViewController;


  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("login");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "chat":
        root = loadChatView("chatView.fxml");
        break;
      case "login":
        root = loadLogInView("logInView.fxml");
        break;
    }
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }

    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);

    primaryStage.show();
  }

  public void closeView()
  {
    primaryStage.close();
  }

  private Region loadLogInView(String fxmlFile)
  {
    if (logInViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        logInViewController = loader.getController();
        logInViewController
            .init(this, viewModelFactory.getLogInViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      logInViewController.reset();
    }
    return logInViewController.getRoot();
  }

  private Region loadChatView(String fxmlFile)
  {
    if (chatViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        chatViewController = loader.getController();
        chatViewController
            .init(this, viewModelFactory.getChatViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      chatViewController.reset();
    }
    return chatViewController.getRoot();
  }
}
