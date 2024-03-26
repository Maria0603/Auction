package model;

import utility.observer.javaobserver.NamedPropertyChangeSubject;

public interface ChatModel extends NamedPropertyChangeSubject
{
    String send(String username, String message); //used for sending the message, on Send button
    String getWholeConversation(String username); //used to reload the updated conversation, after send() was called
  void addContent(String username, String content);
  void createUser(String username, String password) throws IllegalArgumentException; // for user creation
  UserList getUsers();
}
