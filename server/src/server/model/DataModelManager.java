package server.model;

import server.model.database.Database;
import server.model.database.DatabaseManager;
import shared.model.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class DataModelManager implements DataModelInterface
{

  private final Database dbmanager;
  private final RequestHandler requestHandler;
  private final PropertyChangeSupport support;

  public DataModelManager()
  {
    dbmanager = new DatabaseManager();
    requestHandler = new RequestHandler();
    support = new PropertyChangeSupport(this);

    requestHandler.addListener("RequestChange", this::onRequestChange);

  }

  private void onRequestChange(PropertyChangeEvent event)
  {
    System.out.println("Datamodel sending change");
    support.firePropertyChange(event);
  }

  @Override public ArrayList<Product> getProductList(String productID)
  {
    return dbmanager.getProductList(productID);
  }

  @Override public ArrayList<Product> getProductList(String productID, int size)
  {
    return dbmanager.getProductList(productID, size);
  }

  @Override public boolean addProduct(Product product)
  {
    return dbmanager.addProduct(product);
  }

  @Override public void processTransaction(Boolean isReturn, ArrayList<Product> productList)
  {
    dbmanager.processTransaction(isReturn, productList);
  }

  @Override
  public void createDatabaseConnection() {
    dbmanager.createDatabaseConnection();
  }

  public ArrayList<Product> getRequests()
  {
    return requestHandler.getRequests();
  }

  public void removeRequest(Product product)
  {
    requestHandler.removeRequest(product);
  }

  public void addRequest(Product product)
  {
    requestHandler.addRequest(product);
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
