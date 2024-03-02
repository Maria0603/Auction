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
    public Vinyl getVinyl(int index){

        if (album.get(index) != null) {
            return album.get(index);
        }
        else{
            return null;
        }
    }
    public void addVinyl(Vinyl vinyl){album.add(vinyl);}
    public void removeVinyl(Vinyl vinyl){album.remove(vinyl);}
    public void Borrow(String title, String name){
        for(Vinyl v : album){
            if(v.getTitle().equals(title)){
                v.Borrow(name);
            }
        }
    }


    public void Return(String title, String name){
        for(Vinyl v : album){
            if(v.getTitle().equals(title)){
                v.Return(name);
            }
        }
    }


    public void Reserve(String title, String name){
        for(Vinyl v : album){
            if(v.getTitle().equals(title)){
                v.Reserve(name);
            }
        }
    }



    public ArrayList<Vinyl> getAlbum() {
        return album;
    }
}
