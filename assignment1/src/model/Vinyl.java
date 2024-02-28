package model;

public class Vinyl
{
  private VinylState lendingState;
  public Vinyl()
  {
    this.lendingState=new VinylAvailable(null, null);
  }

  public VinylState getLendingState()
  {
    return lendingState;
  }

  public void setLendingState(VinylState lendingState)
  {
    this.lendingState = lendingState;
  }
}
