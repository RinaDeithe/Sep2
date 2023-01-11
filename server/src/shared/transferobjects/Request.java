package shared.transferobjects;

import shared.model.Product;

import java.io.Serializable;

public class Request implements Serializable
{
  private Product product;

  public Request(Product product)
  {
    this.product = product;
  }

  public void setProduct(Product product)
  {
    this.product = product;
  }

  public Product getProduct()
  {
    return product;
  }
}
