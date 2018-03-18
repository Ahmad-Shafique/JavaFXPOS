package core.application.services.emulator;

import core.domain.model.entities.*;
import core.domain.model.entities._utilities.console;

import java.time.Instant;
import java.util.*;

public class DBEmulator {
    private static List<Category> categoryList = Arrays.asList(
            new Category(1,"Biriyani","Rice + Masala + Meat"),
            new Category(2,"Khichuri","Rice + Meat")
    );
    private static List<Item> itemList= Arrays.asList(
            new Item(1,1,"Kachchi Biriyani", 250,10,"Yummy !!"),
            new Item(2,1,"Chicken Biriyani", 240,10,"Oh my !!")
    );
    private static List<Sale> saleList = createSaleList();
    private static List<SaleItem> saleItemList = Arrays.asList(
            new SaleItem(1, 1, 1, 1, 250),
            new SaleItem(2, 1, 2, 2, 480)
        );
    private static List<User> userList = Arrays.asList(
            new User(1,"1","1","","","")
    );
    private static List<Invoice> invoiceList= Arrays.asList(
            new Invoice(1,1, "Janice", 500, 500, 500, 0, new Date().toString(), 0.0, 0.0),
            new Invoice(2,1, "Janice", 275, 300, 500, 200, new Date().toString(), 25.0, 0.0)
    );





    private static List<Sale> createSaleList(){

        List<SaleItem> saleItemList1 = Arrays.asList(
                new SaleItem(1, 1, 1, 1, 250),
                new SaleItem(2, 1, 2, 2, 480)
        );
        List<SaleItem> saleItemList2 = Arrays.asList(
                new SaleItem(1, 1, 1, 4, 1000)
        );

        List<Sale> returnable = Arrays.asList(
                new Sale(1, 1, 730, new Date(), saleItemList1),
                new Sale(2, 1, 1000, new Date(), saleItemList2)
        );

        return returnable;
    }















    public static boolean addCategory(Category item){
        // DBEmulator.categoryList.add(item);
        return true;
    }
    public static Category getCategory(int id){
        Category result = new Category(-1,"");
        for (Category item:categoryList
             ) {
            if(item.getId() == id ){
                result = item;
                break;
            }
        }
        return result;
    }
    public static boolean updateCategory(int id, Category item){
        int itemIndex=-1;
        int listSize = categoryList.size();
        Category[] array = new Category[listSize];
        array = categoryList.toArray(array);
        for(int i=0; i<listSize; i++){
            if(array[i].getId() == id){
                itemIndex = i;
                break;
            }
        }
        array[itemIndex] = item;
        categoryList = Arrays.asList(array);
        return true;
    }
    public static boolean deleteCategory(int id){
        // DBEmulator.categoryList.remove(id);
        return true;
    }
    public static List<Category> getAllCategories(){
        return categoryList;
    }
    public static List<String> getAllCategoryName(){
        List<String> categoryNames = new ArrayList<String>();
        for (Category item: categoryList
             ) {
            categoryNames.add(item.getName());
        }
        return categoryNames;
    }









    public static boolean addItem(Item item){
/*        console.log("Entered db emulator");
        console.log("Passed object: \nId:"+item.getId()+"\nCategoryId:"+item.getCategoryId()+"\nName:"
                        +item.getName()+"\nPrice:"+item.getPrice()+"\nQuantity:"+item.getQuantityAvailable()
                        +"\nDescription:"+item.getDescription());
        itemList.add(item);
        console.log("Added to list in DB emulator");*/
        return true;
    }
    public static Item getItem(int id){
        Item result = new Item(-1,-1,"",-1,-1,"Hello World");
        for (Item item:itemList
                ) {
            if(item.getId() == id ){
                result = item;
                break;
            }
        }
        return result;
    }
    public static boolean updateItem(int id, Item item){
        int itemIndex=-1;
        int listSize = itemList.size();
        Item[] array = new Item[listSize];
        array = itemList.toArray(array);
        for(int i=0; i<listSize; i++){
            if(array[i].getId() == id){
                itemIndex = i;
                break;
            }
        }
        array[itemIndex] = item;
        itemList = Arrays.asList(array);
        return true;
    }
    public static boolean deleteItem(int id){
        // DBEmulator.itemList.remove(id);
        return true;
    }
    public static List<Item> getAllItems(){
        return itemList;
    }













