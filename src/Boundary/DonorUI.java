/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import ADT.SortedListSetInterface;
import Entity.Donor;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class DonorUI {
     Scanner scanner = new Scanner(System.in);

    public String getDonorMenu() {
        System.out.println(""
                + "\n DONOR MANAGEMENT"
                + "\n 1. Display all donor"
                + "\n 2. Add a new donor"
                + "\n 3. Remove a donor"
                + "\n 4. Update donor details"
                + "\n 5. Search donor details"
                + "\n 6. List donors with all the donations made"
                + "\n 7. Filter donor based on criteria"
                + "\n 8. Generate summary reports"
                + "\n 9. Back to MAIN MENU");
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
    }

    public void printText(String text) {
        System.out.println("\n" + text + "\n");
    }
    
     public void printDonorTitle() {
        System.out.printf("\n%-10s %-15s %-40s %-20s %-15s %-20s %-70s %-10s\n", "Donor ID", "Donor Category", "Donor Name", "Contact Person", "Contact","Email", "Address" , "Status");
    }
     
     public void printAllDonors(SortedListSetInterface<Donor> donors) {
        System.out.println("\n" + donors);
    }

    public void printNumberOfEntries(SortedListSetInterface<Donor> donors) {
        System.out.println("\nTotal Number of Donors > " + donors.getNumberOfEntries());
    }
    
    public String getDonorCategory() {
        System.out.println("Please select the type of Donor:");
        System.out.println(String.format("%-5s %-15s", "1 = ", "Public"));
        System.out.println(String.format("%-5s %-15s", "2 = ", "Private"));
        System.out.println(String.format("%-5s %-15s", "3 = ", "Government"));
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
    }
    
    public String getDonorName() {
        System.out.print("\nEnter Donor's Name :");
        String name = scanner.nextLine();
        return name;
    }

    public String getDonorEmail() {
        System.out.print("\nEnter Donor's Email :");
        String email = scanner.nextLine();
        return email;
    }
    
    public String getDonorContactPerson() {
        System.out.print("\nEnter Donor's Contact Person :");
        String contact = scanner.nextLine();
        return contact;
    }

    public String getDonorContact() {
        System.out.print("\nEnter Donor's Contact with (-) :");
        String contact = scanner.nextLine();
        return contact;
    }

    public String getDonorAddress() {
        System.out.print("\nEnter Donor's Address :");
        String address = scanner.nextLine();
        return address;
    }
    
    public String getDonorSearchMenu(){
         System.out.println(""
                + "\n Search Donor by : "
                + "\n 1. Donor ID"
                + "\n 2. Donor Name"
                + "\n 3. Donor Contact Person"
                + "\n 4. Donor Contact"
                + "\n 5. Back"
                );
          System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
    }
    
    public String getDonorDeleteMenu(){
         System.out.println(""
                + "\n Remove Donor by search: "
                + "\n 1. Donor ID"
                + "\n 2. Donor Name"
                + "\n 3. Donor Contact"
                + "\n 4. Cuztomize delete"
                + "\n 5. Back"
                );
          System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
    }
    
    public String getDeleteDonorID(){
        System.out.print("\n Note : Use + to delete more donors"
                + "\nNote : Enter 0 to exit"
                + "\nEnter Donor ID to delete :");
        String donorID = scanner.nextLine();
        return donorID;
    }
    
    public String getDeleteConfirmation(){
        System.out.print("\n\nDo you sure to delete (Y/N): " );
        String yesno = scanner.nextLine();
        return yesno;
    }
    
    public String getDonorID(){
        System.out.print("\nEnter Donor ID:");
        String donorID = scanner.nextLine();
        return donorID;
    }
}
