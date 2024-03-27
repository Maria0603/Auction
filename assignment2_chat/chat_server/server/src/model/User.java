package model;

public class User
{
  private String username, password;
  public User(String username, String password)
  {
    setUsername(username);
    setPassword(password);
  }

  public void setPassword(String password)
  {
    if (password == null || password.isEmpty())
      throw new IllegalArgumentException("Password cannot be empty");
    else if (password.length() < 8)
      throw new IllegalArgumentException("Password must be at least 8 characters long");
    else this.password=password;
  }

  public void setUsername(String username)
  {
      if (username.length()<3)
        throw new IllegalArgumentException("The username is too short");
      else if(username.length()>25)
        throw new IllegalArgumentException("The username is too long");
      else this.username=username;
  }

  public String getUsername()
  {
    return username;
  }


}
