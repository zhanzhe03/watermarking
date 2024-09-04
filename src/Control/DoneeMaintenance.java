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
    private int localDay = doneeUI.getLocalDate().getDayOfMonth();
    private int localMonth = doneeUI.getLocalDate().getMonthValue();
    private int localYear = doneeUI.getLocalDate().getYear();

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

    private boolean validateDate(int day, int month, int year) {

        if (year < 2000 || year > localYear) {
            return false;
        } else if (year == localYear && month > localMonth) {
            return false;
        } else if (year == localYear && month == localMonth && day > localDay) {
            return false;
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31)) {
            return false;
        } else if (month == 2 && day > 29 && (year % 4 == 0)) {
            return false;
        } else if (month == 2 && day > 28 && (year % 4 != 0)) {
            return false;
        }
        return true;
    }

    private Date getValidDate(String desc) {
        String date;
        boolean isValid = false;
        int day = 0;
        int month = 0;
        int year = 0;
        do {
            date = doneeUI.getInputString(desc);
            if (!CommonUse.validateDateFormat(date)) {
                MessageUI.displayInvalidDateFormatMessage();
            } else {
                day = Integer.parseInt(date.substring(0, 2));
                month = Integer.parseInt(date.substring(3, 5));
                year = Integer.parseInt(date.substring(6));
                if (!validateDate(day, month, year)) {
                    MessageUI.displayInvalidDateMessage();
                } else {
                    isValid = true;
                }
            }
        } while (!isValid);

        return new Date(day, month, year);
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
                        doneeType = "ORGANISATION";
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
            } else if (contact.length() > 11) {
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
                        RemoveDonee(donees, distributions);
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

    private String printDistributionDetails(String doneeId, Date distributionDate, SortedListSetInterface<SelectedItem> distributedItemList, SortedListSetInterface<Item> donatedItemList, String status) {
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
                        "\n%-15s %-30s %-20s %-20s %-10s %-10s",
                        doneeId, // Donee ID
                        itemType, // Received Item (Item Type)
                        distributionDate, // Receive Date
                        selectedItem.getItemId(), // Item ID

                        selectedItem.getSelectedQuantity() > 0 ? selectedItem.getSelectedQuantity() : String.format("%.2f", selectedItem.getAmount()),
                        // Quantity/Amount
                        status
                ));
                hasPrintedDoneeId = true;
            } else {
                // Print details without Donee ID
                output.append(String.format(
                        "\n%-15s %-30s %-20s %-20s %-10s %-10s",
                        "", // No Donee ID for subsequent items
                        itemType, // Received Item (Item Type)
                        distributionDate, // Receive Date
                        selectedItem.getItemId(), // Item ID
                        selectedItem.getSelectedQuantity() > 0 ? selectedItem.getSelectedQuantity() : String.format("%.2f", selectedItem.getAmount()), // Quantity/Amount
                        status
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

    private boolean isAnyShippedDonee(SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Donee> donees) {
        // Set to hold donees with shipped distributions
        SortedListSetInterface<Donee> doneesWithShippedDistribution = new SortedDoublyLinkedListSet<>();

        // Iterate over each donee in the collection
        Iterator<Donee> doneeIterator = donees.getIterator();
        while (doneeIterator.hasNext()) {
            Donee donee = doneeIterator.next();
            // Check if this donee is part of any shipped distribution
            if (isShippedDonee(distributions, donee)) {
                doneesWithShippedDistribution.add(donee);
            }
        }

        // Return true if there are any donees with shipped distributions
        return !doneesWithShippedDistribution.isEmpty();
    }

    private void removeShippedDistributions(SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Donee> doneesWithShippedDistribution) {

        // To keep track of distributions that need to be removed
        SortedListSetInterface<Distribution> distributionsToRemove = new SortedDoublyLinkedListSet<>();

        // Iterate over each donee and determine their associated distributions
        Iterator<Donee> doneeIterator = doneesWithShippedDistribution.getIterator();
        while (doneeIterator.hasNext()) {
            Donee donee = doneeIterator.next();
            // Use an iterator to go through the distributions
            Iterator<Distribution> distributionIterator = distributions.getIterator();
            while (distributionIterator.hasNext()) {
                Distribution distribution = distributionIterator.next();
                // Check if the distribution contains the donee
                if (distribution.getDistributedDoneeList().contains(donee)) {
                    // Remove the donee from the distribution
                    distribution.getDistributedDoneeList().remove(donee);
                    // Mark the distribution for removal if it is now empty
                    if (distribution.getDistributedDoneeList().isEmpty()) {
                        distributionsToRemove.add(distribution);
                    }
                }
            }
        }

        // Remove the marked distributions
        Iterator<Distribution> removeDistributionIterator = distributionsToRemove.getIterator();
        while (removeDistributionIterator.hasNext()) {
            Distribution dist = removeDistributionIterator.next();
            distributions.remove(dist); // Remove the distribution from the set
        }
        doneeUI.printText("Donees and associated distributions have been removed.");
    }

    private void removeShippedDistributions1(SortedListSetInterface<Distribution> distributions, Donee donee) {
        // Set to keep track of distributions that need to be removed
        SortedListSetInterface<Distribution> distributionsToRemove = new SortedDoublyLinkedListSet<>();

        // Use an iterator to go through the distributions
        Iterator<Distribution> distributionIterator = distributions.getIterator();
        while (distributionIterator.hasNext()) {
            Distribution distribution = distributionIterator.next();

            // Check if this distribution contains the donee
            if (distribution.getDistributedDoneeList().contains(donee)) {
                // Remove the donee from the distribution
                distribution.getDistributedDoneeList().remove(donee);

                // If the distribution is empty after removal, mark it for removal
                if (distribution.getDistributedDoneeList().isEmpty()) {
                    distributionsToRemove.add(distribution);
                }
            }
        }

        // Remove the marked distributions
        Iterator<Distribution> removeDistributionIterator = distributionsToRemove.getIterator();
        while (removeDistributionIterator.hasNext()) {
            Distribution dist = removeDistributionIterator.next();
            distributions.remove(dist); // Remove the distribution from the set
        }

        doneeUI.printText("Donee and associated distributions have been removed.");
    }

    private boolean isShippedDonee(SortedListSetInterface<Distribution> distributions, Donee foundOneDonee) {
        // Create an iterator for the distributions

        Iterator<Distribution> distributionIterator = distributions.getIterator();
        DistributionManager distManager = new DistributionManager();
        distManager.updateDistributionStatus(distributions);

        boolean isShippedFound = false; // Track if any "shipped" status is found

        while (distributionIterator.hasNext()) {
            Distribution distribution = distributionIterator.next();
            // Check if the donee is part of this distribution
            if (distribution.getDistributedDoneeList().contains(foundOneDonee)) {
                // Check if the distribution status is "shipped"
                if ("shipped".equalsIgnoreCase(distribution.getStatus())) {
                    isShippedFound = true; // Mark that at least one "shipped" status was found
                    doneeUI.printText("\nThe donee with ID: " + foundOneDonee.getDoneeId() + " is part of a distribution that is currently shipping.");
                    break; // Exit early since we've found a shipped distribution
                }
            }
        }

        return isShippedFound;
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

    private void listDoneesByDate(SortedListSetInterface<Donee> donees) {
        // Totals
        int totalRequests = 0;
        int totalIndividual = 0;
        int totalOrganization = 0;
        int totalFamily = 0;

        Date startDate = getValidDate("Enter start date (DD-MM-YYYY): ");
        Date endDate = getValidDate("Enter end date (DD-MM-YYYY): ");
        CommonUse.getLogo();
        SortedListSetInterface<Donee> foundDonees = findDoneesDateInRange(donees, startDate, endDate);

        if (foundDonees != null && foundDonees.getNumberOfEntries() > 0) {
            doneeUI.printText("\n\t\tDonees registered between " + startDate + " and " + endDate + ":");
            doneeUI.printRegisterDoneeTitle();
            doneeUI.displayEnDash();

            // Iterate through the found donees
            Iterator<Donee> doneeIterator = foundDonees.getIterator();
            while (doneeIterator.hasNext()) {
                Donee donee = doneeIterator.next();
                String doneeId = donee.getDoneeId();
                Date registerDate = donee.getRegisterDate();
                String doneeType = donee.getDoneeType();

                // Get the donee's requests
                SortedListSetInterface<Request> doneeRequests = donee.getRequests();
                Iterator<Request> requestIterator = doneeRequests.getIterator();

                // Start building the output string for this donee
                StringBuilder outputStr = new StringBuilder();
                boolean firstRequest = true;

                while (requestIterator.hasNext()) {
                    Request request = requestIterator.next();
                    String requestItem = request.getRequestItems();

                    if (firstRequest) {
                        // Print Donee ID, Register Date, and first request item
                        outputStr.append(String.format("\n|%-15s | %-20s | %-20s | %-30s |\n", doneeId, doneeType, registerDate, requestItem));
                        firstRequest = false;
                    } else {
                        // Align subsequent request items
                        outputStr.append(String.format("|%-15s | %-20s | %-20s | %-30s |\n", "", "", "", requestItem));
                    }

                    // Increase the request count
                    totalRequests++;
                }

                // Print the accumulated string for this donee
                doneeUI.printText(outputStr.toString());

                // Determine donee type and update counts
                doneeType = donee.getDoneeType();
                totalIndividual = updateDoneeTypeCounts(doneeType, totalIndividual, "INDIVIDUAL");
                totalOrganization = updateDoneeTypeCounts(doneeType, totalOrganization, "ORGANISATION");
                totalFamily = updateDoneeTypeCounts(doneeType, totalFamily, "FAMILY");
            }
            doneeUI.displayEnDash();

            // Print the summary
            doneeUI.printSummaryHeader(startDate, endDate);
            doneeUI.displayEnDash();
            doneeUI.printText(String.format("\n                                         |%-30s %14d|", "Total Donees:", foundDonees.getNumberOfEntries()));
            doneeUI.printText(String.format("\n                                         |%-30s %14d|", "Total Requests:", totalRequests));
            doneeUI.displayEnDash();

            // Print donee types and their totals
            doneeUI.printText("\n                                         Donee Types and Their Counts:");
            doneeUI.printText(String.format("\n                                         |%-30s %14d|", "Individual:", totalIndividual));
            doneeUI.printText(String.format("\n                                         |%-30s %14d|", "Organisation:", totalOrganization));
            doneeUI.printText(String.format("\n                                         |%-30s %14d|", "Family:", totalFamily));

            generateBarChart(foundDonees.getNumberOfEntries(), totalRequests, totalIndividual, totalOrganization, totalFamily);

        } else {
            doneeUI.printText("No donees found within the specified date range.");
        }
    }

    private void generateBarChart(int totalDonees, int totalRequests, int totalIndividual, int totalOrganization, int totalFamily) {
        doneeUI.displayEnDash();
        doneeUI.printText("\n\t\t\t\tBar chart of Totals");
        doneeUI.displayEnDash();
        doneeUI.printText("\nNumber of Entries");
        doneeUI.printText("       ^");

        // Find the maximum value among all totals to determine the chart height
        int maxValue = Math.max(totalDonees, Math.max(totalRequests, Math.max(totalIndividual, Math.max(totalOrganization, totalFamily))));

        // Print the bars from top (max value) to bottom (1)
        for (int y = maxValue; y > 0; y--) {
            if (y % 5 == 0) {
                doneeUI.printTextWithoutNextLine(String.format("%5d  | ", y));  // Print y-axis scale
            } else {
                doneeUI.printTextWithoutNextLine("       | ");
            }

            // Print bar segments
            donationUI.printTextWithoutNextLine(totalDonees == y ? "   +-----+   " : totalDonees > y ? "   |     |   " : "             ");
            donationUI.printTextWithoutNextLine(totalRequests == y ? "   +-----+   " : totalRequests > y ? "   |     |   " : "             ");
            donationUI.printTextWithoutNextLine(totalIndividual == y ? "   +-----+   " : totalIndividual > y ? "   |     |   " : "             ");
            donationUI.printTextWithoutNextLine(totalOrganization == y ? "   +-----+   " : totalOrganization > y ? "   |     |   " : "             ");
            donationUI.printTextWithoutNextLine(totalFamily == y ? "   +-----+   " : totalFamily > y ? "   |     |   " : "             ");

            donationUI.printText("");  // New line after each row
        }

        // Print x-axis line
        donationUI.printTextWithoutNextLine("       +");
        for (int i = 0; i < 85; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("> Totals");

        // Print x-axis labels
        donationUI.printText("            Donees       Requests    Individual   Organisation   Family");
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

    public void RemoveDonee(SortedListSetInterface<Donee> donees, SortedListSetInterface<Distribution> distributions) {
        SortedListSetInterface<Donee> foundDonee;
        int choose = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                doneeUI.displayEnDash();
                choose = Integer.parseInt(doneeUI.getDoneeDeleteMenu()); // Ensure input is treated as an integer

                if (choose >= 1 && choose <= 4) {
                    validInput = true;  // Valid input received
                } else {
                    MessageUI.displayInvalidOptionMessage(); // Handle out-of-range values
                }
            } catch (NumberFormatException e) {
                MessageUI.displayInvalidIntegerMessage(); // Handle invalid integer input
            }
        }

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

                    // Check if the donee is part of any distribution with the status "shipped"
                    boolean isShippedFound = isShippedDonee(distributions, foundOneDonee);

                    // If isShippedDonee returns true, it means there are shipped distributions to handle
                    if (isShippedFound) {
                        // Ask for removal confirmation if the donee is part of a shipped distribution
                        String yesNo = doneeUI.confirmOperation();
                        if (yesNo.equalsIgnoreCase("Y")) {
                            // Remove the donee and the associated shipped distributions
                            removeShippedDistributions1(distributions, foundOneDonee);
                            donees.remove(foundOneDonee);
                            doneeUI.printText("Donee(s) with ID: " + inputID + " and associated distributions have been removed successfully.");
                        } else {
                            doneeUI.printText("Removal cancelled.");
                        }
                    } else {
                        // If no shipped distribution is found, proceed to remove the donee
                        String yesNo = doneeUI.confirmOperation();
                        if (yesNo.equalsIgnoreCase("Y")) {
                            donees.remove(foundOneDonee);
                            doneeUI.printText("Donee(s) with ID: " + inputID + " have been removed successfully.");
                        } else {
                            doneeUI.printText("Removal cancelled.");
                        }
                    }
                } else {
                    doneeUI.printText("\n\nNo results found for ID: " + inputID + "\n\n");
                }
                break;

            case 2:
                // Remove Donee by Location
                String doneeLocation = inputLocation();
                foundDonee = findDoneeLocation(donees, doneeLocation);

                if (foundDonee != null) {
                    // Display the found donee information
                    doneeUI.printText("Donee(s) found:\n\n");
                    doneeUI.printDoneeTitle();
                    doneeUI.displayEnDash();
                    doneeUI.printText(foundDonee.toString());

                    // Check if any donee in the collection is part of a distribution with the status "shipped"
                    boolean anyShipped = isAnyShippedDonee(distributions, foundDonee);
                    if (anyShipped) {
                        doneeUI.printText("WARNING: Some of these donees are part of distributions that are currently shipping. Removing them will also remove the associated distributions.");
                    }

                    if (!anyShipped) {
                        // Prompt for removal confirmation
                        String yesNo = doneeUI.confirmOperation();
                        if (yesNo.equalsIgnoreCase("Y")) {
                            removeShippedDistributions(distributions, foundDonee);
                            donees.relativeComplement(foundDonee);
                            doneeUI.printText("Donee(s) with location: " + doneeLocation + " have been removed successfully.");
                        } else {
                            doneeUI.printText("Removal cancelled.");
                        }
                    }
                } else {
                    doneeUI.printText("\n\nNo results found for: " + doneeLocation + "\n\n");
                }
                break;

            case 3:
                // Remove Donees by ID Range
                doneeUI.printText("\nEnter first Donee ID and second Donee ID");
                doneeUI.displayEnDash();
                String inputId1 = doneeUI.getDoneeID();
                String inputId2 = doneeUI.getDoneeID();
                foundDonee = findDoneeIDInRange(donees, inputId1, inputId2);

                if (foundDonee != null) {
                    // Display the found donee information
                    doneeUI.printText("\nDonees within range from " + inputId1 + " to " + inputId2 + ": \n");
                    doneeUI.printDoneeTitle();
                    doneeUI.displayEnDash();
                    doneeUI.printText("\n" + foundDonee.toString());
                    doneeUI.displayEnDash();

                    // Check if any donee in the range is part of a distribution with the status "shipped"
                    boolean anyShipped = isAnyShippedDonee(distributions, foundDonee);

                    if (anyShipped) {
                        doneeUI.printText("WARNING: Some of these donees are part of distributions that are currently shipping. Removing them will also remove the associated distributions.");
                    }

                    // Prompt for removal confirmation
                    String yesNo = doneeUI.confirmOperation();
                    if (yesNo.equalsIgnoreCase("Y")) {
                        // Remove the donees and their associated distributions
                        removeShippedDistributions(distributions, foundDonee);
                        donees.relativeComplement(foundDonee);
                        doneeUI.printText("\nDonee(s) with ID: " + inputId1 + " to " + inputId2 + " have been removed successfully.");
                    } else {
                        doneeUI.printText("Removal cancelled.");
                    }
                } else {
                    doneeUI.printText("\n\nNo results found for: " + inputId1 + " to " + inputId2 + "\n\n");
                }
                break;

            case 4:
                // Exit or another operation
                break;
        }
    }

    public void UpdateDonee(SortedListSetInterface<Donee> donees) {
        boolean founded = false;
        boolean validInput = false;
        int choose = 0;
        String newLocation;
        Donee targetDonee = null;
        String newName;
        String newContact;
        String inputID;

        while (!founded) {
            doneeUI.displayEnDash();

            // Get Donee ID from the user
            inputID = doneeUI.getDoneeID();

            // Attempt to find the Donee
            Donee foundDonees = findDoneeID(donees, inputID);

            if (foundDonees != null) {
                targetDonee = foundDonees;
                founded = true;  // Exit the loop if donee is found
            } else {
                doneeUI.printText("Donee with ID " + inputID + " not found. Returning to menu...");
                return;  // Exit the method to return to the menu
            }
        }

        if (founded) {
            boolean continueUpdating;
            do {
                while (!validInput) {
                    doneeUI.displayEnDash();

                    try {
                        // Attempt to parse the input to an integer
                        choose = Integer.parseInt(doneeUI.getDoneeUpdateMenu());

                        // Check if the input falls within the valid range
                        if (choose >= 1 && choose <= 5) {
                            validInput = true;  // Valid input received, exit loop
                        } else {
                            // Display a message if the input is out of range
                            MessageUI.displayInvalidOptionMessage();
                        }
                    } catch (NumberFormatException e) {
                        // Display a message if the input is not a valid integer
                        MessageUI.displayInvalidIntegerMessage();
                    }
                }

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
        doneeUI.displayEnDash();
        doneeUI.donationTitle();
        doneeUI.displayEnDash();

        // Create an iterator for the distributions
        DistributionManager distManager = new DistributionManager();
        distManager.updateDistributionStatus(distributions);
        Iterator<Distribution> distributionIterator = distributions.getIterator();

        while (distributionIterator.hasNext()) {
            Distribution distribution = distributionIterator.next();
            Iterator<Donee> doneeIterator = distribution.getDistributedDoneeList().getIterator();
            while (doneeIterator.hasNext()) {
                Donee donee = doneeIterator.next();
                String doneeId = donee.getDoneeId();
                Date date = distribution.getDistributionDate();
                SortedListSetInterface<SelectedItem> item = distribution.getDistributedItemList();
                String status = distribution.getStatus();
                String outputStr = printDistributionDetails(doneeId, date, item, donatedItemList, status);
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
                Date startDate = getValidDate("Enter start date (DD-MM-YYYY): ");
                Date endDate = getValidDate("Enter end date (DD-MM-YYYY): ");
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
                    listDoneesByDate(donees);
                    break;
                case "2":
                    CommonUse.getLogo();
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

        generateReceivedBarChart(totalMonetaryReceived, totalClothingReceived, totalFoodReceived, totalHouseholdReceived,
                totalEducationalReceived, totalElectronicReceived, totalMedicalReceived);

        generateRequestedBarChart(totalMonetaryRequested, totalClothingRequested, totalFoodRequested, totalHouseholdRequested,
                totalEducationalRequested, totalElectronicRequested, totalMedicalRequested);
    }

    private void generateReceivedBarChart(int totalMonetaryReceived, int totalClothingReceived, int totalFoodReceived,
            int totalHouseholdReceived, int totalEducationalReceived,
            int totalElectronicReceived, int totalMedicalReceived) {

        doneeUI.printText("\n\n\t\t\t\tBar chart of Received Items\n");
        doneeUI.printText("Number of Items");
        doneeUI.printText("       ^");

        // Find the maximum value among all totals to determine the chart height
        int maxValueReceived = Math.max(totalMonetaryReceived, Math.max(totalClothingReceived, Math.max(totalFoodReceived,
                Math.max(totalHouseholdReceived, Math.max(totalEducationalReceived,
                        Math.max(totalElectronicReceived, totalMedicalReceived))))));

        // Print the bars from top (max value) to bottom (1)
        for (int y = maxValueReceived; y > 0; y--) {
            if (y % 5 == 0) {
                doneeUI.printTextWithoutNextLine(String.format("%5d  | ", y));  // Print y-axis scale
            } else {
                doneeUI.printTextWithoutNextLine("       | ");
            }

            // Print bar segments for each category
            doneeUI.printTextWithoutNextLine(totalMonetaryReceived == y ? "   +-----+   " : totalMonetaryReceived > y ? "   |     |   " : "             ");
            doneeUI.printTextWithoutNextLine(totalClothingReceived == y ? "   +-----+   " : totalClothingReceived > y ? "   |     |   " : "             ");
            doneeUI.printTextWithoutNextLine(totalFoodReceived == y ? "   +-----+   " : totalFoodReceived > y ? "   |     |   " : "             ");
            doneeUI.printTextWithoutNextLine(totalHouseholdReceived == y ? "   +-----+   " : totalHouseholdReceived > y ? "   |     |   " : "             ");
            doneeUI.printTextWithoutNextLine(totalEducationalReceived == y ? "   +-----+   " : totalEducationalReceived > y ? "   |     |   " : "             ");
            doneeUI.printTextWithoutNextLine(totalElectronicReceived == y ? "   +-----+   " : totalElectronicReceived > y ? "   |     |   " : "             ");
            doneeUI.printTextWithoutNextLine(totalMedicalReceived == y ? "   +-----+   " : totalMedicalReceived > y ? "   |     |   " : "             ");
            doneeUI.printText("");  // New line after each row
        }

        // Print x-axis line
        doneeUI.printTextWithoutNextLine("       +");
        for (int i = 0; i < 140; i++) { // Adjusted length for the bars
            doneeUI.printTextWithoutNextLine("-");
        }
        doneeUI.printText("> Received Items");

        // Print x-axis labels
        doneeUI.printText("            Monetary     Clothing      Food      Household    Educational    Electronic    Medical");
        doneeUI.printText("                                  Received Items");
    }

    private void generateRequestedBarChart(int totalMonetaryRequested, int totalClothingRequested, int totalFoodRequested,
            int totalHouseholdRequested, int totalEducationalRequested,
            int totalElectronicRequested, int totalMedicalRequested) {

        doneeUI.printText("\n\n\t\t\t\tBar chart of Requested Items\n");
        doneeUI.printText("Number of Items");
        doneeUI.printText("       ^");

        // Find the maximum value among all totals to determine the chart height
        int maxValueRequested = Math.max(totalMonetaryRequested, Math.max(totalClothingRequested, Math.max(totalFoodRequested,
                Math.max(totalHouseholdRequested, Math.max(totalEducationalRequested,
                        Math.max(totalElectronicRequested, totalMedicalRequested))))));

        // Print the bars from top (max value) to bottom (1)
        for (int y = maxValueRequested; y > 0; y--) {
            if (y % 5 == 0) {
                doneeUI.printTextWithoutNextLine(String.format("%5d  | ", y));  // Print y-axis scale
            } else {
                doneeUI.printTextWithoutNextLine("       | ");
            }

            // Print bar segments for each category
            doneeUI.printTextWithoutNextLine(totalMonetaryRequested == y ? "   +-----+   " : totalMonetaryRequested > y ? "   |     |   " : "             ");
            doneeUI.printTextWithoutNextLine(totalClothingRequested == y ? "   +-----+   " : totalClothingRequested > y ? "   |     |   " : "             ");
            doneeUI.printTextWithoutNextLine(totalFoodRequested == y ? "   +-----+   " : totalFoodRequested > y ? "   |     |   " : "             ");
            doneeUI.printTextWithoutNextLine(totalHouseholdRequested == y ? "   +-----+   " : totalHouseholdRequested > y ? "   |     |   " : "             ");
            doneeUI.printTextWithoutNextLine(totalEducationalRequested == y ? "   +-----+   " : totalEducationalRequested > y ? "   |     |   " : "             ");
            doneeUI.printTextWithoutNextLine(totalElectronicRequested == y ? "   +-----+   " : totalElectronicRequested > y ? "   |     |   " : "             ");
            doneeUI.printTextWithoutNextLine(totalMedicalRequested == y ? "   +-----+   " : totalMedicalRequested > y ? "   |     |   " : "             ");
            doneeUI.printText("");  // New line after each row
        }

        // Print x-axis line
        doneeUI.printTextWithoutNextLine("       +");
        for (int i = 0; i < 140; i++) { // Adjusted length for the bars
            doneeUI.printTextWithoutNextLine("-");
        }
        doneeUI.printText("> Requested Items");

        // Print x-axis labels
        doneeUI.printText("            Monetary     Clothing      Food      Household    Educational    Electronic    Medical");
        doneeUI.printText("                                  Requested Items");
    }

}
