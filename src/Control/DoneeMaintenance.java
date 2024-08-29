/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import ADT.SortedDoublyLinkedListSet;
import ADT.SortedListSetInterface;
import Boundary.DonationUI;
import Boundary.DoneeUI;
import DAO.EntityInitializer;
import Entity.Donation;
import Entity.Donee;
import Entity.Donor;
import Utility.ClearScreen;
import Utility.MessageUI;
import java.time.LocalDate;
import java.util.Iterator;
import Entity.Date;
import Entity.Request;
import java.util.Stack;

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
    private DonationUI donationUI = new DonationUI();

    private String generateNewDoneeId(SortedListSetInterface<Donee> donees) {
        String lastId = donees.isEmpty() ? "DE000" : donees.getLastEntries().getDoneeId();
        int nextNumericPart = Integer.parseInt(lastId.substring(2)) + 1;
        return String.format("DE%03d", nextNumericPart);
    }
    
    private boolean confirmUpdate() {
        return doneeUI.confirmOperation().equalsIgnoreCase("Y");
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

    private String inputCategory() {
        String categoryType = "";
        boolean validInput = false;
        int category = 0;

        while (!validInput) {
            try {
                category = Integer.parseInt(donationUI.getItemTypeMenu());

                if (category >= 1 && category <= 7) {
                    validInput = true;  // Valid input received
                } else {
                    MessageUI.displayInvalidOptionMessage();
                }
            } catch (NumberFormatException e) {
                MessageUI.displayInvalidIntegerMessage();
            }
        }

        switch (category) {
            case 1:
                categoryType = "Monetary";
                break;
            case 2:
                categoryType = "Clothing and Apparel";
                break;
            case 3:
                categoryType = "Food and Beverage";
                break;
            case 4:
                categoryType = "Household Items";
                break;
            case 5:
                categoryType = "Educational Materials";
                break;
            case 6:
                categoryType = "Electronic";
                break;
            case 7:
                categoryType = "Medical";
                break;
            default:
                break;
        }
        return categoryType;
    }

    private Date getRequestDate() {
        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        return new Date(day, month, year);
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

    private void sortDoneesByIdDesc(SortedListSetInterface<Donee> donees) {
        Stack<Donee> myStack = new Stack<>();
        Iterator<Donee> iterator = donees.getIterator();

        // Push all Donee objects onto the stack
        while (iterator.hasNext()) {
            Donee donee = iterator.next();
            myStack.push(donee);
        }

        // Pop Donees from the stack (in LIFO order) and print them
        while (!myStack.isEmpty()) {
            Donee donee = myStack.pop();
            doneeUI.printText(donee.toString());
        }
    }

    private void sortDoneesByIdAsc(SortedListSetInterface<Donee> donees) {
        Stack<Donee> myStack = new Stack<>();
        Stack<Donee> reversedStack = new Stack<>();
        Iterator<Donee> iterator = donees.getIterator();

        // Push all Donee objects onto the stack
        while (iterator.hasNext()) {
            Donee donee = iterator.next();
            myStack.push(donee);
        }

        // Reverse the stack by popping from myStack and pushing onto reversedStack
        while (!myStack.isEmpty()) {
            reversedStack.push(myStack.pop());
        }

        // Pop Donees from the reversed stack (now in descending order) and print them
        while (!reversedStack.isEmpty()) {
            Donee donee = reversedStack.pop();
            doneeUI.printText(donee.toString());
        }
    }

    private void sortDoneesByRequestDateAsc(SortedListSetInterface<Donee> donees) {
        Stack<Donee> myStack = new Stack<>();
        Iterator<Donee> iterator = donees.getIterator();

        // Push all Donee objects onto the stack
        while (iterator.hasNext()) {
            Donee donee = iterator.next();
            insertInOrder(myStack, donee, true);  // true for ascending
        }

        // Pop Donees from the stack and print them
        while (!myStack.isEmpty()) {
            doneeUI.printText(myStack.pop().toString());
        }
    }

    private void sortDoneesByRequestDateDesc(SortedListSetInterface<Donee> donees) {
        Stack<Donee> myStack = new Stack<>();
        Iterator<Donee> iterator = donees.getIterator();

        // Push all Donee objects onto the stack
        while (iterator.hasNext()) {
            Donee donee = iterator.next();
            insertInOrder(myStack, donee, false);  // false for descending
        }

        // Pop Donees from the stack and print them
        while (!myStack.isEmpty()) {
            doneeUI.printText(myStack.pop().toString());
        }
    }

    private void insertInOrder(Stack<Donee> stack, Donee donee, boolean ascending) {
        if (stack.isEmpty()) {
            stack.push(donee);
            return;
        }

        Donee top = stack.pop();
        Date topDate = top.getRequests().getIterator().next().getRequestDate();
        Date doneeDate = donee.getRequests().getIterator().next().getRequestDate();

        if (ascending ? doneeDate.beforeDate(topDate) : doneeDate.afterDate(topDate)) {
            stack.push(top);
            stack.push(donee);
        } else {
            insertInOrder(stack, donee, ascending);
            stack.push(top);
        }
    }

    public void ListAllDonee(SortedListSetInterface<Donee> donees) {
        while (true) {
            int sortOption = 0;

            while (true) {
                try {
                    sortOption = Integer.parseInt(doneeUI.getSortMenu());

                    // Check if the input is within the valid range
                    if (sortOption >= 1 && sortOption <= 5) {
                        break; // Valid input, exit loop
                    } else {
                        MessageUI.displayInvalidOptionMessage();
                    }

                } catch (NumberFormatException e) {
                    MessageUI.displayInvalidInputMessage();
                }
            }

            if (sortOption == 5) {
                return;  // Exit the sort menu and return to the previous menu
            }

            doneeUI.printText("All Donees Records");
            doneeUI.displayEnDash();
            doneeUI.printDoneeTitle();
            doneeUI.displayEnDash();

            // Perform the chosen sorting operation
            switch (sortOption) {
                case 1:
                    sortDoneesByIdAsc(donees);
                    break;
                case 2:
                    sortDoneesByIdDesc(donees);
                    break;
                case 3:
                    sortDoneesByRequestDateAsc(donees);
                    break;
                case 4:
                    sortDoneesByRequestDateDesc(donees);
                    break;
                default:
                    break;
            }

            doneeUI.displayEnDash();
            doneeUI.printNumberOfEntries(donees);
        }
    }

    public void AddNewDonee(SortedListSetInterface<Donee> donees) {
        String continueOpt;

        do {
            // Auto-generate DoneeID
            String doneeId = generateNewDoneeId(donees);

            // Title
            doneeUI.displayEnDash();
            doneeUI.printText("Add New Donee");
            doneeUI.displayEnDash();

            // Name
            String name = inputName();

            // Type
            String doneeType = inputType();

            // Email
            String email = inputEmail();

            // Contact
            String contact = inputContact();

            // Address
            String address = doneeUI.getDoneeAddress();

            // Location
            String location = inputLocation();

            // Confirm Operation
            String confirmOpt;
            do {
                confirmOpt = doneeUI.confirmOperation();
                if (!confirmOpt.equalsIgnoreCase("Y")) {
                    doneeUI.printText("Operation cancelled.");
                    return;
                }
            } while (!confirmOpt.equalsIgnoreCase("Y"));

            Donee newDonee = new Donee(doneeId, doneeType, name, email, contact, address, location);
            donees.add(newDonee);
            doneeUI.printText("New Donee Added: ");
            doneeUI.printText(newDonee.toString());

            // Ask if the user wants to continue adding donees
            continueOpt = doneeUI.continueOperation();

        } while (continueOpt.equalsIgnoreCase("Y"));

        doneeUI.printText("Donee addition operation completed.");
    }

    public void SearchDonation(SortedListSetInterface<Donee> donees) {
        SortedListSetInterface<Donee> foundDonee;
        int opt;

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
                            doneeUI.printText("\n\nNo results found for ID: " + inputId + "\n\n");
                        }
                        break;
                    case 2:
                        String doneeType = inputType();
                        foundDonee = findDoneeType(donees, doneeType);
                        if (foundDonee != null && foundDonee.getNumberOfEntries() > 0) {
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
                        if (foundDonee != null && foundDonee.getNumberOfEntries() > 0) {
                            doneeUI.printText("Search Result\n\n");
                            doneeUI.printDoneeTitle();
                            doneeUI.displayEnDash();
                            doneeUI.printText(foundDonee.toString());
                        } else {
                            doneeUI.printText("\n\nNo results found for location: " + doneeLocation + "\n\n");
                        }
                        break;
                    case 4:
                        return;
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        continue;
                }

                String continueOpt = doneeUI.continueOperation();
                if (!continueOpt.equalsIgnoreCase("Y")) {
                    break;
                }

            } catch (NumberFormatException ex) {
                MessageUI.displayInvalidIntegerMessage();
            }

        } while (true);
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
                    String yesNo = doneeUI.confirmOperation();
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

                    String yesNo = doneeUI.confirmOperation();
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

                    String yesNo = doneeUI.confirmOperation();
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
            boolean continueUpdating;
            do {
                // Display update menu and perform updates based on user choice
                int choose = doneeUI.getDoneeUpdateMenu();
                doneeUI.displayEnDash();

                switch (choose) {
                    case 1:
                        // Update Name
                        newName = inputName(); 
                        if (confirmUpdate()) {
                            targetDonee.setName(newName);
                            doneeUI.printText("Donee name updated successfully.");
                        }
                        break;

                    case 2:
                        // Update Contact
                        newContact = inputContact(); 
                        if (confirmUpdate()) {
                            targetDonee.setContact(newContact);
                            doneeUI.printText("Donee contact updated successfully.");
                        }
                        break;

                    case 3:
                        // Update Location
                        newLocation = inputLocation(); 
                        if (confirmUpdate()) {
                            targetDonee.setLocation(newLocation);
                            doneeUI.printText("Donee location updated successfully.");
                        }
                        break;

                    case 4:
                        // Update Request
                        String requestItems = inputCategory();
                        Date requestDate = getRequestDate();
                        Request newRequest = new Request(requestDate, requestItems);
                        if (confirmUpdate()) {
                            targetDonee.addRequest(newRequest);
                            doneeUI.printText("Donee request updated successfully.");
                        }
                        break;

                    case 5:
                        // Exit update menu
                        return;

                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
                }

                continueUpdating = doneeUI.continueOperation().equalsIgnoreCase("Y");
            } while (continueUpdating);
        }
    }

    

    public void DoneeWithDistribute(SortedListSetInterface<Donee> donees) {

    }

    public void FilterDonee(SortedListSetInterface<Donee> donees) {

    }

    public void DoneeReports(SortedListSetInterface<Donee> donees) {

    }

}
