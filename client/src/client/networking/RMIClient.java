package client.networking;

import shared.model.Product;
import shared.util.Client;
import shared.util.Server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIClient implements Client
{

  private Server server;
  private final PropertyChangeSupport support;

  public RMIClient(int port) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Registry registry = null;
    support = new PropertyChangeSupport(this);
    try
    {
      registry = LocateRegistry.getRegistry("localhost", port);
      server = (Server) registry.lookup("SkoServer");
      server.register(this);
    }
    catch (NotBoundException | RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public ArrayList<Product> getProductList(String productID)
      throws RemoteException
  {
    return server.getProductList(productID);
  }

  @Override public ArrayList<Product> getProductList(String productID, int size)
      throws RemoteException
  {
    System.out.println(server.getProductList(productID, size));
    return server.getProductList(productID, size);
  }

  @Override public void processTransaction(Boolean isReturn, ArrayList<Product> products)
      throws RemoteException
  {
    server.processTransaction(isReturn, products);
  }

  @Override public boolean addProduct(Product product)
  {
    try
    {
      server.addProduct(product);
      return true;
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      return false;
    }
  }

  public ArrayList<Product> getRequests() throws RemoteException
  {
    return server.getRequests();
  }

  public void removeRequest(Product product) throws RemoteException
  {
    server.removeRequest(product);
  }

  @Override public void onRequestChange(ArrayList<Product> requests)
  {
    System.out.println("client firing to model" + requests);
    support.firePropertyChange("RequestChange", null, requests);
  }

  public void addRequest(Product product) throws RemoteException
  {
    System.out.println("Client calling addRequest() on server");
    server.addRequest(product);
  }

  @Override public void addListener(String eventName,
      PropertyChangeListener listener) throws RemoteException
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener) throws RemoteException
  {
    support.removePropertyChangeListener(eventName, listener);
  }
}
