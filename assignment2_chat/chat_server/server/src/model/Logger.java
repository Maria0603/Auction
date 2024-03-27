package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Logger
{
  private static Logger instance;
  private static final Object lock = new Object();

  private String output;

  private Logger()
  {
    output = "";
  }

  public static Logger getInstance() {
    if (instance == null) {
      synchronized (lock){
        if(instance == null){

          instance = new Logger();
        }
      }
    }
    return instance;
  }
  public void addLog(String text)
  {
    output+=text+'\n';
    addToFile(text +'\n');
  }

  public String getOutput()
  {
    return output;
  }

  public String getSortableDate()
  {
    DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return LocalDateTime.now().format(dtf);
  }

  private void addToFile(String line)
  {
    if (line == null)
    {
      return;
    }
    BufferedWriter out = null;
    try
    {
      String filename= getSortableDate()+".txt";
      out = new BufferedWriter(new FileWriter(filename, true));
      out.write(line + "\n");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        out.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }

}
