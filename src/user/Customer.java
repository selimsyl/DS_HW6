package user;

import java.util.LinkedList;
import Product.Order;

public class Customer extends User{
  public Customer(String userId, String password) {
    super(userId, password, UserRole.CUSTOMER);
  }


  private LinkedList<Order> orderList;
}
