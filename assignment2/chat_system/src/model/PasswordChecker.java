package model;

public class PasswordChecker {
  private String errorMessage;

  public PasswordChecker() {
    this.errorMessage = "Error";
  }

  public String check(String password) {
    if (password == null || password.isEmpty()) {
      errorMessage = "Password cannot be empty";
    } else if (password.length() < 8) {
      errorMessage = "Password must be at least 8 characters long";
    } else {
      errorMessage = ""; //no error
    }
    return errorMessage;
  }
}
