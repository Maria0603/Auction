package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ChatModel;
import model.CommunicationPackage;
import observer.UnnamedPropertyChangeSubject;

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
    System.out.println("Received in the chat view model: " + evt.getNewValue().toString());
    {
      Platform.runLater(()->{
        {
          if(!((CommunicationPackage)evt.getNewValue()).getSender().equals(headerProperty.get()))
          {
            listProperty.set(listProperty.get() + evt.getNewValue().toString());
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
