package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler {
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;
  private ManageVinylViewController manageVinylViewController;
  private VinylListViewController vinylListViewController;

  public ViewHandler(ViewModelFactory viewModelFactory) {
    this.viewModelFactory = viewModelFactory;
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    openView("list");
  }

  public void openView(String id) {
    Region root = null;
    switch (id) {
      case "list":
        root = loadListView("vinylList.fxml");
        break;
      case "manage":
        root = loadManageView("manageVinyl.fxml");
        break;
    }
    currentScene.setRoot(root);
    String title = "Vinyl Shop";
    if (root.getUserData() != null) {
      title += root.getUserData();
    }

    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight() + 30);
    primaryStage.show();
  }

  private Region loadListView(String fxmlFile) {
    if (vinylListViewController == null) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        vinylListViewController = loader.getController();
        vinylListViewController.init(this,
            viewModelFactory.getVinylListViewModel(), root);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    else {
      vinylListViewController.reset();
    }
    return vinylListViewController.getRoot();
  }

  private Region loadManageView(String fxmlFile) {
    if (manageVinylViewController == null) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        manageVinylViewController = loader.getController();
        manageVinylViewController.init(this,
            viewModelFactory.getManageVinylViewModel(), root);
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    else {
      manageVinylViewController.reset();
    }
    return manageVinylViewController.getRoot();
  }

}
