package server.networking;

import server.model.DataModelInterface;
import server.model.DataModelManager;
import shared.model.Product;
import shared.util.Client;
import shared.util.Server;

import java.beans.PropertyChangeEvent;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIServerImpl implements Server
{
  private final DataModelInterface dataManager;
  private final Registry registry;
  private final ArrayList<Client> clients;

  public RMIServerImpl(DataModelManager dataModel, int port)
      throws RemoteException, AlreadyBoundException
  {
    registry = LocateRegistry.createRegistry(port);
    this.dataManager = dataModel;
    UnicastRemoteObject.exportObject(this, 0);
    registry.bind("SkoServer", this);
    clients = new ArrayList<>();

    dataManager.createDatabaseConnection();

    dataModel.addListener("RequestChange", this::onRequestChange);
  }

  private void onRequestChange(PropertyChangeEvent event)
  {
    System.out.println("Server sending to client");
    for (Client client : clients) {

      try {

        client.onRequestChange((ArrayList<Product>) event.getNewValue());

      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }

  @Override public ArrayList<Product> getProductList(String productID)
      throws RemoteException
  {
    return dataManager.getProductList(productID);
  }

  @Override public ArrayList<Product> getProductList(String productID, int size)
      throws RemoteException
  {
    return dataManager.getProductList(productID, size);
  }

  @Override public void processTransaction(Boolean isReturn, ArrayList<Product> products)
      throws RemoteException
  {
    dataManager.processTransaction(isReturn, products);
  }

  @Override public boolean addProduct(Product product) throws RemoteException
  {
    System.out.println("Calling addProduct() on DataManager");
    return dataManager.addProduct(product);
  }

  public ArrayList<Product> getRequests()
  {
    return dataManager.getRequests();
  }

  public void removeRequest(Product product)
  {
    dataManager.removeRequest(product);
  }

  @Override public void register(Client client) throws RemoteException
  {
    clients.add(client);
  }

  public void addRequest(Product product)
  {
    dataManager.addRequest(product);
  }
}
