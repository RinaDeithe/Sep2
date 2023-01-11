package test.client.model.shoe;

import client.model.shoe.ModelManager;
import client.networking.RMIClient;
import org.junit.jupiter.api.*;
import shared.model.Product;
import shared.model.Shoe;
import shared.util.Client;
import shared.util.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) class ModelManagerTest
{
  private ModelManager model;
  private Server server;

  //Arrange
  @BeforeEach public void createTestEnvironment()
      throws AlreadyBoundException, RemoteException
  {
    Client client = new RMIClient(8792);
    model = new ModelManager(client);
  }

  @AfterEach public void stopTestEnvironment() throws RemoteException
  {
    //server.stopServer();
  }

  @Test public void getProductListDoesNotReturnEmptyWithValidID()
      throws RemoteException
  {
    //Act
    ArrayList<Product> modelTest;
    modelTest = model.getProductList("S0002");
    //Assert
    assertEquals(4, modelTest.size());

  }

  @Test void addProduct() throws RemoteException
  {
    //Act
    model.addProduct("S0002", 23);

    //Assert
    assertEquals(5, model.getProductList("S0002").size());

  }

  @Test void getShoeReturnsCorrectID() throws RemoteException
  {
    //Act
    Product shoe = model.getShoe("S0002", 35);
    //Assert
    assertEquals(shoe.getId(), "S0002");
  }

  @Test void getShoeReturnsCorrectSize() throws RemoteException
  {
    //Act
    Shoe shoe = (Shoe) model.getShoe("S0002", 35);
    //Assert
    assertEquals(shoe.getSize(), 35);
  }
}