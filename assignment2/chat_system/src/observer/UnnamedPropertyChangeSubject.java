package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface UnnamedPropertyChangeSubject
{
  void addListener(PropertyChangeListener listener);
  void removeListener(PropertyChangeListener listener);
}
