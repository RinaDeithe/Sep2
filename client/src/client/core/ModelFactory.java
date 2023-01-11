package client.core;

import client.model.basket.BasketManager;
import client.model.basket.BasketModel;
import client.model.request.RequestManager;
import client.model.request.RequestModel;
import client.model.shoe.Model;
import client.model.shoe.ModelManager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ModelFactory
{
  private static ModelFactory instance;
  private volatile Model model;
  private volatile BasketModel basketModel;
  private volatile RequestModel requestModel;
  private static final Lock lock = new ReentrantLock();

  private ModelFactory()
  {
  }

  public static ModelFactory getInstance()
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new ModelFactory();
        }
      }
    }
    return instance;
  }

  public Model getModel()
  {
    if (model == null)
    {
      synchronized (this)
      {
        if (model == null)
        {
          model = new ModelManager(
                  ClientFactory.getInstance().getClient());
        }
      }
    }
    return model;
  }

  public BasketModel getBasketModel()
  {
    if (basketModel == null)
    {
      synchronized (this)
      {
        if (basketModel == null)
        {
          basketModel = new BasketManager(
              ClientFactory.getInstance().getClient());
        }
      }
    }
    return basketModel;
  }

  public RequestModel getRequestModel()
  {
    if (requestModel == null)
    {
      synchronized (this)
      {
        if (requestModel == null)
        {
          requestModel = new RequestManager(
              ClientFactory.getInstance().getClient());
        }
      }
    }
    return requestModel;
  }
}
