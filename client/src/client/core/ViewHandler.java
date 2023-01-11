package client.core;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private static final ViewHandler instance = new ViewHandler();
  private Stage stage1;

  private ViewHandler()
  {
  }

  public static ViewHandler getInstance()
  {
    return instance;
  }

  public void start() throws IOException
  {
    stage1 = new Stage();
    ViewFactory.init(stage1);
    stage1.centerOnScreen();
    openStart();
  }

  public void openSearch()
  {
    Scene search = ViewFactory.getScene("search");
    stage1.setScene(search);
    stage1.centerOnScreen();
    stage1.show();
    stage1.setOnCloseRequest(e -> Platform.exit());

  }

  public void openAddShoe()
  {
    Scene addShoe = ViewFactory.getScene("addShoe");
    stage1.setScene(addShoe);
    stage1.centerOnScreen();
    stage1.show();
    stage1.setOnCloseRequest(e -> Platform.exit());

  }

  public void openSellAndReturn()
  {
    Scene sellAndReturn = ViewFactory.getScene("sellAndReturn");
    stage1.setScene(sellAndReturn);
    stage1.centerOnScreen();
    stage1.show();
    stage1.setOnCloseRequest(e -> Platform.exit());
  }

  public void openStart()
  {
    Scene start = ViewFactory.getScene("start");
    stage1.setScene(start);
    stage1.centerOnScreen();
    stage1.show();
    stage1.setOnCloseRequest(e -> Platform.exit());
  }

  public void openInventory()
  {
    Scene inventory = ViewFactory.getScene("inventory");
    stage1.setScene(inventory);
    stage1.centerOnScreen();
    stage1.show();
    stage1.setOnCloseRequest(e -> Platform.exit());
  }
}
