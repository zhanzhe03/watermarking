/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import ADT.SortedListSetInterface;
import Entity.Donation;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class DonationUI {

    private Scanner scanner = new Scanner(System.in);

    
    public LocalDate getLocalDate(){
        return LocalDate.now();
    }
    
    public String getDonationMenu() {
        System.out.println(""
                + "\nDONATION MANAGEMENT"
                + "\n 1. List all donations (done)"
                + "\n 2. Add a new donation (done)"
                + "\n 3. Search donation details (can amend or remove)"
                + "\n 4. Amend donation details"
                + "\n 5. Track donated items in categories"
                + "\n 6. List donation by different donor"
                + "\n 7. remove donation"
                + "\n 8. Filter donation based on criteria"
                + "\n 9. Generate summary reports"
                + "\n 9. Back to MAIN MENU");
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
    }
    
    public String getRegisteredMenu(){
        System.out.println(""
                + "\nThis Contact Number has not been registered. Find a solution"
                + "\n 1. Registered a new donor"
                + "\n 2. Donate anonymously");
        System.out.print("\nopt > ");
        return scanner.nextLine();
    }
    
    public String getAmendRemoveMenu(){
        System.out.println(""
                + "\nAvailable Operation for this donation"
                + "\n 1. Amend donation date"
                + "\n 2. Amend donated item record"
                + "\n 3. Remove this donation"
                + "\n 9. Return");
        System.out.print("opt > ");
        return scanner.nextLine();
    }

    public void printDonorMade(Donation donation){
        System.out.println("\nMade By");
        System.out.println("Donor ID : " + donation.getDonor().getDonorId());
        System.out.println("Name : " + donation.getDonor().getName());
        System.out.println("Contact : " + donation.getDonor().getContact());
        System.out.println("Email : " + donation.getDonor().getEmail());
    }
    
    public void printText(String text) {
        System.out.println("\n" + text + "\n");
    }
    
    public String getInputString(String desc){
        System.out.print(desc);
        return scanner.nextLine();
    }

//    public String getInputType() {
//        System.out.print("Type : ");
//        return scanner.nextLine();
//    }
//
//    public String getInputAmount() {
//        System.out.print("Amount : ");
//        return scanner.nextLine();
//    }
//
//    public String getInputQuantity() {
//        System.out.print("Quantity : ");
//        return scanner.nextLine();
//    }
//
//    public String getInputValuePerItem() {
//        System.out.print("Value Per Item : ");
//        return scanner.nextLine();
//    }
//    
//    public String getInputContact() {
//        System.out.print("Contact Number : ");
//        return scanner.nextLine();
//    }
//    
//    public String getInputName(){
//        System.out.print("Name : ");
//        return scanner.nextLine();
//    }
//    
//    public String getInputEmail(){
//        System.out.print("Email : ");
//        return scanner.nextLine();
//    }

    public char yesNoOption(String textString) {
        System.out.print("\n" + textString + " (Y = yes)? ");
        return scanner.nextLine().trim().toUpperCase().charAt(0);
    }

    public void printDonationTitle(){
        System.out.printf("\n%-15s  %-15s  %-15s  %-15s  %-15s  %-15s  %-15s  %-15s\n", "Donation ID", "Donation Date", "Item ID", "Type", "Quantity", "Value Per Item", "Total Amount", "Expiry Date");
    }
    
    public void printOneDonation(Donation donation) {
        System.out.println(donation);
    }
    
    public void printAllDonations(SortedListSetInterface<Donation> donations){
        System.out.print(donations);
    }
    
    public void printNumberOfEntries(SortedListSetInterface<Donation> donations){
        System.out.println("\nTotal Number of Donation > " + donations.getNumberOfEntries());
    }
}
