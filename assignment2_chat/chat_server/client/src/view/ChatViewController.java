package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatViewController implements PropertyChangeListener
{
  @FXML private Label headerLabel;
  @FXML private TextArea chatList;
  @FXML private TextField inputField;
  @FXML private Label errorLabel;
  private Region root;
  private ViewHandler viewHandler;
  private ChatViewModel chatViewModel;

  //initializations and bindings
  public void init(ViewHandler viewHandler,
      ChatViewModel chatViewModel, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.chatViewModel=chatViewModel;
    headerLabel.textProperty().bindBidirectional(chatViewModel.getHeaderProperty());
    chatList.textProperty().bindBidirectional(chatViewModel.getListProperty());
    inputField.textProperty().bindBidirectional(chatViewModel.getInputProperty());
    errorLabel.textProperty().bindBidirectional(chatViewModel.getErrorProperty());
    chatViewModel.addListener(this);
    reset();
  }
  public void reset()
  {
    chatViewModel.reset();
  }

  public Region getRoot()
  {
    return root;
  }


  //triggered by pressing the Send button
  @FXML public void sendPressed()
  {
    chatViewModel.send();
    inputField.requestFocus();

    //when a message is sent, scroll down
    if(chatList.getText()!=null && !chatList.getText().isEmpty())
      chatList.positionCaret(chatList.getText().length());
  }
  @FXML public void onEnter()
  {
    sendPressed();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    //when a message is sent by someone else, scroll down
    if(chatList.getText()!=null && !chatList.getText().isEmpty())
      chatList.positionCaret(chatList.getText().length());
  }
}
