package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.VinylLibraryModel;

public class ManageVinylViewModel
{
  public StringProperty windowTypeProperty, titleProperty, artistProperty, statusProperty, errorProperty;

  private ObjectProperty<Boolean> editableProperty;

  private VinylLibraryModel model;
  private ViewModelState viewState;


  public ManageVinylViewModel(VinylLibraryModel model, ViewModelState viewState){
    this.model = model;
    this.viewState = viewState;

    windowTypeProperty = new SimpleStringProperty();
    titleProperty = new SimpleStringProperty();
    artistProperty = new SimpleStringProperty();
    statusProperty = new SimpleStringProperty();
    errorProperty = new SimpleStringProperty();
    editableProperty = new SimpleObjectProperty<>();

  }
}
