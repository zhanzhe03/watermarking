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
import Entity.Donee;
import Entity.Distribution;
import Entity.Item;
import Utility.ClearScreen;
import Utility.MessageUI;
import Utility.CommonUse;
import java.time.LocalDate;
import java.util.Iterator;
import Entity.Date;
import Entity.Request;
import java.util.Stack;
import Entity.SelectedItem;

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

    private int updateDoneeTypeCounts(String doneeType, int currentCount, String targetType) {
        if (doneeType.equalsIgnoreCase(targetType)) {
            return currentCount + 1;  // Increment the count if type matches
        }
        return currentCount;  // Return current count if no match
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

    private int countTypeForRequests(String type, SortedListSetInterface<Request> requests) {
        int count = 0;

        // Iterate through each request
        Iterator<Request> requestIterator = requests.getIterator();
        while (requestIterator.hasNext()) {
            Request request = requestIterator.next();

            // Get the type of the current request
            String requestType = request.getRequestItems();

            // Compare the type and increment count if it matches
            if (requestType.equalsIgnoreCase(type)) {
                count++;
            }
        }

        return count;
    }

    private Date getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        return new Date(day, month, year);
    }

    //Public method 
    public void doneeManagement(EntityInitializer entityInitialize) {
        SortedListSetInterface<Donee> donees = entityInitialize.getDonees();
        SortedListSetInterface<Distribution> distributions = entityInitialize.getDistributions();
        SortedListSetInterface<Item> items = entityInitialize.getItems();
        SortedListSetInterface<Request> requests = entityInitialize.getRequest();

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
                        DoneeWithDistribute(donees, distributions, items);
                        break;
                    case 7:
                        ClearScreen.clearJavaConsoleScreen();
                        FilterDonee(donees, distributions, items);
                        break;
                    case 8:
                        ClearScreen.clearJavaConsoleScreen();
                        DoneeReports(donees, requests, distributions, items);
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

    private String printDistributionDetails(String doneeId, Date distributionDate, SortedListSetInterface<SelectedItem> distributedItemList, SortedListSetInterface<Item> donatedItemList) {
        StringBuilder output = new StringBuilder();

        boolean hasPrintedDoneeId = false;

        // Iterator for distributed items
        Iterator<SelectedItem> itemIterator = distributedItemList.getIterator();

        while (itemIterator.hasNext()) {
            SelectedItem selectedItem = itemIterator.next();

            // Get item type from donatedItemList
            String itemType = getItemTypeFromDistributedItem(selectedItem, donatedItemList);

            if (!hasPrintedDoneeId) {
                // Print the Donee ID once
                output.append(String.format(
                        "\n%-15s %-30s %-20s %-20s %-10s",
                        doneeId, // Donee ID
                        itemType, // Received Item (Item Type)
                        distributionDate, // Receive Date
                        selectedItem.getItemId(), // Item ID
                        selectedItem.getSelectedQuantity() > 0 ? selectedItem.getSelectedQuantity() : String.format("%.2f", selectedItem.getAmount()) // Quantity/Amount
                ));
                hasPrintedDoneeId = true;
            } else {
                // Print details without Donee ID
                output.append(String.format(
                        "\n%-15s %-30s %-20s %-20s %-10s",
                        "", // No Donee ID for subsequent items
                        itemType, // Received Item (Item Type)
                        distributionDate, // Receive Date
                        selectedItem.getItemId(), // Item ID
                        selectedItem.getSelectedQuantity() > 0 ? selectedItem.getSelectedQuantity() : String.format("%.2f", selectedItem.getAmount()) // Quantity/Amount
                ));
            }
        }

        return output.toString();
    }

    private Item checkItemExist(SortedListSetInterface<Item> donatedItemList, String itemId) {
        // Iterate through the donated item list
        Iterator<Item> itemIterator = donatedItemList.getIterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            // Check if the itemId matches the current item in the list
            if (item.getItemId().equals(itemId)) {
                return item;  // Return the item if found
            }
        }
        return null;  // Return null if no matching item is found
    }

    private String getItemTypeFromDistributedItem(SelectedItem selectedItem, SortedListSetInterface<Item> donatedItemList) {
        Iterator<Item> itemIterator = donatedItemList.getIterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            if (item.getItemId().equalsIgnoreCase(selectedItem.getItemId())) {
                return item.getType(); // Return the item type
            }
        }
        return ""; // Return an empty string if item type is not found
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

    private SortedListSetInterface<Donee> findDoneesDateInRange(SortedListSetInterface<Donee> donees, Date startDate, Date endDate) {
        SortedListSetInterface<Donee> foundDonees = new SortedDoublyLinkedListSet<>();
        Iterator<Donee> iterator = donees.getIterator();

        while (iterator.hasNext()) {
            Donee donee = iterator.next();
            Date registrationDate = donee.getRegisterDate();

            if (isDateInRange(registrationDate, startDate, endDate)) {
                foundDonees.add(donee);
            }
        }

        return foundDonees.getNumberOfEntries() > 0 ? foundDonees : null;
    }

    private boolean isDateInRange(Date date, Date startDate, Date endDate) {
        return (date.afterDate(startDate) || date.equals(startDate))
                && (date.beforeDate(endDate) || date.equals(endDate));
    }

    private void listDoneesByDate(SortedListSetInterface<Donee> donees, SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Item> items) {

        // Totals
        int totalRequests = 0;
        int totalIndividual = 0;
        int totalOrganization = 0;
        int totalFamily = 0;

        doneeUI.printText("Please enter the range of date");
        doneeUI.printText("Start Date (dd-mm-yyyy):");
        Date startDate = doneeUI.getRequestDate();
        doneeUI.displayEnDash();
        doneeUI.printText("End Date (dd-mm-yyyy):");
        Date endDate = doneeUI.getRequestDate();
        SortedListSetInterface<Donee> foundDonees = findDoneesDateInRange(donees, startDate, endDate);

        if (foundDonees != null && foundDonees.getNumberOfEntries() > 0) {
            doneeUI.printText("Donees registered between " + startDate + " and " + endDate + ":");
            doneeUI.printDoneeTitle();
            doneeUI.displayEnDash();

            // Iterate through the found donees
            Iterator<Donee> doneeIterator = foundDonees.getIterator();
            while (doneeIterator.hasNext()) {
                Donee donee = doneeIterator.next();
                doneeUI.printText(donee.toString());

                // Count total requests
                totalRequests += donee.getRequests().getNumberOfEntries();
                String doneeType = donee.getDoneeType();
                totalIndividual = updateDoneeTypeCounts(doneeType, totalIndividual, "INDIVIDUAL");
                totalOrganization = updateDoneeTypeCounts(doneeType, totalOrganization, "ORGANIZATION");
                totalFamily = updateDoneeTypeCounts(doneeType, totalFamily, "FAMILY");
            }
            doneeUI.displayEnDash();

            // Print the summary
            doneeUI.printSummaryHeader(startDate, endDate);
            doneeUI.displayEnDash();
            doneeUI.printText(String.format("\n%-20s %d", "Total Donees:", foundDonees.getNumberOfEntries()));
            doneeUI.printText(String.format("\n%-20s %d", "Total Requests:", totalRequests));
            doneeUI.displayEnDash();

            // Print donee types and their totals
            doneeUI.printText("\nDonee Types and Their Counts:");
            doneeUI.printText(String.format("\n%-30s %d", "Individual:", totalIndividual));
            doneeUI.printText(String.format("\n%-30s %d", "Organization:", totalOrganization));
            doneeUI.printText(String.format("\n%-30s %d", "Family:", totalFamily));
        } else {
            doneeUI.printText("No donees found within the specified date range.");
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

            switch (sortOption) {
                case 1:
                    Donee.setSortByCriteria(Donee.SortByCriteria.DONEEID_INASC);
                    break;
                case 2:
                    Donee.setSortByCriteria(Donee.SortByCriteria.DONEEID_INDESC);
                    break;
                case 3:
                    Donee.setSortByCriteria(Donee.SortByCriteria.REQUESTDATE_INASC);
                    break;
                case 4:
                    Donee.setSortByCriteria(Donee.SortByCriteria.REQUESTDATE_INDESC);
                    break;
                default:
                    break;
            }
            // Sort the donees
            donees.reSort();
            doneeUI.printAllDonees(donees);
            doneeUI.displayEnDash();
            doneeUI.printNumberOfEntries(donees);
            Donee.setSortByCriteria(Donee.SortByCriteria.DONEEID_INASC);
            donees.reSort();
        }
    }

    public void AddNewDonee(SortedListSetInterface<Donee> donees) {
        String continueOpt;

        do {
            // Auto-generate DoneeID
            String doneeId = generateNewDoneeId(donees);

            // Title
            doneeUI.displayEnDash();
            doneeUI.printText("\nAdd New Donee");
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

            //getCurrentDate
            Date registerDate = getCurrentDate();

            // Confirm Operation
            String confirmOpt;
            do {
                confirmOpt = doneeUI.confirmOperation();
                if (!confirmOpt.equalsIgnoreCase("Y")) {
                    doneeUI.printText("Operation cancelled.");
                    return;
                }
            } while (!confirmOpt.equalsIgnoreCase("Y"));

            Donee newDonee = new Donee(doneeId, doneeType, name, email, contact, address, location, registerDate);
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
        String newLocation;
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
                        Date requestDate = getCurrentDate();
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

    public void DoneeWithDistribute(SortedListSetInterface<Donee> donees, SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Item> donatedItemList) {

        doneeUI.donationTitle();
        doneeUI.displayEnDash();

        // Create an iterator for the distributions
        Iterator<Distribution> distributionIterator = distributions.getIterator();

        // Iterate through each distribution
        while (distributionIterator.hasNext()) {
            Distribution distribution = distributionIterator.next();
            Iterator<Donee> doneeIterator = distribution.getDistributedDoneeList().getIterator();
            while (doneeIterator.hasNext()) {
                Donee donee = doneeIterator.next();
                String doneeId = donee.getDoneeId();
                Date date = distribution.getDistributionDate();
                SortedListSetInterface<SelectedItem> item = distribution.getDistributedItemList();
                String outputStr = printDistributionDetails(doneeId, date, item, donatedItemList);
                doneeUI.printText(outputStr);
            }
        }

        doneeUI.displayEnDash();
        doneeUI.printText("\nNumber of distributions: " + distributions.getNumberOfEntries());
    }

    private void filterDoneesByDate(SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Item> donatedItemList, Date startDate, Date endDate) {
        // Print the filtering header
        doneeUI.printText("Filtering donees who received items between " + startDate + " and " + endDate + "\n\n");
        doneeUI.filterByItemHeader();
        doneeUI.displayEnDash();

        // Iterate through each distribution
        Iterator<Distribution> distributionIterator = distributions.getIterator();
        while (distributionIterator.hasNext()) {
            Distribution distribution = distributionIterator.next();
            Date distributionDate = distribution.getDistributionDate();

            // Check if the distribution date is within the specified range
            if (!distributionDate.beforeDate(startDate) && !distributionDate.afterDate(endDate)) {

                // Iterate through the donees in the current distribution
                Iterator<Donee> doneeIterator = distribution.getDistributedDoneeList().getIterator();
                while (doneeIterator.hasNext()) {
                    Donee donee = doneeIterator.next();

                    // Iterate through the items received by the donee in this distribution
                    Iterator<SelectedItem> itemIterator = distribution.getDistributedItemList().getIterator();
                    while (itemIterator.hasNext()) {
                        SelectedItem selectedItem = itemIterator.next();

                        // Get the item details (Assume checkItemExist is available)
                        Item item = checkItemExist(donatedItemList, selectedItem.getItemId());

                        // Print the donee's ID, received item, and the distribution date
                        if (item != null) {
                            doneeUI.printText(String.format("\n%-15s %-30s %-20s",
                                    donee.getDoneeId(), // Donee ID
                                    item.getType(), // Received Item Name
                                    distributionDate.toString() // Distribution Date
                            ));
                        }
                    }
                }
            }
        }

        doneeUI.displayEnDash();
    }

    private void filterDoneesByItem(SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Item> donatedItemList, String itemType) {
        // Print the header for filtering by item type
        doneeUI.printText("\nFiltering donees who received items of type: " + itemType + "\n\n");
        doneeUI.filterByItemHeader();
        doneeUI.displayEnDash();

        // Iterate through each distribution
        Iterator<Distribution> distributionIterator = distributions.getIterator();
        while (distributionIterator.hasNext()) {
            Distribution distribution = distributionIterator.next();

            // Iterate through the selected items in the current distribution
            Iterator<SelectedItem> itemIterator = distribution.getDistributedItemList().getIterator();
            while (itemIterator.hasNext()) {
                SelectedItem selectedItem = itemIterator.next();

                // Use the checkItemExist method to find the item by its ID
                Item item = checkItemExist(donatedItemList, selectedItem.getItemId());

                // Check if the item exists and matches the item type
                if (item != null && item.getType().equalsIgnoreCase(itemType)) {
                    // Iterate through donees in the distribution
                    Iterator<Donee> doneeIterator = distribution.getDistributedDoneeList().getIterator();
                    while (doneeIterator.hasNext()) {
                        Donee donee = doneeIterator.next();

                        doneeUI.printText(String.format("\n%-15s %-30s %-20s",
                                donee.getDoneeId(), // Donee ID
                                item.getType(), // Received Item Name
                                distribution.getDistributionDate().toString() // Distribution Date
                        ));
                    }
                    break;
                }
            }
        }

        doneeUI.displayEnDash();
    }

    public void FilterDonee(SortedListSetInterface<Donee> donees, SortedListSetInterface<Distribution> distribution, SortedListSetInterface<Item> Item) {
        String option = doneeUI.getFilterMenu();
        switch (option) {
            case "1":
                // Filter donees by date range who have received items
                doneeUI.printText("\nPlease enter the range of date");
                doneeUI.printText("Start Date (dd-mm-yyyy):");
                Date startDate = doneeUI.getRequestDate();
                doneeUI.displayEnDash();
                doneeUI.printText("\nEnd Date (dd-mm-yyyy):");
                Date endDate = doneeUI.getRequestDate();
                filterDoneesByDate(distribution, Item, startDate, endDate);
                break;

            case "2":
                // Filter donees by item received
                doneeUI.printText("\nPlease enter the item type to filter by:");
                String itemType = inputCategory();
                filterDoneesByItem(distribution, Item, itemType);
                break;

            case "3":
                break;

            default:
                MessageUI.displayInvalidOptionMessage();
                //FilterDonee(donees, distribution);  // Re-display the filter menu for a valid input
                break;
        }
    }

    public void DoneeReports(SortedListSetInterface<Donee> donees, SortedListSetInterface<Request> requests, SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Item> items) {
        String option;
        // Keep asking for input until the correct option is selected
        do {
            option = doneeUI.getReportMenu();

            switch (option) {
                case "1":
                    listDoneesByDate(donees, distributions, items);
                    break;
                case "2":
                    listDoneeRequestReceive(donees, distributions, items);
                    break;
                case "3":
                    return;
                default:
                    MessageUI.displayInvalidOptionMessage();
            }
        } while (!option.equals("3"));
    }

    private void listDoneeRequestReceive(SortedListSetInterface<Donee> donees, SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Item> items) {

        doneeUI.requestReceiveTitle();
        doneeUI.displayEnDash();

        // Headers for categories
        doneeUI.printText(String.format("\n%-15s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s",
                "", "Monetary", "Clothes", "Food", "Household", "Educational", "Electronic", "Medical"));

        doneeUI.displayEnDash();

        // Initialize total counters
        int totalMonetaryRequested = 0;
        int totalClothingRequested = 0;
        int totalFoodRequested = 0;
        int totalHouseholdRequested = 0;
        int totalEducationalRequested = 0;
        int totalElectronicRequested = 0;
        int totalMedicalRequested = 0;

        int totalMonetaryReceived = 0;
        int totalClothingReceived = 0;
        int totalFoodReceived = 0;
        int totalHouseholdReceived = 0;
        int totalEducationalReceived = 0;
        int totalElectronicReceived = 0;
        int totalMedicalReceived = 0;

        // Iterate through each donee
        Iterator<Donee> doneeIterator = donees.getIterator();
        while (doneeIterator.hasNext()) {
            Donee donee = doneeIterator.next();
            SortedListSetInterface<Request> doneeRequests = donee.getRequests();

            // Count requested items by type for the current donee
            int doneeMonetaryRequested = countTypeForRequests("Monetary", doneeRequests);
            int doneeClothingRequested = countTypeForRequests("Clothing and Apparel", doneeRequests);
            int doneeFoodRequested = countTypeForRequests("Food and Beverage", doneeRequests);
            int doneeHouseholdRequested = countTypeForRequests("Household Items", doneeRequests);
            int doneeEducationalRequested = countTypeForRequests("Educational Materials", doneeRequests);
            int doneeElectronicRequested = countTypeForRequests("Electronic", doneeRequests);
            int doneeMedicalRequested = countTypeForRequests("Medical", doneeRequests);

            // Initialize received items count for the current donee
            int doneeMonetaryReceived = 0;
            int doneeClothingReceived = 0;
            int doneeFoodReceived = 0;
            int doneeHouseholdReceived = 0;
            int doneeEducationalReceived = 0;
            int doneeElectronicReceived = 0;
            int doneeMedicalReceived = 0;

            // Iterate through distributions to find items received by the current donee
            Iterator<Distribution> distributionIterator = distributions.getIterator();
            while (distributionIterator.hasNext()) {
                Distribution distribution = distributionIterator.next();

                // Check if the current donee is part of this distribution
                if (distribution.getDistributedDoneeList().contains(donee)) {
                    // Iterate through the distributed items in the distribution
                    Iterator<SelectedItem> itemIterator = distribution.getDistributedItemList().getIterator();
                    while (itemIterator.hasNext()) {
                        SelectedItem selectedItem = itemIterator.next();
                        Item item = checkItemExist(items, selectedItem.getItemId());

                        if (item != null) {
                            switch (item.getType()) {
                                case "Monetary":
                                    doneeMonetaryReceived++;
                                    break;
                                case "Clothing and Apparel":
                                    doneeClothingReceived++;
                                    break;
                                case "Food and Beverage":
                                    doneeFoodReceived++;
                                    break;
                                case "Household Items":
                                    doneeHouseholdReceived++;
                                    break;
                                case "Educational Materials":
                                    doneeEducationalReceived++;
                                    break;
                                case "Electronic":
                                    doneeElectronicReceived++;
                                    break;
                                case "Medical":
                                    doneeMedicalReceived++;
                                    break;
                            }
                        }
                    }
                }
            }

            // Print donee details
            doneeUI.printText(String.format("\n%-15s : %-12s", "Donee ID", donee.getDoneeId()));

            // Print requested items for the current donee
            doneeUI.printText(String.format("%-15s : %-12d | %-12d | %-12d | %-12d | %-12d | %-12d | %-12d",
                    "Request Item", doneeMonetaryRequested, doneeClothingRequested, doneeFoodRequested,
                    doneeHouseholdRequested, doneeEducationalRequested, doneeElectronicRequested, doneeMedicalRequested));

            // Print received items for the current donee
            doneeUI.printText(String.format("%-15s : %-12d | %-12d | %-12d | %-12d | %-12d | %-12d | %-12d",
                    "Receive Item", doneeMonetaryReceived, doneeClothingReceived, doneeFoodReceived,
                    doneeHouseholdReceived, doneeEducationalReceived, doneeElectronicReceived, doneeMedicalReceived));

            doneeUI.displayEnDash(); // Divider line after each donee

            // Update total counters
            totalMonetaryRequested += doneeMonetaryRequested;
            totalClothingRequested += doneeClothingRequested;
            totalFoodRequested += doneeFoodRequested;
            totalHouseholdRequested += doneeHouseholdRequested;
            totalEducationalRequested += doneeEducationalRequested;
            totalElectronicRequested += doneeElectronicRequested;
            totalMedicalRequested += doneeMedicalRequested;

            totalMonetaryReceived += doneeMonetaryReceived;
            totalClothingReceived += doneeClothingReceived;
            totalFoodReceived += doneeFoodReceived;
            totalHouseholdReceived += doneeHouseholdReceived;
            totalEducationalReceived += doneeEducationalReceived;
            totalElectronicReceived += doneeElectronicReceived;
            totalMedicalReceived += doneeMedicalReceived;
        }

        // Print total counts for all donees
        doneeUI.printText(String.format("\n%-15s :", "Total"));

        doneeUI.printText(String.format("%-15s : %-12d | %-12d | %-12d | %-12d | %-12d | %-12d | %-12d",
                "Requested", totalMonetaryRequested, totalClothingRequested, totalFoodRequested,
                totalHouseholdRequested, totalEducationalRequested, totalElectronicRequested, totalMedicalRequested));

        doneeUI.printText(String.format("%-15s : %-12d | %-12d | %-12d | %-12d | %-12d | %-12d | %-12d",
                "Received", totalMonetaryReceived, totalClothingReceived, totalFoodReceived,
                totalHouseholdReceived, totalEducationalReceived, totalElectronicReceived, totalMedicalReceived));

        doneeUI.displayEnDash();
    }

}
