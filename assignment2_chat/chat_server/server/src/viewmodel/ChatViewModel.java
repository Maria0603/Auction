package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ChatModel;

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
    this.model = model;
    viewModelState = state;
    headerProperty = new SimpleStringProperty();
    inputProperty = new SimpleStringProperty();
    errorProperty = new SimpleStringProperty();
    listProperty = new SimpleStringProperty();
    model.addListener("Message", this);
    model.addListener("Command", this);
    //  Should add a listener to model for "broadcast"... see propertyChange() method bellow
  }

  public void setHeaderProperty(String headerProperty)
  {
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
    /*
    dd/mm/yyyy hh:mm Username: message
    dd/mm/yyyy hh"mm Username: command
    Reply
     */
    try
    {
      //send the user and their message, to update the conversation
      if (inputProperty.get() == null)
        model.send(headerProperty.get(), "");
      else
        model.send(headerProperty.get(), inputProperty.get().trim());
      //System.out.println(headerProperty.get() + " " + inputProperty.get());
      //clear the error label and the input field
      clear();

      //reload the updated conversation
      String updatedConversation = model.getWholeConversation();
      //listProperty.set(Logger.getInstance().extractOnlyMessages(updatedConversation));
      listProperty.set(updatedConversation);
    }
    catch (Exception e)
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

  public void reset()
  {
    headerProperty.set(viewModelState.getUsername());
  }

  @Override public void propertyChange(PropertyChangeEvent evt) {

      // Update the conversation if any kind of message received
      Platform.runLater(() -> {
        String updatedConversation = (String) evt.getOldValue();
        listProperty.set(updatedConversation);
      });

  }
}