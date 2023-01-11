package client.views.search;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.model.Product;

import java.rmi.RemoteException;

public class SearchController implements ViewController
{

  @FXML private TableColumn<Product, String> columnID;

  @FXML private TableColumn<Product, String> columnLocation;

  @FXML private TableColumn<Product, Double> columnPrice;

  @FXML private TableColumn<Product, String> columnSex;

  @FXML private TableColumn<Product, Integer> columnSize;

  @FXML private TextField searchInput;

  @FXML private TableView<Product> searchTable;

  @FXML private Label messageLabel;

  private SearchViewModel searchVM;
  private ViewHandler viewHandler;

  //viewhandler skal ikke oprettes sådan her, da den er statisk, men ved ikke lige hvordan det ellers skal sættes op
  //ViewHandler er sat helt korrekt op her ifht MVVM og JFX (ihvertfald hvordan vi er blevet lært det.)
  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.viewHandler = vh;
    this.searchVM = vmf.getSearchVM();
    searchInput.textProperty().bindBidirectional(new SimpleStringProperty());
    columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
    columnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
    columnSize.setCellValueFactory(new PropertyValueFactory<>("size"));
    columnSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
    columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    messageLabel.textProperty().bind(searchVM.getMessageLabelString());
  }

  @FXML void onSearchButton(ActionEvent event) throws RemoteException
  {
    searchTable.setItems(
        searchVM.getProducts(searchInput.textProperty().getValue()));
  }

  @FXML void onBack()
  {
    searchVM.onBack();
  }

  @FXML void onRequest()
  {
    ObservableList<Product> propertyProduct = searchTable.getSelectionModel()
        .getSelectedItems();
    searchVM.onRequest(propertyProduct.get(0));
  }

}
