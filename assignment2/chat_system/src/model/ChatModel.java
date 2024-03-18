package model;

public interface ChatModel
{
    void send(String username, String message); //used for sending the message, on Send button
    String getWholeConversation(); //used to reload the updated conversation, after send() was called
    abstract void createUser(String username, String password); // for user creation
}
