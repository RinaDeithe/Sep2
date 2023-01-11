package server;

import server.model.DataModelManager;
import server.networking.RMIServerImpl;
import shared.util.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class ServerMain
{
  public static void main(String[] args)
      throws AlreadyBoundException, RemoteException
  {
    Server server = new RMIServerImpl(new DataModelManager(), 1337);
  }
}
