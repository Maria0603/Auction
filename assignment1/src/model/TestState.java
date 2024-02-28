package model;

public class TestState
{
  public static void main(String[] args)
  {
    Customer c1=new Customer("Customer1");
    Customer c2=new Customer("Customer2");
    Customer c3=new Customer("Customer3");
    Vinyl vinyl=new Vinyl();
    try
    {
      vinyl.setLendingState(new VinylAvailable(null, null));
      System.out.println(vinyl.getLendingState().getClass().getName());//available
    }
    catch(IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c1);
      System.out.println(vinyl.getLendingState().getClass().getName() + "  " +vinyl.getLendingState().getBorrower().getName());//borrowed
    //  System.out.println(vinyl.getLendingState().getClass().getName());//borrowed

    }
    catch(IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().reserveVinyl(vinyl, c2);
      //System.out.println(vinyl.getLendingState().getClass().getName());//reservedWhileBorrowed
      System.out.println(vinyl.getLendingState().getClass().getName()+ "  " +vinyl.getLendingState().getReserver().getName());//reservedWhileBorrowed
    }
    catch(IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().reserveVinyl(vinyl, c3);
      //System.out.println(vinyl.getLendingState().getClass().getName());
      System.out.println(vinyl.getLendingState().getClass().getName()+ "  " +vinyl.getLendingState().getReserver().getName() +vinyl.getLendingState().getReserver().getName());

    }
    catch(IllegalStateException e)
    {
      System.out.println(e.getMessage() + vinyl.getLendingState().getReserver().getName());//Already reserved
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c2);
      //System.out.println(vinyl.getLendingState().getClass().getName());
            System.out.println(vinyl.getLendingState().getClass().getName()+ "  " +vinyl.getLendingState().getBorrower().getName());
    }
    catch(IllegalStateException e)
    {
      System.out.println(e.getMessage() + vinyl.getLendingState().getBorrower().getName());//You are not the one who borrowed it
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c1);
      //System.out.println(vinyl.getLendingState().getClass().getName());//reserved
      System.out.println(vinyl.getLendingState().getClass().getName() + vinyl.getLendingState().getReserver().getName());//reserved
    }
    catch(IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c3);
      System.out.println(vinyl.getLendingState().getClass().getName());
    }
    catch(IllegalStateException e)
    {
      System.out.println(e.getMessage());//Reserved by someone else
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c2);
      System.out.println(vinyl.getLendingState().getClass().getName());//borrowed
    }
    catch(IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c3);
      System.out.println(vinyl.getLendingState().getClass().getName());
    }
    catch(IllegalStateException e)
    {
      System.out.println(e.getMessage());//You are not the one who borrowed it
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c2);
      System.out.println(vinyl.getLendingState().getClass().getName());//available
    }
    catch(IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }


  }
}
