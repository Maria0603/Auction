package model;


import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatModelManager implements ChatModel, NamedPropertyChangeSubject
{
  private Conversation conversation;
  private UserList userList;
  private PropertyChangeSupport property;

  public ChatModelManager() {
    this.userList = new UserList();
    conversation = new Conversation();
    property=new PropertyChangeSupport(this);
    System.out.println(Logger.getInstance().getOutput());
  }
  @Override public Package send(String username, String message)
  {
    Package newPackage;
    if(message.startsWith("/")){
      newPackage = conversation.addCommand(username, message, userList);
      property.firePropertyChange("Command", getWholeConversation(), newPackage);
    }else {
      newPackage = conversation.addMessage(username, message, userList);
      property.firePropertyChange("Message", getWholeConversation(), newPackage);
    }
    return newPackage;
  }

  @Override public MessagePackage sendMessage(String username, String message) {
    Package newPackage = conversation.addMessage(username, message, userList);
    property.firePropertyChange("Message", getWholeConversation(), newPackage);
    return (MessagePackage) newPackage;
  }

  @Override public CommandPackage sendCommand(String username, String message) throws IllegalArgumentException {
    Package newPackage = conversation.addCommand(username, message, userList);
    property.firePropertyChange("Command", getWholeConversation(), newPackage);
    return (CommandPackage) newPackage;
  }

  @Override public String getWholeConversation()
  {
    return conversation.getConversationContent();
  }

  @Override public boolean createUser(String username, String password)
  {
    try {
      userList.addUser(username, password);
      System.out.println(userList.toString());
      return true;
    }catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }

  @Override public void setConversation(Conversation conversation)
  {
    this.conversation = conversation;
  }
  @Override public UserList getUsers()
  {
    return userList;
  }
  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName, listener);
  }
}