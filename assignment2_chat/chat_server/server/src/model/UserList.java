package model;

import java.util.ArrayList;

public class UserList
{
  private ArrayList<User> users;

  public UserList()
  {
    users = new ArrayList<>();
  }
  public int getSize()
  {
    return users.size();
  }
  public User getUser(String username)
  {
    for(int i=0; i<users.size(); i++)
    {
      if(username.equals(users.get(i).getUsername()))
        return users.get(i);
    }
    return null;
  }
  public User getUser(int index)
  {
    return users.get(index);
  }

  public void addUser(User user)
  {
    if(getUser(user.getUsername())!=null)
        throw new IllegalArgumentException("This username is taken");
    else
     users.add(user);
  }
  public String getLast()
  {
    return users.get(users.size()-1).getUsername();
  }

  @Override public String toString() {
    String userList="Users:";
    for(int i=0; i< users.size(); i++)
      userList=userList+'\n'+users.get(i).getUsername();
    return userList;
  }
}
