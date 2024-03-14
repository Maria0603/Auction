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
        new PeopleSimulator(this);
    }

    private void createDummyData(){
        list.addVinyl(new Vinyl("Beat it", "MJ", 1992));
        list.addVinyl(new Vinyl("If I die tonight", "Tupac", 1995));
        list.addVinyl(new Vinyl("Big Poppa", "Notorious BIG", 1996));
        list.addVinyl(new Vinyl("Listen Closely", "Four Owls", 2003));
        list.addVinyl(new Vinyl("Please be quiet", "Pink guy", 2010));
        list.addVinyl(new Vinyl("Eat my soul", "@(Q*!@_#!", 2066));
    }


    @Override
    public VinylList getList(){return list;}
    @Override
    public Vinyl getVinyl(String title){
        return list.getVinyl(title);
    }

    @Override
    public void addVinyl(Vinyl vinyl) {
        if(list.getAllVinyls().size() < 10){
            list.addVinyl(vinyl);
        }
    }
    @Override
    public void removeVinyl(Vinyl vinyl){
        if(vinyl!=null)
        {
            if(vinyl.getStatus().equals("Available"))
            {
                list.removeVinyl(vinyl);
            }
        else
            {
                vinyl.setToBeRemoved(true);
                property.firePropertyChange("toBeRemoved", vinyl.getTitle(),
                    vinyl);
            }
        }
    }
    @Override
    public void borrowVinyl(String title, String name){
        list.borrowVinyl(title, name);
        property.firePropertyChange("borrow",title,getVinyl(title));
    }
    @Override
    public void returnVinyl(String title, String name){
        list.returnVinyl(title, name);
        property.firePropertyChange("return",title,getVinyl(title));
    }
    @Override
    public void reserveVinyl(String title, String name){
        list.reserveVinyl(title, name);
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
