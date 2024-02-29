package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ManageVinylViewModel;

public class ManageVinylController
{
  public Label windowTypeLabel;
  public TextField titleField;
  public TextField artistField;
  public TextField statusField;
  public Label errorLabel;
  public Button submitButton;
  public Button cancelButton;

  private ViewHandler viewHandler;
  private ManageVinylViewModel manageViewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ManageVinylViewModel manageVinylViewModel, Region root){

  }

  public void reset(){

  }

  public Region getRoot(){return root;}

  public void submitPressed(ActionEvent actionEvent) {

  }

  public void cancelPressed(ActionEvent actionEvent) {

  }
}
