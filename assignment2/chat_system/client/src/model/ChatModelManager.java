package model;


import mediator.ChatClient;
import mediator.ServerModel;
import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatModelManager implements ChatModel, PropertyChangeListener
{

  private PropertyChangeSupport property;

  private ServerModel server;

  public ChatModelManager() {

    property=new PropertyChangeSupport(this);

    server = new ChatClient();  //  Has defaults

    server.addListener("broadcast",this);
    server.connect();
  }

  @Override public void send(String username, String message)
  {
      server.send(username,message);
  }

  @Override public String getWholeConversation()
  {
    return server.getWholeConversation();
  }

  @Override public void createUser(String username, String password) throws IllegalArgumentException
  {
      server.createUser(username,password);
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
