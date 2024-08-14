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
    

    
    
    public void printText(String text) {
        System.out.println("\n" + text + "\n");
    }
    
    
    public void printDoneeTitle(){
        System.out.printf("\n%-20s %-20s %-20s %-25s %-20s %-35s %-20s\n", "Donee ID", "Donee Type", "Donee Name", "Email", "Contact", "Address", "Location");
    }
    
    public void printAllDonees(SortedListSetInterface<Donee> donees){
        System.out.println("\n" + donees);
    }
    
    public void printNumberOfEntries(SortedListSetInterface<Donee> donees){
        System.out.println("\nTotal Number of Donation > " + donees.getNumberOfEntries());
    }
}
