package client.model.basket;

import shared.model.Product;
import shared.util.Client;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Basket
{
  private ArrayList<Product> basketContent;
  private final Client client;

  public Basket(Client client)
  {
    this.client = client;
    basketContent = new ArrayList<>();
  }

  public void addToBasket(Product product)
  {
    basketContent.add(product);
  }

  public ArrayList<Product> getBasketContent()
  {
    return basketContent;
  }

  public void sellBasket() throws RemoteException
  {
    client.processTransaction(false, basketContent);
    basketContent = new ArrayList<>();
  }

  public void returnBasket() throws RemoteException
  {
    client.processTransaction(true, basketContent);
    basketContent = new ArrayList<>();
  }

}
