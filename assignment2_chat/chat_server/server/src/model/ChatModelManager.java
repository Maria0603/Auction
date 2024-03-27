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
    //System.out.println(Logger.getInstance().getOutput());
  }
  @Override public String send(String username, String message)
  {
    if (message.isEmpty())
    {
      throw new IllegalArgumentException("No input");
    }
    if(message.startsWith("/"))
    {
      return userList.getUser(username).getConversation();
    }
    else
    {
      property.firePropertyChange("Message", null, new CommunicationPackage("Message", username, message, null));
      return userList.getUser(username).getConversation();
    }
  }

  @Override public String getWholeConversation(String username)
  {
    return userList.getUser(username).getConversation();
  }
  @Override public void addContent(String username, String content)
  {
      userList.getUser(username).addContent(content);
  }

  @Override public void createUser(String username, String password) throws IllegalArgumentException
  {
      User user=new User(username, password);
      userList.addUser(user);
      System.out.println(userList.toString());
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
