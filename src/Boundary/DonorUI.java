/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

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
        System.out.printf("\n%-20s %-20s %-20s %-25s %-20s %-35s %-20s %-20s\n", "Donor ID", "Donor Category", "Donor Name", "Contact Person", "Contact","Email", "Address" , "Status");
    }
}
