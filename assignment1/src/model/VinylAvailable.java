package model;

public class VinylAvailable extends VinylState
{
  private Customer borrowerHere, reserverHere;

  public VinylAvailable(Customer borrower, Customer reserver)
  {
    super(null, null);
  }
  @Override public void borrowVinyl(Vinyl vinyl, Customer customer)
  {
    super.setBorrower(customer);
    super.setReserver(null);
    vinyl.setLendingState(new VinylBorrowed(customer, null));
  }
  @Override public void returnVinyl(Vinyl vinyl, Customer customer)
  {
    throw new IllegalStateException("The vinyl is not borrowed");
  }
  @Override public void reserveVinyl(Vinyl vinyl, Customer customer)
  {
    super.setReserver(customer);
    super.setBorrower(null);
    vinyl.setLendingState(new VinylReserved(null, customer));
  }
}
