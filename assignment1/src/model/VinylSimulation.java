package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VinylSimulation implements PropertyChangeListener
{
  public VinylSimulation(VinylLibraryModel model)
  {
    //model.addListener(this);
    new Thread(()-> {
      Customer Bob=new Customer("Bob");
      Customer Wendy=new Customer("Wendy");
      Customer[] customers={Bob, Wendy};
      for(int i=0; i<1000; i++)
      {
        Vinyl vinyl=model.getVinyl((int)(Math.random()* model.getSize()));
        int random=(int)(Math.random()* model.getSize());
        try
        {
          if (random == 1)
          {
            model.borrowVinyl(vinyl, customers[i % 2]);
            System.out.println(customers[i % 2].getName() + " borrowed " + vinyl.getTitle() + " which is now: " + vinyl.getLendingStatus());
          }
          else if (random == 2)
          {
            model.reserveVinyl(vinyl, customers[i % 2]);
            System.out.println(customers[i % 2].getName() + " reserved " + vinyl.getTitle() + " which is now: " + vinyl.getLendingStatus());
          }
          else
          {
            model.returnVinyl(vinyl, customers[i % 2]);
            System.out.println(customers[i % 2].getName() + " returned " + vinyl.getTitle() + " which is now: " + vinyl.getLendingStatus());
          }
        }
        catch (Exception e)
        {
         // System.out.println(e.getMessage());
        }

        try
        {
          Thread.sleep(1500);
        }
        catch (InterruptedException e)
        {
          break;
        }

      }
    }).start();
  }
  @Override public void propertyChange(PropertyChangeEvent event)
  {
    //to be edited
    System.out.println(event.getPropertyName() + " " + event.getOldValue()+" "+ event.getNewValue());
  }
}
