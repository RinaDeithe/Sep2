package shared.util;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public interface Subject
{
  void addListener (String eventName, PropertyChangeListener listener)
      throws RemoteException;
  void removeListener (String eventName, PropertyChangeListener listener)
      throws RemoteException;
}
