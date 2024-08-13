/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Objects;
import java.io.Serializable;
import ADT.*;
import java.util.Iterator;

/**
 *
 * @author USER
 */
public class Donee implements Comparable<Donee> {

    private String doneeId;
    private String doneeType;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDoneeId() {
        return doneeId;
    }

    public void setDoneeId(String doneeId) {
        this.doneeId = doneeId;
    }

    public void setDoneeType(String doneeType) {
        this.doneeType = doneeType;
    }

    public String getDoneeType() {
        return doneeType;
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

    public Donee(String doneeId, String doneeType, String name, String email, String contact, String address, String location) {
        this.doneeType = doneeType;
        this.doneeId = doneeId;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.location = location;
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
        final Donee other = (Donee) obj;
        if (!Objects.equals(this.doneeId, other.doneeId)) {
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
    public int compareTo(Donee other) {
        return this.doneeId.compareTo(other.doneeId);
    }

    @Override
    public String toString() {
        // Format the basic information of the donee
        String outputStr = String.format("%-20s %-20s %-20s %-25s %-20s %-35s %-20s",
                doneeId, doneeType, name, email, contact, address, location);

        return outputStr;
    }

}
