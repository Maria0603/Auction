package model;

public abstract class PackageCreator {

  private Package aPackage;
  protected abstract void createPackage(String sender, String request, String reply);
  public  Package getPackage()
  {
    return aPackage;
  }
}
