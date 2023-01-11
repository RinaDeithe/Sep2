package test.client.model.basket;

import client.model.basket.BasketManager;
import client.model.basket.BasketModel;
import client.model.shoe.ModelManager;
import client.networking.RMIClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.model.Product;
import shared.model.Shoe;
import shared.util.Client;
import shared.util.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketManagerTest
{
  private BasketModel basketModel;
  private ModelManager shoeModel;
  private Server server;

  //Arrange
  @BeforeEach public void createTestEnvironment()
      throws AlreadyBoundException, RemoteException
  {
    Client client = new RMIClient(8792);
    basketModel = new BasketManager(client);
    shoeModel = new ModelManager(client);
  }

  @AfterEach public void stopTestEnvironment() throws RemoteException
  {
    //server.stopServer();
  }

  @Test void sellBasket() throws RemoteException
  {
    //Arrange
    ArrayList<Product> basket = new ArrayList<>();
    basket = shoeModel.getProductList("S0002");

    for (int i = 0; i < basket.size(); i++)
    {
      basketModel.addToBasket(basket.get(i));
    }

    //Act
    basketModel.sellBasket();
    //Assert
    assertEquals(0, shoeModel.getProductList("S0002").size());
  }

  @Test void returnBasket() throws RemoteException
  {
    //Arrange
    ArrayList<Product> basket = new ArrayList<>();
    basket = shoeModel.getProductList("S0002");

    for (int i = 0; i < basket.size(); i++)
    {
      basketModel.addToBasket(basket.get(i));
    }

    //Act
    basketModel.returnBasket();
    //Assert
    assertEquals(8, shoeModel.getProductList("S0002").size());
  }

  @Test void getBasket() throws RemoteException
  {
    //Arrange
    //Act
    basketModel.addToBasket(new Shoe(250, "A21","S0043", 'f',39));
    basketModel.addToBasket(new Shoe(250, "A21","S0043", 'f',40));
    basketModel.addToBasket(new Shoe(250, "A21","S0043", 'f',41));
    basketModel.addToBasket(new Shoe(250, "A21","S0043", 'f',38));
    //Assert
    assertEquals(4, basketModel.getBasket().size());
  }

  @Test void addToBasket() throws RemoteException
  {
    //Arrange
    //Act
    basketModel.addToBasket(new Shoe(250, "A21","S0043", 'f',39));
    basketModel.addToBasket(new Shoe(250, "A21","S0043", 'f',39));

    //Assert
    assertEquals(2, basketModel.getBasket().size());
  }

}