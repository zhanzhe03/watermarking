/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

import ADT.SortedDoublyLinkedListSet;
import ADT.SortedListSetInterface;
import Entity.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author szewen
 */
public class DistributionUI {

    Scanner scanner = new Scanner(System.in);
    private SortedListSetInterface<Item> donatedItemList = new SortedDoublyLinkedListSet<>();

    public String getDistributionMenu() {
        System.out.println(""
                + "\nDISTRIBUTION MANAGEMENT"
                + "\n 1. List all donation distributions "
                + "\n 2. Add new donation distribution"
                + "\n 3. Update donation distribution details"
                + "\n 4. Search donation distribution details "
                + "\n 5. Remove donation distribution"
                + "\n 6. Monitor/track distributed items"
                + "\n 7. Generate summary reports"
                //   + "\n 7. remove donation"
                //   + "\n 8. Filter donation based on criteria"
                // + "\n 9. Generate summary reports"
                + "\n 9. Back to MAIN MENU");
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();

        return opt;
    }
    
      public boolean askIfSort() {
        System.out.print("\nDo you want to sort the distribution list? (y/n) > ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("Y")) {
            return true;
        } else if (response.equalsIgnoreCase("n")) {
            return false;
        } else {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            return askIfSort(); // Recursively prompt until valid input is received
        }
    }

    public int displaySortingMenu() {

        System.out.println("\n========================================");
        System.out.println("1. Sort by Distribution ID (Ascending - smallest to largest)");
        System.out.println("2. Sort by Distribution ID (Descending - largest to smallest)");
        System.out.println("3. Sort by Distribution Date (Ascending - earliest to latest)");
        System.out.println("4. Sort by Distribution Date (Descending - latest to earliest)");
        System.out.println("9. Exit");
        System.out.println("========================================");

        // Prompting the user to choose an option
        System.out.print("Please choose a sorting method (1-4) or 9 to exit: ");

        // Try to parse the user's input; handle non-numeric input gracefully
        try {
            Scanner scanner = new Scanner(System.in);
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number between 1 and 4, or 9 to exit.");
            return displaySortingMenu(); // Recurse to display the menu again
        }
    }

    private void printDistributionTitleDash() {
        for (int i = 0; i < 125; i++) {
            System.out.print("-");
        }
    }

    public void printCategoryCountTableHeader() {
        System.out.println("+------------------------+----------------+--------------------+");
        System.out.println("| Category               | Category Count | Items              |");
        System.out.println("+------------------------+----------------+--------------------+");

    }

    public void printCategoryCountTableContent(String type, int itemCount, StringBuilder items) {
        System.out.printf("| %-22s | %-14d | %-18s |\n", type, itemCount, items.toString());
    }

    public void printCategoryCountTableFooter() {
        System.out.println("+------------------------+----------------+--------------------+");
    }

    private void printDistributionTitle() {
        System.out.printf("\n%-20s  %-15s  %-15s  %-15s  %-12s  %-14s  %-15s  %-15s\n", "Distribution ID", "Assigned Date", "Donee", "Destination", "Item", "Quantity", "Amount(RM)", "Status");
    }

    public void printDistributionTitleHeader() {
        printDistributionTitleDash();
        printDistributionTitle();
        printDistributionTitleDash();
    }

    public LocalDate getLocalDate() {
        return LocalDate.now();
    }

    public void PrintDonatedItemList(SortedListSetInterface<Item> donatedItemList) {
        System.out.println(donatedItemList);
    }

    public void PrintItemList(Item item) {
        System.out.println(item);
    }

    public String getInputString(String desc) {
        System.out.print(desc);
        return scanner.nextLine();
    }


    public void listAllDistributions(SortedListSetInterface<Distribution> distributions) {
        printDistributionTitleHeader();
        System.out.print(distributions);
    }

    public void printDistributionRecord(Distribution record) {
        System.out.println(record);

    }

    public void printDistriburedItemList(SortedListSetInterface<SelectedItem> selectedItem) {
        System.out.println(selectedItem);
    }

    public void displayMessage(String msg) {
        System.out.println(msg);

    }

    public void displayMessageNotInLine(String msg) {
        System.out.printf(msg);

    }

    public boolean promptForDateRangeFilter() {
        System.out.print("\nDo you want to filter the report by date range? (Y/N): ");
        String response = scanner.nextLine().trim().toUpperCase();
        return response.equals("Y");
    }

}
