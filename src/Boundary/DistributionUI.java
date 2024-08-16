/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import ADT.SortedDoublyLinkedListSet;
import ADT.SortedListSetInterface;
import Entity.*;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author szewen
 */
public class DistributionUI {

    Scanner scanner = new Scanner(System.in);
    private SortedListSetInterface<Item> donatedItemList = new SortedDoublyLinkedListSet<>();

    public String getDistributionMenu() {
        System.out.println(""
                + "\nDISTRIBUTION MANAGEMENT"
                + "\n 1. List all donation distributions (filterdistribution by distributeditem(type) / distributeddate" 
                + "\n 2. Add new donation distribution"
                + "\n 3. Update donation distribution details"
                + "\n 4. Remove donation distribution"
                + "\n 5. Monitor/track distributed items"
                + "\n 6. Generate summary reports"
                //   + "\n 7. remove donation"
                //   + "\n 8. Filter donation based on criteria"
                // + "\n 9. Generate summary reports"
                + "\n 9. Back to MAIN MENU");
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();      
        
        return opt;
    }

    public LocalDate getLocalDate() {
        return LocalDate.now();
    }

    public void PrintDonatedItemList(SortedListSetInterface<Item> donatedItemList) {
        System.out.println(donatedItemList);
    }
    
    public void PrintItemList(Item item){
        System.out.println(item);
    }


    public String getInputString(String desc) {
        System.out.print(desc);
        return scanner.nextLine();
    }

    public int getInputQty(String desc) {
        System.out.print(desc);
        int opt = scanner.nextInt();
        scanner.nextLine();
        return opt;
    }

    public void listAllDistributions(SortedListSetInterface<Distribution> distributions) {
        System.out.print(distributions);
    }
    
    public void displayMessage(String msg){
        System.out.println(msg);
        
    }
    
   /* public void filterItemByType(SortedListSetInterface<Item> donatedItemList,String type){
    
          Iterator<Item> iterator = donatedItemList.getIterator();

          System.out.println("Item List filter by " + type + ": ");
    while (iterator.hasNext()) {
        Item currentItem = iterator.next();
        if (currentItem.getType().equalsIgnoreCase(type)) {
            System.out.println(currentItem);
        }
    }
    }*/
    


}
