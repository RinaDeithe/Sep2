package client.views.sellAndReturn;

import client.core.ViewHandler;
import client.model.basket.BasketModel;
import client.model.shoe.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.model.Product;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class SellAndReturnViewModel
{

  private final Model model;
  private final BasketModel basketModel;
  private final SimpleStringProperty messageLabelString;
  private final SimpleStringProperty totalLabelString;
  private ObservableList<Product> list;

  public SellAndReturnViewModel(Model model, BasketModel basketModel)
  {
    this.basketModel = basketModel;
    this.model = model;
    messageLabelString = new SimpleStringProperty();
    totalLabelString = new SimpleStringProperty();
    list = FXCollections.observableArrayList();
  }

  public void onBack()
  {
    ViewHandler.getInstance().openStart();
  }

  public void onAdd(String id, String size)
      throws RemoteException
  {
    if (id.isEmpty() && size.isEmpty())
    {
      messageLabelString.setValue("Fill out things please");
    }

    try {
      Product shoe = model.getShoe(id, Integer.parseInt(size));
      if (shoe != null)
      {
        basketModel.addToBasket(shoe);
        updateList();
      } else
      {
        messageLabelString.setValue("Shoe does not exist");
      }
    } catch (NumberFormatException e)
    {
      messageLabelString.setValue("Size is invalid");
    }
  }

  public void onSell()
  {
    try {
      basketModel.sellBasket();
      updateList();
      messageLabelString.setValue("Shoes sold");
    }
    catch (RemoteException e)
    {
      messageLabelString.setValue("Server down. Good luck");
    }


  }

  public void onReturn()
  {
    try
    {
      basketModel.returnBasket();
      updateList();
      messageLabelString.setValue("Shoes returned");
    }
    catch (RemoteException e)
    {
      messageLabelString.setValue("Server down. Good luck");
    }

  }

  public SimpleStringProperty getMessageLabelString()
  {
    return messageLabelString;
  }

  public SimpleStringProperty getTotalLabelString()
  {
    return totalLabelString;
  }

  public void updateList()
  {
    ArrayList<Product> basket = basketModel.getBasket();
    double total = 0;

    list.removeAll(list);
    list.addAll(basket);
    messageLabelString.setValue("");

    for (int i = 0; i < basket.size(); i++)
    {
      total += basket.get(i).getPrice();
    }

    totalLabelString.setValue(total + " ,-");
  }

  public ObservableList<Product> getList()
  {
    return list;
  }
}
