package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Region;
import model.ChatModel;
import view.ViewHandler;


public class ChatViewModel
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
    headerProperty.set(viewModelState.getUsername());
    inputProperty=new SimpleStringProperty();
    errorProperty=new SimpleStringProperty();
    listProperty=new SimpleStringProperty();
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
      model.send(headerProperty.get(), inputProperty.get());

      //clear the error label and the input field
      clear();

      //reload the updated conversation
      listProperty.set(model.getWholeConversation());
    }
    catch(Exception e)
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
}
