/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import ADT.SortedDoublyLinkedListSet;
import ADT.SortedListSetInterface;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class Donor implements Comparable<Donor> {
    private String donorId;
    private String name;
    private String contactPerson; //can be same as name 
    private String contact;
    private String email;
    private String address;
    private String category; //government,public, private
    private String status; //active,inactive, prospect, banned

    public Donor(String donorId, String name, String contactPerson,String contact, String email, String address,String category, String status) {
        this.donorId = donorId;
         this.category = category;
        this.name = name;
        this.contactPerson = contactPerson;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.status = status;
    }
    
     public Donor(String donorId,String category, String name,String contactName, String contact, String email, String address) {
        this.donorId = donorId;
        this.category = category;
        this.name = name;
        this.contactPerson = contactName;
        this.contact = contact;
        this.email = email;   
        this.address = address;
        this.status = "prospect";
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    public int compareTo(Donor other) {
        return this.donorId.compareTo(other.donorId);
    } 
    
   @Override
    public String toString() {
        return String.format("\n%-10s %-15s %-40s %-20s %-15s %-20s %-70s %-10s\n",
                             donorId, category, name, contactPerson, contact, email, address, status);
    }
        
}
