/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import ADT.SortedListSetInterface;
import Entity.Item;
import java.util.Iterator;

/**
 *
 * @author USER
 */
public class StockUI {
    public static String getItemType(int opt) {
        switch (opt) {
            case 1:
                return "Monetary";
            case 2:
                return "Clothing and Apparel";
            case 3:
                return "Food and Beverage";
            case 4:
                return "Household Items";
            case 5:
                return "Educational Materials";
            case 6:
                return "Electronic";
            case 7:
                return "Medical";
            case 9:
                break;
            default:
                MessageUI.displayInvalidOptionMessage();
                break;
        }
        return null;
    }

    public static int minimunInventory(String type) {
        switch (type) {
            case "Monetary":
                return 1000; //10000
            case "Clothing and Apparel":
                return 50;  //300
            case "Food and Beverage":
                return 50;  //500
            case "Household Items":
                return 5;  //50
            case "Educational Materials":
                return 15;  //300
            case "Electronic":
                return 5;  //50
            case "Medical":
                return 5; //100
            default:
                return 0;
        }
    }

    public static int getTotalInventory(String type, SortedListSetInterface<Item> items) {
        int qty = 0;

        Iterator<Item> iterator = items.getIterator();
        do {
            Item item = iterator.next();
            if (item.getType().equalsIgnoreCase(type)) {
                qty += item.getQuantity();
            }
        } while (iterator.hasNext());

        return qty;
    }

    public static double getTotalAmount(String type, SortedListSetInterface<Item> items) {
        double amount = 0;

        Iterator<Item> iterator = items.getIterator();
        do {
            Item item = iterator.next();
            if (item.getType().equalsIgnoreCase(type)) {
                amount += item.getTotalAmount();
            }
        } while (iterator.hasNext());
        return amount;
    }

    public static boolean checkInventory(String type, SortedListSetInterface<Item> items){
        return getTotalInventory(type,items) >= minimunInventory(type);
    }
    
    public static boolean checkMonetary(String type, SortedListSetInterface<Item> items){
        return getTotalAmount(type,items) >= minimunInventory(type);
    }
}
