package model;

import java.util.ArrayList;

public class UserList
{
  private ArrayList<String> users;
  private PasswordChecker checker;

  public UserList()
  {
    users = new ArrayList<>();
    checker=new PasswordChecker();
  }
  public String getSize()
  {
    return "Number of chatters: " + users.size();
  }

  public void addUser(String username, String password) throws IllegalArgumentException
  {
      checker.check(password);
      if(users.contains(username))
        throw new IllegalArgumentException("This username is taken");
      else if (username.length()<3)
        throw new IllegalArgumentException("The username cannot be empty");
      else
        users.add(username);
  }
  public String getLast()
  {
    return "Last joined: " + users.get(users.size()-1);
  }

  @Override public String toString() {
    String userList="Users:";
    for(int i=0; i< users.size(); i++)
      userList=userList+'\n'+users.get(i);
    return userList;
  }
}
