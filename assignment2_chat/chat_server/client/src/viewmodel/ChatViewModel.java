package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ChatModel;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  private StringProperty headerProperty, inputProperty, errorProperty, listProperty;
  private ChatModel model;
  private ViewModelState viewModelState;
  private PropertyChangeSupport property;

  //initializations
  public ChatViewModel(ChatModel model, ViewModelState state)
  {
    this.model=model;
    viewModelState=state;
    headerProperty=new SimpleStringProperty();
    inputProperty=new SimpleStringProperty();
    errorProperty=new SimpleStringProperty();
    listProperty= new SimpleStringProperty();
    listProperty.set("Your conversation: \n");
    property=new PropertyChangeSupport(this);
    model.addListener("Message", this);
  }


  public StringProperty getInputProperty()
  {
    return inputProperty;
  }

  public StringProperty getHeaderProperty()
  {
    return headerProperty;
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public StringProperty getListProperty()
  {
    return listProperty;
  }

  //functionality for the Send button
  public void send()
  {
    try
    {
      if(inputProperty.get()!=null && !inputProperty.get().trim().isEmpty())
      {
        //send the data to the server and append the reply
        listProperty.set(listProperty.get() + model.send(headerProperty.get(), inputProperty.get().trim()));
      }
      //clear the error label and the input field
      clear();

    }
    catch(IllegalArgumentException e)
    {
      errorProperty.set(e.getMessage());
    }
  }
  public void clear()
  {
    inputProperty.set(null);
    errorProperty.set(null);
  }


  public void reset() {
    headerProperty.set(viewModelState.getUsername());
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    {
      Platform.runLater(()->{
        {
          //if it was the same user the one who sent it, it is already displayed
          if(!(evt.getOldValue()).equals(headerProperty.get()))
          {
            //append the message from other users to the conversation
            listProperty.set(listProperty.get() + evt.getNewValue().toString());
            //tell the viewController to scroll down
            property.firePropertyChange("Scroll down", null, null);
          }
        }
      });
    }
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
