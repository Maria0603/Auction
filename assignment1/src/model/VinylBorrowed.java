package model;

public class VinylBorrowed extends VinylState
{

  public VinylBorrowed(Customer borrower, Customer reserver)
  {
    super(borrower, reserver);
  }
  @Override public void borrowVinyl(Vinyl vinyl, Customer customer)
  {
    throw new IllegalStateException("The vinyl is already borrowed");
  }
  @Override public void returnVinyl(Vinyl vinyl, Customer customer)
  {
    if(super.getBorrower() != null && super.getBorrower().getName().equals(customer.getName()))
    {
      vinyl.setLendingState(new VinylAvailable(null, super.getReserver()));
    }
    else throw new IllegalStateException("You are not the one who borrowed the vinyl");
  }
  @Override public void reserveVinyl(Vinyl vinyl, Customer customer)
  {
    vinyl.setLendingState(new VinylReservedWhileBorrowed(super.getBorrower(), customer));
  }
}
