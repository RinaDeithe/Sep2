package client.model.basket;

import shared.model.Product;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface BasketModel
{
  void addToBasket(Product product);
  void sellBasket() throws RemoteException;
  void returnBasket() throws RemoteException;
  ArrayList<Product> getBasket();
}
