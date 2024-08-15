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
                        AddNewDistribution(donatedItemList, distributions); //filter havent done
                        break;
                    case 3:
                        ClearScreen.clearJavaConsoleScreen();
                        // SearchDonation(donations, donors);
                        break;
                    case 4:
                        break;
                    case 5:
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
        System.out.print(distributions);
    }

    public void AddNewDistribution(SortedListSetInterface<Item> donatedItemList, SortedListSetInterface<Distribution> distributions) {

        // Generate new distribution info
        int localDay = distributionUI.getLocalDate().getDayOfMonth();
        int localMonth = distributionUI.getLocalDate().getMonthValue();
        int localYear = distributionUI.getLocalDate().getYear();
        Date distributedDate = new Date(localDay, localMonth, localYear);
        int lastDistributionId = Integer.parseInt(distributions.getLastEntries().getDistributionId().substring(4)) + 1;
        String newDistId = "DIST" + String.format("%03d", lastDistributionId);

        String input;
        boolean isContinue = false;

        Distribution newDistribution = new Distribution(newDistId, distributedDate);

        do {
            try {
                distributionUI.PrintDonatedItemList(donatedItemList);   // Display the list of donated items
                input = distributionUI.getInputString("Enter item ID or Q to quit > ");

                if (input.equalsIgnoreCase("Q")) {  // Quit the loop
                    break;
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
        distributionUI.displayMessage("Quiting from Adding Menu....");
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

}
