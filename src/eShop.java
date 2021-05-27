import FileStroage.FileStorage;
import Product.Product;
import user.Customer;
import user.Trader;
import user.User;

import java.util.TreeSet;

public class eShop {
  private static int uniqueUserId = 10000000;
    private void initSystemFiles() {
      ProductCSVInit initProduct = new ProductCSVInit("e-commerce-samples.csv");
      TreeSet<Product> tree = initProduct.getProductTree();
      TreeSet<User> utree = new TreeSet<>();

      FileStorage<Product> productFileCreater = new FileStorage<Product>("ProductFile.txt");
      FileStorage<User> userFileCreater = new FileStorage<User>("UserFile.txt");

      utree.add(new Customer(uniqueUserId++,"111111"));

      var iter = tree.iterator();
      while (iter.hasNext()) {
        var p = iter.next();
        utree.add(new Trader(uniqueUserId++,"111111"));
      }

      productFileCreater.writeToFile(tree);
      userFileCreater.writeToFile(utree);
    }

    public void openShop(){
      initSystemFiles();
    }
}
