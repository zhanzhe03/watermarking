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
                        //AddNewDistribution(donatedItemList, distributions);
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




}
