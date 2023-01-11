package client.views.addShoe;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;

public class AddShoeController implements ViewController
{

  private ViewHandler viewHandler;
  private AddShoeViewModel addShoeVM;

  @FXML private TextField locationInput;

  @FXML private Label messageLabel;

  @FXML private TextField priceInput;

  @FXML private TextField productNumberInput;

  @FXML private TextField sexInput;

  @FXML private TextField sizeInput;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    viewHandler = vh;
    addShoeVM = vmf.getAddShoeVM();
    messageLabel.textProperty().bind(addShoeVM.getMessageLabel());

  }

  @FXML void onAdd() throws RemoteException
  {
    if (locationInput.getText().isEmpty() || sexInput.getText().isEmpty()
        || priceInput.getText().isEmpty())
    {
      addShoeVM.onAdd(productNumberInput.getText(), sizeInput.getText());
    }
    else
    {
      addShoeVM.onAdd(locationInput.getText(), priceInput.getText(),
          productNumberInput.getText(), sexInput.getText(),
          sizeInput.getText());
    }
  }

  @FXML void onBack()
  {
    addShoeVM.onBack();
  }
}
