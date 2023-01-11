package client.model.request;

import shared.model.Product;
import shared.util.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class RequestManager implements RequestModel
{
  private final RequestHandler requestHandler;
  private final PropertyChangeSupport support;

  public RequestManager(Client client)
  {
    requestHandler = new RequestHandler(client);
    support = new PropertyChangeSupport(this);
    requestHandler.addListener("RequestChange", this::onRequestChange);
  }

  private void onRequestChange(PropertyChangeEvent event)
  {
    support.firePropertyChange(event);
  }

  @Override public ArrayList<Product> getRequests()
  {
    return requestHandler.getRequests();
  }

  @Override public void addRequest(Product product)
  {
    requestHandler.addRequest(product);
  }

  @Override public void acceptRequest(Product product) throws RemoteException
  {
    requestHandler.removeRequest(product);
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
