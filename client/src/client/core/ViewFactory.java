package client.core;

import client.views.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.HashMap;
import java.util.Map;

public class ViewFactory
{
  private static Stage stage;
  private static final Map<String, Scene> scenes;

  private ViewFactory()
  {
  }

  static
  {
    scenes = new HashMap<>();
  }

  public static void init(Stage thisStage) throws IOException
  {
    stage = thisStage;
    openView("search");
    openView("addShoe");
    openView("sellAndReturn");
    openView("start");
    openView("inventory");
  }

  private static void openView(String viewToOpen)
  {
    Scene scene = null;
    if (viewToOpen.equals("search"))
    {
      try
      {
        Parent root = loadFXML(
            "/client/views/" + viewToOpen + "/" + viewToOpen + ".fxml");
        stage.setTitle(viewToOpen);
        scene = new Scene(root);
      }
      catch (IOException | NotBoundException e)
      {
        e.printStackTrace();
      }

    }

    else if (viewToOpen.equals("addShoe"))
    {
      try
      {
        Parent root = loadFXML(
            "/client/views/" + viewToOpen + "/" + viewToOpen + ".fxml");

        scene = new Scene(root);
        stage.setTitle(viewToOpen);
      }
      catch (IOException | NotBoundException e)
      {
        e.printStackTrace();
      }
    }
    else if (viewToOpen.equals("sellAndReturn"))
    {
      try
      {
        Parent root = loadFXML(
            "/client/views/" + viewToOpen + "/" + viewToOpen + ".fxml");

        scene = new Scene(root);
        stage.setTitle(viewToOpen);
      }
      catch (IOException | NotBoundException e)
      {
        e.printStackTrace();
      }
    }
    else if (viewToOpen.equals("start"))
    {
      try
      {
        Parent root = loadFXML(
            "/client/views/" + viewToOpen + "/" + viewToOpen + ".fxml");

        scene = new Scene(root);
        stage.setTitle(viewToOpen);
      }
      catch (IOException | NotBoundException e)
      {
        e.printStackTrace();
      }
    }
    else if (viewToOpen.equals("other"))
    {
      try
      {
        Parent root = loadFXML(
            "/client/views/" + viewToOpen + "/" + viewToOpen + ".fxml");

        scene = new Scene(root);
        stage.setTitle(viewToOpen);
      }
      catch (IOException | NotBoundException e)
      {
        e.printStackTrace();
      }
    }
    else if (viewToOpen.equals("inventory"))
    {
      try
      {
        Parent root = loadFXML(
            "/client/views/" + viewToOpen + "/" + viewToOpen + ".fxml");

        scene = new Scene(root);
        stage.setTitle(viewToOpen);
      }
      catch (IOException | NotBoundException e)
      {
        e.printStackTrace();
      }
    }
    scenes.put(viewToOpen, scene);
  }

  private static Parent loadFXML(String path)
      throws IOException, NotBoundException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(ViewFactory.class.getResource(path));
    Parent root = loader.load();

    ViewController ctrl = loader.getController();
    ctrl.init(ViewHandler.getInstance(), ViewModelFactory.getInstance());
    return root;
  }

  public static Scene getScene(String sceneName)
  {
    return scenes.get(sceneName);
  }

}
