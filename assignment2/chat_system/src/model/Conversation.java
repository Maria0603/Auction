package model;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
  private String conversationContent;
  private List<Package> packages;

  public Conversation() {
    packages = new ArrayList<>();
  }

  public void addPackage(Package pack) {
    packages.add(pack);
    conversationContent = conversationContent + '\n' + pack.toString();
  }

  public String getConversationContent() {
    return conversationContent;
  }
}
