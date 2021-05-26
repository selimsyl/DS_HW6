package user;

import java.util.*;

import FileStroage.FileStorage;
import Product.Order;
import Product.Product;

public class Customer extends User{
  public enum SortType {PRICE,DISCOUNT_PERCENT,NAME};

  public Customer(String userId, String password) {
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
    put(SortType.PRICE,compPrice);
    put(SortType.NAME,compName);
    put(SortType.DISCOUNT_PERCENT,compDiscountPercent);
  }};

  private final Comparator<Product> compPrice = (p1, p2) -> {
    return p1.getPrice().compareTo(p2.getPrice());
  };
  private final Comparator<Product> compDiscountPercent = (p1, p2) -> {
    return p1.getDiscountedPrice().compareTo(p2.getDiscountedPrice());
  };
  private final Comparator<Product> compName = (p1, p2) -> {
    return p1.getName().compareTo(p2.getName());
  };

}
