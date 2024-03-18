package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class CommandPackageCreator extends PackageCreator {

  private static final String[] COMMANDS = {"/list", "/number", "/date", "/time"};
  @Override
  protected void createPackage(String sender, String command, String reply) {
    switch (command)
    {
      case "/list" -> new CommandPackage(sender, command, "list of chatters");
      case "/number" ->
          new CommandPackage(sender, command, "number of chatters");
      case "/date" ->
          new CommandPackage(sender, command, LocalDate.now().toString());
      case "/time" ->
          new CommandPackage(sender, command, LocalTime.now().toString());
    }
  }
}
