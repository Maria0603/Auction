package model;

import utility.observer.javaobserver.NamedPropertyChangeSubject;

public interface ChatModel extends NamedPropertyChangeSubject
{
  Package send(String username, String message); //used for sending the message, on Send button
  String getWholeConversation(); //used to reload the updated conversation, after send() was called
  void createUser(String username, String password) throws IllegalArgumentException; // for user creation
  void setConversation(Conversation conversation);
  UserList getUsers();
}