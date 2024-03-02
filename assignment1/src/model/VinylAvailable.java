package model;

public class VinylAvailable extends VinylState
{

  public VinylAvailable(Customer borrower, Customer reserver)
  {
    super(null, null);
  }
  @Override public synchronized void borrowVinyl(Vinyl vinyl, Customer customer)
  {
    vinyl.setLendingState(new VinylBorrowed(customer, null));
  }
  @Override public synchronized void returnVinyl(Vinyl vinyl, Customer customer)
  {
    throw new IllegalStateException("The vinyl is not borrowed");
  }
  @Override public synchronized  void reserveVinyl(Vinyl vinyl, Customer customer)
  {
    vinyl.setLendingState(new VinylReserved(null, customer));
  }
}
