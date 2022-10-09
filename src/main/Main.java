import models.Item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import models.Cart;
import models.Store;

public class Main {
    static Store store = new Store();
    static Cart cart = new Cart();

    public static void main(String[] args) {

        try{
         loadItems("products.txt");
         manageItems();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
   
    }
    
    public static void loadItems(String fileName) throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanFile = new Scanner(fis);

        for(int i = 0; scanFile.hasNextLine(); i++){
            String line = scanFile.nextLine();
            String[] items = line.split(";");
            for (int j = 0; j < items.length; j++) {
                String[] fields = items[j].split("=");
                store.setItem(i, j , new Item(fields[0], Double.parseDouble(fields[1])));
            }
        }
        scanFile.close();
    }
    
    public static void manageItems() {
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("\n\t******************************JAVA GROCERS******************************\n");
            System.out.println(store);

            System.out.println("Options: \n\ta) Add to cart\n\tb) Remove from cart \n\tc) Checkout");
            String response = input.nextLine();

            switch(response){
                case "a" : System.out.print("\nChoose an aisle number between: 1-7: ");
                           int row = input.hasNextInt() ? input.nextInt() - 1 : 404; 
                           input.nextLine();
                           System.out.print("\nChoose an item number between: 1-3: ");
                           int column = input.hasNextInt() ? input.nextInt() - 1 : 404 ;
                           input.nextLine();
                           if(row == 404 || column == 404){
                            continue;
                           }else if(row < 0 || row > 6 || column < 0 || column > 2){
                            continue;
                           }
                           Item item = store.getItem(row,column);
                           if(!(cart.add(item))){
                              System.out.println(item.getName() + " is already in your shopping cart.");
                           }else{
                              System.out.println(item.getName()+" was added to your shopping cart."); 
                           }
                           break;

                case "b" :
                       if(cart.isEmpty()){
                        continue;
                       } 
                         System.out.print("Enter the item you'd like to remove: ");
                         String name = input.nextLine();
                         cart.remove(name);
                         break;
                case "c" :
                        if(cart.isEmpty()){
                            continue;
                        }
                         System.out.println(cart.checkout());
                         input.close();
                         return;
                default : continue;
            }

            System.out.println("\n\nSHOPPING CART\n\n" + cart);
            System.out.print("Enter anything to continue: ");
            input.nextLine();
        }
    }

}