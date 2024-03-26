package model;

import utility.observer.javaobserver.NamedPropertyChangeSubject;

public interface ChatModel extends NamedPropertyChangeSubject
{
  Package send(String username, String message); //needed to be deleted
  MessagePackage sendMessage(String username, String message);
  CommandPackage sendCommand(String username, String message);
  String getWholeConversation(); //used to reload the updated conversation, after send() was called
  boolean createUser(String username, String password) throws IllegalArgumentException; // for user creation
  void setConversation(Conversation conversation);
  UserList getUsers();
}