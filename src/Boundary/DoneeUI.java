/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import ADT.SortedListSetInterface;
import Entity.Date;
import java.time.LocalDate;
import java.util.Scanner;
import Entity.Donee;
import Entity.Request;

/**
 *
 * @author TANJIANQUAN
 */
public class DoneeUI {

    Scanner scanner = new Scanner(System.in);

    public void displayEnDash() {
        for (int i = 0; i < 220; i++) {
            System.out.print("-");
        }
    }

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

    public String getDoneeType() {
        System.out.println("\nPlease select the type of Donee:");
        System.out.println(String.format("%-5s %-15s", "1 = ", "INDIVIDUAL"));
        System.out.println(String.format("%-5s %-15s", "2 = ", "ORGANIZATION"));
        System.out.println(String.format("%-5s %-15s", "3 = ", "FAMILY"));
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
    }

    public String getDoneeLocation() {
        System.out.println("\nPlease select the location of Donee:");
        System.out.println(String.format("%-5s %-15s", "1 = ", "Location A"));
        System.out.println(String.format("%-5s %-15s", "2 = ", "Location B"));
        System.out.println(String.format("%-5s %-15s", "3 = ", "Location C"));
        System.out.print("\nopt (1-3 ) >");
        String opt = scanner.nextLine();
        return opt;
    }

    public int getDoneeDeleteMenu() {
        System.out.println(""
                + "\nDONEE DELETE MANAGEMENT"
                + "\n 1. Donee ID"
                + "\n 2. Donee Location"
                + "\n 3. Donee Range"
                + "\n 4. Back to Donee MENU");
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
                + "\n 3. Donee Location"
                + "\n 4. Donee Request"
                + "\n 5. BACK to Donee MENU");
        System.out.print("\nopt > ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        return opt;
    }

    public String getSortMenu() {
        System.out.println(""
                + "\nDONEE SORT MANAGEMENT"
                + "\n 1. Sorted by Donee ID with ASC"
                + "\n 2. Sorted by Donee ID with DACS"
                + "\n 3. Sorted by Donee Request Date with ASC"
                + "\n 4. Sorted by Donee Request Date with DACS"
                + "\n 5. Back to Donee MENU");
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
    }
    
    public String getReceiveSortMenu() {
        System.out.println(""
                + "\nDONEE SORT MANAGEMENT"
                + "\n 1. Sorted by Donee ID with ASC"
                + "\n 2. Sorted by Donee ID with DACS"
                + "\n 3. Sorted by Donee Receive Date with ASC"
                + "\n 4. Sorted by Donee Receive Date with DACS"
                + "\n 5. Back to Donee MENU");
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
    }

    public void printText(String text) {
        System.out.println("\n" + text + "\n");
    }

    public void printDoneeTitle() {
        System.out.printf("\n%-15s %-20s %-20s %-25s %-20s %-30s %-14s %-20s %-20s %-20s\n", "Donee ID", "Donee Type", "Donee Name", "Email", "Contact", "Address", "Location", "Register Date", "Request Date", "Request Item");
    }

    public void donationTitle() {
        System.out.printf("\n%-15s %-30s %-20s %-20s %-10s\n", "Donee ID", "Receive Item", "Recevice Date", "Item ID", "Quantity/Amount");
    }

    public void printAllDonees(SortedListSetInterface<Donee> donees) {
        System.out.println(donees);
    }

    public void printRequest(SortedListSetInterface<Request> request) {
        System.out.print(request);
    }

    public void printNumberOfEntries(SortedListSetInterface<Donee> donees) {
        System.out.println("\nTotal Number of Donees > " + donees.getNumberOfEntries());
    }

    public String getDoneeName() {
        System.out.print("\nEnter name :");
        String name = scanner.nextLine();
        return name;
    }

    public String getDoneeID() {
        System.out.print("\nEnter Donee ID :");
        String doneeId = scanner.nextLine();
        return doneeId;
    }

    public String getDoneeEmail() {
        System.out.print("\nEnter email :");
        String email = scanner.nextLine();
        return email;
    }

    public String getDoneeContact() {
        System.out.print("\nEnter contact :");
        String contact = scanner.nextLine();
        return contact;
    }

    public String getDoneeAddress() {
        System.out.print("\nEnter address :");
        String address = scanner.nextLine();
        return address;
    }

    public Date getRequestDate() {
        int day, month, year;

        System.out.print("Enter day (1-31): ");
        day = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter month (1-12): ");
        month = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter year (e.g., 2024): ");
        year = Integer.parseInt(scanner.nextLine());

        return new Date(day, month, year);
    }

    public String confirmOperation() {
        System.out.println("Confirm to perform this operation ? (Y = Yes)");
        System.out.print("Opt >");
        String yesNo = scanner.nextLine();
        return yesNo;
    }

    public String continueOperation() {
        System.out.println("Continue to perform this operation ? (Y = Yes)");
        System.out.print("Opt >");
        String yesNo = scanner.nextLine();
        return yesNo;
    }

}
