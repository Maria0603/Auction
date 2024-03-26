package mediator;

import com.google.gson.Gson;
import model.ChatModel;
import model.CommandPackage;
import model.MessagePackage;
import model.Package;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientHandler implements Runnable, PropertyChangeListener {
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private ChatModel model;
  private Gson gson;

  public ChatClientHandler(Socket socket, ChatModel model) {
    this.socket = socket;
    this.model = model;
    gson = new Gson();
    running = true;
    model.addListener("Message", this);
  }

  @Override public void run() {
    try {
      in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
      out = new PrintWriter(this.socket.getOutputStream(), true);

      while (running) {
        String requestJson = in.readLine();
        UserPackage request = gson.fromJson(requestJson, UserPackage.class);

        // LogIn case
        if(request.getType().equalsIgnoreCase("LogIn")){
          try{
            model.createUser(request.getSender(), request.getMessage());

            UserPackage reply = new UserPackage("LogIn", request.sender, request.message);
            String replyJson = gson.toJson(reply);
            out.println(replyJson);

          } catch (IllegalArgumentException e){
            UserPackage reply = new UserPackage("Error", e.getMessage());
            String replyJson = gson.toJson(reply);
            out.println(replyJson);
          }

          //Command case
        } else if (request.getType().equalsIgnoreCase("Command")){
          try {
            CommandPackage replyPackage = model.sendCommand(request.getSender(), request.getMessage());

            UserPackage reply = new UserPackage("Command", replyPackage.getCommand(), replyPackage.getReply());
            String replyJson = gson.toJson(reply);
            out.println(replyJson);

          }catch (IllegalArgumentException e){
            UserPackage reply = new UserPackage("Error", e.getMessage());
            String replyJson = gson.toJson(reply);
            out.println(replyJson);
          }
        }

        //Message case
        else if (request.getType().equalsIgnoreCase("Message")) {
          try {
            MessagePackage replyPackage = model.sendMessage(request.getSender(), request.getMessage());

          }catch (IllegalArgumentException e){
            UserPackage reply = new UserPackage("Error", e.getMessage());
            String replyJson = gson.toJson(reply);
            out.println(replyJson);
          }
        }
      }

    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt) {
    //TODO: if evt name is "Message", send evt message on a client side
   if(evt.getPropertyName().equalsIgnoreCase("Message")){
     //UserPackage reply = new UserPackage(evt.getPropertyName(), (Package)evt.getNewValue().);
     //String replyJson = gson.toJson(reply);
     //out.println(replyJson);
   }
  }
}