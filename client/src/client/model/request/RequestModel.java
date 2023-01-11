package client.model.request;

import shared.model.Product;
import shared.util.Subject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RequestModel extends Subject
{
  ArrayList<Product> getRequests();
  void addRequest(Product product);
  void acceptRequest(Product product) throws RemoteException;

}
