import FileStroage.FileStorage;
import Product.Product;
import Product.Order;
import user.Customer;
import user.Trader;

import java.util.Comparator;

public class Main {

  public static void main(String[] args) {
    eShop shop = new eShop();
    Menu menu = new Menu();
    shop.openShop();
    menu.run();
    System.out.println("hello havigi");
  }
}
