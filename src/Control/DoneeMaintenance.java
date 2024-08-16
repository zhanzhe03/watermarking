/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import ADT.SortedDoublyLinkedListSet;
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
                        ClearScreen.clearJavaConsoleScreen();
                        RemoveDonee(donees);
                        break;
                    case 4:
                        ClearScreen.clearJavaConsoleScreen();
                        UpdateDonee(donees);
                        break;
                    case 5:
                        ClearScreen.clearJavaConsoleScreen();
                        SearchDonation(donees);
                        break;
                    case 6:
                        ClearScreen.clearJavaConsoleScreen();
                        DoneeWithDistribute(donees);
                        break;
                    case 7:
                        ClearScreen.clearJavaConsoleScreen();
                        FilterDonee(donees);
                        break;
                    case 8:
                        ClearScreen.clearJavaConsoleScreen();
                        DoneeReports(donees);
                        break;
                    case 9:
                        ClearScreen.clearJavaConsoleScreen();
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
        doneeUI.printText("Add New Donee");
        MessageUI.diplayEnDash();

        //Name
        String name = doneeUI.getDoneeName();

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
            email = doneeUI.getDoneeEmail();         
            if (!email.contains("@")) {
                MessageUI.displayInvalidEmailMessage(); 
            }

        } while (!email.contains("@"));

        //Contact
        do {
            contact = doneeUI.getDoneeContact();

            if (contact.length() > 11 || !contact.startsWith("0") || !contact.matches("\\d+")) {
                MessageUI.displayInvalidContactMessage();  // Display an error message if invalid
            }

        } while (contact.length() > 11 || !contact.startsWith("0") || !contact.matches("\\d+"));

        //Address
        String address = doneeUI.getDoneeAddress();

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
                        String inputId = doneeUI.getDoneeID();
                        founded = false;
                        iterator = donees.getIterator();
                        while (iterator.hasNext()) {
                            Donee donee = iterator.next();
                            if (donee.getDoneeId().equalsIgnoreCase(inputId)) {
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

    public void RemoveDonee(SortedListSetInterface<Donee> donees) {
        Scanner scanner = new Scanner(System.in);
        Iterator<Donee> iterator = donees.getIterator();
        SortedListSetInterface<Donee> foundDonees = new SortedDoublyLinkedListSet<>();
        int choose;
        String location = "";
        boolean validInput = false;
        boolean founded = false;
        do {
            MessageUI.diplayEnDash();
            choose = doneeUI.getDoneeDeleteMenu();
            if (choose >= 1 && choose <= 3) {
                validInput = true;
            } else {
                MessageUI.displayInvalidOptionMessage();
            }
        } while (!validInput);
        MessageUI.diplayEnDash();
        switch (choose) {
            case 1:
                // Remove Donee by ID
                iterator = donees.getIterator();
                 String inputID = doneeUI.getDoneeID();
                founded = false;

                while (iterator.hasNext()) {
                    Donee donee = iterator.next();
                    if (donee.getDoneeId().equalsIgnoreCase(inputID)) {
                        founded = true;

                        doneeUI.printText("\nDonee found: \n");
                        doneeUI.printDoneeTitle();
                        MessageUI.diplayEnDash();
                        doneeUI.printText("\n" + donee.toString());
                        String YesNo = doneeUI.getConfirmation();

                        if (YesNo.equalsIgnoreCase("y")) {
                            donees.remove(donee);
                            doneeUI.printText("Donee with ID : " + inputID + " has been removed successfully.");
                        } else {
                            doneeUI.printText("Removal cancelled.");
                        }
                        break;  // Exit the loop after the Donee is found and handled
                    }

                }//end while

                if (!founded) {
                    doneeUI.printText("\nDonee not found: " + inputID + "\n");
                }
                break;
                
            //remove from location;
            case 2:
                do {
                    choose = doneeUI.getDoneeLocation();
                    if (choose >= 1 && choose <= 3) {
                        validInput = true;
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

                founded = false;
                iterator = donees.getIterator();
                while (iterator.hasNext()) {
                    Donee donee = iterator.next();
                    if (donee.getLocation().equalsIgnoreCase(location)) {
                        foundDonees.add(donee);
                        founded = true;
                    }
                }

                if (founded) {
                    doneeUI.printText("\nDonees found at location: " + location + "\n");
                    doneeUI.printDoneeTitle();
                    MessageUI.diplayEnDash();

                    Iterator<Donee> foundIterator = foundDonees.getIterator();
                    while (foundIterator.hasNext()) {
                        Donee donee = foundIterator.next();
                        doneeUI.printText("\n" + donee.toString());
                    }

                    String YesNo = doneeUI.getConfirmation();
                    if (YesNo.equalsIgnoreCase("y")) {
                        foundIterator = foundDonees.getIterator();
                        while (foundIterator.hasNext()) {
                            Donee donee = foundIterator.next();
                            donees.remove(donee);
                        }
                        doneeUI.printText("Donees at location: " + location + " have been removed successfully.");
                    } else {
                        doneeUI.printText("Removal cancelled.");
                    }
                } else {
                    doneeUI.printText("\nNo Donees found at location: " + location + "\n");
                }
                break;
            case 3:
                doneeUI.printText("Enter first Donee ID and seconde Donee ID");
                MessageUI.diplayEnDash();
                String inputId1 = doneeUI.getDoneeID();
                String inputId2 = doneeUI.getDoneeID();
                founded = false;
                iterator = donees.getIterator();
                while (iterator.hasNext()) {
                    Donee donee = iterator.next();
                    String doneeID = donee.getDoneeId();

                    if (isInRange(doneeID, inputId1, inputId2)) {
                        // Add matching Donee to the temporary collection
                        foundDonees.add(donee);
                        founded = true;
                    }
                }

                if (founded) {
                    // Display Donee information
                    doneeUI.printText("\nDonees within range from " + inputId1 + " to " + inputId2 + ": \n");
                    doneeUI.printDoneeTitle();
                    MessageUI.diplayEnDash();

                    // Iterate again to display all found Donees
                    Iterator<Donee> foundIterator = foundDonees.getIterator();
                    while (foundIterator.hasNext()) {
                        Donee donee = foundIterator.next();
                        doneeUI.printText("\n" + donee.toString());
                    }

                    String YesNo = doneeUI.getConfirmation();
                    if (YesNo.equalsIgnoreCase("y")) {
                        foundIterator = foundDonees.getIterator();
                        while (foundIterator.hasNext()) {
                            Donee donee = foundIterator.next();
                            donees.remove(donee);
                        }
                    } else {
                        doneeUI.printText("Remove cancelled!");
                    }
                } else {
                    doneeUI.printText("\nNo Donees found in the range from " + inputId1 + " to " + inputId2 + "\n");
                }
                break;

        }
    }

    private boolean isInRange(String doneeID, String startID, String endID) {
        return doneeID.compareTo(startID) >= 0 && doneeID.compareTo(endID) <= 0;
    }

    public void UpdateDonee(SortedListSetInterface<Donee> donees) {
        Scanner scanner = new Scanner(System.in);
        Iterator<Donee> iterator = donees.getIterator();
        boolean founded = false;
        String newLocation = null;
        Donee targetDonee = null;

        while (!founded) {
            iterator = donees.getIterator();
            String inputID = doneeUI.getDoneeID();
            while (iterator.hasNext()) {
                Donee donee = iterator.next();
                if (donee.getDoneeId().equalsIgnoreCase(inputID)) {
                    founded = true;
                    targetDonee = donee; // Store the found Donee for further processing
                    break;  // Exit the inner loop once the Donee is found
                }
            }

            if (!founded) {
                doneeUI.printText("Donee with ID " + inputID + " not found");
            }
        }

        if (founded) {
            int choose = doneeUI.getDoneeUpdateMenu();
            MessageUI.diplayEnDash();
            switch (choose) {
                case 1:
                    String newName = doneeUI.getDoneeName();
                    targetDonee.setName(newName);
                    doneeUI.printText("Donee name updated successfully.");
                    break;
                case 2:
                    // Update location
                    String newContact = doneeUI.getDoneeContact();
                    targetDonee.setContact(newContact);
                    doneeUI.printText("Donee contact updated successfully.");
                    break;
                case 3:
                    int opt = doneeUI.getDoneeLocation();
                    switch (opt) {
                        case 1:
                            newLocation = "Location A";
                            break;
                        case 2:
                            newLocation = "Location B";
                            break;
                        case 3:
                            newLocation = "Location C";
                            break;
                        default:
                            MessageUI.displayInvalidOptionMessage();
                            break;
                    }
                    if (newLocation != null) {
                        targetDonee.setLocation(newLocation);
                    }
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
        }

    }

    public void DoneeWithDistribute(SortedListSetInterface<Donee> donees) {

    }

    public void FilterDonee(SortedListSetInterface<Donee> donees) {

    }

    public void DoneeReports(SortedListSetInterface<Donee> donees) {

    }

}
