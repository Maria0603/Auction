package model;

public class VinylBorrowed extends VinylState
{

  public VinylBorrowed(Customer borrower, Customer reserver)
  {
    super(borrower, reserver);
  }
  @Override public  synchronized void borrowVinyl(Vinyl vinyl, Customer customer)
  {
    throw new IllegalStateException("The vinyl is already borrowed");
  }
  @Override public synchronized  void returnVinyl(Vinyl vinyl, Customer customer)
  {
    if(super.getBorrower() != null && super.getBorrower().getName().equals(customer.getName()))
    {
      vinyl.setLendingState(new VinylAvailable(null, super.getReserver()));
    }
    else throw new IllegalStateException("You are not the one who borrowed the vinyl");
  }
  @Override public synchronized  void reserveVinyl(Vinyl vinyl, Customer customer)
  {
    if(super.getBorrower()!=customer)
     vinyl.setLendingState(new VinylReservedWhileBorrowed(super.getBorrower(), customer));
    else throw new IllegalStateException("You already borrowed this vinyl");
  }
}
