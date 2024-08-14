/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

/**
 *
 * @author USER
 */
public class MessageUI {

    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLACK = "\u001B[0m";
    
    public static void diplayEnDash(){
        for (int i = 0; i < 140; i++) 
            System.out.print("-");
    }
    
    public static void displaySuccessfulMessage(){
        System.out.println(ANSI_GREEN + "\nOperation Successful." + ANSI_BLACK);
    }
    
    public static void displayUnsuccessfulMessage(){
        System.out.println(ANSI_RED + "\nOperation Cancelled." + ANSI_BLACK);
    }
    
    public static void displayInvalidIntegerMessage(){
        System.out.println(ANSI_RED + "\nPlease enter a number." + ANSI_BLACK);
    }
    
    public static void displayInvalidOptionMessage() {
        System.out.println(ANSI_RED + "\nInvalid option." + ANSI_BLACK);
    }
    
    public static void displayInvalidDateFormatMessage(){
        System.out.println(ANSI_RED + "\nPlease follow the date format provided." + ANSI_BLACK);
    }
    
    public static void displayInvalidDateMessage(){
        System.out.println(ANSI_RED + "\nInvalid date." + ANSI_BLACK);
    }

    public static void displayExitMessage() {
        System.out.println("\nHave a Nice Day !");
    }
}
