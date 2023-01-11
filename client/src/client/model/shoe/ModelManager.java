package client.model.shoe;

import client.model.basket.Basket;
import shared.model.Product;
import shared.model.Shoe;
import shared.util.Client;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelManager implements Model
{

  private final Client client;
  private final Basket basket;

  public ModelManager(Client client)
  {
    this.client = client;
    basket = new Basket(client);
  }

  @Override public ArrayList<Product> getProductList(String productID)
      throws RemoteException
  {

    return client.getProductList(productID);
  }

  @Override public boolean addProduct(String location, double price, String id,
      char sex, int size) throws RemoteException
  {
    return client.addProduct(new Shoe(price, location, id, sex, size));
  }

  @Override public void addProduct(String id, int size) throws RemoteException
  {
    client.addProduct(new Shoe(0, "", id, 'l', size));
  }

  @Override public Product getShoe(String id, int size) throws RemoteException
  {
    try
    {
      return client.getProductList(id, size).get(0);
    }
    catch (IndexOutOfBoundsException e)
    {
      throw new IndexOutOfBoundsException();
    }
  }
}
