package tests;

import model.Customer;
import model.Vinyl;
import model.VinylLibraryModelManager;

public class TestThreads {
  public static void main(String[] args)
  {
    Customer c1 = new Customer("Customer1");
    Customer c2 = new Customer("Customer2");
    Customer c3 = new Customer("Customer3");
    Vinyl vinyl1 = new Vinyl("a", "a", 1990);
    Vinyl vinyl2 = new Vinyl("b", "b", 1899);
    Vinyl vinyl3 = new Vinyl("c", "c", 1799);
    Vinyl vinyl4 = new Vinyl("d", "d", 1909);

    VinylLibraryModelManager list=new VinylLibraryModelManager();
    list.addVinyl(vinyl1);
    list.addVinyl(vinyl2);
    list.addVinyl(vinyl3);
    list.addVinyl(vinyl4);


    /*Vinyl vinyl = list.getVinyl("a");
    System.out.println(vinyl.getLendingStatus());
    list.addVinyl(vinyl);
    System.out.println(list.getAllVinyls());
   // list.removeVinyl(vinyl.getTitle());
   // System.out.println(list.getAllVinyls());

    list.getVinyl(1).setLendingState(new VinylReserved(null, new Customer("Brrr")));
    list.removeVinyl(list.getVinyl(1).getTitle());
    System.out.println(list.getAllVinyls());
    System.out.println(list.getVinyl(1).getLendingStatus());
    System.out.println(list.getVinyl(1).getLendingState().getScheduledForRemoval());
    list.getVinyl(1).setLendingState(new VinylAvailable(null, null));
    list.removeVinyl(list.getVinyl(1).getTitle());
    System.out.println(list.getAllVinyls());*/

   /* try
    {
      vinyl.setLendingState(new VinylAvailable(null, null));
      System.out.println(vinyl.getLendingStatus());//available
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c1);
      System.out.println(vinyl.getLendingStatus()+ "  "
          + vinyl.getLendingState().getBorrower().getName());//borrowed

    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().reserveVinyl(vinyl, c2);
      System.out.println(vinyl.getLendingStatus() + "  "
          + vinyl.getLendingState().getReserver().getName());//reservedWhileBorrowed
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().reserveVinyl(vinyl, c3);
      System.out.println(vinyl.getLendingState().getClass().getName() + "  "
          + vinyl.getLendingStatus() + vinyl.getLendingState().getReserver().getName());

    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage() + vinyl.getLendingState().getReserver().getName());//Already reserved
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c2);
      System.out.println(vinyl.getLendingStatus() + "  "
          + vinyl.getLendingState().getBorrower().getName());
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage() + vinyl.getLendingState().getBorrower().getName());//You are not the one who borrowed it
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c1);
      System.out.println(vinyl.getLendingStatus() + vinyl.getLendingState()
          .getReserver().getName());//reserved
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c3);
      System.out.println(vinyl.getLendingStatus());
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());//Reserved by someone else
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c2);
      System.out.println(vinyl.getLendingStatus());//borrowed
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c3);
      System.out.println(vinyl.getLendingStatus());
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());//You are not the one who borrowed it
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c2);
      System.out.println(vinyl.getLendingStatus());//available
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c2);
      System.out.println(vinyl.getLendingStatus());
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());//it is not borrowed
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c3);
      System.out.println(vinyl.getLendingStatus());//borrowed
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().borrowVinyl(vinyl, c1);
      System.out.println(vinyl.getLendingStatus());
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());//already borrowed
    }
    try
    {
      vinyl.getLendingState().reserveVinyl(vinyl, c1);
      System.out.println(vinyl.getLendingStatus());//reservedWhileBorrowed
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      vinyl.getLendingState().returnVinyl(vinyl, c1);
      System.out.println(vinyl.getLendingStatus());
    }
    catch (IllegalStateException e)
    {
      System.out.println(e.getMessage());//it is not you the one
    }*/
  }
}
