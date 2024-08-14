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
 * @author USER
 */
public class Donation implements Comparable<Donation> {

    private String donationId;
    private Date donationDate;
    private SortedListSetInterface<Item> donatedItemList;
    private Donor donor;
    private boolean isAssigned = false;

    public Donation(String donationId, Date donationDate, Donor donor) {
        this.donationId = donationId;
        this.donationDate = donationDate;
        this.donatedItemList = new SortedDoublyLinkedListSet<>();
        this.donor = donor;
    }

    public Donation(String donationId, Date donationDate) {
        this.donationId = donationId;
        this.donationDate = donationDate;
        this.donatedItemList = new SortedDoublyLinkedListSet<>();
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    public SortedListSetInterface<Item> getDonatedItemList() {
        return donatedItemList;
    }

    public void setDonatedItemList(SortedListSetInterface<Item> donatedItemList) {
        this.donatedItemList = donatedItemList;
    }
    
    public boolean isIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public void assignItems(Item item) {
        donatedItemList.add(item);
        isAssigned = true;
    }

    @Override
    public int compareTo(Donation other) {
        return this.donationId.compareTo(other.donationId);
    }

    @Override
    public String toString() {
        String outputStr = String.format("\n%-15s  %-15s  ", donationId, donationDate);
        Iterator<Item> iterator = donatedItemList.getIterator();
        Item item = iterator.next();
        outputStr += String.format(item + "");
        while (iterator.hasNext()) {
            item = iterator.next();
            outputStr += String.format("\n%-15s  %-15s  ", "", "");
            outputStr += String.format(item + "");
        }
//        if(donor != null){
//            outputStr += String.format(donor.getDonorId() + "  " + donor.getName() + "  " + donor.getContact());
//        }
        return outputStr;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        final Donation other = (Donation) obj;
        if (this == obj) {
            return true;
        } else if (this.donationId.equals(other.donationId)) {
            return true;
        }
        
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        if (!Objects.equals(this.donationId, other.donationId)) {
            return false;
        }
        if (!Objects.equals(this.donationDate, other.donationDate)) {
            return false;
        }
        if (!Objects.equals(this.donatedItemList, other.donatedItemList)) {
            return false;
        }
        return Objects.equals(this.donor, other.donor);
    }
}
