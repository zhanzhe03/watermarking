/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import ADT.SortedListSetInterface;
import Entity.*;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class DonationUI {

    private Scanner scanner = new Scanner(System.in);

    public String getDonationMenu() {
        System.out.println(""
                + "\nDONATION MANAGEMENT"
                + "\n 1. List all donation (done) - ASC, DESC"
                + "\n 2. Add donation (done) - Add new, Add on prev"
                + "\n 3. Search donation details (done) - can remove, amend"
                + "\n 4. Amend donation"
                + "\n 5. Remove donation (done) - by specific, before, and range date"
                + "\n 6. Filter donation"
                + ""
                + "\n 5. Track donated items in categories" //to understand the item distributed patterns
                + "\n 5. "
                + "\n 6. "
                + "\n 7. List donation by different donor" // from questions
                + "\n 8. Filter donation based on criteria" 
                + "\n 9. Generate summary reports"
                + "\n 9. Back to MAIN MENU");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }

    public String getListDonationMenu() {
        System.out.println(""
                + "\nDonation Display Method"
                + "\n  1. Sorted by Donation ID in ASC"
                + "\n  2. Sorted by Donation ID in DASC"
                + "\nItem Display Method "
                + "\n  3. Sorted by Item ID in ASC"
                + "\n  4. Sorted by Item ID in DESC"
                + "\n  5. Sorted by Item Total Amount in ASC"
                + "\n  6. Sorted by Item Total Amount in DESC"
                + "\n  9. Return");
        System.out.print("opt > ");
        return scanner.nextLine();
    }

    public String getAddDonationMenu() {
        System.out.println(""
                + "\nDonation Added Method"
                + "\n 1. Add a New Donation"
                + "\n 2. Add Items in Existing Donation Records"
                + "\n 9. Return");
        System.out.print("opt > ");
        return scanner.nextLine();
    }

    public String getItemTypeMenu() {
        System.out.println(""
                + "\nDonation Item Type List"
                + "\n 1. Monetary"
                + "\n 2. Clothing and Apparel"
                + "\n 3. Food and Beverage"
                + "\n 4. Household Items"
                + "\n 5. Educational Materials"
                + "\n 6. Electronic"
                + "\n 7. Medical");
        System.out.print("opt > ");
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
    
    public String getAfterSearchMenu() {
        System.out.println(""
                + "\nAvailable Operation for this donation"
                + "\n 1. Amend donation date"
                + "\n 2. Amend donated item record"
                + "\n 3. Remove this donation"
                + "\n 9. Return");
        System.out.print("opt > ");
        return scanner.nextLine();
    }
    
    public String getItemAmendMenu(){
        System.out.println(""
                + "\nAvailable fields for Amended"
                + "\n 1. Description"
                + "\n 2. Quantity"
                + "\n 3. Value Per Item"
                + "\n 4. Amount (for monetary type only)"
                + "\n 5. Expity Date (for food and beverage only)"
                + "\n 6. Remove this item from this donation"
                + "\n 9. Return");
        System.out.print("opt > ");
        return scanner.nextLine();
    }
    
    public String getAmendDonationMenu(){
        System.out.println(""
                + "\nDonation Amended Method"
                + "\n 1. "
                + "\n 2. ");
        System.out.println("\nopt > ");
        return scanner.nextLine();
    }
    
    public String getRemoveDonationMenu(){
        System.out.println(""
                + "\nDonation Removed Method"
                + "\n 1. By a Specific Donation Date"
                + "\n 2. Removed All before a Specific Donation Date"
                + "\n 3. By a Range of Donation Date"
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

    public void printDonationTitle() {
        System.out.printf("\n%-14s  %-16s  %-10s  %-24s  %-19s  %-11s  %-17s  %-15s  %-14s\n", "Donation ID", "Donation Date", "Item ID", "Type", "Description", "Quantity", "Value Per Item", "Total Amount", "Expiry Date");
    }
    
    public void printItemTitle() {
        System.out.printf("\n%-10s  %-24s  %-19s  %-11s  %-17s  %-15s  %-14s\n", "Item ID", "Type", "Description", "Quantity", "Value Per Item", "Total Amount", "Expiry Date");
    }

    public void printOneItem(Item item) {
        System.out.println(item);
    }
    
    public void printOneDonation(Donation donation) {
        System.out.println(donation);
    }
    
    public void printAllItems(SortedListSetInterface<Item> items){
        System.out.print("\n" + items);
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
        for (int i = 0; i < 160; i++) 
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
