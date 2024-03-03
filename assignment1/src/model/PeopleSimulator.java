package model;

import javafx.application.Platform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PeopleSimulator implements PropertyChangeListener {

    public PeopleSimulator(Model model){

        model.addListener(this);

        new Thread(() -> {
            String[] customers = {"Bob", "Wendy"};
            String[] vinylTitles = new String[model.getList().getAllVinyls().size()];

            for (int z = 0; z < model.getList().getAllVinyls().size(); z++) {
                vinylTitles[z] = model.getList().getAllVinyls().get(z).getTitle();
            }

            for (int i = 0; ; i++) {
                String name = customers[i%2];
                int index = (int) (Math.random() * model.getList().getAllVinyls().size());

                double rand = Math.random();
                if(rand < 0.33){
                    try {
                        model.borrowVinyl(vinylTitles[index], name);
                    }
                    catch (IllegalStateException e){
                        //  UN-OUT-COMMENT THE FOLLOWING LINE TO SEE THE ERRORS GOT BY THE ROBOTS
                        //System.out.println(e.getMessage());
                    }
                } else if (rand > 0.33 && rand < 0.66) {
                    try{
                        model.reserveVinyl(vinylTitles[index], name);
                    }
                    catch (IllegalStateException e){
                        //  UN-OUT-COMMENT THE FOLLOWING LINE TO SEE THE ERRORS GOT BY THE ROBOTS
                        //System.out.println(e.getMessage());
                    }
                }
                else{
                    try {
                        model.returnVinyl(vinylTitles[index], name);
                    }
                    catch (IllegalStateException e){
                        //  UN-OUT-COMMENT THE FOLLOWING LINE TO SEE THE ERRORS GOT BY THE ROBOTS
                        //System.out.println(e.getMessage());
                    }
                }


                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //  UN-OUT-COMMENT THE FOLLOWING LINE TO SEE THE EVENTS FIRE IN THE CONSOLE
       // System.out.println(" -- > " + evt.getPropertyName() + ": " + evt.getOldValue() + ", " + evt.getNewValue());
    }
}
