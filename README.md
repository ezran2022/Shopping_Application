# Shopping_Application
## This is a Java Desktop application which is used for shopping different items and updating them when it is needed 


By starting this application load all available items in store ,so you can see what you need to choose and give you 3 choices 
of add item in cart , removing item in cart and do a checkout

![image](https://user-images.githubusercontent.com/103323625/193792612-b0d1b555-15b6-427b-8403-b8f89a8f92e8.png)

After that you can choose let start by adding in cart (a) and you see that we have to
choose between 1-7 to get the row of an item and choose between 1-3 to get the column of an item and as we see we got it
and item is added in the cart

![image](https://user-images.githubusercontent.com/103323625/193793841-f4578306-100a-4f01-92fb-0c27f91951cd.png)


And now let choose b to remove item in the cart all you have to do is type the name of item , 
if you type the name of item wich is not in the cart this will do nothing to youu it will just load the same shopping cart as before


![image](https://user-images.githubusercontent.com/103323625/193795047-ae7f8105-b483-4261-ac75-daa049e00c1c.png)

but if you choose what it is really in cart it will remove it directly

![image](https://user-images.githubusercontent.com/103323625/193795553-a89ba1e6-402b-4765-a5d4-e017faf69f99.png)

So after adding and removing what you want in the cart you can complete a checkout by pressing (c)

![image](https://user-images.githubusercontent.com/103323625/193795910-7510e9ef-d685-4206-9b27-9c1b33030fdf.png)


### And a Great thing about this application is that we can test all task peformed by this app one by one without running the app
### and make sure that the application work properly
By starting this is the requirement we have to test for this application and make sure there are all passed the test


     
     
### This is how our application work!!


### Code
```java

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

```
 
