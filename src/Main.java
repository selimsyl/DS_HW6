import Product.Product;

import java.io.*;
import java.util.TreeSet;

public class Main {

  public static void main(String[] args) throws IOException {
    String row;
    try {
      BufferedReader csvReader = new BufferedReader(new FileReader("e-commerce-samples.csv"));
//      while ((row = csvReader.readLine()) != null) {
      row = csvReader.readLine();
      System.out.println(row);

      row = csvReader.readLine();
      System.out.println(row);
      String[] data1 = row.split(";");
      System.out.println(data1.length);
      System.out.println(data1[2]);
      var dal  = data1[2].split("\"\"");
      System.out.println(dal.length);

      for (String val:dal) {
        System.out.println(val);
      }

      var resw = dal[1].split(">>");

      for (String val:resw
      ) {
        System.out.println(val.strip());
      }

//      }
      csvReader.close();

      Product p = new Product("13", "selim", "kirko", 152, 26, "selamlar", "adfas");
      Product p1 = new Product("53", "akan", "zirko", 313, 168, "kelamlar", "trator");
      Product p2 = new Product("53", "zakan", "zirko", 313, 168, "kelamlar", "trator");
      Product p3 = new Product("53", "erkan", "enaktarlar", 313, 168, "kelamlar", "trator");

      TreeSet<Product> productTree = new TreeSet<>();
      productTree.add(p);
      productTree.add(p1);
      productTree.add(p2);

      FileOutputStream fileWriter = new FileOutputStream("objectDeneme.txt");
      ObjectOutputStream out =
              new ObjectOutputStream(fileWriter);

//      out.writeObject(productTree);
      var iter = productTree.descendingIterator();
      while(iter.hasNext()) {
        out.writeObject(iter.next());
      }
    }
    catch (Exception e) {
      System.out.println("asansdnas");
    }
  }
}
