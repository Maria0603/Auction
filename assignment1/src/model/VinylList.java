package model;

import java.util.ArrayList;
import java.util.List;

public class VinylList
{
  private List<Vinyl> vinyls;

  public VinylList()
  {
    this.vinyls = new ArrayList<>(10);
  }

  public Vinyl getVinyl(String title)
  {
    for (Vinyl vinyl : vinyls)
    {
      if (vinyl.getTitle().equals(title))
      {
        return vinyl;
      }
    }
    return null;
  }

  public void addVinyl(Vinyl vinyl)
  {
    if (vinyls.size() < 10)
    {
      vinyls.add(vinyl);
    }
    else
    {
      System.out.println("The list of vinyls is full.");
    }
  }

  public List<Vinyl> getAllVinyls()
  {
    return new ArrayList<>(vinyls);
  }

  public void removeVinyl(Vinyl vinyl)
  {
    if (vinyl.getTitle().equals(vinyl.getTitle()) && vinyl.getLendingStatus().equals("Available"))
    {
      vinyls.remove(vinyl);
    }
    else
      getVinyl(vinyl.getTitle()).getLendingState().setScheduledForRemoval(true);
  }
}
