/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import ADT.SortedListSetInterface;
import Boundary.DoneeUI;
import DAO.EntityInitializer;
import Entity.Donation;
import Entity.Donee;
import Entity.Donor;
import Utility.ClearScreen;
import Utility.MessageUI;
import java.util.Scanner;

/**
 *
 * @author TANJIANQUAN
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author TANJIANQUAN
 */
public class DoneeMaintenance {

    private DoneeUI doneeUI = new DoneeUI();

    public void doneeManagement(EntityInitializer entityInitialize) {
        SortedListSetInterface<Donee> donees = entityInitialize.getDonees();

        int opt = 0;
        do {
            try {
                opt = Integer.parseInt(doneeUI.getDoneeMenu());

                switch (opt) {
                    case 1:
                        ClearScreen.clearJavaConsoleScreen();
                        ListAllDonee(donees);
                        break;
                    case 2:
                        ClearScreen.clearJavaConsoleScreen();
                        AddNewDonee(donees);
                        break;
                    case 3:
                        ClearScreen.clearJavaConsoleScreen();
                        SearchDonation(donees);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 9:
                        break;
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
                }

            } catch (NumberFormatException ex) {
                MessageUI.displayInvalidIntegerMessage();
            }
        } while (opt != 9);
    }

    public void ListAllDonee(SortedListSetInterface<Donee> donees) {
        doneeUI.printText("All Donees Records");
        MessageUI.diplayEnDash();
        doneeUI.printDoneeTitle();
        MessageUI.diplayEnDash();
        doneeUI.printAllDonees(donees);
        MessageUI.diplayEnDash();
        doneeUI.printNumberOfEntries(donees);
    }

    public void AddNewDonee(SortedListSetInterface<Donee> Donees) {
        String doneeType = "";
        String email;
        int choose;
        String contact;
        String location = "";

        Scanner scanner = new Scanner(System.in);

        //auto generate DoneeID
        String lastId = "DE000"; // Default ID if no donees are present
        if (!Donees.isEmpty()) {
            Donee lastDonee = Donees.getLastEntries();
            lastId = lastDonee.getDoneeId(); // 
        }
        int lastNumericPart = Integer.parseInt(lastId.substring(2));
        int nextNumericPart = lastNumericPart + 1; //
        String doneeId = String.format("DE%03d", nextNumericPart);

        //Title
        doneeUI.printAddDoneeTitle();
        MessageUI.diplayEnDash();

        //Name
        doneeUI.printAddDoneeName();
        String name = scanner.nextLine();

        //Type
        do {
            doneeUI.printAddDoneeType();
            while (!scanner.hasNextInt()) {
                MessageUI.displayInvalidIntegerMessage();
                scanner.next(); // Clear the invalid input
                doneeUI.printAddDoneeType(); // Prompt the user again
            }

            choose = scanner.nextInt();
            scanner.nextLine();

            if (choose < 1 || choose > 3) {
                MessageUI.displayInvalidOptionMessage();
            }

        } while (choose < 1 || choose > 3);

        switch (choose) {
            case 1:
                doneeType = "INDIVIDUAL";
                break;
            case 2:
                doneeType = "ORGANIZATION";
                break;
            case 3:
                doneeType = "FAMILY";
                break;
            default:
                break;

        }

        //Emial
        do {
            doneeUI.printAddDoneeEmail();           // Prompt the user to enter an email
            email = scanner.nextLine();                 // Capture the email input

            if (!email.contains("@")) {
                MessageUI.displayInvalidEmailMessage();  // Display an error message if '@' is missing
            }

        } while (!email.contains("@"));

        //Contact
        do {
            doneeUI.printAddDoneeContact();
            contact = scanner.nextLine();

            if (contact.length() > 11 || !contact.startsWith("0") || !contact.matches("\\d+")) {
                MessageUI.displayInvalidContactMessage();  // Display an error message if invalid
            }

        } while (contact.length() > 11 || !contact.startsWith("0") || !contact.matches("\\d+"));

        //Address
        doneeUI.printAddDoneeAddress();
        String address = scanner.nextLine();

        //Location
        do {
            doneeUI.printAddDoneeLocation();  // Prompt the user to select the location

            while (!scanner.hasNextInt()) {
                MessageUI.displayInvalidIntegerMessage();  // Display an error message for non-integer input
                scanner.next();  // Clear the invalid input
                doneeUI.printAddDoneeLocation();  // Prompt the user again
            }
            choose = scanner.nextInt();
            scanner.nextLine();
            if (choose < 1 || choose > 3) {
                MessageUI.displayInvalidOptionMessage();  // Display an error message for invalid option
            }

        } while (choose < 1 || choose > 3);

        switch (choose) {
            case 1:
                location = "Location A";
                break;
            case 2:
                location = "Location B";
                break;
            case 3:
                location = "Location C";
                break;
            default:
                break;
        }

        Donee newDonee = new Donee(doneeId, doneeType, name, email, contact, address, location);
        Donees.add(newDonee);
        System.out.println("Donee added successfully: " + newDonee);

    }

    public void SearchDonation(SortedListSetInterface<Donee> Donees) {

    }

}
