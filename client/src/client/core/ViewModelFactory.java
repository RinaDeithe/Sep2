package client.core;

import client.views.addShoe.AddShoeViewModel;
import client.views.inventory.InventoryViewModel;
import client.views.search.SearchViewModel;
import client.views.sellAndReturn.SellAndReturnViewModel;
import client.views.start.StartViewModel;

public class ViewModelFactory
{
  private static final ViewModelFactory instance = new ViewModelFactory();
  private SearchViewModel searchVM;
  private AddShoeViewModel addShoeVM;
  private SellAndReturnViewModel sellAndReturnVM;
  private StartViewModel startVM;
  private InventoryViewModel inventoryVM;

  private ViewModelFactory()
  {
  }

  public static ViewModelFactory getInstance()
  {
    return instance;
  }

  public SearchViewModel getSearchVM()
  {
    if (searchVM == null)
      searchVM = new SearchViewModel(ModelFactory.getInstance().getModel(),
          ModelFactory.getInstance().getRequestModel());
    return searchVM;
  }

  public AddShoeViewModel getAddShoeVM()
  {
    if (addShoeVM == null)
      addShoeVM = new AddShoeViewModel(ModelFactory.getInstance().getModel());
    return addShoeVM;
  }

  public SellAndReturnViewModel getSellAndReturnVM()
  {
    if (sellAndReturnVM == null)
      sellAndReturnVM = new SellAndReturnViewModel(
          ModelFactory.getInstance().getModel(),
          ModelFactory.getInstance().getBasketModel());
    return sellAndReturnVM;
  }

  public StartViewModel getStartVM()
  {
    if (startVM == null)
      startVM = new StartViewModel(ModelFactory.getInstance().getModel());
    return startVM;
  }

  public InventoryViewModel getInventoryVM()
  {
    if (inventoryVM == null)
      inventoryVM = new InventoryViewModel(
          ModelFactory.getInstance().getModel(),
          ModelFactory.getInstance().getRequestModel());
    return inventoryVM;
  }

}
