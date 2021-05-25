package Product;

import java.io.Serializable;

public class Order implements Serializable {

  public Order(int orderAmount, Product product) {
    this.orderAmount = orderAmount;
    this.product = product;
  }

  private int orderAmount;

  private Product product;
}
