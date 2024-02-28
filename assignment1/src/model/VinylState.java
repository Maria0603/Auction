package model;

public abstract class VinylState
{
  private boolean scheduledForRemoval;
  private Customer borrower, reserver;
  public VinylState()
  {
    super();
    this.scheduledForRemoval=false;
  }
  public void setScheduledForRemoval(boolean remove)
  {
    this.scheduledForRemoval=remove;
  }
  public boolean getScheduledForRemoval()
  {
    return scheduledForRemoval;
  }
  public Customer getBorrower()
  {
    return borrower;
  }

  public void setBorrower(Customer borrower)
  {
    this.borrower = borrower;
  }

  public Customer getReserver()
  {
    return reserver;
  }

  public void setReserver(Customer reserver)
  {
    this.reserver = reserver;
  }
  public abstract void borrowVinyl(Vinyl vinyl, Customer customer);
  public abstract void returnVinyl(Vinyl vinyl, Customer borrower, Customer reserver);
  public abstract void reserveVinyl(Vinyl vinyl, Customer customer);
}
