package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import model.Vinyl;

public class VinylViewModel
{
  private StringProperty titleProperty;
  private StringProperty artistProperty;
  private StringProperty statusProperty;


  public VinylViewModel(Vinyl vinyl){
    titleProperty = new SimpleStringProperty(vinyl.getTitle());
    artistProperty = new SimpleStringProperty(vinyl.getArtist());
    statusProperty = new SimpleStringProperty(vinyl.getLendingStatus());
  }

  public StringProperty getTitleProperty() {
    return titleProperty;
  }

  public StringProperty getArtistProperty() {
    return artistProperty;
  }

  public StringProperty getStatusProperty() {
    return statusProperty;
  }
}