    public static boolean addSaleItem(SaleItem item){
        return DBEmulator.saleItemList.add(item);
    }
    public static SaleItem getSaleItem(int id){
        SaleItem result = new SaleItem(-1,-1,-1,-1,-1);
        for (SaleItem item:saleItemList
                ) {
            if(item.getId() == id ){
                result = item;
                break;
            }
        }
        return result;
    }
    public static boolean updateSaleItem(int id, SaleItem item){
        int itemIndex=-1;
        int listSize = saleItemList.size();
        SaleItem[] array = new SaleItem[listSize];
        array = saleItemList.toArray(array);
        for(int i=0; i<listSize; i++){
            if(array[i].getId() == id){
                itemIndex = i;
                break;
            }
        }
        array[itemIndex] = item;
        saleItemList = Arrays.asList(array);
        return true;
    }
    public static boolean deleteSaleItem(int id){
        DBEmulator.saleItemList.remove(id);
        return true;
    }
    public static List<SaleItem> getAllSaleItems(){
        return saleItemList;
    }














    public static boolean addSale(Sale item){
        return DBEmulator.saleList.add(item);
    }
    public static Sale getSale(int id){
        Sale result ;
        SaleItem[] sIA = new SaleItem[1];
        Collection<SaleItem> sIL = Arrays.asList(sIA);
        result = new Sale(-1, -1, -1, new Date(), sIL);
        for (Sale item:saleList
                ) {
            if(item.getId() == id ){
                result = item;
                break;
            }
        }

        return result;
    }
    public static boolean updateSale(int id, Sale item){
        int itemIndex=-1;
        int listSize = saleList.size();
        Sale[] array = new Sale[listSize];
        array = saleList.toArray(array);
        for(int i=0; i<listSize; i++){
            if(array[i].getId() == id){
                itemIndex = i;
                break;
            }
        }
        array[itemIndex] = item;
        saleList = Arrays.asList(array);
        return true;
    }
    public static boolean deleteSale(int id){
        DBEmulator.saleList.remove(id);
        return true;
    }
    public static List<Sale> getAllSales(){
        return saleList;
    }










    public static boolean addUser(User item){
        return DBEmulator.userList.add(item);
    }
    public static User getUser(int id){
        User result = new User(-1,"","","","","");
        for (User item:userList
                ) {
            if(item.getId() == id ){
                result = item;
                break;
            }
        }
        return result;
    }
    public static boolean updateUser(int id, User item){
        int itemIndex=-1;
        int listSize = userList.size();
        User[] array = new User[listSize];
        array = userList.toArray(array);
        for(int i=0; i<listSize; i++){
            if(array[i].getId() == id){
                itemIndex = i;
                break;
            }
        }
        array[itemIndex] = item;
        userList = Arrays.asList(array);
        return true;
    }
    public static boolean deleteUser(int id){
        DBEmulator.userList.remove(id);
        return true;
    }
    public static List<User> getAllUsers(){
        return userList;
    }
    public static User authenticateUser(String username){
/*        System.out.println("Inside the db");
        System.out.println("passed param is : " + username);
        System.out.println("Users available now: ");*/
/*        for (User user: userList
             ) {
            System.out.println("Username : " + username);
            if(user.getUserName().equals(username) ) System.out.println("Found desired username in db");
        }*/
        User result = new User();

        for (User user: userList
             ) {
            if( user.getUserName().trim().equals(username.trim() )) {
                /*System.out.println("Found user name in DB");*/
                return user;
                // result = user;
                // System.out.println(result.getUserName());
                // break;
            }
        }
        return result;
    }











    public static boolean addInvoice(Invoice item){
        DBEmulator.invoiceList.add(item);
        return true;
    }
    public static Invoice getInvoice(int id){
        Invoice result = new Invoice();
        for (Invoice item:invoiceList
                ) {
            if(item.getId() == id ){
                result = item;
                break;
            }
        }
        return result;
    }
    public static boolean updateInvoice(int id, Invoice item){
        int itemIndex=-1;
        int listSize = invoiceList.size();
        Invoice[] array = new Invoice[listSize];
        array = invoiceList.toArray(array);
        for(int i=0; i<listSize; i++){
            if(array[i].getId() == id){
                itemIndex = i;
                break;
            }
        }
        array[itemIndex] = item;
        invoiceList = Arrays.asList(array);
        return true;
    }
    public static boolean deleteInvoice(int id){
        DBEmulator.invoiceList.remove(id);
        return true;
    }
    public static List<Invoice> getAllInvoices(){
        return invoiceList;
    }


}
