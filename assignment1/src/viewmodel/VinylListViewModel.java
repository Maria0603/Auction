package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Vinyl;
import model.VinylLibraryModel;
import model.VinylLibraryModelManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VinylListViewModel implements PropertyChangeListener
{
  private VinylLibraryModel vinylLibraryModel;
  private ViewModelState viewModelState;
  private ObservableList<VinylViewModel> list;
  private ObjectProperty<VinylViewModel> selectedVinylProperty;
  private StringProperty errorProperty;
  public VinylListViewModel(VinylLibraryModel vinylLibraryModel, ViewModelState viewModelState) {
    this.vinylLibraryModel = vinylLibraryModel;
    this.viewModelState = viewModelState;

    this.list = FXCollections.observableArrayList();
    this.selectedVinylProperty = new SimpleObjectProperty<>();
    this.errorProperty = new SimpleStringProperty();

    loadFromModel();
    vinylLibraryModel.addListener(this);

  }

  private void loadFromModel(){
    for (Vinyl vinyl : vinylLibraryModel.getAllVinyls()) {
      list.add(new VinylViewModel(vinyl));
    }
  }

  public void clear(){
    errorProperty.set("");
  }

  public ObservableList<VinylViewModel> getAll() {
    return list;
  }

  public StringProperty getErrorProperty(){return errorProperty;}

  public void setSelectedVinylProperty(VinylViewModel vinylViewModel){
    this.selectedVinylProperty.set(vinylViewModel);
  }

  @Override public void propertyChange(PropertyChangeEvent evt) {
    Platform.runLater(() ->{
      switch (evt.getPropertyName()){

      }
    });
  }


  public static void main(String[] args) {
    VinylLibraryModelManager model = new VinylLibraryModelManager();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);

    for (int i = 0;
         i < viewModelFactory.getVinylListViewModel().list.size(); i++) {
      System.out.println(viewModelFactory.getVinylListViewModel().list.get(i).toString());
    }
  }
}
