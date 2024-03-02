package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Vinyl;
import model.VinylLibraryModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VinylListViewModel implements PropertyChangeListener
{
  private VinylLibraryModel vinylLibraryModel;
  private ViewModelState viewModelState;
  private ObservableList<VinylViewModel> list;
  private ObjectProperty<VinylViewModel> selectedVinylProperty;
  public VinylListViewModel(VinylLibraryModel vinylLibraryModel, ViewModelState viewModelState) {
    this.vinylLibraryModel = vinylLibraryModel;
    this.viewModelState = viewModelState;
    this.list = FXCollections.observableArrayList();
    this.selectedVinylProperty = new SimpleObjectProperty<>();

    vinylLibraryModel.addListener(this);
    //test
    list.add(new VinylViewModel(new Vinyl("Song", "Artist", 222)));
  }

  public ObservableList<VinylViewModel> getAll() {
    return list;
  }

  @Override public void propertyChange(PropertyChangeEvent evt) {

  }
}
