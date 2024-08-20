/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Boundary.*;
import Utility.*;
import ADT.*;
import Entity.*;
import java.util.Iterator;
import DAO.EntityInitializer;
import java.time.LocalDate;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author USER
 */
public class DonationMaintenance {

    private DonationUI donationUI = new DonationUI();

    public void donationManagement(EntityInitializer entityInitialize) {
        SortedListSetInterface<Donation> donations = entityInitialize.getDonations();
        SortedListSetInterface<Donor> donors = entityInitialize.getDonors();
        SortedListSetInterface<Item> items = entityInitialize.getItems();
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getDonationMenu());

            switch (opt) {
                case 1:
                    ClearScreen.clearJavaConsoleScreen();
                    ListDonation(donations, items);
                    break;
                case 2:
                    ClearScreen.clearJavaConsoleScreen();
                    AddDonation(donations, donors, items);
                    break;
                case 3:
                    ClearScreen.clearJavaConsoleScreen();
                    SearchDonation(donations, items);
                    break;
                case 4:
                    ClearScreen.clearJavaConsoleScreen();
                    amendDonation(donations, items);
                    break;
                case 5:
                    ClearScreen.clearJavaConsoleScreen();
                    RemoveDonation(donations, items);
                    break;
                case 6:
                    ClearScreen.clearJavaConsoleScreen();
                    FilterDonation(donations, items);
                    break;
                case 7:
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
        } while (opt != 9);
    }

