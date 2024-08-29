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
    private static final String ANSI_BLUE = "\u001B[34m";
     private static final String ANSI_MAGENTA = "\u001B[35m";
    private static final String ANSI_BLACK = "\u001B[0m";
    
    
    public static void diplayEnDash(){
        for (int i = 0; i < 140; i++) 
            System.out.print("-");
    }
    
    public static void displayNotEnoughStockMessage(String typeOpt){
        System.out.println(ANSI_RED + "\nPlease find more type (" + typeOpt + ") of donations to open distribution !!" + ANSI_BLACK);
    }
    
    public static void displayEnoughStockMessage(String typeOpt){
        System.out.println(ANSI_GREEN + "\nThis type (" + typeOpt + ") of item is currently sufficient for distribution !!" + ANSI_BLACK);
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
    
    public static void displayInvalidInputMessage(){
        System.out.println(ANSI_RED + "\nPlease enter a valid input." + ANSI_BLACK);
    }
    
    public static void displayInvalidDateFormatMessage(){
        System.out.println(ANSI_RED + "\nPlease follow the date format provided." + ANSI_BLACK);
    }
    
    public static void displayInvalidContactFormatMessage(){
        System.out.println(ANSI_RED + "\nPlease follow the contact format provided." + ANSI_BLACK);
    }
    
    public static void displayInvalidEmailFormatMessage(){
        System.out.println(ANSI_RED + "\nPlease follow the email format provided." + ANSI_BLACK);
    }
    
    public static void displayInvalidDateMessage(){
        System.out.println(ANSI_RED + "\nInvalid date." + ANSI_BLACK);
    }

    public static void displayExitMessage() {
        System.out.println("\nHave a Nice Day !");
    }
    
    public static void displayInvalidEmailMessage() {
        System.out.println(ANSI_RED + "Invalid email address. Please make sure the email contains the '@' symbol." + ANSI_BLACK);
    }
    
    public static void displayInvalidContactMessage() {
        System.out.println(ANSI_RED + "Invalid contact number. It must start with '0' and be no longer than 11 digits."+ ANSI_BLACK);
    }
    
        //szewen
    public static void displayExceedMessage(){
        System.out.println(ANSI_RED + "\nYour input quantity has exceeded." + ANSI_BLACK);
    }
    
    public static void displayInvalidAmountMessage(){
        System.out.println(ANSI_RED + "\nPlease enter a valid amount." + ANSI_BLACK);
    }
    
    public static void displayInvalidItemMessage(){
        System.out.println(ANSI_RED + "\nItem not found." + ANSI_BLACK);
    }
    
    public static void displayRecordAddedMessage(){
        System.out.println(ANSI_GREEN + "\nDistribution record is added." + ANSI_BLACK);
    }
    
        public static void displayNoRecordAddedMessage(){
        System.out.println(ANSI_RED + "\nNo distribution added." + ANSI_BLACK);
    }
    
    public static void displayInsufficientMessage(){
        System.out.println(ANSI_RED + "Sumimasih, this category is currently not sufficient for distribution:(\n" + ANSI_BLACK);
    }  
    
    public static void displayInvalidDonee(){
        System.out.println(ANSI_RED + "\nDonee not found." + ANSI_BLACK);
    }
    
    public static void displayNoRecord(String ID){
        System.out.println(ANSI_RED+"\nNo record with ID < " + ID + " > exists."+ ANSI_BLACK);
    }
    
    public static void displayRed(String msg){
        System.out.println(ANSI_RED + msg +ANSI_BLACK);
    }
    
    public static void displayBlueRemindMsg(String msg){
        System.out.println(ANSI_BLUE + msg + ANSI_BLACK);
    }
    
    public static void displayMagentaPreviewMsg(String msg){
        System.out.println(ANSI_MAGENTA + msg + ANSI_BLACK);
    }
    
    public static void displayGreenSuccessfulMsg(String msg){
        System.out.println(ANSI_GREEN + msg + ANSI_BLACK);
    }
    
    
    
        

    
    
}
