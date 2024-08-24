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
    private SortedListSetInterface<Request> requests;

    public void addRequest(Request request) {
        if (requests == null) {
            requests = new SortedDoublyLinkedListSet<>();
        }
        requests.add(request);
    }

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
        this.requests = new SortedDoublyLinkedListSet<>();
    }

    public Donee() {
        this.requests = new SortedDoublyLinkedListSet<>();
    }

    public SortedListSetInterface<Request> getRequests() {
        return requests;
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
        // Base Donee information
        String outputStr = String.format(
                "\n%-15s %-20s %-20s %-25s %-20s %-30s %-15s",
                doneeId, doneeType, name, email, contact, address, location
        );
        if (requests != null && requests.getNumberOfEntries() > 0) {
            Iterator<Request> iterator = requests.getIterator();
            while (iterator.hasNext()) {
                Request request = iterator.next();
                outputStr += String.format("%-20s %-20s", request.getRequestDate(), request.getRequestItems());
                outputStr += String.format("\n%-15s %-20s %-20s %-25s %-20s %-30s %-15s","","","","","","","");
            }
        } else {
            outputStr += String.format("%5s %25s", "-", "-");
        }

        return outputStr;
    }

}
