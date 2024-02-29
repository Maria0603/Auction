package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ManageViewModel;

public class ManageViewController
{
  public Label windowTypeLabel;
  public TextField titleField;
  public TextField artistField;
  public TextField statusField;
  public Label errorLabel;
  public Button submitButton;
  public Button cancelButton;

  private ViewHandler viewHandler;
  private ManageViewModel manageViewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ManageViewModel manageViewModel, Region root){

  }

  public void reset(){

  }

  public Region getRoot(){return root;}

  public void submitPressed(ActionEvent actionEvent) {

  }

  public void cancelPressed(ActionEvent actionEvent) {

  }
}
