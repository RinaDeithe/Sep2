package client.views.inventory;

import client.core.ViewHandler;
import client.model.request.RequestModel;
import client.model.shoe.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.model.Product;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class InventoryViewModel
{
  private final Model model;
  private final RequestModel requestModel;
  private final ObservableList<Product> list;
  private SimpleStringProperty messageLabel;

  public InventoryViewModel(Model model, RequestModel requestModel)
  {
    this.model = model;
    this.requestModel = requestModel;
    try
    {
      requestModel.addListener("RequestChange", this::onRequestChange);
    }
    catch (RemoteException e)
    {
      messageLabel.setValue("Server down. Good Luck");
      e.printStackTrace();
    }
    list = FXCollections.observableArrayList();

    messageLabel = new SimpleStringProperty();
  }

  private void onRequestChange(PropertyChangeEvent event)
  {
    list.removeAll(list);
    if(event.getNewValue() != null)
    {
      list.addAll((ArrayList<Product>) event.getNewValue());
    }
  }

  public void onBack()
  {
    ViewHandler.getInstance().openStart();
  }

  public void onAccept(Product product){
    try
    {
      requestModel.acceptRequest(product);
    }
    catch (RemoteException e)
    {
      messageLabel.setValue("Server down. Good luck");
      e.printStackTrace();
    }
  }

  public void onClickInTable(){

  }

  public ObservableList<Product> showProducts()
  {
    return list;
  }

  public SimpleStringProperty getMessageLabel() {
    return messageLabel;
  }
}
