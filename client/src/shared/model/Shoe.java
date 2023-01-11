package shared.model;

import java.io.Serializable;

public record Shoe(double price, String location, String id, char sex,
                   int size) implements Product, Serializable {

  public String getSex() {
    return Character.toString(sex);
  }

  public Integer getSize() {
    return size;
  }

  @Override
  public Double getPrice() {
    return price;
  }

  @Override
  public String getLocation() {
    return location;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Shoe{" + "price=" + price + ", location='" + location + '\''
            + ", size=" + size + ", sex=" + sex + ", id='" + id + '\'' + '}';
  }
}
