package client.views.start;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StartController implements ViewController
{

  private ViewHandler viewHandler = ViewHandler.getInstance();
  private StartViewModel startVM;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    viewHandler = vh;
    startVM = vmf.getStartVM();
  }

  @FXML void onAddShoe(ActionEvent event)
  {
    startVM.onAddShoe();
  }

  @FXML void onFindShoe(ActionEvent event)
  {
    startVM.onFindShoe();
  }

  @FXML void onSellReturnShoe(ActionEvent event)
  {
    startVM.onSellReturnShoe();
  }

  @FXML void onInventory(ActionEvent event)
  {
    startVM.onInventory();
  }
}
