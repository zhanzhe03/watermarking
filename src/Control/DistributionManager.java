/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author szewen
 */
import Boundary.DonationUI;
import Boundary.DistributionUI;
import Utility.ClearScreen;
import Utility.CommonUse;
import Utility.MessageUI;
import Utility.StockUI;
import ADT.SortedDoublyLinkedListSet;
import ADT.SortedListSetInterface;
import Boundary.DoneeUI;
import Entity.Date;
import Entity.Distribution;
import Entity.Donation;
import Entity.Donee;
import Entity.Item;
import Entity.SelectedItem;
import Entity.Request;
import java.util.Iterator;
import DAO.EntityInitializer;

public class DistributionManager {

    private DistributionUI distributionUI = new DistributionUI();
    private DonationUI donationUI = new DonationUI();
    private DoneeUI doneeUI = new DoneeUI();

    private DoneeMaintenance doneeMaintenance = new DoneeMaintenance();
    private int localDay = distributionUI.getLocalDate().getDayOfMonth();
    private int localMonth = distributionUI.getLocalDate().getMonthValue();
    private int localYear = distributionUI.getLocalDate().getYear();

    public void distributionManager(EntityInitializer entityInitialize) {

        SortedListSetInterface<Distribution> distributions = entityInitialize.getDistributions();
        SortedListSetInterface<Item> donatedItemList = entityInitialize.getItems();


        SortedListSetInterface<Donee> donees = entityInitialize.getDonees();
        SortedListSetInterface<Donation> donations = entityInitialize.getDonations();

        SortedListSetInterface<Item> availableItemList = StockUI.getAvailableItemList(donations);
        

        updateDistributionStatus(distributions);

        int opt = 0;
        do {
            try {
                opt = Integer.parseInt(distributionUI.getDistributionMenu());

                switch (opt) {
                    case 1:
                        ClearScreen.clearJavaConsoleScreen();
                        ListAllDistributions(distributions);
                        break;
                    case 2:
                        ClearScreen.clearJavaConsoleScreen();
                        AddNewDistribution(donatedItemList, distributions, donees, donations, availableItemList);
                        break;
                    case 3:
                        ClearScreen.clearJavaConsoleScreen();
                        UpdateDonationDistribution(donatedItemList, distributions, donees);
                        break;
                    case 4:
                        SearchDonationDistribution(distributions, donatedItemList);
                        break;
                    case 5:
                        ClearScreen.clearJavaConsoleScreen();
                        RemoveDonationDistribution(distributions);
                        break;
                    case 6:
                        TrackDistributedItems(distributions, donatedItemList);
                        break;
                    case 7:
                        ClearScreen.clearJavaConsoleScreen();
                        GenerateSummaryReport(distributions, donatedItemList);
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

    private void updateDistributionStatus(SortedListSetInterface<Distribution> distributions) {
        Date currentDate = new Date(localDay, localMonth, localYear); // Assume localDay, localMonth, and localYear are properly initialized
        Iterator<Distribution> iterator = distributions.getIterator();

        while (iterator.hasNext()) {
            Distribution currentRecord = iterator.next();
            Date distributedDate = currentRecord.getDistributionDate();
            int daysBetween = currentDate.daysBetween(distributedDate);

            // Update status based on the number of days and the current status
            if (currentRecord.getStatus().equalsIgnoreCase("MERGED")) {
                if (daysBetween >= 2) {
                    currentRecord.setStatus("DISTRIBUTED");
                } else if (daysBetween == 1) {
                    currentRecord.setStatus("SHIPPED");
                }
            } else if (currentRecord.getStatus().equalsIgnoreCase("PENDING")) { // For records that are not "MERGED"
                if (daysBetween >= 2) {
                    currentRecord.setStatus("DISTRIBUTED");
                } else if (daysBetween == 1) {
                    currentRecord.setStatus("SHIPPED");
                } else {
                    currentRecord.setStatus("PENDING"); // Or another default status if needed
                }
            }
        }
    }

    public void ListAllDistributions(SortedListSetInterface<Distribution> distributions) {
        // Default: List all distributions sorted by Distribution ID in ascending order
        Distribution.setSortByCriteria(Distribution.SortByCriteria.DISTID_INASC);
        distributions.reSort();
        distributionUI.displayMessage("\nAll Distribution Records : ");
        distributionUI.listAllDistributions(distributions);

        // Ask user if they want to list distributions with a different sorting method
        listDistribution(distributions);
    }

    public void listDistribution(SortedListSetInterface<Distribution> distributions) {
        // Ask the user if they want to sort the list
        boolean sortList = distributionUI.askIfSort(); // Assuming askIfSort() returns a boolean

        if (sortList) {
            int opt;
            do {
                opt = distributionUI.displaySortingMenu(); // Display the menu and get the user's choice
                switch (opt) {
                    case 1:
                        ClearScreen.clearJavaConsoleScreen();
                        distributionUI.displayMessage("All Distribution Records sorted by Distribution ID in Ascending Order");
                        Distribution.setSortByCriteria(Distribution.SortByCriteria.DISTID_INASC);
                        break;
                    case 2:
                        ClearScreen.clearJavaConsoleScreen();
                        distributionUI.displayMessage("All Distribution Records sorted by Distribution ID in Descending Order");
                        Distribution.setSortByCriteria(Distribution.SortByCriteria.DISTID_INDESC);
                        break;
                    case 3:
                        ClearScreen.clearJavaConsoleScreen();
                        distributionUI.displayMessage("All Distribution Records sorted by Distribution Date in Ascending Order");
                        Distribution.setSortByCriteria(Distribution.SortByCriteria.DISTRIBUTIONDATE_INASC);
                        break;
                    case 4:
                        ClearScreen.clearJavaConsoleScreen();
                        distributionUI.displayMessage("All Distribution Records sorted by Distribution Date in Descending Order");
                        Distribution.setSortByCriteria(Distribution.SortByCriteria.DISTRIBUTIONDATE_INDESC);
                        break;
                    case 9:
                        ClearScreen.clearJavaConsoleScreen();
                        distributionUI.displayMessage("Exiting...");
                        break;
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
                }

                if (opt >= 1 && opt <= 4) {
                    distributions.reSort();
                    distributionUI.listAllDistributions(distributions);
                }
            } while (opt != 9);
        } else {
            distributionUI.displayMessage("No sorting applied. Exiting...");
        }
    }

//**** Adding purpose  //done ask whether add other items into same distribution
    //done ask add another distribution?   distribute according request and remove request
    public void AddNewDistribution(SortedListSetInterface<Item> donatedItemList, SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Donee> donees, SortedListSetInterface<Donation> donations,
            SortedListSetInterface<Item> availableItemList) {
        String input;
        boolean isContinue = false;
        boolean foundType;

        double minttlAmt = 0.0;
        int minQty = 0 ;

        do {
            // Generate new distribution info
            Date distributedDate = new Date(localDay, localMonth, localYear);
            int lastDistributionId = Integer.parseInt(distributions.getLastEntries().getDistributionId().substring(4)) + 1;
            String newDistId = "DIST" + String.format("%03d", lastDistributionId);

            Donee selectedDonee = null;
            do {
                try {
                    distributionUI.displayMessage("Available Donees : ");
                    doneeUI.displayEnDash();
                    doneeUI.printDoneeTitle();
                     doneeUI.displayEnDash();
                    distributionUI.displayMessage("" + donees); // Display the list of donees
                    input = distributionUI.getInputString("Please enter the Donee ID to distribute to ('Q' quit) > ");

                    if (input.equalsIgnoreCase("Q")) {  // Quit the loop
                        return;
                    }

                    selectedDonee = CommonUse.findDonee(input, donees); // Check if the donee exists            
                    if (selectedDonee == null) {
                        MessageUI.displayInvalidDonee();
                    } else {
                        if (selectedDonee.getRequests().getNumberOfEntries() >= 1) {
                            displayDoneeRequest(selectedDonee); // Display Donee request
                        }

                        if (selectedDonee.getDoneeType().equalsIgnoreCase("Organisation")) {
                            minQty = 10;
                            minttlAmt = 1000.00;
                        } else if (selectedDonee.getDoneeType().equalsIgnoreCase("Family")) {
                            minQty = 3;
                            minttlAmt = 100.00;
                        } else {
                            minQty = 1;
                            minttlAmt = 1.00;
                        }
                        break;
                    } // Donee found, exit the loop
                } catch (Exception e) {
                    distributionUI.displayMessage("An error occurred: " + e.getMessage());
                }
            } while (selectedDonee == null); // Continue until a valid donee is selected

            // Create the new distribution with the selected donee
            Distribution newDistribution = new Distribution(newDistId, distributedDate);
            newDistribution.addDonee(selectedDonee);

            do {
                try {
                    
                    CommonUse.printItemHeader();
                    distributionUI.displayMessage("\n");
                    distributionUI.displayMessage("" + availableItemList);
                    input = distributionUI.getInputString("Please enter the Item ID that you would like to distribute ('Q' quit; 'S' sort By Category ; F' filter By Category ) > ");

                    if (input.equalsIgnoreCase("Q")) {  // Quit the loop
                        handleQuit(isContinue, newDistribution, distributions);
                        return; // Exit method
                    }

                    if (input.equalsIgnoreCase("F")) { // Filter items

                        String inputType = distributionUI.getInputString("Please enter the type you would like to show > ");
                        foundType = filterItemByType(availableItemList, inputType, selectedDonee);

                        if (!foundType) {
                            return;
                        }

                        input = distributionUI.getInputString("Please enter the Item ID that you would like to distribute ('Q' quit) > ");
                        if (input.equalsIgnoreCase("Q")) { // Quit the loop
                            handleQuit(isContinue, newDistribution, distributions);
                            return; // Exit method
                        }
                    } else if (input.equalsIgnoreCase("S")) {
                        ClearScreen.clearJavaConsoleScreen();

                        displayDoneeRequest(selectedDonee); // Display Donee request again after sorting
                        CommonUse.printItemHeader();
                        distributionUI.displayMessage("\n");
                        Item.setSortByCriteria(Item.SortByCriteria.TYPE_INASC);
                        availableItemList.reSort();
                        distributionUI.displayMessage("" + availableItemList);
                        Item.setSortByCriteria(Item.SortByCriteria.ITEMID_INASC);
                        availableItemList.reSort();

                        input = distributionUI.getInputString("Please enter the Item ID that you would like to distribute ('Q' quit) > ");
                        if (input.equalsIgnoreCase("Q")) { // Quit the loop
                            handleQuit(isContinue, newDistribution, distributions);
                            return; // Exit method
                        }
                    }

                    Item inputItem = checkItemExist(donatedItemList, input); // Check if the item exists in donated list
                    if (inputItem != null) { // If item is found
                        Item availableItem = checkItemAvailable(availableItemList, input); // Check if the item is available for distribution
                        if (availableItem != null) { // If the item is available
                            if (inputItem.getType().equalsIgnoreCase("Monetary")) {
                                if (StockUI.checkMonetary("Monetary", donations)) {
                                    isContinue = handleMonetaryItem(inputItem, newDistribution, distributions, minttlAmt, selectedDonee, donations);
                                    isDistributionMatchingRequest(newDistribution, selectedDonee, availableItemList);
                                } else {
                                    MessageUI.displayInsufficientMessage();
                                    isContinue = false;
                                }
                            } else {
                                if (StockUI.checkInventory(inputItem.getType(), donations)) {
                                    isContinue = handleNonMonetaryItem(inputItem, newDistribution, distributions, donations);
                                    isDistributionMatchingRequest(newDistribution, selectedDonee, availableItemList);
                                } else {
                                    MessageUI.displayInsufficientMessage();
                                    isContinue = true; // Allow user to enter a new item
                                }
                            }
                        } else {
                            MessageUI.displayRed("Item exist in donated Item List, but its currently not available for distribution.");// Display message if item is not available for distribution
                            isContinue = true; // Allow user to enter a new item
                        }
                    } else {
                        MessageUI.displayInvalidItemMessage();
                        isContinue = true; // Allow user to enter a new item
                    }

                } catch (Exception e) {
                    distributionUI.displayMessage("An error occurred: " + e.getMessage());
                    isContinue = true; // Allow user to enter a new item
                }
            } while (isContinue); // Continue until the user decides to quit

            input = distributionUI.getInputString("\nWould you like to add another distribution for a different donee? ('Y' to continue; 'N' to quit) > ");
        } while (input.equalsIgnoreCase("Y"));
    }

    private void displayDoneeRequest(Donee selectedDonee) {
        distributionUI.displayMessage("\nDonee " + selectedDonee.getDoneeId() + " requests for : ");
        distributionUI.displayMessage("" + selectedDonee.getRequests());
    }

    private void handleQuit(boolean isContinue, Distribution newDistribution, SortedListSetInterface<Distribution> distributions) {
        if (isContinue == true) {
            String keepOrDiscard = distributionUI.getInputString("You have not added any items. Do you want to keep the previous actions? ('Y' to keep; 'N' to discard) > ");
            if (keepOrDiscard.equalsIgnoreCase("Y")) {
                distributions.add(newDistribution); // Add the distribution to the list even if empty
                MessageUI.displayGreenSuccessfulMsg("\nDistribution record is added.");
            } else {
                MessageUI.displayBlueRemindMsg("\nNo distribution added.");
            }
        }
    }

    private void isDistributionMatchingRequest(Distribution distribution, Donee donee, SortedListSetInterface<Item> donatedItemList) {

        Iterator<SelectedItem> distributedItemsIterator = distribution.getDistributedItemList().getIterator();
        boolean foundMatch = false;

        while (distributedItemsIterator.hasNext()) {
            SelectedItem selectedItem = distributedItemsIterator.next();

            // Lookup the item to get its type
            Item item = checkItemExist(donatedItemList, selectedItem.getItemId());
            if (item == null) {
                continue; // Skip if the item is not found
            }

            String itemType = item.getType();

            // Reset the donee requests iterator for each distributed item
            Iterator<Request> doneeRequestsIterator = donee.getRequests().getIterator();

            // Traverse through donee's requests
            while (doneeRequestsIterator.hasNext()) {
                Request requestItem = doneeRequestsIterator.next();

                // Check if the distributed item's type matches any of the donee's requests
                if (itemType.equalsIgnoreCase(requestItem.getRequestItems())) {
                    donee.getRequests().remove(requestItem);
                    distributionUI.displayMessage("Done distributed for donee for request category: " + requestItem.getRequestItems());
                    foundMatch = true;
                    break; // Exit the inner loop if a match is found
                }
            }
            if (foundMatch) {
                break; // Exit the outer loop if a match is found
            }
        }
    }

    private Item checkItemExist(SortedListSetInterface<Item> donatedItemList, String input) {
        Iterator<Item> iterator = donatedItemList.getIterator();
        while (iterator.hasNext()) {
            Item currentItem = iterator.next(); // Get the current item
            if (currentItem.getItemId().equalsIgnoreCase(input)) { // Check if item ID matches the input
                return currentItem; // Return the item if found
            }
        }
        return null; // Return null if the item is not found
    }

    private Item checkItemAvailable(SortedListSetInterface<Item> availabletemList, String input) {
        Iterator<Item> iterator = availabletemList.getIterator();
        while (iterator.hasNext()) {
            Item currentItem = iterator.next(); // Get the current item
            if (currentItem.getItemId().equalsIgnoreCase(input)) { // Check if item ID matches the input
                return currentItem; // Return the item if found
            }
        }
        return null; // Return null if the item is not found
    }

    private boolean handleMonetaryItem(Item inputItem, Distribution newDistribution, SortedListSetInterface<Distribution> distributions, double minAmt, Donee donee, SortedListSetInterface<Donation> donations) {
        boolean isValidAmt = false;
        boolean isContinue = false;
        double inputAmt;

        while (!isValidAmt) {
            try {
                // Get and validate the desired amount
                MessageUI.displayCyanNote("***Please note that the donee type is " + donee.getDoneeType()
                        + " , suggested minimum distributed amount is RM " + minAmt);

                inputAmt = Double.parseDouble(distributionUI.getInputString("\nPlease enter the desired amount (RM) > "));
                if (inputAmt <= 0) {
                    MessageUI.displayRed("Invalid amount entered. Amount must be greater than 0. Please try again.");
                    continue; // Prompt user again
                }

                if (inputItem.getTotalAmount() >= inputAmt) {
                    SelectedItem selectedItem = new SelectedItem(inputItem.getItemId(), inputAmt);

                    // Display details for confirmation
                    distributionUI.displayMessage("You are about to add the following item to the distribution:");
                    MessageUI.displayMagentaPreviewMsg("Item ID : " + selectedItem.getItemId() + "\n");
                    MessageUI.displayMagentaPreviewMsg("Description : " + inputItem.getDesc() + "\n");
                    MessageUI.displayMagentaPreviewMsg("Amount : " + inputAmt + "\n");
                    String confirmAdd = distributionUI.getInputString("\nDo you want to confirm adding this item? (y/n) > ");

                    if (confirmAdd.equalsIgnoreCase("Y")) {
                        newDistribution.addSelectedItem(selectedItem); // Add selected item into the new distribution
                        inputItem.setTotalAmount(inputItem.getTotalAmount() - inputAmt);
                        isValidAmt = true; // Exit the amount input loop
                        Donation donation = CommonUse.findDonationByItem(inputItem, donations);
                        donation.setStatus("Distributing");

                        String keepAdding = distributionUI.getInputString("Do you want to add another item into this distribution? (y/n) > ");
                        if (keepAdding.equalsIgnoreCase("N")) {
                            distributions.add(newDistribution); // Add the distribution to the list
                            MessageUI.displayGreenSuccessfulMsg("\nDistribution record is added.");
                            isContinue = false; // Stop adding items
                        } else {
                            isContinue = true; // Continue adding items
                        }
                    } else {
                        MessageUI.displayBlueRemindMsg("Item addition canceled. Please enter a new Item ID.");
                        return true; // Return true to continue the outer loop to allow re-entry of item ID
                    }
                } else {
                    MessageUI.displayRed("Your input amount exceeds the in-stock amount. Please try again.");
                    distributionUI.displayMessage("In-stock amount: " + inputItem.getTotalAmount());
                }
            } catch (NumberFormatException e) {
                MessageUI.displayRed("Invalid input. Please enter a valid numeric amount.");
            }
        }

        return isContinue;
    }

    private boolean handleNonMonetaryItem(Item inputItem, Distribution newDistribution, SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Donation> donations) {
        boolean isValidQty = false;
        boolean isContinue = false;
        int inputQty;

        while (!isValidQty) { // Loop until a valid quantity is entered
            try {
                inputQty = Integer.parseInt(distributionUI.getInputString("Please enter the desired quantity > "));

                if (inputQty <= 0) {
                    MessageUI.displayRed("Invalid amount entered. Amount must be greater than 0. Please try again.");
                    continue; // Prompt user again
                }

                if (inputItem.getQuantity() >= inputQty) {
                    SelectedItem selectedItem = new SelectedItem(inputItem.getItemId(), inputQty);

                    // Display details for confirmation
                    distributionUI.displayMessage("You are about to add the following item to the distribution:");
                    MessageUI.displayMagentaPreviewMsg("Item ID : " + selectedItem.getItemId() + "\n");
                    MessageUI.displayMagentaPreviewMsg("Description : " + inputItem.getDesc() + "\n");
                    MessageUI.displayMagentaPreviewMsg("Quantity : " + inputQty + "\n");
                    String confirmAdd = distributionUI.getInputString("Do you want to confirm adding this item? (y/n) > ");

                    if (confirmAdd.equalsIgnoreCase("Y")) {
                        newDistribution.addSelectedItem(selectedItem); // Add selected item into the new distribution
                        inputItem.setQuantity(inputItem.getQuantity() - inputQty);
                        isValidQty = true; // Exit the quantity input loop
                        Donation donation = CommonUse.findDonationByItem(inputItem, donations);
                        donation.setStatus("Distributing");
                        
                        String keepAdding = distributionUI.getInputString("Do you want to add another item into this distribution? (y/n) > ");
                        if (keepAdding.equalsIgnoreCase("N")) {
                            distributions.add(newDistribution); // Add the distribution to the list
                            MessageUI.displayGreenSuccessfulMsg("\nDistribution record is added.");
                            isContinue = false; // Stop adding items
                        } else {
                            isContinue = true; // Continue adding items
                        }
                    } else {
                        MessageUI.displayBlueRemindMsg("Item addition canceled. Please enter a new Item ID.");
                        return true; // Return true to continue the outer loop to allow re-entry of item ID
                    }
                } else {
                    MessageUI.displayRed("Your input amount has exceed the in-stock amount. Please try again.");
                    distributionUI.displayMessage("In-stock quantity : " + inputItem.getQuantity());
                }
            } catch (NumberFormatException e) {
                MessageUI.displayInvalidIntegerMessage();
            }
        }

        return isContinue;
    }

//**** Adding purpose
    private boolean filterItemByType(SortedListSetInterface<Item> donatedItemList, String type, Donee selectedDonee) {
        boolean foundType = false;
        Iterator<Item> iterator = donatedItemList.getIterator();

        // Display the donee request before filtering
        displayDoneeRequest(selectedDonee);

        // Iterate through the item list and check for items of the specified type
        while (iterator.hasNext()) {
            Item currentItem = iterator.next();
            if (currentItem.getType().equalsIgnoreCase(type)) {
                if (!foundType) {
                    // Print the item header only once, before the first matching item is displayed

                    distributionUI.displayMessage("Item List filtered by " + type + " : ");
                    CommonUse.printItemHeader();
                    distributionUI.displayMessage("");
                    foundType = true;
                }
                distributionUI.displayMessage("" + currentItem);
            }
        }

        // If no items of the specified type are found, display a message
        if (!foundType) {
            MessageUI.displayRed("No items match the type: < " + type + " >.\n\n");
        }

        distributionUI.displayMessage("\n");

        return foundType;
    }

    //update add/dltitem qty  done  //add another item consider as merge
    //update donee done
    //status either pending or distributed done
    //record cant be changed after 2 days from distributed date done
    public void UpdateDonationDistribution(SortedListSetInterface<Item> donatedItemList, SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Donee> donees) {
        // List all distributions and get the distribution ID to update
        ListAllDistributions(distributions);
        String updateDistID = distributionUI.getInputString("\nPlease enter the distribution ID that you would like to update ('Q' quit ) > ");
        if (updateDistID.equalsIgnoreCase("Q")) {
            return;
        }
        Distribution updateDist = checkDistributionExist(distributions, updateDistID);

        if (updateDist != null) {  // Check if the record exists
            boolean continueLoop = true;

            if (updateDist.getStatus().equalsIgnoreCase("Distributed")) {
                MessageUI.displayBlueRemindMsg("The distribution record with ID < " + updateDistID + " > is already distributed  and cannot be updated.");
                return;
            } else if (updateDist.getStatus().equalsIgnoreCase("Shipped")) {

                MessageUI.displayBlueRemindMsg("The distribution record with ID < " + updateDistID + " > is already shipped and cannot be updated.");
                return;
            }
            else if (updateDist.getStatus().equalsIgnoreCase("MERGED")) {

                MessageUI.displayBlueRemindMsg("The distribution record with ID < " + updateDistID + " > is merged and not allowed to update.");
                return;
            }

            while (continueLoop) {
                distributionUI.displayMessage("\nWhat would you like to update? ");
                distributionUI.displayMessage("1. Item details \n2. Donee details\n3. Cancel this distribution\n4. Exit");
                String userOpt = distributionUI.getInputString("> ").toLowerCase();

                switch (userOpt) {
                    case "1":
                        updateItemDetails(distributions, donatedItemList, updateDist);
                        continueLoop = false;  // Exit the loop
                        break;

                    case "2":
                        updateDoneeDetails(updateDist, donees);
                        break;

                    case "3":
                        cancelCurrentRecord(distributions, updateDist);
                        break;

                    case "4":
                        distributionUI.displayMessage("Returning to the menu.");
                        continueLoop = false;  // Exit the loop
                        break;

                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
                }
            }
        } else {
            MessageUI.displayNoRecord(updateDistID.toUpperCase());
        }
    }

    private Distribution checkDistributionExist(SortedListSetInterface<Distribution> distributions, String checkDistID) {
        Iterator<Distribution> iterator = distributions.getIterator();
        while (iterator.hasNext()) {
            Distribution currentRecord = iterator.next();
            if (currentRecord.getDistributionId().equalsIgnoreCase(checkDistID)) {
                ClearScreen.clearJavaConsoleScreen();
                distributionUI.printDistributionTitleHeader();
                distributionUI.displayMessage("" + currentRecord);
                return currentRecord;

            } else {
            }
        }
        return null;
    }

    private void updateItemDetails(SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Item> donatedItemList, Distribution updateDist) {
        boolean continueUpdating = true;
        boolean isUpdated = false;  // Flag to track if any updates were made

        while (continueUpdating) {
            // Display the current list of distributed items
            distributionUI.printDistriburedItemList(updateDist.getDistributedItemList());

            if (updateDist.getDistributedItemList().getNumberOfEntries() >= 1) {
                SelectedItem selectedItemToUpdate;
                String input;

                // Prompt the user for the ItemId if there's more than one item
                if (updateDist.getDistributedItemList().getNumberOfEntries() > 1) {
                    input = distributionUI.getInputString("Enter ItemId that you would like to update ('Q' to quit) > ");
                    if (input.equalsIgnoreCase("q")) {
                        return;  // Exit the update process if the user enters 'Q'
                    }
                    selectedItemToUpdate = findSelectedItem(updateDist, input);
                } else {
                    // Automatically select the item if there's only one
                    selectedItemToUpdate = updateDist.getDistributedItemList().getLastEntries();
                }

                if (selectedItemToUpdate != null) {
                    // Check if the item exists in the donated item list
                    Item itemToUpdate = checkItemExist(donatedItemList, selectedItemToUpdate.getItemId());

                    if (itemToUpdate != null) {
                        // Update based on the item's type
                        if (itemToUpdate.getType().equalsIgnoreCase("Monetary")) {
                            isUpdated = updateMonetaryItem(distributions, selectedItemToUpdate, itemToUpdate, updateDist) || isUpdated;
                        } else {
                            isUpdated = updateNonMonetaryItem(distributions, selectedItemToUpdate, itemToUpdate, updateDist) || isUpdated;
                        }
                    } else {
                        MessageUI.displayRed("Corresponding donated item not found. \n"
                                + "It may have been removed, hence this item record cannot be amended or updated.");
                    }
                } else {
                    MessageUI.displayInvalidItemMessage();
                }

                // Ask the user if they want to continue updating
                if (updateDist.getDistributedItemList().getNumberOfEntries() > 1) {
                    try {
                        String continueInput = distributionUI.getInputString("Do you want to update another item? (Y/N) > ");
                        if (!continueInput.equalsIgnoreCase("Y")) {
                            continueUpdating = false;  // Stop if the user enters 'N'
                            if (isUpdated) {
                                distributionUI.displayMessage("");
                                MessageUI.displayMagentaPreviewMsg("Updated distribution record: " + updateDist);
                                distributionUI.displayMessage("");
                            }
                        }
                    } catch (Exception ex) {
                        MessageUI.displayInvalidOptionMessage();
                    }
                } else {
                    // If only one item was updated, exit the loop after the update
                    continueUpdating = false;
                    if (isUpdated) {
                        distributionUI.displayMessage("");
                        MessageUI.displayMagentaPreviewMsg("Updated distribution record: " + updateDist);
                        distributionUI.displayMessage("");
                    }
                }
            }
        }
    }

    private boolean updateMonetaryItem(SortedListSetInterface<Distribution> distributions, SelectedItem selectedItemToUpdate, Item itemToUpdate, Distribution updateDist) {
        double oriAmt = selectedItemToUpdate.getAmount();
        distributionUI.displayMessage("Original amount: " + oriAmt);

        double instockAmt = itemToUpdate.getTotalAmount();
        distributionUI.displayMessage("In stock amount: " + instockAmt);

        double updatedAmt = 0.0;
        boolean isUpdated = false;

        do {
            try {
                distributionUI.displayMessage("Please enter your desired amount to update ");
                MessageUI.displayRed("*** Enter 0 = cancel item from this distribution record. ");
                updatedAmt = Double.parseDouble(distributionUI.getInputString("> "));

                if (updatedAmt < 0) {
                    MessageUI.displayRed("Invalid amount entered. Amount must be greater than 0. Cancelled update.");
                    continue; // Prompt user again
                }

                if (updatedAmt == 0) {
                    String confirmRemoval = distributionUI.getInputString("You are about to remove the item from the distribution record. Confirm? (Y/N) > ");
                    if (confirmRemoval.equalsIgnoreCase("Y")) {
                        updateDist.getDistributedItemList().remove(selectedItemToUpdate);
                        itemToUpdate.setTotalAmount(instockAmt + oriAmt);
                        distributionUI.displayMessage("Item has been removed from the distribution record.");
                        if (updateDist.getDistributedItemList().getNumberOfEntries() == 0) {
                            distributionUI.displayMessage("Since there are no items in this distribution record, < "
                                    + updateDist.getDistributionId() + " > will be removed.");
                            distributions.remove(updateDist);
                            distributionUI.displayMessage("This record has been removed from the distribution list.");
                        }
                        isUpdated = true;
                        break;
                    } else {
                        MessageUI.displayRed("Item removal canceled.");
                    }
                    return isUpdated;
                }

                if (updatedAmt > instockAmt + oriAmt) {
                    MessageUI.displayRed("The amount entered exceeds the in-stock amount. Please try again.\n\n");
                } else {
                    String confirmAmtUpdate = distributionUI.getInputString("You are about to update the amount from " + oriAmt + " to " + updatedAmt + ". Confirm? (Y/N) > ");
                    if (confirmAmtUpdate.equalsIgnoreCase("Y")) {
                        selectedItemToUpdate.setAmount(updatedAmt);
                        itemToUpdate.setTotalAmount(instockAmt - (updatedAmt - oriAmt));
                        distributionUI.displayMessage("The amount has been updated from " + oriAmt + " to " + updatedAmt);
                        isUpdated = true;
                    } else {
                        MessageUI.displayRed("Amount update canceled.");
                    }
                }
            } catch (NumberFormatException ex) {
                MessageUI.displayRed("Invalid input. Please enter a numeric value.\n\n");
            } catch (Exception ex) {
                distributionUI.displayMessage(ex.getMessage());
            }
        } while (updatedAmt > instockAmt + oriAmt);

        return isUpdated;
    }

    private boolean updateNonMonetaryItem(SortedListSetInterface<Distribution> distributions, SelectedItem selectedItemToUpdate, Item itemToUpdate, Distribution updateDist) {
        int oriQty = selectedItemToUpdate.getSelectedQuantity();
        distributionUI.displayMessage("Original quantity: " + oriQty);

        int instockQty = itemToUpdate.getQuantity();
        distributionUI.displayMessage("In stock quantity: " + instockQty);

        int updatedQty = 0;
        boolean isUpdated = false;

        do {
            try {
                distributionUI.displayMessage("Please enter your desired quantity to update ");
                MessageUI.displayRed("*** Enter 0 = cancel item from this distribution record. ");
                updatedQty = Integer.parseInt(distributionUI.getInputString("> "));

                if (updatedQty < 0) {
                    MessageUI.displayRed("Invalid quantity entered. Quantity must be greater than 0. Cancelled update.");
                    continue; // Prompt user again
                }

                if (updatedQty == 0) {
                    String confirmRemoval = distributionUI.getInputString("You are about to remove the item from the distribution record. Confirm? (Y/N) > ");
                    if (confirmRemoval.equalsIgnoreCase("Y")) {
                        updateDist.getDistributedItemList().remove(selectedItemToUpdate);
                        itemToUpdate.setQuantity(instockQty + oriQty);
                        distributionUI.displayMessage("Item has been removed from the distribution record.");
                        if (updateDist.getDistributedItemList().getNumberOfEntries() == 0) {
                            distributionUI.displayMessage("Since there are no items in this distribution record, < "
                                    + updateDist.getDistributionId() + " > will be removed.");
                            distributions.remove(updateDist);
                            distributionUI.displayMessage("This record has been removed from the distribution list.");
                        }
                        isUpdated = true;
                        break;
                    } else {
                        MessageUI.displayRed("Item removal canceled.");
                    }
                    return isUpdated;
                }

                if (updatedQty > instockQty + oriQty) {
                    MessageUI.displayRed("The quantity entered exceeds the in-stock quantity. Please try again.\n\n");
                } else {
                    String confirmQtyUpdate = distributionUI.getInputString("You are about to update the quantity from " + oriQty + " to " + updatedQty + ". Confirm? (Y/N) > ");
                    if (confirmQtyUpdate.equalsIgnoreCase("Y")) {
                        selectedItemToUpdate.setSelectedQuantity(updatedQty);
                        itemToUpdate.setQuantity(instockQty - (updatedQty - oriQty));
                        distributionUI.displayMessage("The quantity has been updated from " + oriQty + " to " + updatedQty);
                        isUpdated = true;
                    } else {
                        MessageUI.displayRed("Quantity update canceled.");
                    }
                }
            } catch (NumberFormatException ex) {
                MessageUI.displayRed("Invalid input. Please enter a numeric value.\n\n");
            } catch (Exception ex) {
                distributionUI.displayMessage(ex.getMessage());
            }
        } while (updatedQty > instockQty + oriQty);

        return isUpdated;
    }

    private SelectedItem findSelectedItem(Distribution updateDist, String itemId) {
        Iterator<SelectedItem> iterator = updateDist.getDistributedItemList().getIterator();
        while (iterator.hasNext()) {
            SelectedItem currentRecord = iterator.next();
            if (currentRecord.getItemId().equalsIgnoreCase(itemId)) {
                return currentRecord;
            }
        }
        return null;
    }

    private void updateDoneeDetails(Distribution updateDist, SortedListSetInterface<Donee> donees) {

        distributionUI.displayMessage("\nDistribution Details: " + updateDist + "\n");
        MessageUI.displayMagentaPreviewMsg("Current Donee Details: " + updateDist.getDistributedDoneeList());

        String yesNo;
        Donee foundDonee;

        Iterator<Donee> doneeIterator = updateDist.getDistributedDoneeList().getIterator();
        Donee selectedDonee = doneeIterator.next(); //assume any record that can be amended only have one donee

        do {
            try {
                yesNo = distributionUI.getInputString("\nDo you want to change the donee for this distribution <" + updateDist.getDistributionId() + ">? (Y/N) > ");

                if (yesNo.equalsIgnoreCase("Y")) {
                    distributionUI.displayMessage("Available Donees : ");
                    distributionUI.displayMessage("" + donees);
                    String doneeID = distributionUI.getInputString("\nPlease enter the donee ID that you would like to change to: ");
                    foundDonee = CommonUse.findDonee(doneeID, donees);

                    if (foundDonee != null) {
                        distributionUI.displayMessage("\nSelected Donee Details: " + foundDonee);
                        String yn = distributionUI.getInputString("Are you sure you want to update the donee for Distribution "
                                + updateDist.getDistributionId() + " from " + selectedDonee.getDoneeId()
                                + " to " + foundDonee.getDoneeId() + "? (Y/N) > ");

                        if (yn.equalsIgnoreCase("Y")) {
                            updateDist.getDistributedDoneeList().remove(selectedDonee);
                            updateDist.addDonee(foundDonee);
                            MessageUI.displayGreenSuccessfulMsg("Donee has been updated successfully. ");
                            break;
                        } else if (yn.equalsIgnoreCase("N")) {
                            MessageUI.displayBlueRemindMsg("Donee update canceled.");
                            break;
                        } else {
                            throw new IllegalArgumentException("\nInvalid Yes/No option.");
                        }
                    } else {
                        MessageUI.displayRed("Donee ID not found. Please try again.");
                    }
                } else if (yesNo.equalsIgnoreCase("N")) {
                    MessageUI.displayBlueRemindMsg("\nNo changes made to the Donee.");
                    break;
                } else {
                    throw new IllegalArgumentException("\nInvalid Yes/No option.");
                }
            } catch (IllegalArgumentException e) {
                MessageUI.displayInvalidOptionMessage();
            }

        } while (true);
    }

    private void cancelCurrentRecord(SortedListSetInterface<Distribution> distributions, Distribution updateDist) {
        boolean isConfirm = false;

        while (!isConfirm) {
            String confirm = distributionUI.getInputString("Are you sure you want to cancel this distribution record with ID < " + updateDist.getDistributionId() + ">? ('Y' yes / 'N' no / 'Q' quit ) > ").toLowerCase();
            if (confirm.equalsIgnoreCase("y")) {
                distributions.remove(updateDist); // Assuming 'distributions' is accessible here
                MessageUI.displayGreenSuccessfulMsg("\nDistribution record with ID < " + updateDist.getDistributionId() + " > has been cancelled successfully.");
                isConfirm = true; // Confirm and exit loop
            } else if (confirm.equalsIgnoreCase("n")) {
                MessageUI.displayBlueRemindMsg("\nCancellation aborted. Returning to the previous menu.");
                isConfirm = true; // Exit loop without canceling
            } else if (confirm.equalsIgnoreCase("Q")) {
                break;
            } else {
                MessageUI.displayRed("\nInvalid input. Please enter again. ");
            }
        }
    }

    //**** Update purpose
    
    //**** Search purpose
    public void SearchDonationDistribution(SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Item> donatedItemList) {
        String input = distributionUI.getInputString("Please enter the keyword to search > ");
        String lowerInput = input.toLowerCase();
        boolean found = false;
        int foundItem = 0;
        boolean headerPrinted = false;
        boolean isMatching;

        // Lists to store matching distributions
        SortedListSetInterface<Distribution> sameLocationDistributions = new SortedDoublyLinkedListSet<>();
        SortedListSetInterface<Distribution> sameDoneeDistributions = new SortedDoublyLinkedListSet<>();

        Iterator<Distribution> distributionIterator = distributions.getIterator();
        distributionUI.displayMessage("\nResult(s) with < " + input + " >  :");

        while (distributionIterator.hasNext()) {
            Distribution currentDistribution = distributionIterator.next();
            isMatching = false; //reset for each distribution record

            // 1. Check if distribution ID, date, or status contains the keyword
            if (currentDistribution.getDistributionId().contains(input.toUpperCase())
                    || currentDistribution.getDistributionDate().toString().contains(lowerInput)
                    || currentDistribution.getStatus().contains(input.toUpperCase())) {
                isMatching = true;
            } // 2. If no match so far, check Donee details and location
            else {
                Iterator<Donee> doneeIterator = currentDistribution.getDistributedDoneeList().getIterator();
                while (doneeIterator.hasNext()) {
                    Donee selectedDonee = doneeIterator.next();
                    // Check Donee ID
                    if (selectedDonee.getDoneeId().toLowerCase().contains(lowerInput)) {
                        isMatching = true;
                        if (currentDistribution.getStatus().equals("PENDING")) {
                            sameDoneeDistributions.add(currentDistribution);
                        }
                    }
                    // Check Donee Location
                    if (selectedDonee.getLocation().equalsIgnoreCase(lowerInput)) {
                        isMatching = true;
                        if (currentDistribution.getStatus().equals("PENDING")) {
                            sameLocationDistributions.add(currentDistribution);
                        }
                    }
                }
            }

            // 3. If no Donee or location match, check items within the distribution
            if (!isMatching) {
                Iterator<SelectedItem> selectedItemIterator = currentDistribution.getDistributedItemList().getIterator();
                while (selectedItemIterator.hasNext()) {
                    SelectedItem selectedItem = selectedItemIterator.next();
                    Item correspondingItem = checkItemExist(donatedItemList, selectedItem.getItemId());
                    if (correspondingItem != null
                            && (correspondingItem.getItemId().toLowerCase().contains(lowerInput)
                            || correspondingItem.getDesc().toLowerCase().contains(lowerInput)
                            || correspondingItem.getType().toLowerCase().contains(lowerInput))) {
                        isMatching = true;
                    }
                }
            }

            // If a match is found, print the distribution
            if (isMatching) {
                if (!headerPrinted) {
                    distributionUI.printDistributionTitleHeader(); // Print header only once
                    headerPrinted = true;
                }
                distributionUI.printDistributionRecord(currentDistribution);
                found = true;
                foundItem++;
            }
        }

        if (!found) {
            MessageUI.displayRed("\nNo matching distributions or items found for the keyword: " + input);
        } else {
            distributionUI.displayMessage("\n");
            distributionUI.displayMessage(foundItem + " item(s) found.");

            if (sameLocationDistributions.getNumberOfEntries() > 1) {
                String mergeResponse = distributionUI.getInputString("\nDo you want to merge distributions with the same location? (Y/N) > ").toLowerCase();
                if (mergeResponse.equalsIgnoreCase("Y")) {
                    mergeLocationDistributions(sameLocationDistributions, distributions);
                }
            }

            if (sameDoneeDistributions.getNumberOfEntries() > 1) {
                String mergeDoneeResponse = distributionUI.getInputString("\nDo you want to merge distributions with the same donee? (Y/N) > ").toLowerCase();
                if (mergeDoneeResponse.equalsIgnoreCase("Y")) {
                    mergeDistributions(sameDoneeDistributions, distributions);
                }
            }
        }
    }

    private void mergeDistributions(SortedListSetInterface<Distribution> sameDoneeDistribution, SortedListSetInterface<Distribution> distributions) {

        // Get the first distribution as the base for merging
        Distribution baseDistribution = sameDoneeDistribution.getFirstEntry();

        // Iterator for the remaining distributions
        Iterator<Distribution> iterator = sameDoneeDistribution.getIterator();
        iterator.next(); // Skip the first entry since it's the base

        while (iterator.hasNext()) {
            Distribution currentDistribution = iterator.next();
            baseDistribution.getDistributedItemList().merge(currentDistribution.getDistributedItemList());
            distributions.remove(currentDistribution);
        }

        baseDistribution.setStatus("MERGED");
        baseDistribution.setDistributionDate(new Date(localDay, localMonth, localYear));

        distributionUI.displayMessage("\nMerged Distribution:");
        distributionUI.printDistributionTitleHeader();
        distributionUI.printDistributionRecord(baseDistribution);
    }

    private void mergeLocationDistributions(SortedListSetInterface<Distribution> sameLocationDistribution, SortedListSetInterface<Distribution> distributions) {

        // Get the first distribution as the base for merging
        Distribution baseDistribution = sameLocationDistribution.getFirstEntry();

        // Iterator for the remaining distributions
        Iterator<Distribution> iterator = sameLocationDistribution.getIterator();
        iterator.next(); // Skip the first entry since it's the base

        while (iterator.hasNext()) {
            Distribution currentDistribution = iterator.next();
            baseDistribution.getDistributedItemList().merge(currentDistribution.getDistributedItemList());
            baseDistribution.getDistributedDoneeList().merge(currentDistribution.getDistributedDoneeList());
            distributions.remove(currentDistribution);
        }

        baseDistribution.setStatus("Merged");
        baseDistribution.setDistributionDate(new Date(localDay, localMonth, localYear));

        distributionUI.displayMessage("\nMerged Distribution:");
        distributionUI.printDistributionTitleHeader();
        distributionUI.printDistributionRecord(baseDistribution);
    }

    //**** Search purpose
    
    //**** Remove purpose
    public void RemoveDonationDistribution(SortedListSetInterface<Distribution> distributions) {
        distributionUI.displayMessage("Remove Distribution Donation : ");

        // Check if there are distributions available
        if (distributions.getNumberOfEntries() == 0) {
            MessageUI.displayBlueRemindMsg("No distributions available to remove.");
            return;
        }

        String isConfirm;
        boolean removeSuccessful = false;

        do {
            distributionUI.listAllDistributions(distributions);
            String inputDistID = distributionUI.getInputString("\nPlease enter the distribution ID that you would like to remove ('Q' quit ) > ");
            Distribution distributionToRemove = checkDistributionExist(distributions, inputDistID);

            if (inputDistID.equalsIgnoreCase("Q")) {
                return;
            }
            if (distributionToRemove != null) {
                isConfirm = distributionUI.getInputString("\nAre you confirm to remove this distribution record < " + inputDistID.toUpperCase() + " > ? (Y/N)");
                if (isConfirm.equalsIgnoreCase("Y")) {
                    distributions.remove(distributionToRemove);
                    MessageUI.displayGreenSuccessfulMsg("Distribution with ID " + inputDistID + " has been removed successfully.\n");
                    removeSuccessful = true;
                } else {
                    MessageUI.displayBlueRemindMsg("Removal cancel.");
                    break;
                }
            } else {
                MessageUI.displayRed("Distribution with ID " + inputDistID + " not found. Please try again.\n");
            }

        } while (!removeSuccessful); // Repeat until a valid ID is entered and removal is successful
    }
    //**** Remove purpose

    public void TrackDistributedItems(SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Item> donatedItemList) {

        boolean itemFound = false;
        while (!itemFound) {

            String inputItem = distributionUI.getInputString("Please enter the distributed itemID that you would like to track ('Q' Quit) > ");
            if (inputItem.equalsIgnoreCase("Q")) {
                return;
            }
            Iterator<Item> itemIterator = donatedItemList.getIterator();

            while (itemIterator.hasNext()) {
                Item currentItem = itemIterator.next();

                if (currentItem.getItemId().equalsIgnoreCase(inputItem)) {
                    itemFound = true;
                    distributionUI.displayMessage(String.format("\nItem ID: %s\nDescription: %s\nCategory: %s\n",
                            currentItem.getItemId(), currentItem.getDesc(), currentItem.getType()));

                    Iterator<Distribution> distributionIterator = distributions.getIterator();
                    boolean distributionFound = false;
                    int distFound = 0;

                    while (distributionIterator.hasNext()) {
                        Distribution currentDistribution = distributionIterator.next();

                        // Check if the current distribution contains the selected item
                        Iterator<SelectedItem> selectedItemIterator = currentDistribution.getDistributedItemList().getIterator();
                        while (selectedItemIterator.hasNext()) {
                            SelectedItem selectedItem = selectedItemIterator.next();
                            if (selectedItem.getItemId().equalsIgnoreCase(currentItem.getItemId())) {
                                distributionFound = true;
                                distFound++;
                                distributionUI.printDistributionTitleHeader();
                                distributionUI.displayMessage(currentDistribution + "");
                                MessageUI.displayMagentaPreviewMsg("Assigned On: ");
                                MessageUI.displayMagentaPreviewMsg(currentDistribution.getDistributionDate() + "\n");

                                if (currentDistribution.getStatus().equalsIgnoreCase("Distributed")) {
                                    MessageUI.displayMagentaPreviewMsg("Shipped On: ");
                                    MessageUI.displayMagentaPreviewMsg(currentDistribution.getDistributionDate().addDays(1) + "\n");
                                    MessageUI.displayMagentaPreviewMsg("Distributed On: ");
                                    MessageUI.displayMagentaPreviewMsg(currentDistribution.getDistributionDate().addDays(2) + "\n");
                                } else if (currentDistribution.getStatus().equalsIgnoreCase("Shipped")) {
                                    MessageUI.displayMagentaPreviewMsg("Shipped On: ");
                                    MessageUI.displayMagentaPreviewMsg(currentDistribution.getDistributionDate().addDays(1) + "\n");
                                }

                            }
                        }
                    }
                    if (distFound >= 1) {
                        MessageUI.displayBlueRemindMsg("\n" + currentItem.getDesc() + " is found in "
                                + distFound + " record(s). ");
                    }
                    if (!distributionFound) {
                        MessageUI.displayBlueRemindMsg("No distribution records found for this item.\n");
                    }
                    break;
                }
            }
            if (!itemFound) {
                MessageUI.displayRed("The item ID you entered does not exist in the donated items list. Please try again.\n");
            }
        }
    }

    public void GenerateSummaryReport(SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Item> donatedItemList) {
        // result of local year will be shown defaultly
        displayReportForDateRange(distributions, getStartOfYear(localYear), getEndOfYear(localYear), donatedItemList);

        // Prompt user if they want to filter by a custom date range
        if (distributionUI.promptForDateRangeFilter()) {
            Date startDate = getValidDate("Enter start date (DD-MM-YYYY): ");
            Date endDate = getValidDate("Enter end date (DD-MM-YYYY): ");
            displayReportForDateRange(distributions, startDate, endDate, donatedItemList);
        }
    }

    private void displayReportForDateRange(SortedListSetInterface<Distribution> distributions, Date startDate, Date endDate, SortedListSetInterface<Item> donatedItemList) {
        int distFound = 0;
        SortedListSetInterface<Distribution> foundDistribution = new SortedDoublyLinkedListSet<>();
        ClearScreen.clearJavaConsoleScreen();
        CommonUse.getLogo();

        CommonUse.printSummaryReportHeader("DISTRIBUTION ANALYSIS REPORT", String.format("Distributions Summary Report from " + startDate + " to " + endDate));
        distributionUI.displayMessage("");
        distributionUI.printDistributionTitleHeader();

        Iterator<Distribution> distIterator = distributions.getIterator();
        while (distIterator.hasNext()) {
            Distribution currentDist = distIterator.next();
            Date distDate = currentDist.getDistributionDate();
            if (!distDate.beforeDate(startDate) && !distDate.afterDate(endDate)) {
                distFound++;
                distributionUI.displayMessage("" + currentDist);
                foundDistribution.add(currentDist);
            }
        }

        if (distFound == 0) {
            distributionUI.displayMessage("");
            MessageUI.displayBlueRemindMsg("No distribution found from " + startDate + " to " + endDate + "\n");
            return;
        }

        distributionUI.displayMessage("");
        MessageUI.displayMagentaPreviewMsg("Total number of distributions: " + distributions.getNumberOfEntries() + "\n");
        double rate = (double) distFound / distributions.getNumberOfEntries() * 100;
        MessageUI.displayMagentaPreviewMsg("Distributions found from " + startDate + " to " + endDate + " : " + distFound + "\n");

        String rateLevel;
        if (rate < 20) {
            rateLevel = "Low Distribution Activity"; // Low: Less than 20%
            MessageUI.displayMagentaPreviewMsg(String.format("%.2f%%, ", rate) + rateLevel + " - During this period, the charity distribution activity was minimal. This may indicate fewer people were in need.\n\n");
        } else if (rate >= 20 && rate <= 50) {
            rateLevel = "Moderate Distribution Activity"; // Moderate: Between 20% and 50%
            MessageUI.displayMagentaPreviewMsg(String.format("%.2f%%, ", rate) + rateLevel + " - There was a moderate level of charity distribution during this period. A good number of people were supported.\n\n");
        } else {
            rateLevel = "High Distribution Activity"; // High: Greater than 50%
            MessageUI.displayMagentaPreviewMsg(String.format("%.2f%%, ", rate) + rateLevel + " - The charity was highly active during this period, providing substantial support to those in need. \n\n");
        }
        getItemTypeCount(foundDistribution, donatedItemList);
    }

    private void getItemTypeCount(SortedListSetInterface<Distribution> distRecords, SortedListSetInterface<Item> donatedItemList) {
        // Define an array of item types
        String[] itemTypes = {
            "Monetary",
            "Clothing and Apparel",
            "Food and Beverage",
            "Household Items",
            "Educational Materials",
            "Electronic",
            "Medical"
        };

        distributionUI.printCategoryCountTableHeader();

        for (String type : itemTypes) {
            int itemCount = CommonUse.countType(type, distRecords, donatedItemList);

            if (itemCount > 0) {  // Only print the category if the count is greater than 0
                SortedListSetInterface<SelectedItem> uniqueSelected = getItemUniquely(distRecords, donatedItemList, type);
                StringBuilder items = new StringBuilder();

                Iterator<SelectedItem> iterator = uniqueSelected.getIterator();
                while (iterator.hasNext()) {
                    SelectedItem currentItem = iterator.next();
                    items.append(currentItem.getItemId());
                    if (iterator.hasNext()) {
                        items.append(", ");
                    }
                }
                distributionUI.printCategoryCountTableContent(type, itemCount, items);
            }
        }
        distributionUI.printCategoryCountTableFooter();
        
        distributionUI.displayMessage("");
        
        CommonUse.printSummaryReportFooter();
        distributionUI.displayMessage("");

    }

    private SortedListSetInterface<SelectedItem> getItemUniquely(SortedListSetInterface<Distribution> distRecords,
            SortedListSetInterface<Item> donatedItemList,
            String type) {
        SortedListSetInterface<SelectedItem> uniqueSelected = new SortedDoublyLinkedListSet<>();
        Iterator<Distribution> distIterator = distRecords.getIterator();
        while (distIterator.hasNext()) {
            Distribution currentDist = distIterator.next();
            Iterator<SelectedItem> slIterator = currentDist.getDistributedItemList().getIterator();
            while (slIterator.hasNext()) {
                SelectedItem currentSL = slIterator.next();
                String currentItemId = currentSL.getItemId();
                // Check if the selected item matches the required type
                Iterator<Item> itemIterator = donatedItemList.getIterator();
                while (itemIterator.hasNext()) {
                    Item currentItem = itemIterator.next();
                    if (currentItem.getItemId().equalsIgnoreCase(currentItemId)
                            && currentItem.getType().equalsIgnoreCase(type)) {
                        Iterator<SelectedItem> uniqueIterator = uniqueSelected.getIterator();
                        boolean alreadyExists = false;
                        while (uniqueIterator.hasNext()) {
                            SelectedItem uniqueItem = uniqueIterator.next();
                            if (uniqueItem.getItemId().equalsIgnoreCase(currentItemId)) {
                                alreadyExists = true;
                                break;
                            }
                        }
                        // If the item ID doesn't exist, add the currentSL to the uniqueSelected list
                        if (!alreadyExists) {
                            uniqueSelected.add(currentSL);
                        }
                    }
                }
            }
        }

        return uniqueSelected;
    }

    private Date getStartOfYear(int year) {
        return new Date(1, 1, year); 
    }

    private Date getEndOfYear(int year) {
        return new Date(31, 12, year); 
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
            date = donationUI.getInputString(desc);
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

}
