package client.views.sellAndReturn;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.model.Product;

import java.rmi.RemoteException;

public class SellAndReturnController implements ViewController
{
  @FXML private TableColumn<Product, String> columnID;

  @FXML private TableColumn<Product, Double> columnPrice;

  @FXML private TableColumn<Product, Integer> columnSize;

  @FXML private TableView<Product> searchTable;

  private ViewHandler viewHandler;
  private SellAndReturnViewModel sellAndReturnVM;

  @FXML private TextField idTextField;

  @FXML private TextField sizeTextField;

  @FXML private Label messageLabel;

  @FXML private Label totalLabel;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
    columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    columnSize.setCellValueFactory(new PropertyValueFactory<>("size"));

    viewHandler = vh;
    sellAndReturnVM = vmf.getSellAndReturnVM();

    messageLabel.textProperty().bind(sellAndReturnVM.getMessageLabelString());
    totalLabel.textProperty().bind(sellAndReturnVM.getTotalLabelString());

    searchTable.setItems(sellAndReturnVM.getList());

  }

  @FXML void onBack()
  {
    sellAndReturnVM.onBack();
  }

  @FXML void onAdd() throws RemoteException
  {
    sellAndReturnVM.onAdd(idTextField.getText(), sizeTextField.getText());
  }

  @FXML void onReturn()
  {
    sellAndReturnVM.onReturn();
  }

  @FXML void onSell()
  {
    sellAndReturnVM.onSell();
  }

}
