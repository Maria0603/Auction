package model;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
  private String conversationContent;
  private List<Package> packages;

  public Conversation() {
    conversationContent="";
    packages = new ArrayList<>();
  }

  public void addPackage(String sender, String request, UserList list) {
    PackageFactory factory = PackageFactorySelector.getFactory(request, this);
    Package newPackage = factory.getPackage(sender, request, list);
    packages.add(newPackage);
    conversationContent += newPackage.toString()+'\n';
  }


  public String getConversationContent() {
    return conversationContent;
  }

  public int getNumberOdMessages(){
    return packages.size();
  }
}
