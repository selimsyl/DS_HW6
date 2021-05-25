import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.TreeVisitor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class ProductFileStorage<E> {
  private TreeSet<Product> productTree;
  private String ProductFilePath;

  public ProductFileStorage(String filePathToCreateFrom) {
    ProductFilePath = filePathToCreateFrom;

  }

  public ProductFileStorage(Product[] products) {

  }


  private void readProductsFromCSVFile() {
    String row;
    try {
      BufferedReader csvReader = new BufferedReader(new FileReader("e-commerce-samples.csv"));
      row = csvReader.readLine();//read coloumn names
      System.out.println(row);
      while ((row = csvReader.readLine()) != null) {
        row = csvReader.readLine();
      }
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
    return new Product(productDataRow[0],
                                  productDataRow[1],
                                  productDataRow[2],
                                  Integer.parseInt(productDataRow[3]),
                                  Integer.parseInt(productDataRow[4]),
                                  productDataRow[5],
                                  productDataRow[6]);
  }
}
