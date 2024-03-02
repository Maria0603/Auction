package tests;

import model.*;

public class TestAddRemove {
  public static void main(String[] args) {

    Vinyl vinyl1 = new Vinyl("Title1", "Artist1", 2000);
    Vinyl vinyl2 = new Vinyl("Title2", "Artist2", 2010);
    Vinyl vinyl3 = new Vinyl("Title3", "Artist3", 2020);


    VinylList vinylList = new VinylList(10);


    vinylList.addVinyl(vinyl1);
    vinylList.addVinyl(vinyl2);
    vinylList.addVinyl(vinyl3);


    System.out.println("All vinyls:");
    for (Vinyl vinyl : vinylList.getAllVinyls()) {
      System.out.println("Title: " + vinyl.getTitle() + ", Artist: " + vinyl.getArtist() + ", Year: " + vinyl.getYear());
    }


    System.out.println("\nRemoving vinyl with title 'Title2':");
    vinylList.removeVinyl(vinyl2);
    System.out.println("All vinyls after removal:");
    for (Vinyl vinyl : vinylList.getAllVinyls()) {
      System.out.println("Title: " + vinyl.getTitle() + ", Artist: " + vinyl.getArtist() + ", Year: " + vinyl.getYear());
    }


    VinylLibraryModelManager modelManager = new VinylLibraryModelManager();


    System.out.println("\nAdding vinyls using VinylLibraryModelManager:");
    modelManager.addVinyl(vinyl1);
    modelManager.addVinyl(vinyl2);
    modelManager.addVinyl(vinyl2);


    System.out.println("\nAll vinyls in model manager:");
    for (Vinyl vinyl : modelManager.getAllVinyls()) {
      System.out.println("Title: " + vinyl.getTitle() + ", Artist: " + vinyl.getArtist() + ", Year: " + vinyl.getYear());
    }


    System.out.println("\nRemoving vinyl with title 'Title1' using VinylLibraryModelManager:");
    modelManager.removeVinyl("Title1");
    System.out.println("All vinyls in model manager after removal:");
    for (Vinyl vinyl : modelManager.getAllVinyls()) {
      System.out.println("Title: " + vinyl.getTitle() + ", Artist: " + vinyl.getArtist() + ", Year: " + vinyl.getYear());
    }
  }
}

