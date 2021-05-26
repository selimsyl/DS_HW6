package FileStroage;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TreeVisitor;

import java.io.*;
import java.util.TreeSet;

public class FileStorage<E> {
  private String filePath;

  public FileStorage(String filePath) {
    this.filePath = filePath;
  }

  public void writeToFile(TreeSet<E> tree) {
    try {
      FileOutputStream fileWriter = new FileOutputStream(filePath);
      ObjectOutputStream out =
              new ObjectOutputStream(fileWriter);

        out.writeObject(tree);

//      var iter = tree.descendingIterator();
//      while(iter.hasNext()) {
//        out.writeObject(iter.next());
//      }
    }
    catch (FileNotFoundException e) {
      System.out.println("Yazilacak dosya bulunamadi");
    }
    catch (IOException e) {
      System.out.println("Dosyaya verileri yazarken exception olustu");
    }
  }

  public TreeSet<E> readFromFile() {
    TreeSet<E> tree = null;
    try {
      FileInputStream fileReader = new FileInputStream(filePath);
      ObjectInputStream in =
              new ObjectInputStream(fileReader);

        if (fileReader.available() > 0) {
          tree = (TreeSet<E>) in.readObject();
        }
    }
    catch (FileNotFoundException e) {
      System.out.println("Okunacak dosya bulunamadi");
    }
    catch (IOException | ClassNotFoundException e) {
      System.out.println("Dosyadan verileri okurken exception olustu");
    }
    return tree;
  }
}
