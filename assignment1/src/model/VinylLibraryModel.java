package model;

import observer.PropertyChangeSubject;

import java.util.List;

public interface VinylLibraryModel extends PropertyChangeSubject
{
public  List<Vinyl> getAllVinyls();
public  void addVinyl(Vinyl vinyl);
public  void removeVinyl(String id);
VinylState getLendingState(Vinyl vinyl);
Vinyl getVinyl(String title);
Vinyl getVinyl(int index);
int getSize();
void returnVinyl(Vinyl vinyl, Customer customer);
void reserveVinyl(Vinyl vinyl, Customer customer);

void borrowVinyl(Vinyl vinyl, Customer customer);

}
