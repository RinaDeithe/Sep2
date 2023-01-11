package server.model;

import shared.model.Product;
import shared.util.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class RequestHandler implements Subject
{
  private final ArrayList<Product> requests;
  private final PropertyChangeSupport support;

  public RequestHandler() {
    requests = new ArrayList<>();
    support = new PropertyChangeSupport(this);
  }

  public ArrayList<Product> getRequests()
  {
    return requests;
  }

  public void removeRequest(Product product)
  {
    requests.remove(product);
    System.out.println(requests);
    support.firePropertyChange("RequestChange", null, requests);
  }

  public void addRequest(Product product)
  {
    requests.add(product);
    System.out.println("[Request] Requesthandler firing change");
    support.firePropertyChange("RequestChange", null, requests);

  }

  @Override
  public void addListener(String eventName, PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override
  public void removeListener(String eventName, PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }
}
