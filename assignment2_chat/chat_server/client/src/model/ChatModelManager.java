package model;


import mediator.ChatClient;
import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class ChatModelManager implements ChatModel, NamedPropertyChangeSubject, PropertyChangeListener
{
  private PropertyChangeSupport property;
  private ChatClient client;

  public ChatModelManager()
  {
    try
    {
      client = new ChatClient("localhost", 3138);
      property = new PropertyChangeSupport(this);

      client.addListener("Message", this);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }

  //delegate all work to the client side
  @Override public String send(String username, String message)
  {
    return client.send(username, message);
  }

  @Override public void createUser(String username, String password)
  {
    client.createUser(username, password);
  }


  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    client.addListener(propertyName, listener);
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    client.removeListener(propertyName, listener);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    property.firePropertyChange(evt);
  }
}
