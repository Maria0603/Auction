package model;

public interface ChatModel
{
    void send(String username, String message, String error); //used for sending the message, on Send button
    String getConversationContent(); //used to reload the updated conversation, after send() was called
}
