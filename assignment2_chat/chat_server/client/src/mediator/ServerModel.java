package mediator;

import utility.observer.javaobserver.NamedPropertyChangeSubject;

public interface ServerModel extends NamedPropertyChangeSubject {
  void connect();
  void disconnect();
  void send(String username, String message);
  String getWholeConversation();
  void createUser(String username, String password) throws IllegalArgumentException;

}