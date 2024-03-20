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
  }
  @Override public void send(String username, String message)
  {
    PackageCreator creator;
    Package sendMessage=null, sendCommand=null;
    if(!message.startsWith("/"))
    {
      creator=new MessagePackageCreator();
      sendMessage=creator.createPackage(username, message, "");
    }
    else
    {
      creator=new CommandPackageCreator();
      sendCommand=creator.createPackage(username, message, userList);
    }
    if(sendMessage!=null)
      conversation.addPackage(sendMessage);
    else conversation.addPackage(sendCommand);
    System.out.println(sendMessage + "   " + sendCommand);
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

  public void setConversation(Conversation conversation)
  {
    this.conversation = conversation;
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
