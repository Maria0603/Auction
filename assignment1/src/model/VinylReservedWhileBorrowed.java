package model;

public class VinylReservedWhileBorrowed extends VinylState
{
  public VinylReservedWhileBorrowed(Customer borrower, Customer reserver)
  {
    super(borrower, reserver);
  }
  @Override public void borrowVinyl(Vinyl vinyl, Customer customer)
  {
    throw new IllegalStateException("The vinyl is already borrowed by someone else");
  }
  @Override public void returnVinyl(Vinyl vinyl, Customer customer)
  {
    if(super.getBorrower() != null && customer!=null && super.getBorrower().getName().equals(customer.getName()))
    {
      vinyl.setLendingState(new VinylReserved(null, super.getReserver()));
    }
    else throw new IllegalStateException("You are not the one who borrowed the vinyl");
  }
  @Override public void reserveVinyl(Vinyl vinyl, Customer customer)
  {
    throw new IllegalStateException("The vinyl is already reserved");
  }
}
