package client.model.shoe;

import shared.model.Product;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model
{
  ArrayList<Product> getProductList(String productID) throws RemoteException;
  boolean addProduct(String location, double price, String id, char sex, int size)
      throws RemoteException;
  void addProduct(String id, int size) throws RemoteException;
  Product getShoe(String productID, int size) throws RemoteException;
}
