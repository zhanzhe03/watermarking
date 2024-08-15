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
                        AddNewDistribution(donatedItemList, distributions);
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
    System.out.println("Adding New Distribution");

    
   
    // Generate new distribution info
    int localDay = distributionUI.getLocalDate().getDayOfMonth();
    int localMonth = distributionUI.getLocalDate().getMonthValue();
    int localYear = distributionUI.getLocalDate().getYear();
    Date distributedDate = new Date(localDay, localMonth, localYear);
    int lastDistributionId = Integer.parseInt(distributions.getLastEntries().getDistributionId().substring(4)) + 1;
    String newDistId = "DIST" + String.format("%03d", lastDistributionId);

    
    String input;
    int inputQty = 0;
    Item inputItem;
    boolean isContinue = false;

    
    do {
        
        try {
            
            distributionUI.PrintDonatedItemList(donatedItemList);   // Display the list of donated items
            input = distributionUI.getInputString("Enter item ID or Q to quit > ");
            
            if (input.equalsIgnoreCase("Q")) {  // Quit the loop
                break;
            }

            // Create a new distribution
            Distribution newDistribution = new Distribution(newDistId, distributedDate);
            Iterator<Item> iterator = donatedItemList.getIterator(); // Get an iterator for the donated items list
            boolean isFound = false;

            // Iterate through the donated items
            while (iterator.hasNext()) {
                inputItem = iterator.next(); // Get the current item
                if (inputItem.getItemId().equalsIgnoreCase(input)) { // Check if item ID matches the input
                    isFound = true;
                    boolean isValidQty = false;

                    // Loop until a valid quantity is entered
                    while (!isValidQty) {
                        try {
                            inputQty = distributionUI.getInputQty("Please enter the desired quantity > ");
                            if (inputItem.getQuantity() >= inputQty) {
                                SelectedItem selectedItem = new SelectedItem(input, inputQty);
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
                    break; // Exit the loop since the item has been found and processed
                }
            }

            if (!isFound) {
                distributionUI.displayMessage("Item not found.");
            }

        } catch (Exception e) {
            distributionUI.displayMessage("An error occurred: " + e.getMessage());
        }

    } while (isContinue); // Continue until the user decides to quit

    System.out.println("Updated Distribution List: " + distributions);
}



}
