import FileStroage.FileStorage;
import Product.Product;
import user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
  /**
   *  Used to access current system user
   */
  private User user;
  /**
   * abstract base class reference to store current menu string array
   */
  private MenuOperations menuOperations;

  /**
   * input stream handler
   */
  private final Scanner inputScanner = new Scanner(System.in);

  FileStorage<User> userFile = new FileStorage<User>("UserFile.txt");

  FileStorage<Product> productFile = new FileStorage<Product>("ProductFile.txt");

  /**
   * base abstract class for menu strings
   */
  abstract class MenuOperations {
    String[] menu;
    public String[] getMenuOptions() {
      return menu;
    }
  }

  /**
   * System login options
   */
  class LoginMenu extends MenuOperations{
    LoginMenu() {
      menu = new String[]{"Log In","Sign In","Exit"};
    }
  }

  /**
   * Administrator operation menu string array
   */
  class TraderMenu extends MenuOperations {
    TraderMenu(){
      menu = new String[]{"Add Product","Remove Product","Edit Product","List Orders","LogOut"};
    }
  }
  /**
   * EmployeeMenu operation menu string array
   */
  class CustomerMenu extends MenuOperations {
    CustomerMenu(){
      menu = new String[]{"Search Product","LogOut"};
    }
  }

  class SortMenu extends MenuOperations {
    SortMenu(){
      menu = new String[]{"Sort By Price", "Sort By Discount","Sort By Name", "Exit"};
    }
  }

  /**
   * Menu operations base method, all starts from here
   */
  public void run() {
    FileStorage<User> userFile = new FileStorage<User>("UserFile.txt");
    FileStorage<Product> productFile = new FileStorage<Product>("ProductFile.txt");

    System.out.println("=============WELCOM TO OFFICE FURNITRES WONDERLAND=============");
    userMenu();
  }

  /**
   * Get which user uses the sytem
   */
  private void userMenu() {
    do {
      switch (loginMenu().getRole()) {
        case TRADER -> {
          TraderOperations();
        }
        case CUSTOMER -> {
          CustomerOperations();
        }
      }
    }while(true);
  }

  /**
   * @return which user logs in system Customer,Trader
   */
  private User loginMenu() {
    user = null;
    do {
      menuOperations = new LoginMenu();
      switch (listMenu(menuOperations.getMenuOptions())) {
        case "Log In" -> {
          user = getUserCredentials();
        }
        case "Sign In" -> {
          System.out.println("Sign in option is currently under maintenance, will be active very soon...");
        }
        case "Exit" -> {
          System.exit(0);
        }
        default-> {
        }
      }

    } while(user == null);

    System.out.println("Succesfully logged in");
    return user;
  }

  private User getUserCredentials() {
    Scanner input = new Scanner(System.in);
    boolean is_in;
    int id=0;
    User returnUser = null;
    try {
      do {
        System.out.print("Enter ID as a 8 digit Number: ");
        id = input.nextInt();
        is_in=false;

        if (id<10000000 || id>=99999999){
          System.out.println("ID should be 8 digit!");
          is_in=true;
        } else {
          var password = getStringFromConsole("User Password", 6, 6);
          if (password == null) {
            is_in=true;
          } else {
            boolean findFlag = false;
            for (User user : userFile.readFromFile()) {
              if (user.getUserId().equals(id) && user.getPassword().equals(password)) {
                findFlag = true;
                returnUser = user;
              }
            }
            if (!findFlag) {
              System.out.println("Invalid User ");
              is_in = true;
            }
          }
        }
      }while(is_in);
    }
    catch (Exception e){
      System.out.println(e.getMessage());
//      System.out.print("Please enter a valid value!\n");
      input.nextLine();
    }
    return returnUser;
  }

  /**
   * Administrator menu for operations
   */
  private void TraderOperations() {
    boolean menuExit = false;
    user.Trader admin = (user.Trader) user;
    do {
      menuOperations = new TraderMenu();
      switch (listMenu(menuOperations.getMenuOptions())) {
        case "Add Product" -> {
        }
        case "Remove Product" -> {

        }
        case "Edit Product" -> {
        }
        case "List Orders" -> {

        }
        case "LogOut" -> {
          System.out.println("Succesfully logged out");
          menuExit = true;
        }
        default-> {
//                    System.out.println("Please enter a valid option");
        }
      }
    }while (!menuExit);
  }

  /**
   * Customer menu for operations
   */
  private void CustomerOperations() {
    ArrayList<Product> productList = null;
    boolean menuExit = false;
    user.Customer customer = (user.Customer)user;
    do {
      menuOperations = new CustomerMenu();
      switch (listMenu(menuOperations.getMenuOptions())) {
        case "Search Product" -> {
          String productName = getEnteredString("Enter product name", 3, 30);
          productList = customer.searchProducts(productName);
          if (productList != null) {
            for (var p : productList) {
              System.out.println(p.getName());
            }
            sortProductsMenu();
          }
        }
        case "LogOut" -> {
          System.out.println("Succesfully logged out");
          menuExit = true;
        }
        default-> {
        }
      }
    }while (!menuExit);
  }

  /**
   * @param menu String values listed as a menu
   * @return which menu option is selected
   */
  private String listMenu(String[] menu) {
    int i = 1;
    System.out.println("Please choose a option below, enter option number");
    for (String item:menu) {
      System.out.println(i++ + ". " + item);
    }

    var enteredOption = getSelectedOption(1,menu.length);
    if (enteredOption == -1)
      return "";

    System.out.println("Selected option : " + menu[enteredOption-1]);
    return i >= 0 ? menu[enteredOption-1] : "";
  }

  /**
   * @param desiredInput String name for a prompt to user what is expected
   * @param minLength Min length of input
   * @param maxLength Max length of input
   * @return what is entered
   */
  private String getStringFromConsole(String desiredInput,int minLength,int maxLength) {
    System.out.println("Enter 'exit' to abort operations");
    return getEnteredString(desiredInput,minLength,maxLength);
  }

  /**
   * @param desiredInput String name for a prompt to user what is expected
   * @param minLength Min length of input
   * @param maxLength Max length of input
   * @return what is entered
   */
  private String getEnteredString(String desiredInput,int minLength,int maxLength) {
    String revStr = null;
    String aralikDegerString;
    if (minLength == maxLength) {
      aralikDegerString = Integer.toString(minLength);
    } else {
      aralikDegerString = "between " + minLength + "-" + maxLength;
    }
    System.out.println("Please enter a valid "+desiredInput + " " + aralikDegerString + " characters");
    do {
      try {
        revStr = inputScanner.nextLine();
        if (revStr.toLowerCase().equals("exit"))
          continue;
        if (revStr.length() < minLength || revStr.length() > maxLength) {
          System.out.println("Please enter a valid "+desiredInput+" between " + minLength + "-" + maxLength);
        }
        else {
          break;
        }

      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Please enter a valid name");
        revStr = "";
      }

    }while(!revStr.toLowerCase().equals("exit"));
    return revStr;
  }

  /**
   * @param minInput Min input value
   * @param maxInput Max input value
   * @return what value is entered
   * Make user to enter a value between minInput-maxInput values
   */
  private int getSelectedOption(int minInput,int maxInput) {
    int enteredOption = -1;

    try {
      enteredOption = inputScanner.nextInt();
    } catch (Exception e) {
//            System.out.println(e.getMessage());
    }
    inputScanner.nextLine();

    if (enteredOption < minInput || enteredOption > maxInput) {
      System.out.println("Please enter a valid option");
      enteredOption = -1;
    }

    return enteredOption;
  }

  /**
   * @param menu String values for products
   * @return which product option is selected
   */
  private String listProductsMenus(String[] menu) {
    menu = Arrays.copyOf(menu, menu.length+1);
    menu[menu.length-1] = "Exit";

    String selectedModel = "";

    while(selectedModel.equals(""))
      selectedModel = listMenu(menu);

    return selectedModel;
  }

  private Product sortProductsMenu() {
    boolean menuExit = false;
    do {
      menuOperations = new SortMenu();
      switch (listMenu(menuOperations.getMenuOptions())) {
        case "Sort By Price" -> {
        }
        case "Sort By Discount" -> {

        }
        case "Sort By Name" -> {

        }
        case "Exit" -> {
          menuExit = true;
        }
      }
    } while(!menuExit);
    return null;
  }

}
