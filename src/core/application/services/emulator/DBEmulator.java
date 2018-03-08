package core.application.services.emulator;

import core.domain.model.entities.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DBEmulator {
    private static List<Category> categoryList;
    private static List<Item> itemList;
    private static List<Sale> saleList;
    private static List<SaleItem> saleItemList;
    private static List<User> userList;


    public static boolean addCategory(Category item){
        return DBEmulator.categoryList.add(item);
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
        DBEmulator.categoryList.remove(id);
        return true;
    }










    public static boolean addItem(Item item){
        return DBEmulator.itemList.add(item);
    }
    public static Item getItem(int id){
        Item result = new Item(-1,-1,"",-1,-1);
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
        DBEmulator.itemList.remove(id);
        return true;
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


}
