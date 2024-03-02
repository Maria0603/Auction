package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model{
    private VinylList list;
    private PropertyChangeSupport property;
    public ModelManager(){
        list = new VinylList();
        property = new PropertyChangeSupport(this);
        createDummyData();
        //new PeopleSimulator(this);
    }

    private void createDummyData(){
        list.addVinyl(new Vinyl("Beat it", "MJ", 1992));
        list.addVinyl(new Vinyl("If I die tonight", "Tupac", 1995));
        list.addVinyl(new Vinyl("Big Poppa", "Notorious BIG", 1996));
        list.addVinyl(new Vinyl("Listen Closely", "Four Owls", 2003));
        list.addVinyl(new Vinyl("Shut the fuck up", "Pink guy", 2010));
        list.addVinyl(new Vinyl("eat my ass", "@(Q*!@_#!", 2066));
    }
    @Override
    public VinylList getList(){return list;}
    @Override
    public Vinyl getVinyl(String title){
        return list.getVinyl(title);
    }

    @Override
    public void addVinyl(Vinyl vinyl){
        if(list.getAlbum().size() <= 10){
            list.addVinyl(vinyl);
        }
    }
    @Override
    public void removeVinyl(Vinyl vinyl){
        //  FlagVinyl before removal
        list.removeVinyl(vinyl);

    }
    @Override
    public void borrowVinyl(String title, String name){
        list.Borrow(title, name);
        property.firePropertyChange("borrow",title,getVinyl(title));
    }
    @Override
    public void returnVinyl(String title, String name){
        list.Return(title, name);
        property.firePropertyChange("return",title,getVinyl(title));
    }
    @Override
    public void reserveVinyl(String title, String name){
        list.Reserve(title, name);
        property.firePropertyChange("reserve",title,getVinyl(title));
    }


    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }
}
