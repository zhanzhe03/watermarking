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
import ADT.*;
import Entity.*;
import java.util.Iterator;
import DAO.EntityInitializer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DistributionManager {

    private DistributionUI distributionUI = new DistributionUI();
    private int localDay = distributionUI.getLocalDate().getDayOfMonth();
    private int localMonth = distributionUI.getLocalDate().getMonthValue();
    private int localYear = distributionUI.getLocalDate().getYear();

    public void distributionManager(EntityInitializer entityInitialize) {

        SortedListSetInterface<Distribution> distributions = entityInitialize.getDistributions();
        SortedListSetInterface<Item> donatedItemList = entityInitialize.getItems();

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
                        AddNewDistribution(donatedItemList, distributions);
                        break;
                    case 3:
                        ClearScreen.clearJavaConsoleScreen();
                        UpdateDonationDistribution(donatedItemList, distributions);
                        break;
                    case 4:

                        break;
                    case 5:
                        SearchDonationDistribution(distributions,donatedItemList);
                        //**** Searching purpose   
                        //search by distID/distributeditem (type/id)/distDate
                        //incorporate into list 
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

    public void ListAllDistributions(SortedListSetInterface<Distribution> distributions) {
        distributionUI.listAllDistributions(distributions);
    }

//**** Adding purpose
    public void AddNewDistribution(SortedListSetInterface<Item> donatedItemList, SortedListSetInterface<Distribution> distributions) {

        // Generate new distribution info
        Date distributedDate = new Date(localDay, localMonth, localYear);
        int lastDistributionId = Integer.parseInt(distributions.getLastEntries().getDistributionId().substring(4)) + 1;
        String newDistId = "DIST" + String.format("%03d", lastDistributionId);

        String input;
        boolean isContinue = false;
        boolean foundtype;

        Distribution newDistribution = new Distribution(newDistId, distributedDate);

        do {
            try {
                distributionUI.PrintDonatedItemList(donatedItemList);   // Display the list of donated items
                input = distributionUI.getInputString("Please enter the Item ID that you would like to distribute ( 'Q' quit ; 'F' filter ) > ");

                if (input.equalsIgnoreCase("Q")) {  // Quit the loop
                    break;
                }

                if (input.equalsIgnoreCase("F")) {      //filter item
                    do {
                        ClearScreen.clearJavaConsoleScreen();
                        distributionUI.PrintDonatedItemList(donatedItemList);   // Display the list of donated items
                        String inputType = distributionUI.getInputString("Please enter the type you would like to show > ");
                        ClearScreen.clearJavaConsoleScreen();
                        foundtype = filterItemByType(donatedItemList, inputType);
                    } while (!foundtype);
                    input = distributionUI.getInputString("Please enter the Item ID that you would like to distribute ( 'Q' quit ; 'F' filter ) > ");

                    if (input.equalsIgnoreCase("Q")) {  // Quit the loop
                        break;
                    }
                }

                Item inputItem = checkItemExist(donatedItemList, input); // Check if the item exists

                if (inputItem != null) { // If item is found
                    if (inputItem.getType().equalsIgnoreCase("Monetary")) {
                        isContinue = handleMonetaryItem(inputItem, newDistribution, distributions);
                    } else {
                        isContinue = handleNonMonetaryItem(inputItem, newDistribution, distributions);
                    }
                } else {
                    distributionUI.displayMessage("Item not found.");
                }
            } catch (Exception e) {
                distributionUI.displayMessage("An error occurred: " + e.getMessage());
            }
        } while (isContinue); // Continue until the user decides to quit
    }

    public Item checkItemExist(SortedListSetInterface<Item> donatedItemList, String input) {
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
                        distributionUI.displayMessage("Distribution record is added.");
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

        // Loop until a valid quantity is entered
        while (!isValidQty) {
            try {
                inputQty = distributionUI.getInputQty("Please enter the desired quantity > ");
                if (inputItem.getQuantity() >= inputQty) {
                    SelectedItem selectedItem = new SelectedItem(inputItem.getItemId(), inputQty);
                    newDistribution.addSelectedItem(selectedItem); // Add selected item into the new distribution
                    isValidQty = true; // Exit the quantity input loop

                    String keepAdding = distributionUI.getInputString("Do you want to add another item into this distribution? (y/n) > ");
                    if (keepAdding.equalsIgnoreCase("N")) {
                        distributions.add(newDistribution); // Add the distribution to the list
                        distributionUI.displayMessage("Distribution record is added.");

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

    //update add/dltitem
    //update donee
    public void UpdateDonationDistribution(SortedListSetInterface<Item> donatedItemList, SortedListSetInterface<Distribution> distributions) {
        // List all distributions and get the distribution ID to update
        ListAllDistributions(distributions);
        String updateDistID = distributionUI.getInputString("Please enter the distribution ID that you would like to update > ");
        Distribution updateDist = checkDistributionExist(distributions, updateDistID);

        if (updateDist != null) {  // Check if the record exists
            boolean continueLoop = true;

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
                        //updateDoneeDetails(updateDist);
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
            distributionUI.displayMessage("No record with ID < " + updateDistID + " > exists.");
        }
    }

    public Distribution checkDistributionExist(SortedListSetInterface<Distribution> distributions, String updateDistID) {
        Iterator<Distribution> iterator = distributions.getIterator();
        while (iterator.hasNext()) {
            Distribution currentRecord = iterator.next();
            if (currentRecord.getDistributionId().equalsIgnoreCase(updateDistID)) {
                ClearScreen.clearJavaConsoleScreen();
                //   distributionUI.displayMessage(""+currentRecord);
                distributionUI.printDistributionRecord(currentRecord);
                return currentRecord;

            } else {
            }
        }
        return null;
    }

    public void updateItemDetails(SortedListSetInterface<Item> donatedItemList, Distribution updateDist) {
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
                    selectedItemToUpdate = updateDist.getDistributedItemList().getEntry(1);
                }

                if (selectedItemToUpdate != null) {
                    Item itemToUpdate = findDonatedItem(donatedItemList, selectedItemToUpdate.getItemId());

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

    private Item findDonatedItem(SortedListSetInterface<Item> donatedItemList, String itemId) {
        Iterator<Item> itemIterator = donatedItemList.getIterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            if (item.getItemId().equalsIgnoreCase(itemId)) {
                return item;
            }
        }
        return null;
    }
    //**** Update purpose
    
    public void SearchDonationDistribution(SortedListSetInterface<Distribution> distributions,SortedListSetInterface<Item> donatedItemList){
        
    }
}

    
//}
