package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ManageVinylViewModel;

public class ManageVinylViewController
{


  @FXML private TextField nameField;
  @FXML private TextField titleField;
  @FXML private TextField artistField;
  @FXML private TextField statusField;
  @FXML private Label errorLabel;

  @FXML private Button borrowButton;
  @FXML private Button reserveButton;
  @FXML private Button returnButton;

  private ViewHandler viewHandler;
  private ManageVinylViewModel manageViewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ManageVinylViewModel manageVinylViewModel, Region root){
    this.viewHandler = viewHandler;
    this.manageViewModel = manageVinylViewModel;
    this.root = root;

    nameField.textProperty().bindBidirectional(manageVinylViewModel.nameProperty);

    reset();

  }

  public void reset(){
    errorLabel.setText("");

    titleField.setText(manageViewModel.getViewState().getTitle());
    artistField.setText(manageViewModel.getViewState().getArtist());
    statusField.setText(manageViewModel.getViewState().getState());

  }

  public Region getRoot(){return root;}


  @FXML private void borrowPressed() {
    if(manageViewModel.onBorrow()){
      errorLabel.setText("");
      viewHandler.openView("list");
    }
    else{
      errorLabel.setText("No name Inserted");
    }
  }

  @FXML private void reservePressed() {
    if(manageViewModel.onReserve()){
      errorLabel.setText("");
      viewHandler.openView("list");
    }
    else{
      errorLabel.setText("No name Inserted");
    }
  }

  @FXML private void returnPressed() {
    if(manageViewModel.onReturn()){
      errorLabel.setText("");
      viewHandler.openView("list");
    }
    else{
      errorLabel.setText("No name Inserted");
    }
  }
}
