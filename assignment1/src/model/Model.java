package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject {
    void borrowVinyl(String title, String name);

    void returnVinyl(String title, String name);
    void reserveVinyl(String title, String name);
    void addVinyl(Vinyl vinyl);
    void removeVinyl(Vinyl vinyl);
    Vinyl getVinyl(String title);
    VinylList getList();
}
