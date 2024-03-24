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
    return conversation.addPackage(username, message, userList);
  }

  @Override public String getWholeConversation()
  {
    return conversation.getConversationContent();
  }

  @Override public void createUser(String username, String password) throws IllegalArgumentException
  {
    userList.addUser(username, password);
    System.out.println(userList.toString());
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