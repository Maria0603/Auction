package model;


import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChatModelManager implements ChatModel, NamedPropertyChangeSubject
{
  private UserList userList;
  private PropertyChangeSupport property;

  public ChatModelManager()
  {
    this.userList = new UserList();
    property=new PropertyChangeSupport(this);
  }
  @Override public void send(String username, String message)
  {
    //if we received a message, we fire an event
    if (!message.startsWith("/"))
    {
      property.firePropertyChange("Message", username, message);
    }
  }

  @Override public void createUser(String username, String password) throws IllegalArgumentException
  {
    //we try to create a user with the entered data
      User user=new User(username, password);
      userList.addUser(user);
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
