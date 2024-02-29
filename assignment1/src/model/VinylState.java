package model;

public abstract class VinylState
{
  private boolean scheduledForRemoval;
  private Customer borrower, reserver;
  public VinylState(Customer borrower, Customer reserver)
  {
    this.borrower=borrower;
    this.reserver=reserver;
    scheduledForRemoval=false;
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
  public abstract void returnVinyl(Vinyl vinyl, Customer customer);
  public abstract void reserveVinyl(Vinyl vinyl, Customer customer);
}
