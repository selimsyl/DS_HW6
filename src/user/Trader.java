package user;

import Product.Order;

import java.util.ArrayList;
import java.util.Queue;

public class Trader extends User{
  public Trader(String userId, String password) {
    super(userId, password,UserRole.TRADER);
  }

  public void receiveOrder(Order order) {
    receivedOrderQueue.add(order);
  }

  private ArrayList<Order> receivedOrderQueue;
}
