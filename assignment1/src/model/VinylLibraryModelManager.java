package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class VinylLibraryModelManager implements VinylLibraryModel {
  private List<Vinyl> vinylList;
  private PropertyChangeSupport propertyChangeSupport;

  public VinylLibraryModelManager() {
    this.vinylList = new ArrayList<>();
    this.propertyChangeSupport = new PropertyChangeSupport(this);
  }

  @Override
  public List<Vinyl> getAllVinyls() {
    return vinylList;
  }

  @Override
  public void addVinyl(Vinyl[] vinyl) {
    for (Vinyl vinyls : vinyl) {
      vinylList.add(vinyls);
      firePropertyChange("VinylAdded", null, vinyls);
    }
  }

  @Override
  public void removeVinyl(String title) {
    Vinyl vinylToRemove = null;
    for (Vinyl vinyl : vinylList) {
      if (vinyl.getTitle().equals(title)) {
        vinylToRemove = vinyl;
        break;
      }
    }
    if (vinylToRemove != null) {
      vinylList.remove(vinylToRemove);
      firePropertyChange("VinylRemoved", null, vinylToRemove);
    }
  }

  public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
  }

  @Override
  public void addListener(PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override
  public void removeListener(PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
}
