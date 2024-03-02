package model;

import java.util.PropertyResourceBundle;

public class Vinyl
{
  private VinylState lendingState;
  private String title, artist;
  private int year;


  public Vinyl(String title, String artist, int year)
  {
    this.title = title;
    this.artist = artist;
    this.year = year;
    this.lendingState=new VinylAvailable(null, null);
  }

  public String getArtist()
  {
    return artist;
  }

  public String getTitle()
  {
    return title;
  }

  public int getYear()
  {
    return year;
  }
  public VinylState getLendingState()
  {
    return lendingState;
  }

  public void setLendingState(VinylState lendingState)
  {
    this.lendingState = lendingState;
  }

  public String getLendingStatus()
  {
    if (lendingState.getClass().equals(VinylAvailable.class))
      return "Available";
     else if(lendingState.getClass().equals(VinylBorrowed.class))
       return "Borrowed";
     else if(lendingState.getClass().equals(VinylReserved.class))
       return "Reserved";
     else
       return "Borrowed and Reserved";
    }

  @Override public String toString() {
    return "Vinyl{" + "lendingState=" + getLendingStatus() + ", title='" + title
        + '\'' + ", artist='" + artist + '\'' + ", year=" + year + '}';
  }
}

