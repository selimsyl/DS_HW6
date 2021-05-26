import FileStroage.FileStorage;
import Product.Product;
import user.Trader;
import user.User;

import java.util.Scanner;
import java.util.TreeSet;

public class eShop {
  private static int uniqueUserId = 10000000;
  public class Menu {

    private void initSystemFiles() {
      ProductCSVInit initProduct = new ProductCSVInit("e-commerce-samples.csv");
      TreeSet<Product> tree = initProduct.getProductTree();
      TreeSet<User> utree = new TreeSet<>();

      FileStorage<Product> productFileCreater = new FileStorage<Product>("ProductFile.txt");
      FileStorage<User> userFileCreater = new FileStorage<User>("UserFile.txt");


      var iter = tree.iterator();
      while (iter.hasNext()) {
        var p = iter.next();
        utree.add(new Trader(uniqueUserId++,p.getTrader()));
      }
      
      productFileCreater.writeToFile(tree);
      userFileCreater.writeToFile(utree);
    }

    public void startSystem(){
      //create necessary object
      initSystemFiles();
      DataBase data = new DataBase ();
      ReadAndWriteFile readAndWriteFile = new ReadAndWriteFile (data);
      readAndWriteFile.fill ();
      data.openFlag ();
      ManageAdministrator manageAdministrator = new ManageAdministrator(data);
      ManageGovernor manageGovernor = new ManageGovernor (data);
      ManageDoctor manageDoctor = new ManageDoctor (data);
      ManageCook manageCook = new ManageCook (data);
      ManageJailer manageJailer = new ManageJailer (data);
      ManageChiefJailer manageChiefJailer = new ManageChiefJailer (data);
      Validate validator = new Validate(data);
      int choice,id;
      do{
        mainMenu ();
        choice = GetChoiceFromUser.getSubChoice(2,"Answer: ");
        if (choice==1){
          Personnel personnel = validator.validatePersonnel ();
          if (personnel!=null){
            if (personnel.job==JobType.Administrator)
              manageAdministrator.manage (personnel);
            else if (personnel.job==JobType.Governor)
              manageGovernor.manage (personnel);
            else if (personnel.job==JobType.Doctor)
              manageDoctor.manage (personnel);
            else if (personnel.job==JobType.Cook)
              manageCook.manage (personnel);
            else if (personnel.job==JobType.ChiefJailer)
              manageChiefJailer.manage (personnel);
            else if (personnel.job==JobType.Jailer)
              manageJailer.manage (personnel);
          }
          else
            System.out.println("Your ID or Password Wrong!");
        }
        else if (choice == 2){
          int k;
          for ( k = 0; k < 45; k++) System.out.print("-");
          System.out.print("\n");
          System.out.println ("\t***Prison Current Status***");
          if (data.getAlert ())
            System.out.println ("Alert of State: Yes ");
          else
            System.out.println ("Alert of State: No ");
          if (data.toDoSize ()==0)
            System.out.println ("No ToDo! ");
          else{
            Personnel p = data.getPersonnel (data.getToDoFromTop ().getOwnerID ());
            System.out.println ("First ToDo Owner: "+
                    p.name+" "+p.surname);
          }
          for ( k = 0; k < 45; k++) System.out.print("-");
          System.out.print("\n");
          System.out.println ("\t***Prison Structure***");
          data.printPrison ();
        }

      }while (choice!=0);
    }
    private void mainMenu(){
      int k;
      for ( k = 0; k < 45; k++) System.out.print("-");
      System.out.print("\n"+"   ");
      System.out.print("Welcome to Prison Management System\n");
      for ( k = 0; k < 45; k++) System.out.print("-");
      System.out.print("\n"+"   ");
      System.out.println("[1] Login.");
      for ( k = 0; k < 45; k++) System.out.print("-");
      System.out.print("\n"+"   ");
      System.out.println("[2] Check Prison Status.");
      for ( k = 0; k < 45; k++) System.out.print("-");
      System.out.print("\n"+"   ");
      System.out.println("[0] Exit.");
      for ( k = 0; k < 45; k++) System.out.print("-");
      System.out.print("\n");

    }
  }

  /**
   * This Class is first to show the message and then to get choice from user.
   * It contains only static method to use method with class name.
   * */
  public class GetChoiceFromUser {
    /**
     * This method gets an integer number from user with showing a message.
     * @param upperBound Upper Bound of choices.
     * @param message a message to show user.
     * @return Return number that entered by user
     * */
    public int getSubChoice(int upperBound,String message){
      int choice;
      Scanner input = new Scanner(System.in);
      System.out.print(message);
      try {
        choice = input.nextInt();
        while (choice<0 || choice>upperBound){
          System.out.printf("Please enter a number between 0 and %d: ",upperBound);
          choice=input.nextInt();
        }
      }
      catch (Exception e){
        choice=1;
        System.out.printf("Please enter a number between 0 and %d!\n",upperBound);
        input.nextLine();
      }
      return choice;
    }
    /**
     * This method first show the message then gets a String from user.
     * @param message Message that will be printed.
     * @return Return String that entered by user
     * */
    public String getStringFromUser(String message){
      Scanner input = new Scanner(System.in);
      String bName=null;
      System.out.print(message);
      try {
        bName = input.nextLine();
      }
      catch (Exception e){
        System.out.print("Please enter a valid value!\n");
        input.nextLine();
      }
      return bName;
    }
    /**
     * This method gets an integer number from user with showing a message.
     * The number should be bigger than 1.
     * @param message a message to show user.
     * @return Return number that entered by user
     * */
    public int getNumber(String message){
      int choice;
      Scanner input = new Scanner(System.in);
      System.out.print(message);
      try {
        choice = input.nextInt();
        if(choice == -1)
          return -1;
        while (choice<1 && choice != -1){
          System.out.print("Please enter a number bigger than 0: ");
          choice=input.nextInt();
        }
      }
      catch (Exception e){
        choice=1;
        System.out.println("Please enter a number!");
        input.nextLine();
      }
      return choice;
    }
    /**
     * This method gets an double number from user with showing a message.
     * The number should be bigger than 0.
     * @param message a message to show user.
     * @return Return number that entered by user
     * */
    public double getDouble(String message){
      double choice;
      Scanner input = new Scanner(System.in);
      System.out.print(message);
      try {
        choice = input.nextDouble ();
        while (choice<=0){
          System.out.print("Please enter a number bigger than 0: ");
          choice=input.nextDouble ();
        }
      }
      catch (Exception e){
        choice=1;
        System.out.println("Please enter a number!");
        input.nextLine();
      }
      return choice;
    }
    /**
     * This method gets an integer number from user as an id.
     * @param data databas reference
     * @return Return number that entered by user
     * */
    public int getIDFromUser(DataBase data){
      Scanner input = new Scanner(System.in);
      boolean is_in;
      int id=0;
      try {
        do {
          System.out.print("Enter ID as a 5 digit Number: ");
          id = input.nextInt();
          is_in=false;
          if (id<10000 || id>=100000){
            System.out.println("ID should be 5 digit!");
            is_in=true;
          }
          else if (data.IDUsed(id)){
            System.out.println("This ID has used before!");
            is_in=true;
          }
        }while(is_in);
      }
      catch (Exception e){
        System.out.print("Please enter a valid value!\n");
        input.nextLine();
      }
      return id;
    }
  }
}
