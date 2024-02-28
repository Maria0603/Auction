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
    if (lendingState == null) {
      return "Available";
    } else {
      return lendingState.getClass().getSimpleName();
    }
  }
}
