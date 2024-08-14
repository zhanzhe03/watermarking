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
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.category = category;
        this.contactPerson = contactPerson;
        this.status = status;
    }
    
     public Donor(String donorId, String name,String contact, String email, String address,String category) {
        this.donorId = donorId;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.category = category;
        this.contactPerson = name;
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
        if (!Objects.equals(this.donorId, other.donorId)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.contact, other.contact);
    }
    
    @Override
    public int compareTo(Donor other) {
        return this.donorId.compareTo(other.donorId);
    }
}
