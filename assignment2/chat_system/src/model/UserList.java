package model;

import java.util.ArrayList;

public class UserList
{
  private ArrayList<String> users;
  private String username, password;

  public UserList(String username, String password)
  {
    users = new ArrayList<>();
    this.username = username;
    this.password = password;
  }

  public void addUser(String username, String password)
  {
    String user = username + ":" + password;
    users.add(user);
  }

}
