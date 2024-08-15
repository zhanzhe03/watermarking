/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import ADT.SortedListSetInterface;
import java.time.LocalDate;
import java.util.Scanner;
import Entity.Donee;

/**
 *
 * @author TANJIANQUAN
 */
public class DoneeUI {

    Scanner scanner = new Scanner(System.in);

    public String getDoneeMenu() {
        System.out.println(""
                + "\nDONEE MANAGEMENT"
                + "\n 1. Display all donee"
                + "\n 2. Add a new donee"
                + "\n 3. Remove a donee"
                + "\n 4. Update donee details"
                + "\n 5. Search donee details"
                + "\n 6. List donee with all the donations made"
                + "\n 7. Filter donee based on criteria"
                + "\n 8. Generate summary reports"
                + "\n 9. Back to MAIN MENU");
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
    }

    public String getDoneeSearchMenu() {
        System.out.println(""
                + "\nDONEE SEARCH MANAGEMENT"
                + "\n 1. Donee ID"
                + "\n 2. Donee Type"
                + "\n 3. Donee location"
                + "\n 4. Back to Donee MENU");
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
    }

    public int getDoneeType() {
        System.out.println("\nPlease select the type of Donee:");
        System.out.println(String.format("%-5s %-15s", "1 = ", "INDIVIDUAL"));
        System.out.println(String.format("%-5s %-15s", "2 = ", "ORGANIZATION"));
        System.out.println(String.format("%-5s %-15s", "3 = ", "FAMILY"));
        System.out.print("\nopt > ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        return opt;
    }

    public int getDoneeLocation() {
        System.out.println("\nPlease select the location of Donee:");
        System.out.println(String.format("%-5s %-15s", "1 = ", "Location A"));
        System.out.println(String.format("%-5s %-15s", "2 = ", "Location B"));
        System.out.println(String.format("%-5s %-15s", "3 = ", "Location C"));
        System.out.print("\nopt (1-3 ) >");
        int opt = scanner.nextInt();
        scanner.nextLine();
        return opt;
    }

    public int getDoneeDeleteMenu() {
        System.out.println(""
                + "\nDONEE DELETE MANAGEMENT"
                + "\n 1. Donee ID"
                + "\n 2. Donee Location"
                + "\n 3. Donee Range");
        System.out.print("\nopt > ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        return opt;
    }
    
    public int getDoneeUpdateMenu() {
        System.out.println(""
                + "\nDONEE UPDATE MANAGEMENT"
                + "\n 1. Donee Name"
                + "\n 2. Donee Contact"
                + "\n 3. Donee Location");
        System.out.print("\nopt > ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        return opt;
    }
    
    public String getConfirmation() {
        System.out.print("Confirm to perform this operation ? (Y = Yes)");
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
        
    }

    public void printText(String text) {
        System.out.println("\n" + text + "\n");
    }

    public void printDoneeTitle() {
        System.out.printf("\n%-20s %-20s %-20s %-25s %-20s %-35s %-20s\n", "Donee ID", "Donee Type", "Donee Name", "Email", "Contact", "Address", "Location");
    }

    public void printAllDonees(SortedListSetInterface<Donee> donees) {
        System.out.println("\n" + donees);
    }

    public void printNumberOfEntries(SortedListSetInterface<Donee> donees) {
        System.out.println("\nTotal Number of Donees > " + donees.getNumberOfEntries());
    }

    public void printAddDoneeTitle() {
        System.out.println("\nAdd New Donee \n");
    }

    public void printAddDoneeName() {
        System.out.print("\nEnter name :");
    }

    public void printDoneeID() {
        System.out.print("\nEnter Donee ID :");

    }

    public void printAddDoneeEmail() {
        System.out.print("\nEnter email :");
    }

    public void printAddDoneeContact() {
        System.out.print("\nEnter contact :");
    }

    public void printAddDoneeAddress() {
        System.out.print("\nEnter address :");
    }
    
    

}
