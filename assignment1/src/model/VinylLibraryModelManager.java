package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class VinylLibraryModelManager implements VinylLibraryModel
{
  private List<Vinyl> vinylList;
  private PropertyChangeSupport propertyChangeSupport;

  public VinylLibraryModelManager()
  {
    this.vinylList = new ArrayList<>();
    this.propertyChangeSupport = new PropertyChangeSupport(this);
  }

  @Override public List<Vinyl> getAllVinyls()
  {
    return vinylList;
  }

  @Override public void addVinyl(Vinyl[] vinyl)
  {
    for (Vinyl vinyls : vinyl)
    {
      vinylList.add(vinyls);
      propertyChangeSupport.firePropertyChange("VinylAdded", null, vinyls);
    }
  }

  @Override public void removeVinyl(String title)
  {
    for (Vinyl vinyl : vinylList)
      if (vinyl.getTitle().equals(title) && vinyl.getLendingStatus().equals("Available"))
      {
        vinylList.remove(vinyl);
        propertyChangeSupport.firePropertyChange("VinylRemoved", null, vinyl);

        break;
      }
      else
      {
        vinyl.getLendingState().setScheduledForRemoval(true);
        propertyChangeSupport.firePropertyChange("VinylToBeRemoved", null, vinyl);
      }
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

  public static void main(String[] args)
  {
    Customer c1 = new Customer("Customer1");
    Customer c2 = new Customer("Customer2");
    Customer c3 = new Customer("Customer3");
    Vinyl vinyl1 = new Vinyl("a", "a", 1999);
    VinylList list = new VinylList();
    list.addVinyl(vinyl1);
    Vinyl vinyl = list.getVinyl("a");
    try
    {
      vinyl.setLendingState(new VinylAvailable(null, null));
      System.out.println(vinyl.getLendingStatus());//available
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c1);
      System.out.println(vinyl.getLendingStatus()+ "  "
          + vinyl.getLendingState().getBorrower().getName());//borrowed

    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().reserveVinyl(vinyl, c2);
      System.out.println(vinyl.getLendingStatus() + "  "
          + vinyl.getLendingState().getReserver().getName());//reservedWhileBorrowed
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().reserveVinyl(vinyl, c3);
      System.out.println(vinyl.getLendingState().getClass().getName() + "  "
          + vinyl.getLendingStatus() + vinyl.getLendingState().getReserver().getName());

    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage() + vinyl.getLendingState().getReserver().getName());//Already reserved
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c2);
      System.out.println(vinyl.getLendingStatus() + "  "
          + vinyl.getLendingState().getBorrower().getName());
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage() + vinyl.getLendingState().getBorrower().getName());//You are not the one who borrowed it
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c1);
      System.out.println(vinyl.getLendingStatus() + vinyl.getLendingState()
          .getReserver().getName());//reserved
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c3);
      System.out.println(vinyl.getLendingStatus());
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());//Reserved by someone else
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c2);
      System.out.println(vinyl.getLendingStatus());//borrowed
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c3);
      System.out.println(vinyl.getLendingStatus());
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());//You are not the one who borrowed it
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c2);
      System.out.println(vinyl.getLendingStatus());//available
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c2);
      System.out.println(vinyl.getLendingStatus());
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());//it is not borrowed
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c3);
      System.out.println(vinyl.getLendingStatus());//borrowed
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c1);
      System.out.println(vinyl.getLendingStatus());
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());//already borrowed
    }
    try
    {
      vinyl.getLendingState().reserveVinyl(vinyl, c1);
      System.out.println(vinyl.getLendingStatus());//reservedWhileBorrowed
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c1);
      System.out.println(vinyl.getLendingStatus());
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());//it is not you the one
    }
  }
}

