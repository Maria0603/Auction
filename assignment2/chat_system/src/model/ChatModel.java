package model;

public interface ChatModel
{


    String getWholeConversation(); //used to reload the updated conversation, after send() was called
    abstract void createUser(String username, String password); // for user creation

    void send(String username, String message, String error); //used for sending the message, on Send button
    String getConversationContent(); //used to reload the updated conversation, after send() was called

}
