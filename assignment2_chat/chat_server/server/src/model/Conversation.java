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

  public Package addCommand(String sender, String request, UserList list) {
    PackageFactory factory;
    if (request.startsWith("/")){
      factory = new CommandPackageFactory(this);
      Package newPackage = factory.getPackage(sender, request, list);
      packages.add(newPackage);
      conversationContent += newPackage.toString()+'\n';
      return newPackage;
    } else {
      throw new IllegalArgumentException("Command should start with '/'");
    }
  }

  public Package addMessage(String sender, String request, UserList list){
    if (request.startsWith("/")){
      throw new IllegalArgumentException("Message cannot start with '/'");
    } else {
      MessagePackageFactory messagePackageFactory = new MessagePackageFactory(this);
      Package newPackage = messagePackageFactory.getPackage(sender, request, list);
      packages.add(newPackage);
      conversationContent += newPackage.toString()+'\n';
      return newPackage;
    }
  }


  public String getConversationContent() {
    return conversationContent;
  }

  public int getNumberOdMessages(){
    return packages.size();
  }
}