package model;

public class PasswordChecker {

  public PasswordChecker() {

  }
 //  point of this class?
  public void check(String password) {
    if (password == null || password.isEmpty())
      throw new IllegalArgumentException("Password cannot be empty");
    else if (password.length() < 8)
      throw new IllegalArgumentException("Password must be at least 8 characters long");
  }
}
