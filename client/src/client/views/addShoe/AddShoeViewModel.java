package client.views.addShoe;

import client.core.ViewHandler;
import client.model.shoe.Model;
import javafx.beans.property.SimpleStringProperty;

import java.rmi.RemoteException;

public class AddShoeViewModel
{

  private final Model model;
  private SimpleStringProperty messageLabel;

  public AddShoeViewModel(Model model)
  {
    messageLabel = new SimpleStringProperty();
    this.model = model;
  }

  public void onBack()
  {
    ViewHandler.getInstance().openStart();
  }

  public void onAdd(String location, String price, String id, String sex,
      String size) throws RemoteException
  {

    try
    {
      if (model.addProduct(location, Double.parseDouble(price), id, sex.charAt(0),
        Integer.parseInt(size)))
      {
        messageLabel.setValue("Shoe added succesfully");
      } else {
        messageLabel.setValue("Shoe couldnt be added");
      }

    }
    catch (NumberFormatException e)
    {
      e.printStackTrace();
      messageLabel.setValue("Size or Price invalid");
    }
    catch (RemoteException e)
    {
      messageLabel.setValue("Server down. Good luck");
    }
  }

  public void onAdd(String id, String size) throws RemoteException
  {

    try
    {
      if (model.addProduct("", 0, id, 'l', Integer.parseInt(size)))
      {
        messageLabel.setValue("Shoe added succesfully");
      } else {
        messageLabel.setValue("Shoe couldnt be added");
      }

    }
    catch (NumberFormatException e)
    {
      messageLabel.setValue("Shoe invalid");
    }
    catch (RemoteException e)
    {
      messageLabel.setValue("Server down. Good luck");
    }
  }

  public SimpleStringProperty getMessageLabel()
  {
    return messageLabel;
  }
}

