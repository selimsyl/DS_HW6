import Product.Product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class ProductCSVInit {

  private TreeSet<Product> productTree = new TreeSet<>();
  private String ProductFilePath;

  public ProductCSVInit(String filePathToCreateFrom) {
    ProductFilePath = filePathToCreateFrom;
    readProductsFromCSVFile();
    System.out.println("end of ProductCSVInit()");
  }

  public TreeSet<Product> getProductTree() {
    return productTree;
  }

  private void readProductsFromCSVFile() {
    String row;
    try {
      BufferedReader csvReader = new BufferedReader(new FileReader("e-commerce-samples.csv"));
      row = csvReader.readLine();//read coloumn names
      System.out.println(row);
      while ((row = csvReader.readLine()) != null) {
        System.out.println(row);
        row = csvReader.readLine();
        productTree.add(getProductFromFileRowData(row));
      }
      csvReader.close();
    }
    catch (FileNotFoundException f) {
      System.out.println("CSV dosyasi bulunamadi");
    }
    catch (IOException io) {
      System.out.println("CSV dosyasindan product verilerini okurken exception olustu");
    }
  }
  private Product getProductFromFileRowData(String rowData) {
    String[] productDataRow = rowData.split(";");//id,name,category,price,discountedPrice,description,trader
    int price,disCounterdPrice;
    try {
      price = Integer.parseInt(productDataRow[3]);
    } catch (Exception e) { price = 0; }
    try {
      disCounterdPrice = Integer.parseInt(productDataRow[4]);
    } catch (Exception e) {disCounterdPrice = 0;}

    return new Product(productDataRow[0],
            productDataRow[1],
            productDataRow[2],
            price,
            disCounterdPrice,
            productDataRow[5],
            productDataRow[6]);
  }
}
