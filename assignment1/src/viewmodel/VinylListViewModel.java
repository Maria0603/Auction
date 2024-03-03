package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Vinyl;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VinylListViewModel implements PropertyChangeListener
{
  private Model model;
  private ViewState viewState;
  private ObservableList<VinylViewModel> list;
  private ObjectProperty<VinylViewModel> selectedVinylProperty;
  private StringProperty errorProperty;
  public VinylListViewModel(Model model, ViewState viewState) {
    this.model = model;
    this.viewState = viewState;

    this.list = FXCollections.observableArrayList();
    this.selectedVinylProperty = new SimpleObjectProperty<>();
    this.errorProperty = new SimpleStringProperty();
    loadFromModel();
    model.addListener(this);
  }

  private void loadFromModel(){
    list.clear();
    for (int i = 0; i < model.getList().getAllVinyls().size(); i++) {
      list.add(new VinylViewModel(model.getList().getAllVinyls().get(i)));
      if(list.get(i).getStatusProperty().get().equals("Available") && model.getVinyl(
          list.get(i).getTitleProperty().get()).getToBeRemoved())
        onRemovePress();
    }
  }

  public void clear(){
    errorProperty.set("");
  }

  public ObservableList<VinylViewModel> getAll() {
    return list;
  }

  public void setSelected(VinylViewModel vinyl){selectedVinylProperty.set(vinyl);}
  public void onButtonPress(){
    if (selectedVinylProperty != null) {
      viewState.setState(selectedVinylProperty.get().getStatusProperty().get());
      viewState.setArtist(selectedVinylProperty.get().getArtistProperty().get());
      viewState.setTitle(selectedVinylProperty.get().getTitleProperty().get());
      viewState.setState(selectedVinylProperty.get().getStatusProperty().get());
      loadFromModel();
    }
  }
  public void onRemovePress()
  {
    if(selectedVinylProperty!=null)
    {
      model.removeVinyl(model.getVinyl(selectedVinylProperty.get().getTitleProperty().get()));
      loadFromModel();
    }
  }
  private void setVinylState(String title, Vinyl vinyl){

    for (int i = 0; i < list.size(); i++) {
      if(list.get(i).getTitleProperty().get().equals(title)){
        list.get(i).setStateProperty(vinyl.getStatus());
        list.get(i).setBorrowerProperty(vinyl.getBorrower());
        list.get(i).setReserverProperty(vinyl.getReserver());
      }
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt) {
    Platform.runLater(() ->{
      if(evt.getPropertyName().equals("toBeRemoved"))
      {
        Vinyl vinyl=(Vinyl) evt.getNewValue();
        if(!vinyl.getArtist().endsWith(" - THIS VINYL WILL BE REMOVED"))
          vinyl.setArtist(vinyl.getArtist() + " - THIS VINYL WILL BE REMOVED");
        loadFromModel();
      }
        else
          setVinylState(evt.getOldValue().toString(), ((Vinyl) evt.getNewValue()));
    });
    loadFromModel();
  }

}
