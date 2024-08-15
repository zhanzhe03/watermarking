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
            opt = Integer.parseInt(distributionUI.getDistributionMenu());
            try {
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
                        //ClearScreen.clearJavaConsoleScreen();
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
                System.out.println("2");
                MessageUI.displayInvalidIntegerMessage();
            }

        } while (opt != 9);
    }

    public void ListAllDistributions(SortedListSetInterface<Distribution> distributions) {
        System.out.print(distributions);
    }

    public void AddNewDistribution(SortedListSetInterface<Item> donatedItemList, SortedListSetInterface<Distribution> distributions) {
        System.out.println("nice");
        //Generate newInfo 
        int localDay = distributionUI.getLocalDate().getDayOfMonth();
        int localMonth = distributionUI.getLocalDate().getMonthValue();
        int localYear = distributionUI.getLocalDate().getYear();
        Date distibutedDate = new Date(localDay, localMonth, localYear);
        int lastDistributionId = Integer.parseInt(distributions.getLastEntries().getDistributionId().substring(1)) + 1;
        String newDistId = "DIST" + String.format("%03d", lastDistributionId);
        //Genarate newInfo

        String input;
        int inputQty = 0;
        Item inputItem;
        boolean isContinue;
        isContinue = false;  // Initialize as false, will update later if user wants to continue adding

        do {
            distributionUI.PrintDonatedItemList(donatedItemList);   // Display the list of donated items
            input = distributionUI.getInputString(">");
            if (input.equalsIgnoreCase("Q")) {  // Quit the loop 
                break;
            }
            // Create a new distribution and add the selected item
            Distribution newDistribution = new Distribution(newDistId, distibutedDate);
            Iterator<Item> iterator = donatedItemList.getIterator(); // Get an iterator for the donated items list
            boolean isFound = false;

            while (iterator.hasNext()) {    // Iterate through the donated items
                inputItem = iterator.next(); // Get the current item
                if (inputItem.getItemId().equalsIgnoreCase(input)) { // Check if item ID matches the input
                    isFound = true;
                    boolean isValidQty;

                    do { // Loop until a valid quantity is entered
                        isValidQty = false;
                        inputQty = distributionUI.getInputQty("Please enter the desired quantity > ");
                        if (inputItem.getQuantity() >= inputQty) {
                            SelectedItem selectedItem = new SelectedItem(input, inputQty);
                            newDistribution.addSelectedItem(selectedItem); // Add selected item into the new distribution
                            isValidQty = true; // Exit the quantity input loop

                            String keepAdding = distributionUI.getInputString("Do you want to add another item into this distribution? (y/n) > ");
                            if (keepAdding.equalsIgnoreCase("N")) {
                                distributions.add(newDistribution); // Add the distribution to the list
                            } else {
                                distributionUI.PrintDonatedItemList(donatedItemList);
                                input = distributionUI.getInputString(">>>>");
                                if (input.equalsIgnoreCase("Q")) {   // Quit the loop 
                                    break;
                                }
                                isContinue = true; // Set to true to continue adding items
                            }
                        } else {
                            distributionUI.displayMessage("Your input quantity has exceeded.");
                        }
                    } while (!isValidQty); // Continue until a valid quantity is entered
                    break; // Exit the loop since the item has been found and processed
                }
            }
            if (!isFound) {
                distributionUI.displayMessage("Item not found.");
            }

        } while (isContinue);  // Continue until the user decides to quit
        System.out.println(distributions);

    }

}
