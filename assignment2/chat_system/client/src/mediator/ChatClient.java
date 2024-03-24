package mediator;

import com.google.gson.Gson;
import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient implements ServerModel, NamedPropertyChangeSubject {


    private String HOST;
    private int PORT;

    private BufferedReader in;
    private PrintWriter out;
    private Gson gson;

    String receivedText;
    private PropertyChangeSupport property;


    public ChatClient(String HOST, int PORT){
        this.HOST = HOST;
        this.PORT = PORT;
        this.property = new PropertyChangeSupport(this);
    }
    public ChatClient(){
        this("localhost",1234);
    }   //  Default constructor


    //  TODO: send(), getWholeConversation(), createUser(String username, String password)
    //        and logic behind broadcast
    @Override
    public void send(String username, String message) {


    }

    public void receive(String c){
        //  Might want to fire broadcast here for model to listen to
    }
    @Override
    public String getWholeConversation() {

        return null;
    }

    @Override
    public void createUser(String username, String password) throws IllegalArgumentException {

    }
    @Override
    public void connect() {
        try {
            Socket socket = new Socket(HOST, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            gson = new Gson();

            //  Starting the receiver thread here
            ChatClientReceiver receiver = new ChatClientReceiver(this,in);
            Thread thread = new Thread(receiver);
            thread.setDaemon(true);
            thread.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disconnect() {
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        property.addPropertyChangeListener(propertyName,listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        property.removePropertyChangeListener(propertyName,listener);
    }
}
