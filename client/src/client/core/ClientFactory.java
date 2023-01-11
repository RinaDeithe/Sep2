package client.core;

import client.networking.RMIClient;
import shared.util.Client;

import java.rmi.RemoteException;

public class ClientFactory
{
  private static final ClientFactory instance = new ClientFactory();
    private volatile Client client;


  private ClientFactory(){
  }

  public static ClientFactory getInstance(){
    return instance;
  }

  public Client getClient()
  {
    if (client == null)
    {
      synchronized (this)
      {
        if (client == null)
        {
          try
          {
            client = new RMIClient(1337);
          }
          catch (RemoteException e)
          {
            e.printStackTrace();
          }
        }
      }
    }
    return client;
  }

}
