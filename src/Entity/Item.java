/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author USER
 */
public class Item implements Comparable<Item> {

    private String itemId;
    private String type;
    private String desc;
    private int quantity;
    private double valuePerItem;
    private double totalAmount;
    private Date expiryDate;

    public Item() {
    }

    //Monetary donations
    public Item(String itemId, String type, String desc, double totalAmount) {
        this.itemId = itemId;
        this.desc = desc;
        this.type = type;
        this.totalAmount = totalAmount;
    }

    //Except Monetary, Food, and Beverage donations
    public Item(String itemId, String type, String desc, int quantity, double valuePerItem) {
        this.itemId = itemId;
        this.type = type;
        this.desc = desc;
        this.quantity = quantity;
        this.valuePerItem = valuePerItem;
        this.totalAmount = calculateTotalAmount();
    }

    //Food, and Beverage donations
    public Item(String itemId, String type, String desc, int quantity, double valuePerItem, Date expiryDate) {
        this.itemId = itemId;
        this.type = type;
        this.desc = desc;
        this.quantity = quantity;
        this.valuePerItem = valuePerItem;
        this.totalAmount = calculateTotalAmount();
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalAmount = calculateTotalAmount();
    }

    public double getValuePerItem() {
        return valuePerItem;
    }

    public void setValuePerItem(double valuePerItem) {
        this.valuePerItem = valuePerItem;
        this.totalAmount = calculateTotalAmount();
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

    private double calculateTotalAmount() {
        return (double) this.quantity * this.valuePerItem;
    }

    @Override
    public int compareTo(Item other) {
        return this.itemId.compareTo(other.itemId);
    }

    @Override
    public String toString() {
        return (expiryDate != null)
                ? String.format("%-10s  %-24s  %-19s  %8d  %17.2f  %15.2f  %14s", itemId, type, desc, quantity, valuePerItem, totalAmount, expiryDate)
                : (quantity != 0)
                        ? String.format("%-10s  %-24s  %-19s  %8d  %17.2f  %15.2f  %14s", itemId, type, desc, quantity, valuePerItem, totalAmount, "-")
                        : String.format("%-10s  %-24s  %-19s  %8s  %17s  %15.2f  %14s", itemId, type, desc, "-", "-", totalAmount, "-");
    }

}
