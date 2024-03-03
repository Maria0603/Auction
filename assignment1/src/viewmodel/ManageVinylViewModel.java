package viewmodel;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import model.Model;


public class ManageVinylViewModel
{
  public StringProperty nameProperty, titleProperty, artistProperty, statusProperty, errorProperty;

  private Model model;
  private ViewState viewState;


  public ManageVinylViewModel(Model model, ViewState viewState){
    this.model = model;
    this.viewState = viewState;

    nameProperty = new SimpleStringProperty();
    titleProperty = new SimpleStringProperty();
    artistProperty = new SimpleStringProperty();
    statusProperty = new SimpleStringProperty();
    errorProperty = new SimpleStringProperty();
  }

  public ViewState getViewState(){return viewState;}

  public boolean onBorrow(){
    if(nameProperty.get() != null){
      model.borrowVinyl(viewState.getTitle(), nameProperty.get());
      viewState.setBorrower(nameProperty.get());
      return true;
    }
    else{
      return false;
    }
  }
  public boolean onReserve(){
    if(nameProperty.get() != null){
      model.reserveVinyl(viewState.getTitle(), nameProperty.get());
      return true;
    }
    else{
      return false;
    }
  }
  public boolean onReturn(){
    if(nameProperty.get() != null){
      model.returnVinyl(viewState.getTitle(), nameProperty.get());
      viewState.setBorrower(null);
      return true;
    }
    else{
      return false;
    }
  }
}
