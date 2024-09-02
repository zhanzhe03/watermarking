/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import ADT.SortedDoublyLinkedListSet;
import ADT.SortedListSetInterface;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author Chew Zhan Zhe
 */
public class Donation implements Comparable<Donation>, Cloneable {

    private String donationId;
    private Date donationDate;
    private SortedListSetInterface<Item> donatedItemList;
    private String status;
    private Donor donor;

    public Donation(String donationId, Date donationDate, String status,Donor donor) {
        this.donationId = donationId;
        this.donationDate = donationDate;
        this.donatedItemList = new SortedDoublyLinkedListSet<>();
        this.status = status;
        this.donor = donor;
    }

    public Donation(String donationId, Date donationDate, String status) {
        this.donationId = donationId;
        this.donationDate = donationDate;
        this.donatedItemList = new SortedDoublyLinkedListSet<>();
        this.status = status;
    }

    //get the identifier field for sorting, start
    public static void setSortByCriteria(SortByCriteria criteria) {
        sortByCriteria = criteria;
    }

    public static SortByCriteria sortByCriteria = SortByCriteria.DONATIONID_INASC;

    public enum SortByCriteria {
        DONATIONID_INASC,
        DONATIONID_INDESC,
        DONATIONDATE_INASC,
        DONATIONDATE_INDESC,
        STATUS_INASC,
        STATUS_INDESC;
    }

    @Override
    public int compareTo(Donation otherDonation) {
        if (otherDonation == null) {
            throw new NullPointerException("Cannot compare to a null object");
        }
        switch (sortByCriteria) {
            case DONATIONID_INASC:
                return this.donationId.compareTo(otherDonation.donationId);
            case DONATIONID_INDESC:
                return otherDonation.donationId.compareTo(this.donationId);
            case DONATIONDATE_INASC:
                return this.donationDate.compareTo(otherDonation.donationDate);
            case DONATIONDATE_INDESC:
                return otherDonation.donationDate.compareTo(this.donationDate);
            case STATUS_INASC:
                return this.status.compareTo(otherDonation.status);
            case STATUS_INDESC:
                return otherDonation.status.compareTo(this.status);
            default:
                return this.donationId.compareTo(otherDonation.donationId);
        }
    }
    //sorting function method, end

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
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }
    
    public void assignItems(Item item) {
        donatedItemList.add(item);
    }

    @Override
    public String toString() {
        String outputStr = String.format("\n%-14s  %-16s  %-19s  ", donationId, donationDate, status);
        Iterator<Item> iterator = donatedItemList.getIterator();
        Item item = iterator.next();
        outputStr += String.format(item + "");
        while (iterator.hasNext()) {
            item = iterator.next();
            outputStr += String.format("\n%-14s  %-16s  %-19s  ","", "", "");
            outputStr += String.format(item + "");
        }
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
    
    @Override
    public Donation clone() {
        try {
            Donation clonedDonation = (Donation) super.clone();

            // Deep copy of donationDate
            clonedDonation.donationDate = this.donationDate.clone();

            // Deep copy of donor, if donor is not null
            if (this.donor != null) {
                clonedDonation.donor = this.donor.clone(); // Assuming Donor has a clone method
            }

            // Deep copy of donatedItemList
            clonedDonation.donatedItemList = new SortedDoublyLinkedListSet<>();
            Iterator<Item> iterator = this.donatedItemList.getIterator();
            while (iterator.hasNext()) {
                Item item = iterator.next();
                clonedDonation.donatedItemList.add(item.clone()); // Assuming Item has a clone method
            }

            return clonedDonation;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should never happen
        }
    }


}
