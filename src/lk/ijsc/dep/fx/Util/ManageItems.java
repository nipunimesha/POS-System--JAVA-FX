package lk.ijsc.dep.fx.Util;

import lk.ijsc.dep.fx.Model.Items;

import java.util.ArrayList;

public class ManageItems {
    // Database
    private static ArrayList<Items> itemsDB = new ArrayList<>();

    public static ArrayList<Items> getItemsDB(){
        return itemsDB;
    }

    public static void setItemsDB(ArrayList<Items> items){
        itemsDB = items;
    }

    // Dummy Data
    static{
        itemsDB.add(new Items("I001","Mouse",250,50));
        itemsDB.add(new Items("I002","Keyboard",350,50));
        itemsDB.add(new Items("I003","Monitors",5500,50));
        itemsDB.add(new Items("I004","Subwoofers",3500,50));
    }

    public static void createItem(Items item){
        itemsDB.add(item);
    }

    public static void updateItem(int index, Items item){
        itemsDB.get(index).setDescription(item.getDescription());
        itemsDB.get(index).setUnitPrice(item.getUnitPrice());
        itemsDB.get(index).setQtyOnHand(item.getQtyOnHand());
    }

    public static void deleteItem(int index){
        itemsDB.remove(index);
    }

    public static Items findItem(String itemCode) {
        for (Items item : itemsDB) {
            if (item.getCode().equals(itemCode)){
                return item;
            }
        }
        return null;
    }
}
