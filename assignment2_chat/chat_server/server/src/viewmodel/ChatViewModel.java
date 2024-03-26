package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ChatModel;
import model.CommunicationPackage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatViewModel implements PropertyChangeListener
{
  private StringProperty headerProperty, inputProperty, errorProperty, listProperty;
  private ChatModel model;
  private ViewModelState viewModelState;

  //initializations
  public ChatViewModel(ChatModel model, ViewModelState state)
  {
    this.model=model;
    viewModelState=state;
    headerProperty=new SimpleStringProperty();
    inputProperty=new SimpleStringProperty();
    errorProperty=new SimpleStringProperty();
    listProperty= new SimpleStringProperty();
    model.addListener("Message", this);
  }

  public void setHeaderProperty(String headerProperty) {
    this.headerProperty.set(headerProperty);
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
      //send the user and their message, to update the conversation
      if(inputProperty.get()!=null && !inputProperty.get().trim().isEmpty())
       model.send(headerProperty.get(), inputProperty.get().trim());
      //System.out.println(headerProperty.get() + " " + inputProperty.get());
      //clear the error label and the input field
      clear();

      //reload the updated conversation
      String updatedConversation = model.getWholeConversation(headerProperty.get());
      //listProperty.set(Logger.getInstance().extractOnlyMessages(updatedConversation));
      //listProperty.clear();
      listProperty.set(updatedConversation);
    }
    catch(IllegalArgumentException e)
    {
      errorProperty.set(e.getMessage());
    }
  }
  public void clear()
  {
    listProperty.set(null);
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
        //for(int i=0; i<model.getUsers().getSize(); i++)
        {
          //model.send(model.getUsers().getUser(i).getUsername(), ((CommunicationPackage)evt.getNewValue()).toString());
          //model.getWholeConversation(model.getUsers().getUser(i).getUsername());
          listProperty.set(listProperty.get()+'\n'+ ((CommunicationPackage)evt.getNewValue()).toString());
        }
      });
    }
  }
}
