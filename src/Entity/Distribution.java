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
 * @author szewen
 */
public class Distribution implements Comparable<Distribution> {  //Comparable interface should be parameterized with the same type as the class itself

    private String distributionId;
    private Date distributionDate;
    private SortedListSetInterface<SelectedItem> distributedItemList;       //multiple Items

    private Donee donee;
    private Item item;

    public Distribution(String distributionId, Date distributionDate, SortedListSetInterface<SelectedItem> distributedItemList, Donee donee) {
        this.distributionId = distributionId;
        this.distributionDate = distributionDate;
        this.distributedItemList = new SortedDoublyLinkedListSet<>();
        this.donee = donee;
    }

    public Distribution(String distributionId, Date distributionDate) {
        this.distributionId = distributionId;
        this.distributionDate = distributionDate;
        this.distributedItemList = new SortedDoublyLinkedListSet<>();

    }


    public void assignItems(SelectedItem item) {
        distributedItemList.add(item);
    }

    public void addSelectedItem(SelectedItem selectedItem) {
     //   if (selectedItem.getSelectedQuantity() > item.getQuantity()) {
       //     throw new IllegalArgumentException("Selected quantity exceeds available quantity.");
        //}
        distributedItemList.add(selectedItem);
    }

    public String getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
    }

    public Date getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(Date date) {
        this.distributionDate = distributionDate;
    }

    public SortedListSetInterface<SelectedItem> getDistributedItemList() {
        return distributedItemList;
    }

    public void setDistributedItemList(SortedListSetInterface<SelectedItem> distributedItemList) {
        this.distributedItemList = distributedItemList;
    }

    public Donee getDonee() {
        return donee;
    }

    public void setDonee(Donee donee) {
        this.donee = donee;
    }

    @Override
    public int compareTo(Distribution other) {
        return this.distributionId.compareTo(other.distributionId);
    }

    @Override
    public String toString() {
        String outputStr = String.format("\n%-15s  %-15s  ", distributionId, distributionDate);
        Iterator<SelectedItem> iterator = distributedItemList.getIterator();
        SelectedItem item = iterator.next();
        outputStr += String.format(item + "");
        while (iterator.hasNext()) {
            item = iterator.next();
            outputStr += String.format("\n%-15s  %-15s  ", "", "");
            outputStr += String.format(item + "");
        }

        return outputStr;
    }

}
