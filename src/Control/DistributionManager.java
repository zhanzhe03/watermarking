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
            if (daysBetween >= 2) {
                currentRecord.setStatus("Distributed"); // update status 
            }
        }

    }

    public void ListAllDistributions(SortedListSetInterface<Distribution> distributions) {
        distributionUI.listAllDistributions(distributions);
    }

//**** Adding purpose  //done ask whether add other items into same distribution
    //done ask add another distribution?
    public void AddNewDistribution(SortedListSetInterface<Item> donatedItemList, SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Donee> donees) {
        String input;
        boolean isContinue = false;
        boolean foundType;

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
                        distributionUI.displayMessage("Donee not found.");
                    } else {
                        break;
                    } // Donee found, exit the loop
                } catch (Exception e) {
                    distributionUI.displayMessage("An error occurred: " + e.getMessage());
                }
            } while (selectedDonee == null); // Continue until a valid donee is selected

            // Create the new distribution with the selected donee
            Distribution newDistribution = new Distribution(newDistId, distributedDate, selectedDonee);

            do {
                try {
                    CommonUse.printItemHeader();
                    distributionUI.displayMessage("\n");
                    distributionUI.PrintDonatedItemList(donatedItemList);
                    input = distributionUI.getInputString("Please enter the Item ID that you would like to distribute ('Q' quit; 'F' filter) > ");

                    if (input.equalsIgnoreCase("Q")) {  // Quit the loop
                        break;
                    }

                    if (input.equalsIgnoreCase("F")) { // Filter items
                        do {
                            ClearScreen.clearJavaConsoleScreen();
                            CommonUse.printItemHeader();
                            distributionUI.displayMessage("\n");
                            distributionUI.PrintDonatedItemList(donatedItemList); // Display the list of donated items
                            String inputType = distributionUI.getInputString("Please enter the type you would like to show > ");
                            ClearScreen.clearJavaConsoleScreen();
                            foundType = filterItemByType(donatedItemList, inputType);
                        } while (!foundType);

                        input = distributionUI.getInputString("Please enter the Item ID that you would like to distribute ('Q' quit; 'F' filter) > ");
                        if (input.equalsIgnoreCase("Q")) { // Quit the loop
                            break;
                        }
                    }

                    Item inputItem = checkItemExist(donatedItemList, input); // Check if the item exists              
                    if (inputItem != null) { // If item is found
                        if (inputItem.getType().equalsIgnoreCase("Monetary")) {
                            if (StockUI.checkMonetary("Monetary", donatedItemList)) {
                                isContinue = handleMonetaryItem(inputItem, newDistribution, distributions);
                            } else {
                                distributionUI.displayMessage("Sumimasih, this category is currently not sufficient for distribution:(\n");
                                isContinue = false;
                            }
                        } else {
                            if (StockUI.checkInventory(inputItem.getType(), donatedItemList)) {
                                isContinue = handleNonMonetaryItem(inputItem, newDistribution, distributions);
                            } else {
                                distributionUI.displayMessage("Sumimasihhhh, this category is currently not sufficient for distribution:(\n");
                                isContinue = true; // Allow user to enter a new item
                            }
                        }
                    } else {
                        distributionUI.displayMessage("Item not found.");
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
        int inputQty;

        while (!isValidAmt) {
            try {
                inputQty = distributionUI.getInputQty("Please enter the desired amount > ");
                if (inputItem.getTotalAmount() >= inputQty) {
                    SelectedItem selectedItem = new SelectedItem(inputItem.getItemId(), inputQty);
                    newDistribution.addSelectedItem(selectedItem); // Add selected item into the new distribution
                    isValidAmt = true; // Exit the amount input loop

                    String keepAdding = distributionUI.getInputString("Do you want to add another item into this distribution? (y/n) > ");
                    if (keepAdding.equalsIgnoreCase("N")) {
                        distributions.add(newDistribution); // Add the distribution to the list
                        distributionUI.displayMessage("\nDistribution record is added.");
                        isContinue = false; // Stop adding items
                    } else {
                        isContinue = true; // Continue adding items
                    }
                } else {
                    distributionUI.displayMessage("Your input amount has exceeded.");
                }
            } catch (NumberFormatException e) {
                distributionUI.displayMessage("Invalid amount entered. Please enter a valid integer.");
            }
        }

        return isContinue;
    }

    private boolean handleNonMonetaryItem(Item inputItem, Distribution newDistribution, SortedListSetInterface<Distribution> distributions) {
        boolean isValidQty = false;
        boolean isContinue = false;
        int inputQty;

        while (!isValidQty) {     // Loop until a valid quantity is entered
            try {
                inputQty = distributionUI.getInputQty("Please enter the desired quantity > ");
                if (inputItem.getQuantity() >= inputQty) {
                    SelectedItem selectedItem = new SelectedItem(inputItem.getItemId(), inputQty);
                    newDistribution.addSelectedItem(selectedItem); // Add selected item into the new distribution
                    isValidQty = true; // Exit the quantity input loop

                    String keepAdding = distributionUI.getInputString("Do you want to add another item into this distribution? (y/n) > ");
                    if (keepAdding.equalsIgnoreCase("N")) {
                        distributions.add(newDistribution); // Add the distribution to the list
                        distributionUI.displayMessage("\nDistribution record is added.");

                        isContinue = false; // Stop adding items
                    } else {
                        isContinue = true; // Continue adding items
                    }
                } else {
                    distributionUI.displayMessage("Your input quantity has exceeded.");
                }
            } catch (NumberFormatException e) {
                distributionUI.displayMessage("Invalid quantity entered. Please enter a valid integer.");
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

    //update add/dltitem  done
    //update donee done
    //status either pending or distributed done
    //record cant be changed after 2 days from distributed date done
    public void UpdateDonationDistribution(SortedListSetInterface<Item> donatedItemList, SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Donee> donees) {
        // List all distributions and get the distribution ID to update
        ListAllDistributions(distributions);
        String updateDistID = distributionUI.getInputString("\nPlease enter the distribution ID that you would like to update > ");
        Distribution updateDist = checkDistributionExist(distributions, updateDistID);

        if (updateDist != null) {  // Check if the record exists
            boolean continueLoop = true;

            if (updateDist.getStatus().equalsIgnoreCase("Distributed")) {
                distributionUI.displayMessage("\nThe distribution record with ID < " + updateDistID + " > is already distributed and cannot be updated.");
                return;
            }

            while (continueLoop) {
                distributionUI.displayMessage("\nWhat would you like to update? ");
                distributionUI.displayMessage("1. Item details \n2. Donee details\n3. Exit\n");
                String userOpt = distributionUI.getInputString("> ").toLowerCase();

                switch (userOpt) {
                    case "1":
                        updateItemDetails(donatedItemList, updateDist);
                        continueLoop = false;  // Exit the loop
                        break;

                    case "2":
                        updateDoneeDetails(updateDist, donees);
                        break;

                    case "3":
                        distributionUI.displayMessage("Returning to the menu.");
                        continueLoop = false;  // Exit the loop
                        break;

                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
                }
            }
        } else {
            distributionUI.displayMessage("\nNo record with ID < " + updateDistID + " > exists.");
        }
    }

    private Distribution checkDistributionExist(SortedListSetInterface<Distribution> distributions, String checkDistID) {
        Iterator<Distribution> iterator = distributions.getIterator();
        while (iterator.hasNext()) {
            Distribution currentRecord = iterator.next();
            if (currentRecord.getDistributionId().equalsIgnoreCase(checkDistID)) {
                ClearScreen.clearJavaConsoleScreen();
                //   distributionUI.displayMessage(""+currentRecord);
                distributionUI.printDistributionRecord(currentRecord);
                return currentRecord;

            } else {
            }
        }
        return null;
    }

    private void updateItemDetails(SortedListSetInterface<Item> donatedItemList, Distribution updateDist) {
        boolean continueUpdating = true;
        distributionUI.displayMessage("Update item details: ");

        while (continueUpdating) {
            distributionUI.printDistriburedItemList(updateDist.getDistributedItemList());

            if (updateDist.getDistributedItemList().getNumberOfEntries() >= 1) {
                SelectedItem selectedItemToUpdate = null;
                String input;

                // If there's more than one item, ask for the Item ID
                if (updateDist.getDistributedItemList().getNumberOfEntries() > 1) {
                    input = distributionUI.getInputString("Enter ItemId that you would like to update > ");
                    selectedItemToUpdate = findSelectedItem(updateDist, input);
                } else {
                    // If only one item, select it directly
                    selectedItemToUpdate = updateDist.getDistributedItemList().getLastEntries();    //lastentry = firstentry when there is only one entry
                }

                if (selectedItemToUpdate != null) {
                    Item itemToUpdate = checkItemExist(donatedItemList, selectedItemToUpdate.getItemId());

                    if (itemToUpdate != null) {
                        if (itemToUpdate.getType().equalsIgnoreCase("Monetary")) {
                            updateMonetaryItem(selectedItemToUpdate, itemToUpdate, updateDist);
                        } else {
                            updateNonMonetaryItem(selectedItemToUpdate, itemToUpdate, updateDist);
                        }
                    } else {
                        distributionUI.displayMessage("Corresponding donated item not found. \n"
                                + "It may be removed hence this item record is not allowed to amend or update.");
                    }
                } else {
                    distributionUI.displayMessage("Item ID not found in the distribution list.");
                }
            } else {
                distributionUI.displayMessage("No items available for update.");
            }

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
    }

    private void updateMonetaryItem(SelectedItem selectedItemToUpdate, Item itemToUpdate, Distribution updateDist) {
        double oriAmt = selectedItemToUpdate.getAmount();
        distributionUI.displayMessage("Original amount: " + oriAmt);

        double instockAmt = itemToUpdate.getTotalAmount();
        distributionUI.displayMessage("In stock amount: " + instockAmt);

        double updatedAmt = 0.0;

        do {
            try {
                updatedAmt = Double.parseDouble(distributionUI.getInputString("Please enter your desired amount to update \n"
                        + "*** Enter 0 = cancel item from this distribution record \n"
                        + " > "));

                if (updatedAmt == 0) {
                    // Remove the item from the distribution list
                    updateDist.getDistributedItemList().remove(selectedItemToUpdate);
                    distributionUI.displayMessage("Item has been removed from the distribution record.");
                    return;
                }

                if (updatedAmt > instockAmt) {
                    distributionUI.displayMessage("The amount entered exceeds the in-stock amount. Please try again.");
                } else {
                    // Update the amount in both the selected item and the donated item
                    selectedItemToUpdate.setAmount(updatedAmt);
                    itemToUpdate.setTotalAmount(instockAmt - updatedAmt);
                    distributionUI.displayMessage("The amount has been updated from " + oriAmt + " to " + updatedAmt);
                }
            } catch (Exception ex) {
                MessageUI.displayInvalidAmountMessage();
            }
        } while (updatedAmt > instockAmt);
    }

    private void updateNonMonetaryItem(SelectedItem selectedItemToUpdate, Item itemToUpdate, Distribution updateDist) {
        int oriQty = selectedItemToUpdate.getSelectedQuantity();
        distributionUI.displayMessage("Original quantity: " + oriQty);

        int instockQty = itemToUpdate.getQuantity();
        distributionUI.displayMessage("In stock quantity: " + instockQty);

        int updatedQty = 0;

        do {
            try {
                updatedQty = distributionUI.getInputQty("Please enter your desired quantity to update \n"
                        + "*** Enter 0 = cancel item from this distribution record \n"
                        + " > ");

                if (updatedQty == 0) {  //remove item from distribution list
                    updateDist.getDistributedItemList().remove(selectedItemToUpdate);
                    distributionUI.displayMessage("Item has been removed from the distribution record.");
                    return;
                }
                if (updatedQty > instockQty) {
                    distributionUI.displayMessage("The quantity entered exceeds the in-stock quantity. Please try again.");
                } else {
                    // Update the quantity in both the selected item and the donated item
                    selectedItemToUpdate.setSelectedQuantity(updatedQty);
                    itemToUpdate.setQuantity(instockQty - updatedQty);
                    distributionUI.displayMessage("The quantity has been updated from " + oriQty + " to " + updatedQty);
                }
            } catch (Exception ex) {
                MessageUI.displayInvalidIntegerMessage();
            }
        } while (updatedQty > instockQty);
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
        distributionUI.displayMessage("Current Donee Details: " + updateDist.getDonee());

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

    //**** Update purpose
    //**** Search purpose
    public void SearchDonationDistribution(SortedListSetInterface<Distribution> distributions, SortedListSetInterface<Item> donatedItemList) {
        String input = distributionUI.getInputString("Please enter the keyword to search > ");
        String lowerInput = input.toLowerCase();
        boolean found = false;
        int foundItem = 0;
        boolean headerPrinted = false;

        // List to store matching distributions with the same location
        SortedListSetInterface<Distribution> sameLocationDistributions = new SortedDoublyLinkedListSet<>();

        Iterator<Distribution> distributionIterator = distributions.getIterator();
        distributionUI.displayMessage("\nResult(s) with " + input + " :");

        while (distributionIterator.hasNext()) {
            Distribution currentDistribution = distributionIterator.next();
            boolean isMatching = false;

            // Check if the distribution ID, date, donee ID, or status contains the keyword
            if (currentDistribution.getDistributionId().toLowerCase().contains(lowerInput)
                    || currentDistribution.getDistributionDate().toString().contains(lowerInput)
                    || currentDistribution.getDonee().getDoneeId().toLowerCase().contains(lowerInput)
                    || currentDistribution.getStatus().equalsIgnoreCase(lowerInput)) {
                isMatching = true;
            }

            // Check if donee location matches the keyword
            if (currentDistribution.getDonee().getLocation().equalsIgnoreCase(lowerInput)) {
                isMatching = true;
                if (currentDistribution.getStatus().equals("Pending")) {
                    sameLocationDistributions.add(currentDistribution);
                }
            }

            // Check if any item in the distribution matches the keyword
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
                    mergeDistributions(sameLocationDistributions);
                }
            }
        }
    }

    private void mergeDistributions(SortedListSetInterface<Distribution> sameLocationDistributions) {

        // Get the first distribution as the base for merging
        Distribution baseDistribution = sameLocationDistributions.getFirstEntry();

        // Iterator for the remaining distributions
        Iterator<Distribution> iterator = sameLocationDistributions.getIterator();
        iterator.next(); // Skip the first entry since it's the base

        while (iterator.hasNext()) {
            Distribution currentDistribution = iterator.next();
            baseDistribution.getDistributedItemList().merge(currentDistribution.getDistributedItemList());
        }

        // Optionally, update the status or other fields of the merged distribution
         int lastMergeId = Integer.parseInt(sameLocationDistributions.getLastEntries().getDistributionId().substring(4)) + 1;
            String newMergeId = "MERGED" + String.format("%03d", lastMergeId);
        baseDistribution.setStatus("Merged");
        baseDistribution.setDistributionId(newMergeId);
        //baseDistribution.setDonee(null);
        baseDistribution.setDistributionDate(new Date(localDay, localMonth, localYear));


        // Display the merged distribution
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

        boolean removeSuccessful = false;

        do {
            distributionUI.listAllDistributions(distributions);
            String inputDistID = distributionUI.getInputString("\nPlease enter the distribution ID that you would like to remove > ");
            Distribution distributionToRemove = checkDistributionExist(distributions, inputDistID);

            if (distributionToRemove != null) {
                distributions.remove(distributionToRemove);
                distributionUI.displayMessage("Distribution with ID " + inputDistID + " has been removed successfully.\n");
                removeSuccessful = true;
            } else {
                distributionUI.displayMessage("Distribution with ID " + inputDistID + " not found. Please try again.\n");
            }

        } while (!removeSuccessful); // Repeat until a valid ID is entered and removal is successful
    }

}



    
//}
