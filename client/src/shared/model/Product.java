package shared.model;

import java.io.Serializable;

public interface Product extends Serializable
{
  Double getPrice();
  String getLocation();
  String getId();

}

