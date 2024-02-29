package model;

import observer.PropertyChangeSubject;

import java.util.List;

public interface VinylLibraryModel extends PropertyChangeSubject
{
public  List<Vinyl> getAllVinyls();
public  void addVinyl(Vinyl[] vinyl);
public  void removeVinyl(String id);
VinylState getLendingState(Vinyl vinyl);
}
