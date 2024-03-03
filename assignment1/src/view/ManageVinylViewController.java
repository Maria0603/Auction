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
  private ViewHandler viewHandler;
  private ManageVinylViewModel manageViewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ManageVinylViewModel manageVinylViewModel, Region root){
    this.viewHandler = viewHandler;
    this.manageViewModel = manageVinylViewModel;
    this.root = root;

    nameField.textProperty().bindBidirectional(manageVinylViewModel.nameProperty);
    statusField.textProperty().bindBidirectional(manageVinylViewModel.statusProperty);

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
    try{
      if(manageViewModel.onBorrow()){
        errorLabel.setText("");
        viewHandler.openView("list");
      }
      else{
        errorLabel.setText("No name inserted");
      }
    } catch (IllegalStateException e){
      errorLabel.setText(e.getMessage());
    }
  }

  @FXML private void reservePressed() {
    try{
      if(manageViewModel.onReserve()){
        errorLabel.setText("");
        viewHandler.openView("list");
      }
      else{
        errorLabel.setText("No name inserted");
      }
    } catch (IllegalStateException e){
      errorLabel.setText(e.getMessage());
    }
  }

  @FXML private void returnPressed() {
    try{
      if(manageViewModel.onReturn()){
        errorLabel.setText("");
        viewHandler.openView("list");
      }
      else{
        errorLabel.setText("No name inserted");
      }
    } catch (IllegalStateException e){
      errorLabel.setText(e.getMessage());
    }
  }
  @FXML private void onCancel(){
    viewHandler.openView("list");
  }
}
