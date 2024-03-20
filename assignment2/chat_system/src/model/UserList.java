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
  public int getSize()
  {
    return users.size();
  }

  public void addUser(String username, String password) throws IllegalArgumentException
  {
      checker.check(password);
      users.add(username);
  }

  @Override public String toString() {
    String userList="Users: \n";
    for(int i=0; i< users.size(); i++)
      userList=userList+users.get(i)+'\n';
    return userList;
  }
}
