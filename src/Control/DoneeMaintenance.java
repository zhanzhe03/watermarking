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

    //private method
    private DoneeUI doneeUI = new DoneeUI();

    private String generateNewDoneeId(SortedListSetInterface<Donee> donees) {
        String lastId = donees.isEmpty() ? "DE000" : donees.getLastEntries().getDoneeId();
        int nextNumericPart = Integer.parseInt(lastId.substring(2)) + 1;
        return String.format("DE%03d", nextNumericPart);
    }
   
    private String inputName() {
        String name;
        do {
            name = doneeUI.getDoneeName();
            if (name.length() > 20) {
                MessageUI.displayExceedMessage();
            }
        } while (name.length() > 20);
        return name;
    }

    private String inputType() {
        String doneeType = "";
        boolean validInput = false;

        while (!validInput) {
            try {
                int choose = Integer.parseInt(doneeUI.getDoneeType());
                switch (choose) {
                    case 1:
                        doneeType = "INDIVIDUAL";
                        validInput = true; //
                        break;
                    case 2:
                        doneeType = "ORGANIZATION";
                        validInput = true; // 
                        break;
                    case 3:
                        doneeType = "FAMILY";
                        validInput = true; // 
                        break;
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
                }
            } catch (NumberFormatException e) {
                MessageUI.displayInvalidOptionMessage();
            }
        }

        return doneeType;
    }

    private String inputEmail() {
        String email;
        do {
            email = doneeUI.getDoneeEmail();
            if (!email.contains("@")) {
                MessageUI.displayInvalidEmailMessage();
            }

        } while (!email.contains("@"));
        return email;
    }

    private String inputContact() {
        String contact;
        do {
            contact = doneeUI.getDoneeContact();

            if (!contact.startsWith("0")) {
                MessageUI.displayInvalidContactMessage();
            } else if (contact.startsWith("011") && contact.length() != 11) {
                MessageUI.displayInvalidContactMessage();
            } else if (contact.startsWith("01") && !contact.startsWith("011") && contact.length() != 10) {
                MessageUI.displayInvalidContactMessage();
            } else if (!contact.matches("\\d+")) {
                MessageUI.displayInvalidContactMessage();
            } else {
                break;  // Valid contact, exit the loop
            }

        } while (true);
        return contact;
    }

    private String inputLocation() {
        String location = "";
        boolean validInput = false;

        while (!validInput) {
            try {
                int choose = Integer.parseInt(doneeUI.getDoneeLocation());
                switch (choose) {
                    case 1:
                        location = "Location A";
                        validInput = true; // Valid input received, exit loop
                        break;
                    case 2:
                        location = "Location B";
                        validInput = true; // Valid input received, exit loop
                        break;
                    case 3:
                        location = "Location C";
                        validInput = true; // Valid input received, exit loop
                        break;
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
                }
            } catch (NumberFormatException e) {
                MessageUI.displayInvalidOptionMessage();
            }
        }

        return location;
    }

    //Public method 
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

    private Donee findDoneeID(SortedListSetInterface<Donee> donees, String inputId) {
        Iterator<Donee> iterator = donees.getIterator();

        while (iterator.hasNext()) {
            Donee donee = iterator.next();
            if (donee.getDoneeId().equalsIgnoreCase(inputId)) {
                return donee;
            }
        }
        return null;
    }

    private SortedListSetInterface<Donee> findDoneeLocation(SortedListSetInterface<Donee> donees, String inputLocation) {
        SortedListSetInterface<Donee> foundDonees = new SortedDoublyLinkedListSet<>();
        Iterator<Donee> iterator = donees.getIterator();

        while (iterator.hasNext()) {
            Donee donee = iterator.next();
            if (donee.getLocation().equalsIgnoreCase(inputLocation)) {
                foundDonees.add(donee);
            }
        }

        return foundDonees.getNumberOfEntries() > 0 ? foundDonees : null;
    }

    private SortedListSetInterface<Donee> findDoneeType(SortedListSetInterface<Donee> donees, String inputType) {
        SortedListSetInterface<Donee> foundDonees = new SortedDoublyLinkedListSet<>();
        Iterator<Donee> iterator = donees.getIterator();

        while (iterator.hasNext()) {
            Donee donee = iterator.next();
            if (donee.getDoneeType().equalsIgnoreCase(inputType)) {
                foundDonees.add(donee);
            }
        }

        return foundDonees.getNumberOfEntries() > 0 ? foundDonees : null;
    }

    private boolean isInRange(String doneeID, String startID, String endID) {
        return doneeID.compareTo(startID) >= 0 && doneeID.compareTo(endID) <= 0;
    }

    private SortedListSetInterface<Donee> findDoneeIDInRange(SortedListSetInterface<Donee> donees, String inputId1, String inputId2) {
        SortedListSetInterface<Donee> foundDonees = new SortedDoublyLinkedListSet<>();
        Iterator<Donee> iterator = donees.getIterator();

        while (iterator.hasNext()) {
            Donee donee = iterator.next();
            String doneeID = donee.getDoneeId();
            if (isInRange(doneeID, inputId1, inputId2)) {
                foundDonees.add(donee);
            }
        }
        return foundDonees.getNumberOfEntries() > 0 ? foundDonees : null;
    }

    private void removeDonees(SortedListSetInterface<Donee> donees, SortedListSetInterface<Donee> foundDonees) {
        Iterator<Donee> foundIterator = foundDonees.getIterator();
        while (foundIterator.hasNext()) {
            donees.remove(foundIterator.next());
        }
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
        //auto generate DoneeID
        String doneeId = generateNewDoneeId(donees);

        //Title
        MessageUI.diplayEnDash();
        doneeUI.printText("Add New Donee");
        MessageUI.diplayEnDash();

        //Name
        String name = inputName();

        //Type
        String doneeType = inputType();

        //Emial
        String email = inputEmail();

        //Contact
        String contact = inputContact();

        //Address
        String address = doneeUI.getDoneeAddress();

        //Location
        String location = inputLocation();

        Donee newDonee = new Donee(doneeId, doneeType, name, email, contact, address, location);
        donees.add(newDonee);
        doneeUI.printText(newDonee.toString());

    }

    public void SearchDonation(SortedListSetInterface<Donee> donees) {
        SortedListSetInterface<Donee> foundDonee;
        int opt = 0;
        do {
            try {
                doneeUI.displayEnDash();
                opt = Integer.parseInt(doneeUI.getDoneeSearchMenu());
                doneeUI.displayEnDash();
                switch (opt) {
                    case 1:
                        String inputId = doneeUI.getDoneeID();
                        Donee foundOneDonee = findDoneeID(donees, inputId);
                        if (foundOneDonee != null) {
                            doneeUI.printText("Search Result\n\n");
                            doneeUI.printDoneeTitle();
                            doneeUI.displayEnDash();
                            doneeUI.printText(foundOneDonee.toString());
                        } else {
                            doneeUI.printText("\n\nNo results found for type: " + inputId + "\n\n");
                        }
                        break;
                    case 2:
                        String doneeType = inputType();
                        foundDonee = findDoneeType(donees, doneeType);
                        if (foundDonee != null) {
                            doneeUI.printText("Search Result\n\n");
                            doneeUI.printDoneeTitle();
                            doneeUI.displayEnDash();
                            doneeUI.printText(foundDonee.toString());
                        } else {
                            doneeUI.printText("\n\nNo results found for type: " + doneeType + "\n\n");
                        }
                        break;
                    case 3:
                        String doneeLocation = inputLocation();
                        foundDonee = findDoneeLocation(donees, doneeLocation);
                        if (foundDonee != null) {
                            doneeUI.printText("Search Result\n\n");
                            doneeUI.printDoneeTitle();
                            doneeUI.displayEnDash();
                            doneeUI.printText(foundDonee.toString());
                        } else {
                            doneeUI.printText("\n\nNo results found for type: " + doneeLocation + "\n\n");
                        }
                        break;
                }

            } catch (NumberFormatException ex) {
                MessageUI.displayInvalidIntegerMessage();
            }

        } while (opt != 4);
    }

    public void RemoveDonee(SortedListSetInterface<Donee> donees) {
        SortedListSetInterface<Donee> foundDonee;
        int choose;
        boolean validInput = false;
        do {
            doneeUI.displayEnDash();
            choose = doneeUI.getDoneeDeleteMenu();
            if (choose >= 1 && choose <= 4) {
                validInput = true;
            } else {
                MessageUI.displayInvalidOptionMessage();
            }
        } while (!validInput);
        doneeUI.displayEnDash();
        switch (choose) {
            case 1:
                // Remove Donee by ID
               String inputID = doneeUI.getDoneeID();
                Donee foundOneDonee = findDoneeID(donees, inputID);
                if (foundOneDonee != null) {
                    doneeUI.printText("Donee(s) found:\n\n");
                    doneeUI.printDoneeTitle();
                    doneeUI.displayEnDash();
                    doneeUI.printText(foundOneDonee.toString());
                    String yesNo = doneeUI.comfirmOperation();
                    if (yesNo.equalsIgnoreCase("Y")) {
                        donees.remove(foundOneDonee);
                        doneeUI.printText("Donee(s) with ID: " + inputID + " have been removed successfully.");
                    } else {
                        doneeUI.printText("Removal cancelled.");
                    }
                } else {
                    doneeUI.printText("\n\nNo results found for ID: " + inputID + "\n\n");
                }
                break;

            //remove from location;
            case 2:
                String doneeLocation = inputLocation();
                foundDonee = findDoneeLocation(donees, doneeLocation);

                if (foundDonee != null) {
                    doneeUI.printText("Donee(s) found:\n\n");
                    doneeUI.printDoneeTitle();
                    doneeUI.displayEnDash();
                    doneeUI.printText(foundDonee.toString());

                    String yesNo = doneeUI.comfirmOperation();
                    if (yesNo.equalsIgnoreCase("Y")) {
                        donees.relativeComplement(foundDonee);
                        doneeUI.printText("Donee(s) with ID: " + doneeLocation + " have been removed successfully.");
                    } else {
                        doneeUI.printText("Removal cancelled.");
                    }
                } else {
                    doneeUI.printText("\n\nNo results found for : " + doneeLocation + "\n\n");
                }
                break;
            case 3:
                doneeUI.printText("Enter first Donee ID and seconde Donee ID");
                doneeUI.displayEnDash();
                String inputId1 = doneeUI.getDoneeID();
                String inputId2 = doneeUI.getDoneeID();
                foundDonee = findDoneeIDInRange(donees, inputId1, inputId2);

                if (foundDonee != null) {
                    // Display Donee information
                    doneeUI.printText("\nDonees within range from " + inputId1 + " to " + inputId2 + ": \n");
                    doneeUI.printDoneeTitle();
                    doneeUI.displayEnDash();
                    doneeUI.printText("\n" + foundDonee.toString());

                    String yesNo = doneeUI.comfirmOperation();
                    if (yesNo.equalsIgnoreCase("Y")) {
                        donees.relativeComplement(foundDonee);
                        doneeUI.printText("Donee(s) with ID: " + inputId1 + "to" + "inputId2" + "have been removed successfully.");
                    } else {
                        doneeUI.printText("Removal cancelled.");
                    }
                } else {
                    doneeUI.printText("\n\nNo results found for : " + inputId1 + "to" + inputId2 + "\n\n");
                }
                break;
            case 4:
                break;

        }
    }

    public void UpdateDonee(SortedListSetInterface<Donee> donees) {
        boolean founded = false;
        String newLocation = null;
        Donee targetDonee = null;
        String newName;
        String newContact;
        String inputID;

        do {
            inputID = doneeUI.getDoneeID();
            Donee foundDonees = findDoneeID(donees, inputID);
            if (foundDonees != null) {
                targetDonee = foundDonees;
                founded = true;
            } else {
                doneeUI.printText("Donee with ID " + inputID + " not found");
            }
        } while (!founded);

        if (founded) {
            // Display update menu and perform updates based on user choice
            int choose = doneeUI.getDoneeUpdateMenu();
            doneeUI.displayEnDash();

            switch (choose) {
                case 1:
                    // Update Name
                    newName = inputName(); // Using private method
                    targetDonee.setName(newName);
                    doneeUI.printText("Donee name updated successfully.");
                    break;

                case 2:
                    // Update Contact
                    newContact = inputContact(); // Using private method
                    targetDonee.setContact(newContact);
                    doneeUI.printText("Donee contact updated successfully.");
                    break;

                case 3:
                    // Update Location
                    newLocation = inputLocation(); // Using private method
                    targetDonee.setLocation(newLocation);
                    doneeUI.printText("Donee location updated successfully.");
                    break;
                case 4:
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
