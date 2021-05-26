package user;

import Product.Order;
import Product.Product;
import com.sun.source.tree.Tree;

import java.util.*;

public class Trader extends User{
  public Trader(Integer userId, String password) {
    super(userId, password,UserRole.TRADER);
  }

  public void receiveOrder(Order order) {
    receivedOrderQueue.offer(order);
  }

  public void addProductToShop(Product product) {
    myShop.add(product);
  }

  public void removeProductFromShop(Product product) {
    myShop.remove(product);
  }

  public Product getProduct(String productName) {
    for (Product p:myShop) {
      if (p.getName().equals(productName))
        return p;
    }
    return null;
  }

  private Queue<Order> receivedOrderQueue = new ArrayDeque<Order>();

  private ArrayList<Product> myShop;
}
