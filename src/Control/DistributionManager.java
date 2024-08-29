/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author szewen
 */
import Boundary.*;
import Utility.*;
import ADT.SortedDoublyLinkedListSet;
import ADT.SortedListSetInterface;
import Entity.*;
import java.util.Iterator;
import DAO.EntityInitializer;

public class DistributionManager {

    private DistributionUI distributionUI = new DistributionUI();
    private DonationUI donationUI = new DonationUI();

    private DoneeMaintenance doneeMaintenance = new DoneeMaintenance();
    private int localDay = distributionUI.getLocalDate().getDayOfMonth();
    private int localMonth = distributionUI.getLocalDate().getMonthValue();
    private int localYear = distributionUI.getLocalDate().getYear();

    public void distributionManager(EntityInitializer entityInitialize) {

        SortedListSetInterface<Distribution> distributions = entityInitialize.getDistributions();
        SortedListSetInterface<Item> donatedItemList = entityInitialize.getItems();
        SortedListSetInterface<Donee> donees = entityInitialize.getDonees();

        updateDistributionStatus(distributions);

        int opt = 0;
        do {
            try {
                opt = Integer.parseInt(distributionUI.getDistributionMenu());

                switch (opt) {
                    case 1:
                        //ClearScreen.clearJavaConsoleScreen();
                        ListAllDistributions(distributions);
                        break;
                    case 2:
                        //ClearScreen.clearJavaConsoleScreen();
                        AddNewDistribution(donatedItemList, distributions, donees);
                        break;
                    case 3:
                        // ClearScreen.clearJavaConsoleScreen();
                        UpdateDonationDistribution(donatedItemList, distributions, donees);
                        break;
                    case 4:
                        //ClearScreen.clearJavaConsoleScreen();
                        SearchDonationDistribution(distributions, donatedItemList);
                        break;
                    case 5:
                        //ClearScreen.clearJavaConsoleScreen();
                        RemoveDonationDistribution(distributions);
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

    private void updateDistributionStatus(SortedListSetInterface<Distribution> distributions) {
        Date currentDate = new Date(localDay, localMonth, localYear);
        Iterator<Distribution> iterator = distributions.getIterator();
        while (iterator.hasNext()) {
            Distribution currentRecord = iterator.next();
            Date distributedDate = currentRecord.getDistributionDate();
            int daysBetween = currentDate.daysBetween(distributedDate);
            if (daysBetween >= 3) {
                currentRecord.setStatus("Distributed"); // update status 
            }
        }

    }

    public void ListAllDistributions(SortedListSetInterface<Distribution> distributions) {
        distributionUI.listAllDistributions(distributions);
    }

//**** Adding purpose  //done ask whether add other items into same distribution
    //done ask add another distribution?   distribute according request and remove request
    public void AddNewDistribution(SortedListSetInterface<Item> donatedItemList, SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Donee> donees) {
        String input;
        boolean isContinue = false;
        boolean foundType;
        
        double minttlAmt = 0.0;
        int minQty = 0;

        do {
            // Generate new distribution info
            Date distributedDate = new Date(localDay, localMonth, localYear);
            int lastDistributionId = Integer.parseInt(distributions.getLastEntries().getDistributionId().substring(4)) + 1;
            String newDistId = "DIST" + String.format("%03d", lastDistributionId);

            Donee selectedDonee = null;
            do {
                try {
                    distributionUI.displayMessage("Available Donees: \n" + donees); // Display the list of donees
                    input = distributionUI.getInputString("Please enter the Donee ID to distribute to ('Q' quit) > ");

                    if (input.equalsIgnoreCase("Q")) {  // Quit the loop
                        return;
                    }

                    selectedDonee = CommonUse.findDonee(input, donees); // Check if the donee exists            
                    if (selectedDonee == null) {
                        MessageUI.displayInvalidDonee();
                    } else {
                        if (selectedDonee.getRequests().getNumberOfEntries() >= 1) {
                            distributionUI.displayMessage("Donee Requests for : ");
                            distributionUI.displayMessage("" + selectedDonee.getRequests());
                        }
                        
                        if(selectedDonee.getDoneeType().equalsIgnoreCase("Organisation")){
                            minQty = 10;
                            minttlAmt =1000.00;
                        }
                        else if(selectedDonee.getDoneeType().equalsIgnoreCase("Family")){
                            minQty = 3;
                            minttlAmt = 100.00;
                        }
                        else{
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
                    distributionUI.PrintDonatedItemList(donatedItemList);
                    input = distributionUI.getInputString("Please enter the Item ID that you would like to distribute ('Q' quit; 'F' filter By Category ) > ");

                    if (input.equalsIgnoreCase("Q")) {  // Quit the loop
                        // Prompt user to decide whether to keep or discard the current distribution
                        if (isContinue == true) {
                            String keepOrDiscard = distributionUI.getInputString("You have not added any items. Do you want to keep the previous actions? ('Y' to keep; 'N' to discard) > ");
                            if (keepOrDiscard.equalsIgnoreCase("Y")) {
                                distributions.add(newDistribution); // Add the distribution to the list even if empty
                                MessageUI.displayRecordAddedMessage();
                            } else {
                                MessageUI.displayNoRecordAddedMessage();
                            }
                        }
                        return; // Exit method
                    }

                    if (input.equalsIgnoreCase("F")) { // Filter items
                        do {
                            ClearScreen.clearJavaConsoleScreen();
                            CommonUse.printItemHeader();
                            distributionUI.displayMessage("\n");
                            distributionUI.PrintDonatedItemList(donatedItemList); // Display the list of donated items
                            String inputType = distributionUI.getInputString("Please enter the type you would like to show > ");
                            foundType = filterItemByType(donatedItemList, inputType);
                        } while (!foundType);

                        input = distributionUI.getInputString("Please enter the Item ID that you would like to distribute ('Q' quit) > ");
                        if (input.equalsIgnoreCase("Q")) { // Quit the loop
                            // Prompt user to decide whether to keep or discard the current distribution
                            String keepOrDiscard = distributionUI.getInputString("You have not added any items. Do you want to keep the previous actions? ('Y' to keep; 'N' to discard) > ");
                            if (keepOrDiscard.equalsIgnoreCase("Y")) {
                                distributions.add(newDistribution); // Add the distribution to the list even if empty
                                MessageUI.displayRecordAddedMessage();
                            } else {
                                MessageUI.displayNoRecordAddedMessage();
                            }
                            return; // Exit method
                        }
                    }

                    Item inputItem = checkItemExist(donatedItemList, input); // Check if the item exists              
                    if (inputItem != null) { // If item is found
                        if (inputItem.getType().equalsIgnoreCase("Monetary")) {
                            if (StockUI.checkMonetary("Monetary", donatedItemList)) {
                                isContinue = handleMonetaryItem(inputItem, newDistribution, distributions);
                                isDistributionMatchingRequest(newDistribution, selectedDonee, donatedItemList);

                            } else {
                                MessageUI.displayInsufficientMessage();
                                isContinue = false;
                            }
                        } else {
                            if (StockUI.checkInventory(inputItem.getType(), donatedItemList)) {
                                isContinue = handleNonMonetaryItem(inputItem, newDistribution, distributions);
                                isDistributionMatchingRequest(newDistribution, selectedDonee, donatedItemList);

                            } else {
                                MessageUI.displayInsufficientMessage();
                                isContinue = true; // Allow user to enter a new item
                            }
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

    private boolean handleMonetaryItem(Item inputItem, Distribution newDistribution, SortedListSetInterface<Distribution> distributions) {
        boolean isValidAmt = false;
        boolean isContinue = false;
        double inputAmt;

        while (!isValidAmt) {
            try {
                // Get and validate the desired amount
                inputAmt = distributionUI.getInputDouble("Please enter the desired amount > ");
                if (inputItem.getTotalAmount() >= inputAmt) {
                    SelectedItem selectedItem = new SelectedItem(inputItem.getItemId(), inputAmt);

                    // Display details for confirmation
                    distributionUI.displayMessage("You are about to add the following item to the distribution:");
                    distributionUI.displayMessage("Item ID : " + selectedItem.getItemId());
                    distributionUI.displayMessage("Description : " + inputItem.getDesc());
                    distributionUI.displayMessage("Amount : " + inputAmt);
                    String confirmAdd = distributionUI.getInputString("Do you want to confirm adding this item? (y/n) > ");

                    if (confirmAdd.equalsIgnoreCase("Y")) {
                        newDistribution.addSelectedItem(selectedItem); // Add selected item into the new distribution
                        inputItem.setTotalAmount(inputItem.getTotalAmount() - inputAmt);
                        isValidAmt = true; // Exit the amount input loop

                        String keepAdding = distributionUI.getInputString("Do you want to add another item into this distribution? (y/n) > ");
                        if (keepAdding.equalsIgnoreCase("N")) {
                            distributions.add(newDistribution); // Add the distribution to the list
                            MessageUI.displayRecordAddedMessage();
                            isContinue = false; // Stop adding items
                        } else {
                            isContinue = true; // Continue adding items
                        }
                    } else {
                        distributionUI.displayMessage("Item addition canceled. Please enter a new Item ID.");
                        return true; // Return true to continue the outer loop to allow re-entry of item ID
                    }
                } else {
                    MessageUI.displayExceedMessage();
                }
            } catch (NumberFormatException e) {
                MessageUI.displayInvalidAmountMessage();
            }
        }

        return isContinue;
    }

    private boolean handleNonMonetaryItem(Item inputItem, Distribution newDistribution, SortedListSetInterface<Distribution> distributions) {
        boolean isValidQty = false;
        boolean isContinue = false;
        int inputQty;

        while (!isValidQty) { // Loop until a valid quantity is entered
            try {
                inputQty = distributionUI.getInputQty("Please enter the desired quantity > ");
                if (inputItem.getQuantity() >= inputQty) {
                    SelectedItem selectedItem = new SelectedItem(inputItem.getItemId(), inputQty);

                    // Display details for confirmation
                    distributionUI.displayMessage("You are about to add the following item to the distribution:");
                    distributionUI.displayMessage("Item ID: " + selectedItem.getItemId());
                    distributionUI.displayMessage("Description : " + inputItem.getDesc());
                    distributionUI.displayMessage("Quantity : " + inputQty);
                    String confirmAdd = distributionUI.getInputString("Do you want to confirm adding this item? (y/n) > ");

                    if (confirmAdd.equalsIgnoreCase("Y")) {
                        newDistribution.addSelectedItem(selectedItem); // Add selected item into the new distribution
                        inputItem.setQuantity(inputItem.getQuantity() - inputQty);
                        isValidQty = true; // Exit the quantity input loop

                        String keepAdding = distributionUI.getInputString("Do you want to add another item into this distribution? (y/n) > ");
                        if (keepAdding.equalsIgnoreCase("N")) {
                            distributions.add(newDistribution); // Add the distribution to the list
                            MessageUI.displayRecordAddedMessage();
                            isContinue = false; // Stop adding items
                        } else {
                            isContinue = true; // Continue adding items
                        }
                    } else {
                        distributionUI.displayMessage("Item addition canceled. Please enter a new Item ID.");
                        return true; // Return true to continue the outer loop to allow re-entry of item ID
                    }
                } else {
                    MessageUI.displayExceedMessage();
                }
            } catch (NumberFormatException e) {
                MessageUI.displayInvalidIntegerMessage();
            }
        }

        return isContinue;
    }

//**** Adding purpose
    private boolean filterItemByType(SortedListSetInterface<Item> donatedItemList, String type) {
        boolean foundtype = false;
        Iterator<Item> iterator = donatedItemList.getIterator();
        distributionUI.displayMessage("Item List filter by " + type + " : ");
        while (iterator.hasNext()) {
            Item currentItem = iterator.next();
            if (currentItem.getType().equalsIgnoreCase(type)) {
                distributionUI.PrintItemList(currentItem);
                foundtype = true;
            }
        }
        if (!foundtype) {
            distributionUI.displayMessage("No item match < " + type + " > type.\n\n");
        }
        return foundtype;
    }

    //update add/dltitem qty  done  //add another item consider as merge
    //update donee done
    //status either pending or distributed done
    //record cant be changed after 2 days from distributed date done
    public void UpdateDonationDistribution(SortedListSetInterface<Item> donatedItemList, SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Donee> donees) {
        // List all distributions and get the distribution ID to update
        ListAllDistributions(distributions);
        String updateDistID = distributionUI.getInputString("\nPlease enter the distribution ID that you would like to ('Q' quit ) > ");
        if (updateDistID.equalsIgnoreCase("Q")) {
            return;
        }
        Distribution updateDist = checkDistributionExist(distributions, updateDistID);

        if (updateDist != null) {  // Check if the record exists
            boolean continueLoop = true;

            if (updateDist.getStatus().equalsIgnoreCase("Distributed")) {

                distributionUI.displayMessage("\nThe distribution record with ID < " + updateDistID + " > is already distributed and cannot be updated.");
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
        distributionUI.displayMessage("Update item details: ");

        while (continueUpdating) {
            distributionUI.printDistriburedItemList(updateDist.getDistributedItemList());

            if (updateDist.getDistributedItemList().getNumberOfEntries() >= 1) {
                SelectedItem selectedItemToUpdate;
                String input;

                // If there's more than one item, ask for the Item ID
                if (updateDist.getDistributedItemList().getNumberOfEntries() > 1) {
                    input = distributionUI.getInputString("Enter ItemId that you would like to update ( 'Q' quit ) > ");
                    if (input.equalsIgnoreCase("q")) {
                        return;
                    }
                    selectedItemToUpdate = findSelectedItem(updateDist, input);
                } else {
                    // If only one item, select it directly
                    selectedItemToUpdate = updateDist.getDistributedItemList().getLastEntries();    //lastentry = firstentry when there is only one entry
                }

                if (selectedItemToUpdate != null) {
                    Item itemToUpdate = checkItemExist(donatedItemList, selectedItemToUpdate.getItemId());

                    if (itemToUpdate != null) {
                        if (itemToUpdate.getType().equalsIgnoreCase("Monetary")) {
                            updateMonetaryItem(distributions, selectedItemToUpdate, itemToUpdate, updateDist);
                        } else {
                            updateNonMonetaryItem(distributions, selectedItemToUpdate, itemToUpdate, updateDist);
                        }
                    } else {
                        MessageUI.displayRed("Corresponding donated item not found. \n"
                                + "It may be removed hence this item record is not allowed to amend or update.");
                    }
                } else {
                    MessageUI.displayInvalidItemMessage();
                }

                if (updateDist.getDistributedItemList().getNumberOfEntries() >= 1) {
                    try {
                        String continueInput = distributionUI.getInputString("Do you want to update another item? (Y/N) > ");
                        if (!continueInput.equalsIgnoreCase("Y")) {
                            continueUpdating = false;
                            distributionUI.displayMessage("\n\nUpdated distribution record : " + updateDist);
                        }
                    } catch (Exception ex) {
                        MessageUI.displayInvalidOptionMessage();
                    }
                }

                return;

            }
        }
    }

    private void updateMonetaryItem(SortedListSetInterface<Distribution> distributions, SelectedItem selectedItemToUpdate, Item itemToUpdate, Distribution updateDist) {
        double oriAmt = selectedItemToUpdate.getAmount();
        distributionUI.displayMessage("Original amount: " + oriAmt);

        double instockAmt = itemToUpdate.getTotalAmount();
        distributionUI.displayMessage("In stock amount: " + instockAmt);

        double updatedAmt = 0.0;

        do {
            try {
                distributionUI.displayMessage("Please enter your desired amount to update ");
                MessageUI.displayRed("*** Enter 0 = cancel item from this distribution record. ");
                updatedAmt = Double.parseDouble(distributionUI.getInputString("> "));

                if (updatedAmt == 0) {
                    String confirmRemoval = distributionUI.getInputString("You are about to remove the item from the distribution record. Confirm? (Y/N) > ");
                    if (confirmRemoval.equalsIgnoreCase("Y")) {
                        updateDist.getDistributedItemList().remove(selectedItemToUpdate);
                        itemToUpdate.setTotalAmount(instockAmt + oriAmt);
                        distributionUI.displayMessage("Item has been removed from the distribution record.");
                        if (updateDist.getDistributedItemList().getNumberOfEntries() == 0) {
                            distributionUI.displayMessage("Since there are no item in this distribution record, < "
                                    + updateDist.getDistributionId() + " > will be removed.");
                            distributions.remove(updateDist);
                            distributionUI.displayMessage("This record have been removed from the distribution list.");
                            break;
                        }
                        return;
                    } else {
                        MessageUI.displayRed("Item removal canceled.");
                    }
                    return;
                }

                if (updatedAmt > instockAmt + oriAmt) {
                    MessageUI.displayRed("The amount entered exceeds the in-stock amount. Please try again.\n\n");
                } else {
                    String confirmAmtUpdate = distributionUI.getInputString("You are about to update the amount from " + oriAmt + " to " + updatedAmt + ". Confirm? (Y/N) > ");
                    if (confirmAmtUpdate.equalsIgnoreCase("Y")) {
                        selectedItemToUpdate.setAmount(updatedAmt);
                        itemToUpdate.setTotalAmount(instockAmt - (updatedAmt - oriAmt));
                        distributionUI.displayMessage("The amount has been updated from " + oriAmt + " to " + updatedAmt);
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
    }

    private void updateNonMonetaryItem(SortedListSetInterface<Distribution> distributions, SelectedItem selectedItemToUpdate, Item itemToUpdate, Distribution updateDist) {
        int oriQty = selectedItemToUpdate.getSelectedQuantity();
        distributionUI.displayMessage("Original quantity: " + oriQty);

        int instockQty = itemToUpdate.getQuantity();
        distributionUI.displayMessage("In stock quantity: " + instockQty);

        int updatedQty = 0;

        do {
            try {
                distributionUI.displayMessage("Please enter your desired quantity to update ");
                MessageUI.displayRed("*** Enter 0 = cancel item from this distribution record. ");
                updatedQty = distributionUI.getInputQty("> ");

                if (updatedQty == 0) {
                    // Ask for confirmation before removing the item
                    String confirmRemoval = distributionUI.getInputString("You are about to remove the item from the distribution record. Confirm? (Y/N) > ");
                    if (confirmRemoval.equalsIgnoreCase("Y")) {
                        updateDist.getDistributedItemList().remove(selectedItemToUpdate);
                        itemToUpdate.setQuantity(instockQty + oriQty);
                        distributionUI.displayMessage("Item has been removed from the distribution record.");

                        // Check if the distribution record is now empty
                        if (updateDist.getDistributedItemList().getNumberOfEntries() == 0) {
                            distributionUI.displayMessage("Since there are no items in this distribution record, < "
                                    + updateDist.getDistributionId() + " > will be removed.");
                            distributions.remove(updateDist);
                            distributionUI.displayMessage("This record has been removed from the distribution list.");
                            break; // Exit the loop if the distribution record is removed
                        }
                        return; // Exit the method if the item was removed
                    } else {
                        MessageUI.displayRed("Item removal canceled.");
                    }
                    return; // Exit the method if no removal occurred
                }

                if (updatedQty > instockQty + oriQty) {
                    MessageUI.displayRed("The quantity entered exceeds the in-stock quantity. Please try again.\n\n");
                } else {
                    // Ask for confirmation before applying the quantity update
                    String confirmQtyUpdate = distributionUI.getInputString("You are about to update the quantity from " + oriQty + " to " + updatedQty + ". Confirm? (Y/N) > ");
                    if (confirmQtyUpdate.equalsIgnoreCase("Y")) {
                        // Update the quantity in both the selected item and the donated item
                        selectedItemToUpdate.setSelectedQuantity(updatedQty);
                        itemToUpdate.setQuantity(instockQty - (updatedQty - oriQty));
                        distributionUI.displayMessage("The quantity has been updated from " + oriQty + " to " + updatedQty);
                    } else {
                        distributionUI.displayMessage("Quantity update canceled.");
                    }
                }
            } catch (NumberFormatException ex) {
                MessageUI.displayRed("Invalid input. Please enter a numeric value.\n\n");

            } catch (Exception ex) {
                MessageUI.displayInvalidIntegerMessage();
            }
        } while (updatedQty > instockQty + oriQty);
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
        distributionUI.displayMessage("Update Donee Details:\n");
        distributionUI.displayMessage("Distribution Details: " + updateDist);
        distributionUI.displayMessage("Current Donee Details: " + updateDist.getDistributedDoneeList());

        String yesNo;
        Donee foundDonee = null;

        do {
            try {
                yesNo = distributionUI.getInputString("\nDo you want to change the donee for this distribution <" + updateDist.getDistributionId() + ">? (Y/N) > ");

                if (yesNo.equalsIgnoreCase("Y")) {
                    String doneeID = distributionUI.getInputString("\nPlease enter the donee ID that you would like to change to: ");
                    foundDonee = CommonUse.findDonee(doneeID, donees);

                    if (foundDonee != null) {
                        distributionUI.displayMessage("\nSelected Donee Details: " + foundDonee);
                        String yn = distributionUI.getInputString("Are you sure you want to update the donee for Distribution "
                                + updateDist.getDistributionId() + " from " + updateDist.getDonee().getDoneeId()
                                + " to " + foundDonee.getDoneeId() + "? (Y/N) > ");

                        if (yn.equalsIgnoreCase("Y")) {
                            updateDist.setDonee(foundDonee);
                            distributionUI.displayMessage("\nDonee has been updated successfully.");
                        } else if (yn.equalsIgnoreCase("N")) {
                            distributionUI.displayMessage("\nDonee update canceled.");
                        } else {
                            throw new IllegalArgumentException("\nInvalid Yes/No option.");
                        }
                    } else {
                        distributionUI.displayMessage("\nDonee ID not found. Please try again.");
                    }
                } else if (yesNo.equalsIgnoreCase("N")) {
                    distributionUI.displayMessage("\nNo changes made to the Donee.");
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
                distributionUI.displayMessage("\nDistribution record with ID < " + updateDist.getDistributionId() + " > has been cancelled.");
                isConfirm = true; // Confirm and exit loop
            } else if (confirm.equalsIgnoreCase("n")) {
                distributionUI.displayMessage("\nCancellation aborted. Returning to the previous menu.");
                isConfirm = true; // Exit loop without canceling
            } else if (confirm.equalsIgnoreCase("Q")) {
                break;
            } else {
                distributionUI.displayMessage("\nInvalid input. Please enter again. ");
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
        distributionUI.displayMessage("\nResult(s) with " + input + " :");

        while (distributionIterator.hasNext()) {
            Distribution currentDistribution = distributionIterator.next();
            isMatching = false; //reset for each distribution record

            // 1. Check if distribution ID, date, or status contains the keyword
            if (currentDistribution.getDistributionId().equalsIgnoreCase(lowerInput)
                    || currentDistribution.getDistributionDate().toString().contains(lowerInput)
                    || currentDistribution.getStatus().equalsIgnoreCase(lowerInput)) {
                isMatching = true;
            } // 2. If no match so far, check Donee details and location
            else {
                Iterator<Donee> doneeIterator = currentDistribution.getDistributedDoneeList().getIterator();
                while (doneeIterator.hasNext()) {
                    Donee selectedDonee = doneeIterator.next();

                    // Check Donee ID
                    if (selectedDonee.getDoneeId().toLowerCase().contains(lowerInput)) {
                        isMatching = true;
                        if (currentDistribution.getStatus().equals("Pending")) {
                            sameDoneeDistributions.add(currentDistribution);
                        }
                    }

                    // Check Donee Location
                    if (selectedDonee.getLocation().equalsIgnoreCase(lowerInput)) {
                        isMatching = true;
                        if (currentDistribution.getStatus().equals("Pending")) {
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
            distributionUI.displayMessage("\nNo matching distributions or items found for the keyword: " + input);
        } else {
            distributionUI.displayMessage("\n");
            distributionUI.displayMessage(foundItem + " item(s) found.");

            if (sameLocationDistributions.getNumberOfEntries() > 1) {
                String mergeResponse = distributionUI.getInputString("\nDo you want to merge distributions with the same location? (yes/no) > ").toLowerCase();
                if (mergeResponse.equals("yes")) {
                    mergeLocationDistributions(sameLocationDistributions, distributions);
                }
            }

            if (sameDoneeDistributions.getNumberOfEntries() > 1) {
                String mergeDoneeResponse = distributionUI.getInputString("\nDo you want to merge distributions with the same donee? (yes/no) > ").toLowerCase();
                if (mergeDoneeResponse.equals("yes")) {
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
            baseDistribution.getDistributedDoneeList().merge(currentDistribution.getDistributedDoneeList());
            distributions.remove(currentDistribution);
        }

        baseDistribution.setStatus("Merged");
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
            distributionUI.displayMessage("No distributions available to remove.");
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
                    distributionUI.displayMessage("Distribution with ID " + inputDistID + " has been removed successfully.\n");
                    removeSuccessful = true;
                } else {
                    distributionUI.displayMessage("Removal cancel.");
                    break;
                }
            } else {
                distributionUI.displayMessage("Distribution with ID " + inputDistID + " not found. Please try again.\n");
            }

        } while (!removeSuccessful); // Repeat until a valid ID is entered and removal is successful
    }

}



    
//}
