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
import java.util.Iterator;

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

                        break;
                    case 4:
                        break;
                    case 5:
                        ClearScreen.clearJavaConsoleScreen();
                        SearchDonation(donees);
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

    public void AddNewDonee(SortedListSetInterface<Donee> donees) {
        String doneeType = "";
        String email;
        int choose;
        String contact;
        String location = "";
        boolean validInput = false;

        Scanner scanner = new Scanner(System.in);

        //auto generate DoneeID
        String lastId = "DE000"; // Default ID if no donees are present
        if (!donees.isEmpty()) {
            Donee lastDonee = donees.getLastEntries();
            lastId = lastDonee.getDoneeId(); // 
        }
        int lastNumericPart = Integer.parseInt(lastId.substring(2));
        int nextNumericPart = lastNumericPart + 1; //
        String doneeId = String.format("DE%03d", nextNumericPart);

        //Title
        MessageUI.diplayEnDash();
        doneeUI.printAddDoneeTitle();
        MessageUI.diplayEnDash();

        //Name
        doneeUI.printAddDoneeName();
        String name = scanner.nextLine();

        //Type
        do {
            choose = doneeUI.getDoneeType();
            if (choose >= 1 && choose <= 3) {
                validInput = true; // Valid input received
            } else {
                MessageUI.displayInvalidOptionMessage();
            }
        } while (!validInput);

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
            choose = doneeUI.getDoneeLocation();
            if (choose >= 1 && choose <= 3) {
                validInput = true; // Valid input received
            } else {
                MessageUI.displayInvalidOptionMessage();
            }
        } while (!validInput);

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
        donees.add(newDonee);
        doneeUI.printText(newDonee.toString());

    }

    public void SearchDonation(SortedListSetInterface<Donee> donees) {
        Scanner scanner = new Scanner(System.in);
        Iterator<Donee> iterator = donees.getIterator();

        boolean founded = false;
        boolean validInput = false;
        int choose;
        int opt = 0;
        String type = "";
        do {
            try {
                MessageUI.diplayEnDash();
                opt = Integer.parseInt(doneeUI.getDoneeSearchMenu());
                MessageUI.diplayEnDash();
                switch (opt) {
                    case 1:
                        
                        doneeUI.printDoneeID();
                        String inputId = scanner.nextLine();
                        founded = false;
                        iterator = donees.getIterator();                      
                        while (iterator.hasNext()) {
                            Donee donee = iterator.next();
                            if (donee.getDoneeId().equalsIgnoreCase(inputId)) {
                                if (!founded) {
                                    // Print header only if at least one match is found
                                    doneeUI.printText("Search Result\n\n");
                                    doneeUI.printDoneeTitle();
                                    MessageUI.diplayEnDash();
                                    founded = true;
                                }
                                doneeUI.printText(donee.toString());
                            }
                        }
                        if (!founded) {
                            doneeUI.printText("\n\nNo results found for type: " + inputId + "\n\n");
                        }
                        break;
                    case 2:
                        do {
                            choose = doneeUI.getDoneeType();
                            if (choose >= 1 && choose <= 3) {
                                validInput = true;
                            } else {
                                MessageUI.displayInvalidOptionMessage();
                            }
                        } while (!validInput);
                        switch (choose) {
                            case 1:
                                type = "INDIVIDUAL";
                                break;
                            case 2:
                                type = "ORGANIZATION";
                                break;
                            case 3:
                                type = "FAMILY";
                                break;
                            default:
                                break;
                        }
                        founded = false;
                        iterator = donees.getIterator();
                        while (iterator.hasNext()) {
                            Donee donee = iterator.next();
                            if (donee.getDoneeType().equalsIgnoreCase(type)) {
                                if (!founded) {
                                    doneeUI.printText("Search Result\n\n");
                                    doneeUI.printDoneeTitle();
                                    MessageUI.diplayEnDash();
                                    founded = true;
                                }
                                doneeUI.printText(donee.toString());
                            }
                        }
                        if (!founded) {
                            doneeUI.printText("\n\nNo results found for type: " + type + "\n\n");
                        }
                        break;
                    case 3:
                        do {
                            choose = doneeUI.getDoneeLocation();
                            if (choose >= 1 && choose <= 3) {
                                validInput = true;
                            } else {
                                MessageUI.displayInvalidOptionMessage();
                            }
                        } while (!validInput);
                        String location = "";
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
                        founded = false;
                        iterator = donees.getIterator();
                        while (iterator.hasNext()) {
                            Donee donee = iterator.next();
                            if (donee.getLocation().equalsIgnoreCase(location)) {
                                if (!founded) {
                                    // Print header only if at least one match is found
                                    doneeUI.printText("Search Result\n\n");
                                    doneeUI.printDoneeTitle();
                                    MessageUI.diplayEnDash();
                                    founded = true;
                                }
                                doneeUI.printText(donee.toString());
                            }
                        }
                        if (!founded) {
                            doneeUI.printText("\n\nNo results found for type: " + location + "\n\n");
                        }
                        break;
                    default:
                        break;
                }

            } catch (NumberFormatException ex) {
                MessageUI.displayInvalidIntegerMessage();
            }

        } while (opt != 4);
    }
}
