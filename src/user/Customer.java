package user;

import java.io.Serializable;
import java.util.*;

import FileStroage.FileStorage;
import Product.Order;
import Product.Product;

public class Customer extends User implements Serializable {
  public enum SortType {PRICE,DISCOUNT_PERCENT,NAME};

  public Customer(Integer userId, String password) {
    super(userId, password, UserRole.CUSTOMER);
  }

  public boolean makeOrder(Product product) {
    return true;
  }

  public ArrayList<Product> searchProducts(String productName) {
    FileStorage<Product> fileStorage = new FileStorage("Product.txt");
    var productTree = fileStorage.readFromFile();
    var iter = productTree.iterator();
    while(iter.hasNext()) {
      var product = iter.next();
      if (product.getName().contains(productName)) {
        productQueryList.add(iter.next());
      }
    }
    return productQueryList;
  }

  public ArrayList<Product> getProductQueryList() {
    return productQueryList;
  }

  public void sortQueryResults(SortType sortType) {
    productQueryList.sort(sortTypeComparatorMap.get(sortType));
  }

  private ArrayList<Product> productQueryList = new ArrayList<Product>();

//  private LinkedList<Order> orderList;

  private HashMap<SortType, Comparator<Product>> sortTypeComparatorMap = new HashMap<>() {{
    put(SortType.PRICE, new CompareProductByPrice());
    put(SortType.NAME, new CompareProductByDiscount());
    put(SortType.DISCOUNT_PERCENT, new CompareProductByName());
  }};

  private static class CompareProductByPrice implements Comparator<Product>,Serializable {
    @Override
    public int compare(Product o1, Product o2) {
      return o1.getPrice().compareTo(o2.getPrice());
    }
  }
  private static class CompareProductByDiscount implements Comparator<Product>,Serializable {
    @Override
    public int compare(Product o1, Product o2) {
      return o1.getDiscountedPrice().compareTo(o2.getDiscountedPrice());
    }
  }
  private static class CompareProductByName implements Comparator<Product>,Serializable {
    @Override
    public int compare(Product o1, Product o2) {
      return o1.getName().compareTo(o2.getName());
    }
  }
}
