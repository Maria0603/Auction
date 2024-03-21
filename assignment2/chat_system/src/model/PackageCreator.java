package model;

public abstract class PackageCreator {

  private Package aPackage;
  protected abstract Package createPackage(String sender, String request, Object reply);
  public  Package getPackage()
  {
    return aPackage;
  }
}
