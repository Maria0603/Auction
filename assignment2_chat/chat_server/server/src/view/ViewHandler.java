package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
        root = loadView("chatView.fxml");
        break;
      case "login":
        root = loadView("logInView.fxml");
        break;
    }
    if (root != null)
    {
      currentScene.setRoot(root);
      String title =
          root.getUserData() != null ? root.getUserData().toString() : "";
      primaryStage.setTitle(title);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    }
    else
    {
      System.err.println("Failed to load view with ID: " + id);
    }
  }

  public void closeView()
  {
    primaryStage.close();
  }

  private Region loadView(String fxmlFile)
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      URL location = getClass().getResource(fxmlFile);
      if (location == null)
      {
        throw new IOException("FXML file not found: " + fxmlFile);
      }
      loader.setLocation(location);

      // Set encoding to UTF-8
      loader.setCharset(StandardCharsets.UTF_8);

      Region root = loader.load();

      // Determine the type of controller based on the loaded FXML file
      Object controller = loader.getController();
      if (controller instanceof ChatViewController)
      {
        chatViewController = (ChatViewController) controller;
      }
      else if (controller instanceof LogInViewController)
      {
        logInViewController = (LogInViewController) controller;
      }
      else
      {
        throw new IOException("Unknown controller type for FXML file: " + fxmlFile);
      }

      // Initialize the controller
      if (chatViewController != null)
      {
        chatViewController.init(this, viewModelFactory.getChatViewModel(), root);
      }
      else if (logInViewController != null)
      {
        logInViewController.init(this, viewModelFactory.getLogInViewModel(),
            root);
      }

      return root;
    }
    catch (IOException e)
    {
      e.printStackTrace(); // Handle or log the exception appropriately
      return null;
    }
  }
}