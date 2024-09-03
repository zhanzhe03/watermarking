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
 * @author USER
 */
public class Donor implements Comparable<Donor> , Cloneable{

    private String donorId;
    private String category; //government,public, private
    private String name;
    private String contactPerson;
    private String contact;
    private String email;
    private String address;
    private String status; //active,inactive, prospect, banned
    private SortedListSetInterface<Donation> donationList;
    private Date registeredDate;
    private double totalDonatedAmount;

    public Donor() {
        this.donorId = "";
        this.category = "";
        this.name = "";
        this.contactPerson = "";
        this.contact = "";
        this.email = "";
        this.address = "";
        this.donationList = new SortedDoublyLinkedListSet<>();
        this.status = "";
        this.totalDonatedAmount= 0;
    }

    public Donor(String donorId, String category, String name, String contactPerson, String contact, String email, String address,Date registeredDate, String status) {
        this.donorId = donorId;
        this.category = category;
        this.name = name;
        this.contactPerson = contactPerson;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.status = status;
        this.registeredDate = registeredDate;
        donationList = new SortedDoublyLinkedListSet<>();
        this.totalDonatedAmount = 0;
    }

    public Donor(String donorId, String category, String name, String contactName, String contact, String email, String address, Date registeredDate) {
        this.donorId = donorId;
        this.category = category;
        this.name = name;
        this.contactPerson = contactName;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.status = "prospect";
        this.registeredDate = registeredDate;
        this.donationList = new SortedDoublyLinkedListSet<>();
        this.totalDonatedAmount = 0;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public SortedListSetInterface<Donation> getDonationList() {
        return donationList;
    }

    public void setDonationList(SortedListSetInterface<Donation> donationList) {
        this.donationList = donationList;
    }

    public void addDonationToList(Donation donation) {
        this.donationList.add(donation);
        
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public double getTotalDonatedAmount() {
        return totalDonatedAmount;
    }

    public void setTotalDonatedAmount(double totalDonatedAmount) {
        this.totalDonatedAmount = totalDonatedAmount;
    }
    
     public static void setSortByCriteria(SortByCriteria criteria) {
        sortByCriteria = criteria;
    }

    public static SortByCriteria sortByCriteria = SortByCriteria.DONORID_INASC;

    public enum SortByCriteria {
        DONORID_INASC,
        DONORID_INDESC,
        TOTALDONATEDAMT_INDESC,
    }

    @Override
    public int compareTo(Donor other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to a null object");
        }

        switch (sortByCriteria) {
            case DONORID_INASC:
                return this.donorId.compareTo(other.donorId);
            case DONORID_INDESC:
                return other.donorId.compareTo(this.donorId);
            case TOTALDONATEDAMT_INDESC:
                return Double.compare(other.totalDonatedAmount, this.totalDonatedAmount);
            default:
                return this.donorId.compareTo(other.donorId); // Default to ascending by ID
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Donor other = (Donor) obj;
        return Objects.equals(donorId, other.donorId)
                && Objects.equals(name, other.name)
                && Objects.equals(contactPerson, other.contactPerson)
                && Objects.equals(contact, other.contact)
                && Objects.equals(email, other.email)
                && Objects.equals(address, other.address)
                && Objects.equals(category, other.category)
                && Objects.equals(status, other.status);
    }
    
    @Override
    public Donor clone() {
        try {
            
            Donor clonedDonor = (Donor) super.clone();
            clonedDonor.registeredDate = registeredDate.clone(); // Deep copy of registeredDate
            clonedDonor.donationList = new SortedDoublyLinkedListSet<>();
            Iterator<Donation> iterator = donationList.getIterator();

            iterator = this.donationList.getIterator();
            while (iterator.hasNext()) {
                Donation donation = iterator.next();
                clonedDonor.donationList.add(donation.clone()); 
            }
            
            return clonedDonor;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should never happen
        }
    }

    @Override
    public String toString() {

        String outputStr = String.format("\n%-10s %-30s %-40s %-30s %-15s %-30s %-70s %-20s %-10s",
                donorId, category, name, contactPerson, contact, email, address, registeredDate, status);

        if(!donationList.isEmpty()){
        Iterator<Donation> iterator = donationList.getIterator();
        Donation donation = iterator.next();
        outputStr += String.format(" %-15s %-20s", donation.getDonationId(), donation.getDonationDate());
        while (iterator.hasNext()) {
            donation = iterator.next();
            outputStr += String.format("\n\n%-10s %-30s %-40s %-30s %-15s %-30s %-70s %-20s %-10s", "", "", "", "", "", "", "", "", "");
            outputStr += String.format(" %-15s %-20s", donation.getDonationId(), donation.getDonationDate());
        }
        }else{
            outputStr += String.format(" %-15s %-20s", "-", "-");
        }

        return outputStr.toString();
    }
}
