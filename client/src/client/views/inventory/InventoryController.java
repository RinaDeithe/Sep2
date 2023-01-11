package client.views.inventory;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.model.Product;

public class InventoryController implements ViewController
{
  @FXML private TableColumn<Product, String> columnLocation;
  @FXML private TableColumn<Product, String> columnShoeID;
  @FXML private TableColumn<Product, String> columnSize;
  @FXML private TableView<Product> table;
  private InventoryViewModel inventoryVM;
  private ViewHandler viewHandler;

  @FXML private Label messageLabel;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.viewHandler = vh;
    this.inventoryVM = vmf.getInventoryVM();
    columnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
    columnSize.setCellValueFactory(new PropertyValueFactory<>("size"));
    columnShoeID.setCellValueFactory(new PropertyValueFactory<>("id"));
    table.setItems(inventoryVM.showProducts());
    messageLabel.textProperty().bind(inventoryVM.getMessageLabel());
  }

  @FXML void onBack()
  {
    inventoryVM.onBack();
  }

  @FXML void onAccept(ActionEvent event)
  {
    inventoryVM.onAccept(table.getSelectionModel().getSelectedItems().get(0));
  }
}
