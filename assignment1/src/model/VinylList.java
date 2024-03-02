package model;

import java.util.ArrayList;
import java.util.List;

public class VinylList
{
  private ArrayList<Vinyl> vinyls;

  public VinylList(int capacity)
  {
    this.vinyls = new ArrayList<>(capacity);
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
  public Vinyl getVinyl(int index)
  {
    return vinyls.get(index);
  }

  public void addVinyl(Vinyl vinyl)
  {
    if (vinyls.size() < 10)
      vinyls.add(vinyl);
  }

  public ArrayList<Vinyl> getAllVinyls()
  {
    return vinyls;
  }

  public void removeVinyl(Vinyl vinyl)
  {
    if (vinyl.getLendingStatus().equals("Available"))
    {
      vinyls.remove(vinyl);
    }
    else
      getVinyl(vinyl.getTitle()).getLendingState().setScheduledForRemoval(true);
  }
  public int getSize()
  {
    return vinyls.size();
  }
}
