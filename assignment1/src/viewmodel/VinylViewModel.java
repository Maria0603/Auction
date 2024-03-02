package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Vinyl;

public class VinylViewModel
{
  private StringProperty titleProperty;
  private StringProperty artistProperty;
  private StringProperty statusProperty;
  private StringProperty borrowerProperty;
  private StringProperty stateProperty;
  private StringProperty reserverProperty;

  public VinylViewModel(Vinyl vinyl){
    titleProperty = new SimpleStringProperty(vinyl.getTitle());
    artistProperty = new SimpleStringProperty(vinyl.getArtist());
    statusProperty = new SimpleStringProperty(vinyl.getStatus());

    borrowerProperty = new SimpleStringProperty(vinyl.getBorrower());
    reserverProperty = new SimpleStringProperty(vinyl.getReserver());
    stateProperty = new SimpleStringProperty(vinyl.getStatus());
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
  public StringProperty getBorrowerProperty() {return borrowerProperty;}
  public StringProperty getReserverProperty() {return reserverProperty;}

  public void setBorrowerProperty(String name){
    this.borrowerProperty.set(name);
  }
  public void setReserverProperty(String reserver){this.reserverProperty.set(reserver);}
  public void setStateProperty(String stateProperty) {
    this.stateProperty.set(stateProperty);
  }

  @Override public String toString() {
    return "VinylViewModel{" + "title = " + titleProperty.get()
        + ", artist = " + artistProperty.get() + ", status = "
        + statusProperty.get() +  ", borrower = "
        + borrowerProperty.get() + '}';
  }
}
