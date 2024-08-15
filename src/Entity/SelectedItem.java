/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;


import java.io.Serializable;
import ADT.*;
import java.util.Iterator;
import java.util.Objects;
/**
 *
 * 
 * @author szewen
 */
public class SelectedItem implements Comparable<SelectedItem> {
   // private Donation donation;     // The donation this item is selected from
    private String itemId;
    private int selectedQuantity;
    private double amount;

   // public SelectedItem(Donation donation, String itemId, int selectedQuantity) {
      //  this.donation = donation;
      //  this.itemId = itemId;
     //   this.selectedQuantity = selectedQuantity;

        //Check whether the item is belong in this donation
    //   if (!isValidItem()) {
     //    throw new IllegalArgumentException("Item ID" + itemId + " is not valid for this donation.");
     //   }
  //  }

    public SelectedItem(String itemId) {
        this.itemId = itemId;
    }
    
    

    public SelectedItem(String itemId, int selectedQuantity) {
        this.itemId = itemId;
        this.selectedQuantity = selectedQuantity;
    }
    
    public SelectedItem(String itemId, double amount) {
        this.itemId = itemId;
        this.amount = amount;
    }
    

   
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

 /* private boolean isValidItem() {
        Iterator<Item> iterator = donation.getDonatedItemList().getIterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getItemId().equals(itemId)) {
                return true;
            }
        }
        return false;
    }*/

//    @Override
//    public String toString() {
//        return "SelectedItem{" +
//                "itemId='" + itemId + '\'' +
//                ", selectedQuantity=" + selectedQuantity +
//                '}';
//    }
    
    @Override
    public String toString() {
        return (amount != 0) ?
                String.format("%-15s %-15s %-15.2f",itemId, "-", amount)
                :
                String.format("%-15s %-15d %-15s", itemId, selectedQuantity,"-");
    }

 

    @Override
    public int compareTo(SelectedItem other) {
        return this.itemId.compareTo(other.itemId);
    }
}
