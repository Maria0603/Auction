package mediator;

public class UserPackage extends Package
{
  private String type, username, password, error;
  public UserPackage(String type, String username, String password)
  {
    super();
    this.type=type;
    this.username = username;
    this.password = password;
    error=null;
  }
  public UserPackage(String type, String error)
  {
    super();
    this.type=type;
    this.error=error;
    this.username=null;
    this.password=null;
  }

  @Override public String toString()
  {
    if(error==null)
     return type + " " + username + " " + password;
    else return error;
  }

  public String getPassword()
  {
    return password;
  }

  public String getUsername()
  {
    return username;
  }

  public String getError()
  {
    return error;
  }

  public String getType()
  {
    return type;
  }
}
