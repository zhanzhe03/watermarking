/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

/**
 *
 * @author USER
 */
import java.util.Scanner;

public class CharityUI {
    
    private Scanner scanner = new Scanner(System.in);
    
    public String getMenu(){
        System.out.println(""
                + "\nMAIN MENU"
                + "\n 1. Donor Management"
                + "\n 2. Donee Management"
                + "\n 3. Donation Management"
                + "\n 4. Donation Distribution"
                + "\n 9. Exit Program");
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
    }
}
