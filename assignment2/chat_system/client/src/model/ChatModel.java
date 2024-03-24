package model;

import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface ChatModel extends NamedPropertyChangeSubject, PropertyChangeListener
{
    void send(String username, String message); //used for sending the message, on Send button
    String getWholeConversation(); //used to reload the updated conversation, after send() was called
    void createUser(String username, String password) throws IllegalArgumentException; // for user creation

}
