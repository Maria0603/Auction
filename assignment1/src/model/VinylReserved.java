package model;

public class VinylReserved extends VinylState
{
  public VinylReserved(Customer borrower, Customer reserver)
  {
    super(borrower, reserver);
  }
  @Override public  synchronized void borrowVinyl(Vinyl vinyl, Customer customer)
  {
    if(customer.getName().equals(super.getReserver().getName()))
    {
      vinyl.setLendingState(new VinylBorrowed(customer, null));
    }
    else throw new IllegalStateException("The vinyl is reserved by someone else");
  }
  @Override public synchronized  void returnVinyl(Vinyl vinyl, Customer customer)
  {
    throw new IllegalStateException("The vinyl is not borrowed");
  }
  @Override public synchronized  void reserveVinyl(Vinyl vinyl, Customer customer)
  {
    throw new IllegalStateException("The vinyl is already reserved by someone else");
  }
}