//validation
    private int validateMenuNumberFormatInput(String input) {
        int num;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return 0;
        }
        return num;
    }

    private int validateNumberFormatIntegerInput(String desc) {
        int num = 0;
        boolean isValid;
        do {
            try {
                num = Integer.parseInt(donationUI.getInputString(desc));
                isValid = true;
            } catch (NumberFormatException ex) {
                MessageUI.displayInvalidInputMessage();
                isValid = false;
            }
        } while (!isValid);
        return num;
    }

    private double validateNumberFormatDoubleInput(String desc) {
        double num = 0;
        boolean isValid;
        do {
            try {
                num = Double.parseDouble(donationUI.getInputString(desc));
                isValid = true;
            } catch (NumberFormatException ex) {
                MessageUI.displayInvalidInputMessage();
                isValid = false;
            }
        } while (!isValid);
        return num;
    }

    private char validateYesNoOption(String desc) {
        char yesNo = '\0';
        boolean isValid = false;
        do {
            String input = donationUI.getInputString(desc);
            if (input.isEmpty() || input.length() != 1) {
                MessageUI.displayInvalidInputMessage();
            } else {
                yesNo = input.toUpperCase().charAt(0);
                if (yesNo == 'Y' || yesNo == 'N') {
                    isValid = true;
                } else {
                    MessageUI.displayInvalidInputMessage();
                }
            }
        } while (!isValid);
        return yesNo;
    }

    private boolean validateContactNumberFormat(String contact) {
        String regex = "^{10,15}&";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }

    private boolean validateEmailFormat(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validateDateFormat(String date) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    private boolean validateDate(int day, int month, int year) {
        Date currentDate = getCurrentDate();
        int localDay = currentDate.getDay();
        int localMonth = currentDate.getMonth();
        int localYear = currentDate.getYear();

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

    private boolean validExpiryDate(int day, int month, int year) {
        Date currentDate = getCurrentDate();
        int localDay = currentDate.getDay();
        int localMonth = currentDate.getMonth();
        int localYear = currentDate.getYear();

        if (year < localYear) {
            return false;
        } else if (year == localYear && month < localMonth) {
            return false;
        } else if (year == localYear && month == localMonth && day < localDay) {
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

    //get info method
    private Date getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        int localDay = localDate.getDayOfMonth();
        int localMonth = localDate.getMonthValue();
        int localYear = localDate.getYear();
        return new Date(localDay, localMonth, localYear);
    }

    private Date getValidExpiryDate(String desc) {
        String date;
        boolean isValid = false;
        int day = 0;
        int month = 0;
        int year = 0;
        do {
            date = donationUI.getInputString(desc);
            if (!validateDateFormat(date)) {
                MessageUI.displayInvalidDateFormatMessage();
            } else {
                day = Integer.parseInt(date.substring(0, 2));
                month = Integer.parseInt(date.substring(3, 5));
                year = Integer.parseInt(date.substring(6));
                if (!validExpiryDate(day, month, year)) {
                    MessageUI.displayInvalidDateMessage();
                } else {
                    isValid = true;
                }
            }
        } while (!isValid);

        return new Date(day, month, year);
    }

    private Date getValidDonationDate(String desc) {
        String date;
        boolean isValid = false;
        int day = 0;
        int month = 0;
        int year = 0;
        do {
            date = donationUI.getInputString(desc);
            if (!validateDateFormat(date)) {
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

    private String getValidContactNumber() {
        String contact;
        boolean isValid = true;
        do {
            contact = donationUI.getInputString("Contact Number (012-34567890): ");
            if (!validateContactNumberFormat(contact)) {
                MessageUI.displayInvalidContactFormatMessage();
                isValid = false;
            }
        } while (!isValid);
        return contact;
    }

    private String getValidEmail() {
        String email;
        boolean isValid = true;
        do {
            email = donationUI.getInputString("Email (abc@example.com): ");
            if (!validateEmailFormat(email)) {
                MessageUI.displayInvalidEmailFormatMessage();
                isValid = false;
            }
        } while (!isValid);
        return email;
    }

    private String generateNextDonationId(SortedListSetInterface<Donation> donations) {
        int nextDonationId = Integer.parseInt(donations.getLastEntries().getDonationId().substring(1)) + 1;
        return "D" + String.format("%03d", nextDonationId);
    }

    private String generateNextItemId(SortedListSetInterface<Item> items, int n) {
        int nextItemId = Integer.parseInt(items.getLastEntries().getItemId().substring(1)) + n;
        return "I" + String.format("%03d", nextItemId);
    }

    //list method
    private void listAllDonationInACS(SortedListSetInterface<Donation> donations) {
        donationUI.printDonationEnDash();
        donationUI.printDonationTitle();
        donationUI.printDonationEnDash();
        donationUI.printAllDonations(donations);
        donationUI.printDonationEnDash();
        donationUI.printTotalDonation(donations);
    }

    //need find solution to replace stack method
    private void listAllDonationInDESC(SortedListSetInterface<Donation> donations) {
        Stack<Donation> myStack = new Stack<>();
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            myStack.push(donation);
        } while (iterator.hasNext());

        donationUI.printDonationEnDash();
        donationUI.printDonationTitle();
        donationUI.printDonationEnDash();
        while (!myStack.isEmpty()) {
            donationUI.printOneDonation(myStack.pop());
        }
        donationUI.printDonationEnDash();
        donationUI.printTotalDonation(donations);
    }

    private void listOneDonation(Donation donation) {
        donationUI.printDonationEnDash();
        donationUI.printDonationTitle();
        donationUI.printDonationEnDash();
        donationUI.printOneDonation(donation);
        donationUI.printDonationEnDash();
    }

    private void listAllItemInACS(SortedListSetInterface<Item> items) {
        donationUI.printItemEnDash();
        donationUI.printItemTitle();
        donationUI.printItemEnDash();
        donationUI.printAllItems(items);
        donationUI.printItemEnDash();
        donationUI.printTotalItem(items);
    }

    private void listAllItemInDESC(SortedListSetInterface<Item> items) {
        Stack<Item> myStack = new Stack<>();
        Iterator<Item> iterator = items.getIterator();
        do {
            Item item = iterator.next();
            myStack.push(item);
        } while (iterator.hasNext());

        donationUI.printItemEnDash();
        donationUI.printItemTitle();
        donationUI.printItemEnDash();
        donationUI.printText("");
        while (!myStack.isEmpty()) {
            donationUI.printOneItem(myStack.pop());
        }
        donationUI.printItemEnDash();
        donationUI.printTotalItem(items);
    }

    //return method
    private Item createMonetoryItem(String itemId, String type) {
        int opt;
        String methodName = null;
        double amount = 0;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getMonetoryDescMenu());

            switch (opt) {
                case 1:
                    methodName = "Cash";
                    amount = validateNumberFormatDoubleInput("Amount: ");
                    break;
                case 2:
                    methodName = "Online Transfer";
                    amount = validateNumberFormatDoubleInput("Amount: ");
                    break;
                case 3:
                    methodName = "QR Code Payment";
                    amount = validateNumberFormatDoubleInput("Amount: ");
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
        } while (opt < 1 || opt > 3);
        return new Item(itemId, type, methodName, amount);
    }

    private Item createItem(String itemId, String type) {
        String desc = donationUI.getInputString("Description: ");
        int qty = validateNumberFormatIntegerInput("Quantity: ");
        double valuePerItem = validateNumberFormatDoubleInput("Value Per Item : ");
        return new Item(itemId, type, desc, qty, valuePerItem);
    }

    private Item createItemWithExpiryDate(String itemId, String type) {
        String desc = donationUI.getInputString("Description: ");
        int qty = validateNumberFormatIntegerInput("Quantity: ");
        double valuePerItem = validateNumberFormatDoubleInput("Value Per Item : ");

        Date expiryDate = getValidExpiryDate("Item Expiry Date (dd-mm-yyyy): ");
        return new Item(itemId, type, desc, qty, valuePerItem, expiryDate);
    }

    private Donor findDonor(String contact, SortedListSetInterface<Donor> donors) {
        Iterator<Donor> iterator = donors.getIterator();
        do {
            Donor donor = iterator.next();
            if (donor.getContact().equals(contact)) {
                return donor;
            }
        } while (iterator.hasNext());
        return null;
    }

    private Donation findDonation(String id, SortedListSetInterface<Donation> donations) {
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            if (donation.getDonationId().equalsIgnoreCase(id)) {
                return donation;
            }
        } while (iterator.hasNext());
        return null;
    }

    private Item findItem(String id, SortedListSetInterface<Item> items) {
        Iterator<Item> iterator = items.getIterator();
        do {
            Item item = iterator.next();
            if (item.getItemId().equalsIgnoreCase(id)) {
                return item;
            }
        } while (iterator.hasNext());
        return null;
    }

    //donor details on hold
    private Donor registeredNewDonor(String contact, SortedListSetInterface<Donor> donors) {
        int lastDonorId = Integer.parseInt(donors.getLastEntries().getDonorId().substring(2)) + 1;
        String newDonorId = "DR" + String.format("%03d", lastDonorId);
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getRegisteredMenu());
            switch (opt) {
                case 1:
                    String name = donationUI.getInputString("Name: ");
                    String email = getValidEmail();
                    String address = donationUI.getInputString("Address: ");
                    String category = donationUI.getInputString("Category: ");
                    return new Donor(newDonorId, name, name, contact, email, address, category, "active");
                case 2:
                    return new Donor(newDonorId, "Anonymous", "Anonymous", contact, "Anonymous", "Anonymous", "Anonymous", "active");
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
        } while (opt < 1 || opt > 2);
        return null;
    }

    //on hold
    private boolean checkMultipleMethod() {
        return true;
    }

    //1. list
    public void ListDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        donationUI.printTitle("All Donation Records in Ascending Order");
        listAllDonationInACS(donations);
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getListDonationMenu());

            switch (opt) {
                case 1:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Donation Records in Ascending Order");
                    listAllDonationInACS(donations);
                    break;
                case 2:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Donation Records in Descending Order");
                    listAllDonationInDESC(donations);
                    break;
                case 3:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Item Records in Ascending Order");
                    listAllItemInACS(items);
                    break;
                case 4:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Item Records in Descending Order");
                    listAllItemInDESC(items);
                    break;
                case 5:
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
        } while (opt != 9);
    }

    //2. add
    public void AddDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Donor> donors, SortedListSetInterface<Item> items) {
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getAddDonationMenu());

            switch (opt) {
                case 1:
                    ClearScreen.clearJavaConsoleScreen();
                    AddNewDonation(donations, donors, items);
                    break;
                case 2:
                    ClearScreen.clearJavaConsoleScreen();
                    AddItemToExistingDonation(donations, donors, items);
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
        } while (opt != 9);
    }

    public void processDonation(SortedListSetInterface<Item> newDonatedItems, SortedListSetInterface<Item> items) {
        int n = 1;
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getItemTypeMenu());
            Item item = null;
            String itemId = generateNextItemId(items, n);
            switch (opt) {
                case 1:
                    item = createMonetoryItem(itemId, "Monetory");
                    break;
                case 2:
                    item = createItem(itemId, "Clothing and Apparel");
                    break;
                case 3:
                    item = createItemWithExpiryDate(itemId, "Food and Beverage");
                    break;
                case 4:
                    item = createItem(itemId, "Household Items");
                    break;
                case 5:
                    item = createItem(itemId, "Educational Materials");
                    break;
                case 6:
                    item = createItem(itemId, "Electronic");
                    break;
                case 7:
                    item = createItem(itemId, "Medical");
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }

            if (item != null) {
                newDonatedItems.add(item);
                n++;
            }
        } while (validateYesNoOption("Keep Donating (Y = yes, N = no)? ") == 'Y');
    }

    public void AddNewDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Donor> donors, SortedListSetInterface<Item> items) {
        donationUI.printTitle("Add New Donation");
        SortedListSetInterface<Item> newDonatedItems = new SortedDoublyLinkedListSet<>();
        processDonation(newDonatedItems, items);

        if (!newDonatedItems.isEmpty()) {
            Date donatedDate = getCurrentDate();
            String nextDonationId = generateNextDonationId(donations);
            Donation newDonation = new Donation(nextDonationId, donatedDate);
            newDonation.setDonatedItemList(newDonatedItems);

            listOneDonation(newDonation);

            if (validateYesNoOption("\nDonation Confirmation (Y = yes, N = No)? ") == 'Y') {
                String contact = getValidContactNumber();
                Donor donor = findDonor(contact, donors);
                if (donor != null) {
                    newDonation.setDonor(donor);
                } else {
                    donor = registeredNewDonor(contact, donors);
                    newDonation.setDonor(donor);
                }
                donors.add(donor);
                donations.add(newDonation);
                items.merge(newDonatedItems);
                MessageUI.displaySuccessfulMessage();
            } else {
                MessageUI.displayUnsuccessfulMessage();
            }
        }
    }

    public void AddItemToExistingDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Donor> donors, SortedListSetInterface<Item> items) {
        donationUI.printTitle("Add Items to an existing donation");
        String id = donationUI.getInputString("Donation ID : ");
        Donation donation = findDonation(id, donations);
        if (donation != null) {
            SortedListSetInterface<Item> newDonatedItems = new SortedDoublyLinkedListSet<>();
            processDonation(newDonatedItems, items);
            if (!newDonatedItems.isEmpty()) {
                listAllItemInACS(newDonatedItems);

                if (validateYesNoOption("\nDonation Confirmation (Y = yes, N = No)? ") == 'Y') {
                    donation.getDonatedItemList().merge(newDonatedItems);
                    items.merge(newDonatedItems);
                    MessageUI.displaySuccessfulMessage();
                } else {
                    MessageUI.displayUnsuccessfulMessage();
                }
            }
        } else {
            donationUI.printText("This Donation ID " + id + " Not Found !");
        }
    }

    //3. search
    public void SearchDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        donationUI.printTitle("Searh a donation details (can amend or remove)");
        String id = donationUI.getInputString(""
                + "Enter Donation ID or related Item ID "
                + "\n(Donation ID = D001, Item Id = I001): ");
        Donation currentDonation = findDonation(id, donations);
        Item currentItem = findItem(id, items);

        if (currentItem != null || currentDonation != null) {
            if (currentDonation == null) {
                Iterator<Donation> iterator = donations.getIterator();
                do {
                    currentDonation = iterator.next();
                    if (currentDonation.getDonatedItemList().contains(currentItem)) {
                        break;
                    }
                } while (iterator.hasNext());
            }
            processAfterSearchMethod(currentDonation, currentItem, donations, items);
        } else {
            donationUI.printText("\nThis ID " + id + " Not Found !");
        }
    }

    public void processItemDetailsAmended(Item item, SortedListSetInterface<Item> items, Donation donation) {
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getItemAmendMenu());
            switch (opt) {
                case 1:
                    String newDesc = donationUI.getInputString("New Desc: ");
                    item.setDesc(newDesc);
                    MessageUI.displaySuccessfulMessage();
                    break;
                case 2:
                    if (item.getType().equalsIgnoreCase("Monetary")) {
                        donationUI.printText("\nQuantity Modification is not applicable for monetary type !!\n");
                    } else {
                        int newQuantity = validateNumberFormatIntegerInput("New quantity: ");
                        item.setQuantity(newQuantity);
                        MessageUI.displaySuccessfulMessage();
                    }
                    break;
                case 3:
                    if (item.getType().equalsIgnoreCase("Monetary")) {
                        donationUI.printText("\nValue Per Item is not applicable for monetary type !!\n");
                    } else {
                        double newValuePerItem = validateNumberFormatDoubleInput("New value Per Item: ");
                        item.setValuePerItem(newValuePerItem);
                        MessageUI.displaySuccessfulMessage();
                    }
                    break;
                case 4:
                    if (item.getType().equalsIgnoreCase("Monetory")) {
                        double amount = validateNumberFormatDoubleInput("Amount: ");
                        item.setTotalAmount(amount);
                        MessageUI.displaySuccessfulMessage();
                    } else {
                        donationUI.printText("\nAmount Modification is not applicable except monetary type !!\n");
                    }
                    break;
                case 5:
                    if (item.getType().equalsIgnoreCase("Food and Beverage")) {
                        Date newDate = getValidExpiryDate("new Item Expiry Date (dd-mm-yyyy): ");
                        item.setExpiryDate(newDate);
                        MessageUI.displaySuccessfulMessage();
                    } else {
                        donationUI.printText("\nExpiry Date Modification is not applicable except Food and Beverage type !!\n");
                    }
                    break;
                case 6:
                    if (validateYesNoOption("\nItem removed confirmation from this donation (Y = yes, N = No)? ") == 'Y') {
                        items.remove(item);
                        donation.getDonatedItemList().remove(item);
                        MessageUI.displaySuccessfulMessage();
                    } else {
                        MessageUI.displayUnsuccessfulMessage();
                    }
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
        } while (opt < 1 || opt > 6 && opt != 9);
    }

    public void processAfterSearchMethod(Donation currentDonation, Item currentItem, SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        int opt;
        boolean isRemoved = false;
        do {
            listOneDonation(currentDonation);
            donationUI.printDonorMade(currentDonation);
            opt = validateMenuNumberFormatInput(donationUI.getAfterSearchMenu());

            switch (opt) {
                case 1:
                    Date date = getValidDonationDate("New Donation Date (dd-mm-yyyy): ");
                    if (validateYesNoOption("\nDonation date amended confirmation (Y = yes, N = No)? ") == 'Y') {
                        currentDonation.setDonationDate(date);
                        MessageUI.displaySuccessfulMessage();
                    } else {
                        MessageUI.displayUnsuccessfulMessage();
                    }
                    break;
                case 2:
                    String id = donationUI.getInputString("Item Id: ");
                    Item item = findItem(id, currentDonation.getDonatedItemList());
                    if (item != null) {
                        processItemDetailsAmended(item, items, currentDonation);
                    } else {
                        donationUI.printText("\nThis Item ID " + id + " Not Found in this donation !" + "\n");
                    }
                    break;
                case 3:
                    if (validateYesNoOption("\nDonation removed confirmation (Y = yes, N = No)? ") == 'Y') {
                        items.relativeComplement(currentDonation.getDonatedItemList());
                        donations.remove(currentDonation);
                        MessageUI.displaySuccessfulMessage();
                        isRemoved = true;
                    } else {
                        MessageUI.displayUnsuccessfulMessage();
                    }
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }

        } while (opt != 9 && !isRemoved);
    }

    //4. amend - on hold
    public void amendDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getRemoveDonationMenu());

            switch (opt) {
                case 1:
                    ClearScreen.clearJavaConsoleScreen();
                    removedSpecificDate(donations, items);
                    break;
                case 2:
                    ClearScreen.clearJavaConsoleScreen();
                    removedAllBeforeSpecificDate(donations, items);
                    break;
                case 3:
                    ClearScreen.clearJavaConsoleScreen();
                    removedAllByDateRange(donations, items);
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
        } while (opt != 9);
    }

    //5. remove
    public void RemoveDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getRemoveDonationMenu());

            switch (opt) {
                case 1:
                    ClearScreen.clearJavaConsoleScreen();
                    removedSpecificDate(donations, items);
                    break;
                case 2:
                    ClearScreen.clearJavaConsoleScreen();
                    removedAllBeforeSpecificDate(donations, items);
                    break;
                case 3:
                    ClearScreen.clearJavaConsoleScreen();
                    removedAllByDateRange(donations, items);
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
        } while (opt != 9);
    }

    public void handleConfirmRemoveProcess(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items, SortedListSetInterface<Donation> donationRemovedList, SortedListSetInterface<Item> itemRemovedList, String text) {
        if (!donationRemovedList.isEmpty()) {
            listAllDonationInACS(donationRemovedList);
            if (validateYesNoOption("\nDonation Removed Confirmation (Y = yes, N = No)? ") == 'Y') {
                donations.relativeComplement(donationRemovedList);
                items.relativeComplement(itemRemovedList);
                MessageUI.displaySuccessfulMessage();
            } else {
                MessageUI.displayUnsuccessfulMessage();
            }
        } else {
            donationUI.printText(text);
        }
    }

    public void removedSpecificDate(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        donationUI.printTitle("Remove Donation by a Specific Donation Date");
        Date date = getValidDonationDate("Donation Date (dd-mm-yyyy):");
        SortedListSetInterface<Donation> donationRemovedList = new SortedDoublyLinkedListSet<>();
        SortedListSetInterface<Item> itemRemovedList = new SortedDoublyLinkedListSet<>();
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            if (donation.getDonationDate().equals(date)) {
                donationRemovedList.add(donation);
                itemRemovedList.merge(donation.getDonatedItemList());
            }
        } while (iterator.hasNext());

        String text = "\nNo donations were received on this date " + date;
        handleConfirmRemoveProcess(donations, items, donationRemovedList, itemRemovedList, text);
    }

    public void removedAllBeforeSpecificDate(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        donationUI.printTitle("Remove All Donation before a Specific Donation Date");
        Date dateBefore = getValidDonationDate("Donation Before Date (dd-mm-yyyy): ");
        SortedListSetInterface<Donation> donationRemovedList = new SortedDoublyLinkedListSet<>();
        SortedListSetInterface<Item> itemRemovedList = new SortedDoublyLinkedListSet<>();
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            if (donation.getDonationDate().beforeDate(dateBefore)) {
                donationRemovedList.add(donation);
                itemRemovedList.merge(donation.getDonatedItemList());
            }
        } while (iterator.hasNext());

        String text = "\nNo donations were received before this date " + dateBefore;
        handleConfirmRemoveProcess(donations, items, donationRemovedList, itemRemovedList, text);
    }

    public void removedAllByDateRange(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        donationUI.printTitle("Remove all donations within a specific donation date range");
        Date dateAfter = getValidDonationDate("Donation After Date (dd-mm-yyyy): ");
        Date dateBefore = getValidDonationDate("Donation Before Date (dd-mm-yyyy): ");
        SortedListSetInterface<Donation> donationRemovedList = new SortedDoublyLinkedListSet<>();
        SortedListSetInterface<Item> itemRemovedList = new SortedDoublyLinkedListSet<>();
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            if (donation.getDonationDate().afterDate(dateAfter) && donation.getDonationDate().beforeDate(dateBefore)) {
                donationRemovedList.add(donation);
                itemRemovedList.merge(donation.getDonatedItemList());
            }
        } while (iterator.hasNext());

        String text = "\nNo donations were received within date " + dateAfter + " until " + dateBefore;
        handleConfirmRemoveProcess(donations, items, donationRemovedList, itemRemovedList, text);
    }

    //6. filter
    public void FilterDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {

    }
}
