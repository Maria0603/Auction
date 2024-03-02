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


  public VinylViewModel(Vinyl vinyl){
    titleProperty = new SimpleStringProperty(vinyl.getTitle());
    artistProperty = new SimpleStringProperty(vinyl.getArtist());
    statusProperty = new SimpleStringProperty(vinyl.getLendingStatus());
    borrowerProperty = new SimpleStringProperty("");
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

  public void setBorrowerProperty(String name){
    this.borrowerProperty.set(name);
  }

  @Override public String toString() {
    return "VinylViewModel{" + "title = " + titleProperty.get()
        + ", artist = " + artistProperty.get() + ", status = "
        + statusProperty.get() +  ", borrower = "
        + borrowerProperty.get() + '}';
  }
}
