package client.views.start;

import client.core.ViewHandler;
import client.model.shoe.Model;

public class StartViewModel
{
  private final Model model;

  public StartViewModel(Model model)
  {
    this.model = model;
  }

  void onAddShoe()
  {
    ViewHandler.getInstance().openAddShoe();
  }

  void onFindShoe()
  {
    ViewHandler.getInstance().openSearch();
  }

  void onSellReturnShoe()
  {
    ViewHandler.getInstance().openSellAndReturn();
  }

  void onInventory()
  {
    ViewHandler.getInstance().openInventory();
  }

}
