package client.views.search;

import client.core.ViewHandler;
import client.model.request.RequestModel;
import client.model.shoe.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.model.Product;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SearchViewModel
{
  private final Model model;
  private final RequestModel requestModel;
  private SimpleStringProperty messageLabelString;

  public SearchViewModel(Model model, RequestModel requestModel)
  {
    this.requestModel = requestModel;
    this.model = model;
    messageLabelString = new SimpleStringProperty();
  }

  ObservableList<Product> getProducts(String productID) throws RemoteException
  {
    try {
      ObservableList<Product> list = FXCollections.observableArrayList();
      ArrayList<Product> prodList = new ArrayList<>(
          model.getProductList(productID));

      list.addAll(prodList);
      return list;
    }
    catch (RemoteException e)
    {
      messageLabelString.setValue("Server down. Good luck");
      return null;
    }
  }

  public void onBack()
  {
    ViewHandler.getInstance().openStart();
  }

  public void onRequest(Product product)
  {
    requestModel.addRequest(product);
  }

  public SimpleStringProperty getMessageLabelString()
  {
    return messageLabelString;
  }
}
