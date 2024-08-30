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
    private SortedListSetInterface<Distribution> distributions;

    public SortedListSetInterface<Distribution> getDistributions() {
        return distributions;
    }

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
        this.distributions = new SortedDoublyLinkedListSet<>();
    }

    public Donee() {
        this.requests = new SortedDoublyLinkedListSet<>();
        this.distributions = new SortedDoublyLinkedListSet<>();
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

    public static void setSortByCriteria(SortByCriteria criteria) {
        sortByCriteria = criteria;
    }

    public static SortByCriteria sortByCriteria = SortByCriteria.DONEEID_INASC;

    public enum SortByCriteria {
        DONEEID_INASC,
        DONEEID_INDESC,
        REQUESTDATE_INASC,
        REQUESTDATE_INDESC,
        RECEIVEDATE_INASC,
        RECEIVEDATE_INDESC;
    }

    @Override
    public int compareTo(Donee other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to a null object");
        }

        switch (sortByCriteria) {
            case DONEEID_INASC:
                return this.doneeId.compareTo(other.doneeId);
            case DONEEID_INDESC:
                return other.doneeId.compareTo(this.doneeId);
            case REQUESTDATE_INASC:
                return compareByEarliestRequestDate(this, other);
            case REQUESTDATE_INDESC:
                return compareByLatestRequestDate(this, other);
            case RECEIVEDATE_INASC:
                return compareByEarliestReceiveDate(this, other);
            case RECEIVEDATE_INDESC:
                return compareByLatestReceiveDate(this, other);
            default:
                return this.doneeId.compareTo(other.doneeId); // Default to ascending by ID
        }
    }

// Helper method to compare by earliest request date
    private int compareByEarliestRequestDate(Donee d1, Donee d2) {
        Request earliestD1 = getEarliestRequest(d1);
        Request earliestD2 = getEarliestRequest(d2);

        if (earliestD1 == null && earliestD2 == null) {
            return 0;
        }
        if (earliestD1 == null) {
            return -1;
        }
        if (earliestD2 == null) {
            return 1;
        }

        return earliestD1.getRequestDate().compareTo(earliestD2.getRequestDate());
    }

// Helper method to compare by latest request date
    private int compareByLatestRequestDate(Donee d1, Donee d2) {
        Request latestD1 = getLatestRequest(d1);
        Request latestD2 = getLatestRequest(d2);

        if (latestD1 == null && latestD2 == null) {
            return 0;
        }
        if (latestD1 == null) {
            return -1;
        }
        if (latestD2 == null) {
            return 1;
        }

        return latestD2.getRequestDate().compareTo(latestD1.getRequestDate()); // Descending order
    }

// Retrieve the earliest request
    private Request getEarliestRequest(Donee donee) {
        if (donee.requests == null || donee.requests.isEmpty()) {
            return null;
        }
        return donee.requests.getFirstEntry(); // Assumes the SortedListSetInterface sorts by request date
    }

// Retrieve the latest request
    private Request getLatestRequest(Donee donee) {
        if (donee.requests == null || donee.requests.isEmpty()) {
            return null;
        }
        return donee.requests.getLastEntries(); // Assumes the SortedListSetInterface sorts by request date
    }

    private int compareByEarliestReceiveDate(Donee d1, Donee d2) {
        Distribution earliestD1 = getEarliestDistribution(d1);
        Distribution earliestD2 = getEarliestDistribution(d2);

        Date earliestDateD1 = (earliestD1 != null) ? earliestD1.getDistributionDate() : null;
        Date earliestDateD2 = (earliestD2 != null) ? earliestD2.getDistributionDate() : null;

        if (earliestDateD1 == null && earliestDateD2 == null) {
            return 0;
        }
        if (earliestDateD1 == null) {
            return -1;
        }
        if (earliestDateD2 == null) {
            return 1;
        }

        return earliestDateD1.compareTo(earliestDateD2);
    }

    private int compareByLatestReceiveDate(Donee d1, Donee d2) {
        Distribution latestD1 = getLatestDistribution(d1);
        Distribution latestD2 = getLatestDistribution(d2);

        Date latestDateD1 = (latestD1 != null) ? latestD1.getDistributionDate() : null;
        Date latestDateD2 = (latestD2 != null) ? latestD2.getDistributionDate() : null;

        if (latestDateD1 == null && latestDateD2 == null) {
            return 0;
        }
        if (latestDateD1 == null) {
            return -1;
        }
        if (latestDateD2 == null) {
            return 1;
        }

        return latestDateD2.compareTo(latestDateD1); // Descending order
    }

    private Distribution getEarliestDistribution(Donee donee) {
        if (donee.getDistributions() == null || donee.getDistributions().isEmpty()) {
            return null;
        }
        return donee.getDistributions().getFirstEntry(); // Assumes the SortedListSetInterface sorts by distribution date
    }

    private Distribution getLatestDistribution(Donee donee) {
        if (donee.getDistributions() == null || donee.getDistributions().isEmpty()) {
            return null;
        }
        return donee.getDistributions().getLastEntries();
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
                outputStr += String.format("\n%-15s %-20s %-20s %-25s %-20s %-30s %-15s", "", "", "", "", "", "", "");
            }
        } else {
            outputStr += String.format("%5s %25s", "-", "-");
        }

        return outputStr;
    }

}
