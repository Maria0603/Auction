package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.VinylListViewModel;
import viewmodel.VinylViewModel;

public class VinylListViewController
{
  public TableView<VinylViewModel> vinylTableView;
  public TableColumn<VinylViewModel, String> titleColumn;
  public TableColumn<VinylViewModel, String> artistColumn;
  public TableColumn<VinylViewModel, String> statusColumn;
  public TableColumn<VinylViewModel, String> borrowerColumn;
  public TableColumn<VinylViewModel, String> reserverColumn;
  public Label errorLabel;

  private ViewHandler viewHandler;
  private VinylListViewModel vinylListViewModel;
  private Region root;

  public void init(ViewHandler viewHandler, VinylListViewModel vinylListViewModel, Region root){
    this.viewHandler = viewHandler;
    this.vinylListViewModel = vinylListViewModel;
    this.root = root;

    titleColumn.setCellValueFactory(
        cellData -> cellData.getValue().getTitleProperty());
    artistColumn.setCellValueFactory(
        cellData -> cellData.getValue().getArtistProperty());
    statusColumn.setCellValueFactory(
        cellData -> cellData.getValue().getStatusProperty());
    borrowerColumn.setCellValueFactory(
        cellData -> cellData.getValue().getBorrowerProperty());
    reserverColumn.setCellValueFactory(
            cellData -> cellData.getValue().getReserverProperty());
    vinylTableView.setItems(vinylListViewModel.getAll());

    errorLabel.setText("");

  }

  public void reset(){
    vinylListViewModel.clear();
  }

  public Region getRoot(){return root;}

  public void onButtonPress() {

    vinylListViewModel.setSelected(vinylTableView.getFocusModel().getFocusedItem());
    vinylListViewModel.onButtonPress();
    viewHandler.openView("manage");

  }
  public void onRemovePress()
  {
    vinylListViewModel.setSelected(vinylTableView.getFocusModel().getFocusedItem());
    vinylListViewModel.onRemovePress();
  }

}
