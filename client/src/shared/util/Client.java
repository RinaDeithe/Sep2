package shared.util;

import shared.model.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Client extends Remote, Subject
{
  ArrayList<Product> getProductList(String productID) throws RemoteException;
  ArrayList<Product> getProductList(String productID, int size)
      throws RemoteException;

  void processTransaction(Boolean isReturn, ArrayList<Product> products)
      throws RemoteException;

  boolean addProduct(Product product) throws RemoteException;

  ArrayList<Product> getRequests() throws RemoteException;
  void addRequest(Product product) throws RemoteException;
  void removeRequest(Product product) throws RemoteException;

  void onRequestChange(ArrayList<Product> requests) throws RemoteException;

}
