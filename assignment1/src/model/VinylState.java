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
  public  synchronized void setScheduledForRemoval(boolean remove)
  {
    this.scheduledForRemoval=remove;
  }
  public  synchronized boolean getScheduledForRemoval()
  {
    return scheduledForRemoval;
  }
  public synchronized  Customer getBorrower()
  {
    return borrower;
  }

  public synchronized  void setBorrower(Customer borrower)
  {
     this.borrower = borrower;
  }

  public  synchronized Customer getReserver()
  {
    return reserver;
  }

  public  synchronized void setReserver(Customer reserver)
  {
     this.reserver = reserver;
  }
  public  abstract void borrowVinyl(Vinyl vinyl, Customer customer);
  public abstract void returnVinyl(Vinyl vinyl, Customer customer);
  public abstract void reserveVinyl(Vinyl vinyl, Customer customer);
}
