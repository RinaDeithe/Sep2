package client.model.basket;

import shared.model.Product;
import shared.util.Client;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class BasketManager implements BasketModel
{
  private final Basket basket;

  public BasketManager(Client client)
  {
    basket = new Basket(client);
  }

  @Override public void sellBasket() throws RemoteException
  {
    basket.sellBasket();
  }

  @Override public void returnBasket() throws RemoteException
  {
    basket.returnBasket();
  }

  @Override public void addToBasket(Product product)
  {
    basket.addToBasket(product);
  }

  @Override public ArrayList<Product> getBasket()
  {
    return basket.getBasketContent();
  }

}
