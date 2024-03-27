package mediator;

//for transporting data
public class UserPackage
{
  private String type, username, password, error;
  public UserPackage(String type, String username, String password)
  {
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

   public String toString()
  {
    if(error==null)
     return type + " " + username + " " + password;
    else return error;
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
