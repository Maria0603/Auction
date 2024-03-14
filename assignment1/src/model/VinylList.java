package model;

import java.util.ArrayList;

public class VinylList {
    private ArrayList<Vinyl> album;
    public VinylList(){
        album = new ArrayList<>();
    }
    public Vinyl getVinyl(String title){
        for(Vinyl v : album){
            if(v.getTitle().equals(title))
                return v;
        }
        return null;
    }
    public void addVinyl(Vinyl vinyl){album.add(vinyl);}
    public void removeVinyl(Vinyl vinyl){album.remove(vinyl);}
    public void borrowVinyl(String title, String name){
        for(Vinyl v : album){
            if(v.getTitle().equals(title)){
                v.borrowVinyl(name);
                break;

            }
        }
    }


    public void returnVinyl(String title, String name){
        for(Vinyl v : album){
            if(v.getTitle().equals(title)){
                v.returnVinyl(name);
                break;

            }
        }
    }


    public void reserveVinyl(String title, String name){
        for(Vinyl v : album){
            if(v.getTitle().equals(title)){
                v.reserveVinyl(name);
                break;
            }
        }
    }



    public ArrayList<Vinyl> getAllVinyls() {
        return album;
    }
}
