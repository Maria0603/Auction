package model;

import utility.observer.javaobserver.NamedPropertyChangeSubject;

public interface ChatModel extends NamedPropertyChangeSubject
{
    String send(String username, String message); //used for sending the message, on Send button
    void createUser(String username, String password) throws IllegalArgumentException; // for user creation, on Create account button
}
