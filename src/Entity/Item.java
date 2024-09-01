/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author USER
 */
public class Item implements Comparable<Item>, Cloneable {

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

    //get the identifier field for sorting, start
    public static void setSortByCriteria(SortByCriteria criteria) {
        sortByCriteria = criteria;
    }

    public static SortByCriteria sortByCriteria = SortByCriteria.ITEMID_INASC;

    public enum SortByCriteria {
        ITEMID_INASC,
        ITEMID_INDESC,
        TYPE_INASC,
        TYPE_INDESC,
        DESC_INASC,
        DESC_INDESC,
        TOTALAMOUNT_INASC,
        TOTALAMOUNT_INDESC;
    }

    @Override
    public int compareTo(Item otherItem) {
        if (otherItem == null) {
            throw new NullPointerException("Cannot compare to a null object");
        }
        switch (sortByCriteria) {
            case ITEMID_INASC:
                return this.itemId.compareTo(otherItem.itemId);
            case ITEMID_INDESC:
                return otherItem.itemId.compareTo(this.itemId);
            case TYPE_INASC:
                return this.type.compareTo(otherItem.type);
            case TYPE_INDESC:
                return otherItem.type.compareTo(this.type);
            case DESC_INASC:
                return this.desc.compareTo(otherItem.desc);
            case DESC_INDESC:
                return otherItem.desc.compareTo(this.desc);
            case TOTALAMOUNT_INASC:
                return Double.compare(this.totalAmount, otherItem.totalAmount);
            case TOTALAMOUNT_INDESC:
                return Double.compare(otherItem.totalAmount, this.totalAmount);
            default:
                return this.itemId.compareTo(otherItem.itemId);
        }
    }
    //sorting function method, end

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
    public Item clone() {
        try {
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Should never happen
        }
    }

//    @Override
//    public int compareTo(Item other) {
//        return this.itemId.compareTo(other.itemId);
//    }
    @Override
    public String toString() {
        return (expiryDate != null)
                ? String.format("%-10s  %-24s  %-19s  %8d  %17.2f  %15.2f  %14s", itemId, type, desc, quantity, valuePerItem, totalAmount, expiryDate)
                : (quantity != 0)
                        ? String.format("%-10s  %-24s  %-19s  %8d  %17.2f  %15.2f  %14s", itemId, type, desc, quantity, valuePerItem, totalAmount, "-")
                        : String.format("%-10s  %-24s  %-19s  %8s  %17s  %15.2f  %14s", itemId, type, desc, "-", "-", totalAmount, "-");
    }

}
