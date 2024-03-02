package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class VinylLibraryModelManager implements VinylLibraryModel
{
  private VinylList vinylList;
  private PropertyChangeSupport propertyChangeSupport;

  public VinylLibraryModelManager()
  {
    this.vinylList = new VinylList(10);
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    createDummyData();
    //new VinylSimulation(this);
  }

  private void createDummyData(){
    Customer c1 = new Customer("Bob");
    Customer c2 = new Customer("Wendy");
    Customer c3 = new Customer("Carl");
    Vinyl vinyl1 = new Vinyl("Good song", "Good Artist", 1990);
    Vinyl vinyl2 = new Vinyl("Bad Song", "Bad Artist", 1899);
    Vinyl vinyl3 = new Vinyl("Rock Song", "Rock Artist", 1799);
    Vinyl vinyl4 = new Vinyl("Techno Song", "Techno Artist", 1909);
    Vinyl vinyl5 = new Vinyl("Classic Song", "Classic Artist", 1909);
    Vinyl vinyl6 = new Vinyl("Christmas Song", "Christmas Artist", 1909);
    Vinyl vinyl7 = new Vinyl("Sad Song", "Sad Artist", 1909);
    Vinyl vinyl8 = new Vinyl("Cute Song", "Cute Artist", 1909);
    Vinyl vinyl9 = new Vinyl("Country Song", "Country Artist", 1909);
    Vinyl vinyl10 = new Vinyl("RnB Song", "RnB Artist", 1909);
    addVinyl(vinyl1);
    addVinyl(vinyl2);
    addVinyl(vinyl3);
    addVinyl(vinyl4);
    addVinyl(vinyl5);
    addVinyl(vinyl6);
    addVinyl(vinyl7);
    addVinyl(vinyl8);
    addVinyl(vinyl9);
    addVinyl(vinyl10);
  }

  @Override public ArrayList<Vinyl> getAllVinyls()
  {
    return vinylList.getAllVinyls();
  }

  @Override public void addVinyl(Vinyl vinyl)
  {
      vinylList.addVinyl(vinyl);
      propertyChangeSupport.firePropertyChange("VinylAdded", null, vinylList);
  }
  @Override public Vinyl getVinyl(int index)
  {
    return vinylList.getVinyl(index);
  }

  @Override public void removeVinyl(String title)
  {
    for (int i=0; i<vinylList.getSize(); i++)
    {
      Vinyl vinyl=vinylList.getVinyl(i);
      if (vinyl.getTitle().equals(title))
      {
        if(vinyl.getLendingStatus().equals("Available"))
       {
        vinylList.removeVinyl(vinyl);
        propertyChangeSupport.firePropertyChange("VinylRemoved", null, vinyl);
        break;
       }
      else
       {
        vinyl.getLendingState().setScheduledForRemoval(true);
        propertyChangeSupport.firePropertyChange("VinylToBeRemoved", null, vinyl);
       }
      }
    }
  }
  @Override public int getSize()
  {
    return vinylList.getSize();
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  @Override public VinylState getLendingState(Vinyl vinyl)
  {
    return vinyl.getLendingState();
  }
  @Override public Vinyl getVinyl(String title)
  {
    for (int i=0; i<vinylList.getSize(); i++)
      if (vinylList.getVinyl(i).getTitle().equals(title))
      {
        return vinylList.getVinyl(i);
      }
    return null;
  }
  public void reserveVinyl(Vinyl vinyl, Customer customer)
  {
    vinyl.getLendingState().reserveVinyl(vinyl, customer);
    propertyChangeSupport.firePropertyChange("Reserve", null, vinyl);
  }
  public void returnVinyl(Vinyl vinyl, Customer customer)
  {
    vinyl.getLendingState().returnVinyl(vinyl, customer);
    propertyChangeSupport.firePropertyChange("Reserve", null, vinyl);
  }
  public void borrowVinyl(Vinyl vinyl, Customer customer)
  {
    vinyl.getLendingState().borrowVinyl(vinyl, customer);
    propertyChangeSupport.firePropertyChange("Reserve", null, vinyl);
  }

}

