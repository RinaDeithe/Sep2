package test.client.model.request;

import client.model.request.RequestManager;
import client.model.request.RequestModel;
import client.networking.RMIClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.model.Shoe;
import shared.util.Client;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestManagerTest
{
  private RequestModel requestModel;

  //Arrange
  @BeforeEach public void createTestEnvironment()
      throws AlreadyBoundException, RemoteException
  {
    Client client = new RMIClient(8792);
    requestModel = new RequestManager(client);

    createRequest();
  }

  private void createRequest()
  {
    requestModel.addRequest(new Shoe(250, "A21","S0043", 'f',39));
    requestModel.addRequest(new Shoe(250, "A21","S0043", 'f',40));
    requestModel.addRequest(new Shoe(250, "A21","S0043", 'f',38));
    requestModel.addRequest(new Shoe(250, "A21","S0043", 'f',37));
  }

  @AfterEach public void stopTestEnvironment() throws RemoteException
  {
    //server.stopServer();
  }

  @Test void getRequests()
  {
    //Assert
    assertEquals(4, requestModel.getRequests().size());
  }

  @Test void addRequests()
  {
    requestModel.addRequest(new Shoe(250, "A21","S0043", 'f',39));
    requestModel.addRequest(new Shoe(250, "A21","S0043", 'f',39));


    //Assert
    assertEquals(6, requestModel.getRequests().size());
  }

  @Test void acceptRequest()
  {
    //Arrange
    //Act
    try
    {
      requestModel.acceptRequest(new Shoe(250, "A21","S0043", 'f',39));
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    //Assert
    assertEquals(3, requestModel.getRequests().size());
  }
}