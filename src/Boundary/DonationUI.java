/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import ADT.SortedListSetInterface;
import Entity.Donation;
import Entity.Item;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class DonationUI {

    private final Scanner scanner = new Scanner(System.in);

    public String getDonationMenu() {
        System.out.println(""
                + "\nDONATION MANAGEMENT SYSTEM"
                + "\n 1. List all donation and item"
                + "\n 2. Add donation (donor registration, and any donor status lead to unsuccess donation)"
                + "\n 3. Search donation details"
                + "\n 4. Amend donation"
                + "\n 5. Remove donation"
                + "\n 6. Filter donation based on criteria"
                + "\n 7. Track donated items in different type"
                + "\n 8. Generate summary reports (on hold)"
                + "\n 9. Back to MAIN MENU");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }

    //1. List
    public String getListMenu() {
        System.out.println(""
                + "\n----------------------------------------------------"
                + "\nOthers List Method"
                + "\n----------------------------------------------------"
                + "\n 1. Donation"
                + "\n 2. Only Item"
                + "\n----------------------------------------------------"
                + "\n 9. Return");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }
    
    public String getListDonationMenu() {
        System.out.println(""
                + "\n----------------------------------------------------"
                + "\nAll Donation Display Method"
                + "\n----------------------------------------------------"
                + "\n 1. Sorted by Donation ID in ASC"
                + "\n 2. Sorted by Donation ID in DESC"
                + "\n 3. Sorted by Donation Date in ASC"
                + "\n 4. Sorted by Donation Date in DESC"
                + "\n 5. Sorted by Donation Status in ASC"
                + "\n 6. Sorted by Donation Status in DESC"
                + "\n----------------------------------------------------"
                + "\n 9. Return");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }
    
    public String getListItemMenu() {
        System.out.println(""
                + "\n----------------------------------------------------"
                + "\nAll Item Display Method"
                + "\n----------------------------------------------------"
                + "\n 1. Sorted by Item ID in ASC"
                + "\n 2. Sorted by Item ID in DESC"
                + "\n 3. Sorted by Item Type in ASC"
                + "\n 4. Sorted by Item Type in DESC"
                + "\n 5. Sorted by Item Description in ASC"
                + "\n 6. Sorted by Item Description in DESC"
                + "\n 7. Sorted by Item Total Amount in ASC"
                + "\n 8. Sorted by Item Total Amount in DESC"
                + "\n----------------------------------------------------"
                + "\n 9. Return");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }

    //2. Add
    public String getAddDonationMenu() {
        System.out.println(""
                + "\n----------------------------------------------------"
                + "\nDonation Added Method"
                + "\n----------------------------------------------------"
                + "\n 1. Add a New Donation"
                + "\n 2. Add Items in Existing Donation Records"
                + "\n----------------------------------------------------"
                + "\n 9. Return");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }

    public String getItemTypeMenu() {
        System.out.println(""
                + "\n----------------------------------------------------"
                + "\nDonation Item Type List"
                + "\n----------------------------------------------------"
                + "\n 1. Monetary"
                + "\n 2. Clothing and Apparel"
                + "\n 3. Food and Beverage"
                + "\n 4. Household Items"
                + "\n 5. Educational Materials"
                + "\n 6. Electronic"
                + "\n 7. Medical"
                + "\n----------------------------------------------------"
                + "\n 9. Return");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }
    
    public String getMonetoryDescMenu(){
        System.out.println(""
                + "\nMonetary donation method"
                + "\n 1. Cash"
                + "\n 2. Online Transfer"
                + "\n 3. QR Code Payment");
        System.out.print("opt > ");
        return scanner.nextLine();
    }
    
    public String getRegisteredMenu() {
        System.out.println(""
                + "\nThis Contact Number has not been registered. Find a solution"
                + "\n 1. Registered a new donor"
                + "\n 2. Donate anonymously");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }
    
    //4. Amend
    public String getDonationAmendedMenu() {
        System.out.println(""
                + "\n----------------------------------------------------"
                + "\nAvailable Operation for this donation"
                + "\n----------------------------------------------------"
                + "\n 1. Amend donation date"
                + "\n 2. Amend donated item record"
                + "\n----------------------------------------------------"
                + "\n 9. Return");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }
    
    public String getItemAmendedMenu(){
        System.out.println(""
                + "\n----------------------------------------------------"
                + "\nAvailable fields for Item Amended"
                + "\n----------------------------------------------------"
                + "\n 1. Description"
                + "\n 2. Quantity"
                + "\n 3. Value Per Item"
                + "\n 4. Amount (for monetary type only)"
                + "\n 5. Expity Date (for food and beverage only)"
                + "\n----------------------------------------------------"
                + "\n 9. Return");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }
    
    //5. Remove
    public String getRemoveDonationMenu(){
        System.out.println(""
                + "\n----------------------------------------------------"
                + "\nDonation Removed Method"
                + "\n----------------------------------------------------"
                + "\n 1. Remove a specific donation"
                + "\n 2. Remove item from a donation"
                + "\n 3. By a Specific Donation Date"
                + "\n 4. Remove all before a Specific Donation Date"
                + "\n 5. By a Range of Donation Date"
                + "\n 6. Remove Expired Item"
                + "\n 7. Remove all donation made by a donor"
                + "\n----------------------------------------------------"
                + "\n 9. Return");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }
    
    //6. Filter
    public String getFilterDonationMenu(){
        System.out.println(""
                + "\n----------------------------------------------------"
                + "\nDonation and Item Filter Method"
                + "\n----------------------------------------------------"
                + "\n 1. Donation Status"
                + "\n 2. Donation made by a donor"
                + "\n 3. Donation within the past week"
                + "\n 4. Donation within the past month"
                + "\n 5. Donation within a Range of Date"
                + "\n 6. Expired Items"
                + "\n 7. Upcoming Expiry Items (within next 15 days)"
                + "\n----------------------------------------------------"
                + "\n 9. Return");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }
    
    public String getDonationStatusOption(){
        System.out.println(""
                + "\n----------------------------------------------------"
                + "\nDonation Status"
                + "\n----------------------------------------------------"
                + "\n 1. Pending"
                + "\n 2. Processing"
                + "\n 3. Distributing or Fully Distributed"
                + "\n----------------------------------------------------"
                + "\n 9. Return");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }

    public String getInputString(String desc) {
        System.out.print(desc);
        return scanner.nextLine();
    }
    
    public char yesNoOption(String textString) {
        System.out.print("\n" + textString + " (Y = yes)? ");
        return scanner.nextLine().trim().toUpperCase().charAt(0);
    }

    public void printTitle(String text) {
        System.out.println("\n" + text + "\n");
    }

    public void printText(String text) {
        System.out.println(text);
    }
    
    public void printItemTitle() {
        System.out.printf("\n%-10s  %-24s  %-19s  %-11s  %-17s  %-15s  %-14s\n", "Item ID", "Type", "Description", "Quantity", "Value Per Item", "Total Amount", "Expiry Date");
    }

    public void printOneItem(Item item) {
        System.out.println(item);
    }
    
    public void printAllItems(SortedListSetInterface<Item> items){
        System.out.print(items);
    }
    
    public void printDonationTitle() {
        System.out.printf("\n%-14s  %-16s  %-19s  %-10s  %-24s  %-19s  %-11s  %-17s  %-15s  %-14s\n", "Donation ID", "Donation Date", "Status","Item ID", "Type", "Description", "Quantity", "Value Per Item", "Total Amount", "Expiry Date");
    }
    
    public void printOneDonation(Donation donation) {
        System.out.println(donation);
    }

    public void printAllDonations(SortedListSetInterface<Donation> donations) {
        System.out.print(donations);
    }

    public void printTotalDonation(SortedListSetInterface<Donation> donations) {
        System.out.println("\nTotal Number of Donation > " + donations.getNumberOfEntries() + "\n");
    }
    
    public void printTotalItem(SortedListSetInterface<Item> items) {
        System.out.println("\nTotal Number of Item > " + items.getNumberOfEntries() + "\n");
    }
    
    public void printDonationEnDash(){
        for (int i = 0; i < 180; i++) 
            System.out.print("-");
    }
    
    public void printItemEnDash(){
        for (int i = 0; i < 125; i++) 
            System.out.print("-");
    }
    
    public void printDonorMade(Donation donation) {
        System.out.println("\nMade By");
        System.out.println("Donor ID : " + donation.getDonor().getDonorId());
        System.out.println("Name : " + donation.getDonor().getName());
        System.out.println("Contact : " + donation.getDonor().getContact());
        System.out.println("Email : " + donation.getDonor().getEmail());
    }
}
