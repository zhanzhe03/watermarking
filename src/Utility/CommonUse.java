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
import java.util.Iterator;

/**
 *
 * @author szewen
 */
public class CommonUse {

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
           if(donee.getDoneeId().equals(ID)){
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
}
