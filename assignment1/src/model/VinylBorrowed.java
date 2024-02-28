package model;

public class VinylBorrowed extends VinylState
{
  private Customer borrowerHere, reserverHere;

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
      super.setBorrower(null);
      vinyl.setLendingState(new VinylAvailable(null, super.getReserver()));
    }
    else throw new IllegalStateException("You are not the one who borrowed the vinyl");
  }
  @Override public void reserveVinyl(Vinyl vinyl, Customer customer)
  {
    super.setReserver(customer);
    vinyl.setLendingState(new VinylReservedWhileBorrowed(super.getBorrower(), customer));
  }
}
