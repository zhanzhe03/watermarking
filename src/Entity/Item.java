/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import ADT.*;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class Item implements Comparable<Item> {

    private String itemId;
    private String type; //cash, book, clothes, food, drink
    //private String desc; //cash, story book, clothes, pants, rice, bottle water
    private int quantity;
    private double valuePerItem; //for real items
    private double totalAmount; //for cash
    private Date expiryDate;

    public Item() {
    }

    public Item(String itemId, String type) {
        this.itemId = itemId;
        this.type = type;
    }

    public Item(String itemId, String type, double totalAmount) {
        this.itemId = itemId;
        this.type = type;
        this.totalAmount = totalAmount;
    }

    public Item(String itemId, String type, int quantity, double valuePerItem) {
        this.itemId = itemId;
        this.type = type;
        this.quantity = quantity;
        this.valuePerItem = valuePerItem;
        this.totalAmount = calculateTotalAmount(quantity, valuePerItem);
    }

    public Item(String itemId, String type, int quantity, double valuePerItem, Date expiryDate) {
        this.itemId = itemId;
        this.type = type;
        this.quantity = quantity;
        this.valuePerItem = valuePerItem;
        this.totalAmount = calculateTotalAmount(quantity, valuePerItem);
        this.expiryDate = expiryDate;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValuePerItem() {
        return valuePerItem;
    }

    public void setValuePerItem(double valuePerItem) {
        this.valuePerItem = valuePerItem;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    private double calculateTotalAmount(int quantity, double valuePerItem) {
        return (double) quantity * valuePerItem;
    }

    @Override
    public int compareTo(Item other) {
        return this.itemId.compareTo(other.itemId);
    }

    @Override
    public String toString() {
        return (expiryDate != null)
                ? String.format("%-15s  %-15s  %-15d  %-15.2f  %-15.2f  %-15s", itemId, type, quantity, valuePerItem, totalAmount, expiryDate)
                : (quantity != 0)
                        ? String.format("%-15s  %-15s  %-15d  %-15.2f  %-15.2f  %-15s", itemId, type, quantity, valuePerItem, totalAmount, "-")
                        : String.format("%-15s  %-15s  %-15s  %-15s  %-15.2f  %-15s", itemId, type, "-", "-", totalAmount, "-");
    }

}
