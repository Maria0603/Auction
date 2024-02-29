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
  public Button reserveButton;
  public Button borrowButton;
  public Button returnButton;
  public Label errorLabel;

  private ViewHandler viewHandler;
  private VinylListViewModel vinylListViewModel;
  private Region root;

  public void init(ViewHandler viewHandler, VinylListViewModel vinylListViewModel, Region root){

  }

  public void reset(){

  }

  public Region getRoot(){return root;}

  public void reservePressed(ActionEvent actionEvent) {

  }

  public void borrowPressed(ActionEvent actionEvent) {

  }

  public void returnPressed(ActionEvent actionEvent) {

  }
}
