package client.model.request;

import shared.model.Product;
import shared.util.Client;
import shared.util.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class RequestHandler implements Subject
{
  private final Client client;
  private final PropertyChangeSupport support;

  public RequestHandler(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    try
    {
      client.addListener("RequestChange", this::onRequestChange);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  private void onRequestChange(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }

  public void removeRequest(Product product) throws RemoteException
  {
    client.removeRequest(product);
  }

  public ArrayList<Product> getRequests()
  {
    try
    {
      return client.getRequests();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  public void addRequest(Product product)
  {
    try
    {
      client.addRequest(product);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }
}
