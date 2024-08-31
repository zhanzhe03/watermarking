/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import ADT.SortedListSetInterface;
import Entity.Donation;
import Entity.Donor;
import Entity.Item;
import Entity.Donee;
import Entity.SelectedItem;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author szewen
 */
public class CommonUse {

    public static int countType(String type, SortedListSetInterface<SelectedItem> selectedItemList, SortedListSetInterface<Item> items) {
        int count = 0;
        Iterator<SelectedItem> iterator = selectedItemList.getIterator();
        do {
            SelectedItem selectedItem = iterator.next();
            String id = selectedItem.getItemId();
            Item item = findItem(id, items);
            if (item.getType().equalsIgnoreCase(type)) {
                count++;
            }
        } while (iterator.hasNext());
        return count;
    }

    public static Donor findDonor(String contact, SortedListSetInterface<Donor> donors) {
        Iterator<Donor> iterator = donors.getIterator();
        do {
            Donor donor = iterator.next();
            if (donor.getContact().equals(contact)) {
                return donor;
            }
        } while (iterator.hasNext());
        return null;
    }

    public static Donee findDonee(String ID, SortedListSetInterface<Donee> donees) {
        Iterator<Donee> iterator = donees.getIterator();
        do {
            Donee donee = iterator.next();
            if (donee.getDoneeId().equalsIgnoreCase(ID)) {
                return donee;
            }
        } while (iterator.hasNext());
        return null;
    }

    public static Donation findDonation(String id, SortedListSetInterface<Donation> donations) {
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            if (donation.getDonationId().equalsIgnoreCase(id)) {
                return donation;
            }
        } while (iterator.hasNext());
        return null;
    }

    public static Item findItem(String id, SortedListSetInterface<Item> items) {
        Iterator<Item> iterator = items.getIterator();
        do {
            Item item = iterator.next();
            if (item.getItemId().equalsIgnoreCase(id)) {
                return item;
            }
        } while (iterator.hasNext());
        return null;
    }

    private static void printItemEnDash() {
        for (int i = 0; i < 125; i++) {
            System.out.print("-");
        }
    }

    private static void printItemTitle() {
        System.out.printf("\n%-10s  %-24s  %-19s  %-11s  %-17s  %-15s  %-14s\n", "Item ID", "Type", "Description", "Quantity", "Value Per Item", "Total Amount", "Expiry Date");
    }

    public static void printItemHeader() {
        printItemEnDash();
        printItemTitle();
        printItemEnDash();
    }

    public static boolean validateDateFormat(String date) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
    
    
    
    
    
    
    
}
