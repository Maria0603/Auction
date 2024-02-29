package model;

public class Customer
{
  private String name;
  public Customer(String name)
  {
    //if(name!=null)
     this.name=name;
    //else throw new IllegalArgumentException("Invalid name");
  }

  public String getName()
  {
    return name;
  }
}
