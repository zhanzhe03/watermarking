package Control;

import ADT.SortedListSetInterface;
import ADT.SortedDoublyLinkedListSet;
import Boundary.DonationUI;
import DAO.EntityInitializer;
import Entity.Date;
import Entity.Donation;
import Entity.Donor;
import Entity.Item;
import Utility.MessageUI;
import Utility.ClearScreen;
import Utility.CommonUse;
import Utility.StockUI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Chew Zhan Zhe
 */
public class DonationMaintenance {

    private final DonationUI donationUI = new DonationUI();

    public void donationManagement(EntityInitializer entityInitialize) {
        SortedListSetInterface<Donation> donations = entityInitialize.getDonations();
        SortedListSetInterface<Donor> donors = entityInitialize.getDonors();
        SortedListSetInterface<Item> items = entityInitialize.getItems();
        SortedListSetInterface<Donation> donationHistory = entityInitialize.getDonationsHistory();

        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getDonationMenu());
            checkDonationStatus(donations);
            switch (opt) {
                case 1:
                    ClearScreen.clearJavaConsoleScreen();
                    ListDonation(donations, items);
                    break;
                case 2:
                    ClearScreen.clearJavaConsoleScreen();
                    AddDonation(donations, donors, items, donationHistory);
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
                    RemoveDonation(donations, items, donors);
                    break;
                case 6:
                    ClearScreen.clearJavaConsoleScreen();
                    FilterDonation(donations, items, donors);
                    break;
                case 7:
                    ClearScreen.clearJavaConsoleScreen();
                    TrackItemInDifferentType(items, donations);
                    break;
                case 8:
                    ClearScreen.clearJavaConsoleScreen();
                    summaryReport(donations, items, donationHistory);
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
        String regex = "^\\d{3}-\\d{7,8}$";
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
        boolean isValid = false;
        do {
            contact = donationUI.getInputString("Contact Number (012-34567890): ");
            if (validateContactNumberFormat(contact)) {
                isValid = true;
            } else {
                MessageUI.displayInvalidContactFormatMessage();
            }
        } while (!isValid);
        return contact;
    }

    private String getValidEmail() {
        String email;
        boolean isValid = false;
        do {
            email = donationUI.getInputString("Email (abc@example.com): ");
            if (validateEmailFormat(email)) {
                isValid = true;
            } else {
                MessageUI.displayInvalidEmailFormatMessage();
            }
        } while (!isValid);
        return email;
    }

    private double getTotalValue(SortedListSetInterface<Item> items) {
        double amount = 0;

        Iterator<Item> iterator = items.getIterator();
        do {
            Item item = iterator.next();
            amount += item.getTotalAmount();
        } while (iterator.hasNext());
        return amount;
    }

    //return method
    private String generateNextDonationId(SortedListSetInterface<Donation> donationsHistory) {
        int nextDonationId = Integer.parseInt(donationsHistory.getLastEntries().getDonationId().substring(1)) + 1;
        return "D" + String.format("%03d", nextDonationId);
    }

    private String generateNextItemId(SortedListSetInterface<Donation> donationsHistory, int n) {
        Item item = donationsHistory.getLastEntries().getDonatedItemList().getLastEntries();
        int nextItemId = Integer.parseInt(item.getItemId().substring(1)) + n;
        return "I" + String.format("%03d", nextItemId);
    }

    private Item createMonetoryItem(String itemId, String type) {
        int opt;
        String monetaryDesc = null;
        double amount = 0;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getMonetoryDescMenu());
            switch (opt) {
                case 1:
                    monetaryDesc = "Cash";
                    break;
                case 2:
                    monetaryDesc = "Online Transfer";
                    break;
                case 3:
                    monetaryDesc = "QR Code Payment";
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }

            if (opt >= 1 && opt <= 3) {
                donationUI.printText("Type: " + type);
                donationUI.printText("Description: " + monetaryDesc);
                amount = validateNumberFormatDoubleInput("Amount: ");
            }
        } while (opt < 1 || opt > 3);
        return new Item(itemId, type, monetaryDesc, amount);
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

    private boolean checkDuplicateItem(SortedListSetInterface<Item> items, Item item) {
        Iterator<Item> iterator = items.getIterator();
        do {
            Item itemInList = iterator.next();
            if (itemInList.getType().equalsIgnoreCase("Monetary")
                    && itemInList.getDesc().equalsIgnoreCase(item.getDesc())) {
                itemInList.setTotalAmount(itemInList.getTotalAmount() + item.getTotalAmount());
                return true;
            } else if (itemInList.getType().equalsIgnoreCase("Food and Beverage")
                    && itemInList.getDesc().equalsIgnoreCase(item.getDesc())
                    && itemInList.getValuePerItem() == item.getValuePerItem()
                    && itemInList.getExpiryDate().equals(item.getExpiryDate())) {
                itemInList.setQuantity(itemInList.getQuantity() + item.getQuantity());
                return true;
            } else if (itemInList.getType().equalsIgnoreCase(item.getType())
                    && itemInList.getDesc().equalsIgnoreCase(item.getDesc())
                    && itemInList.getValuePerItem() == item.getValuePerItem()) {
                itemInList.setQuantity(itemInList.getQuantity() + item.getQuantity());
                return true;
            }
        } while (iterator.hasNext());
        return false;
    }

    private boolean checkDonorStatus(Donor donor) {
        return !donor.getStatus().equalsIgnoreCase("Banned");
    }

    //donor details on hold
    private Donor registeredNewDonor(String contact, SortedListSetInterface<Donor> donors) {
        int lastDonorId = Integer.parseInt(donors.getLastEntries().getDonorId().substring(2)) + 1;
        String newDonorId = "DR" + String.format("%03d", lastDonorId);
        String category = setDonorCategoty();
        String name = donationUI.getInputString("Name: ");
        String contactName;
        if (!category.equalsIgnoreCase("Individual")) {
            contactName = donationUI.getInputString("Contact Name: ");
        } else {
            contactName = "-";
        }
        String email = getValidEmail();
        String address = donationUI.getInputString("Address: ");
        return new Donor(newDonorId, category, name, contactName, contact, email, address, getCurrentDate());
    }

    private String setDonorCategoty() {
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getDonorCategory());
            switch (opt) {
                case 1:
                    return "Individual";
                case 2:
                    return "Public Organisation";
                case 3:
                    return "Private Organisation";
                case 4:
                    return "Government Organisation";
                default:
                    MessageUI.displayInvalidOptionMessage();
            }
        } while (opt < 1 || opt > 4);
        return null;
    }

    //display method
    private void listAllDonation(SortedListSetInterface<Donation> donations) {
        donationUI.printDonationEnDash();
        donationUI.printDonationTitle();
        donationUI.printDonationEnDash();
        donationUI.printAllDonations(donations);
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

    private void listAllItem(SortedListSetInterface<Item> items) {
        donationUI.printItemEnDash();
        donationUI.printItemTitle();
        donationUI.printItemEnDash();
        donationUI.printText("");
        donationUI.printAllItems(items);
        donationUI.printItemEnDash();
        donationUI.printTotalItem(items);
    }

    private void checkDonationStatus(SortedListSetInterface<Donation> donations) {
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            boolean isFullyDistributed = checkFullyDistribute(donation.getDonatedItemList());
            if (donation.getStatus().equalsIgnoreCase("Pending") && !donation.getDonationDate().withinTwoDays(getCurrentDate())) {
                donation.setStatus("Processing");
            } else if (!donation.getStatus().equalsIgnoreCase("Fully Distributed") && isFullyDistributed) {
                donation.setStatus("Fully Distributed");
            }
        } while (iterator.hasNext());
    }
    
    private boolean checkFullyDistribute(SortedListSetInterface<Item> items){
        Iterator<Item> iterator = items.getIterator();
        do{
            Item item = iterator.next();
            if(item.getTotalAmount() != 0){
                return false;
            }
        }while(iterator.hasNext());
        return true;
    }

    //1. list
    public void ListDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        donationUI.printTitle("All Donation and Item Records");
        Donation.setSortByCriteria(Donation.SortByCriteria.DONATIONID_INASC);
        donations.reSort();
        listAllDonation(donations);
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getListMenu());
            switch (opt) {
                case 1:
                    listDonation(donations);
                    break;
                case 2:
                    listItem(items);
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;

            }
        } while (opt != 9);
    }

    public void listDonation(SortedListSetInterface<Donation> donations) {
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getListDonationMenu());
            switch (opt) {
                case 1:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Donation Records sorted by Donation ID in Ascending Order");
                    Donation.setSortByCriteria(Donation.SortByCriteria.DONATIONID_INASC);
                    break;
                case 2:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Donation Records sorted by Donation ID in Descending Order");
                    Donation.setSortByCriteria(Donation.SortByCriteria.DONATIONID_INDESC);
                    break;
                case 3:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Donation Records sorted by Donation Date in Ascending Order");
                    Donation.setSortByCriteria(Donation.SortByCriteria.DONATIONDATE_INASC);
                    break;
                case 4:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Donation Records sorted by Donation Date in Descending Order");
                    Donation.setSortByCriteria(Donation.SortByCriteria.DONATIONDATE_INDESC);
                    break;
                case 5:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Donation Records sorted by Donation Status in Ascending Order");
                    Donation.setSortByCriteria(Donation.SortByCriteria.STATUS_INASC);
                    break;
                case 6:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Donation Records sorted by Donation Status in Descending Order");
                    Donation.setSortByCriteria(Donation.SortByCriteria.STATUS_INDESC);
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;

            }
            if (opt >= 1 && opt <= 6) {
                donations.reSort();
                listAllDonation(donations);
            }
        } while (opt != 9);
        Donation.setSortByCriteria(Donation.SortByCriteria.DONATIONID_INASC);
        donations.reSort();
    }

    public void listItem(SortedListSetInterface<Item> items) {
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getListItemMenu());
            switch (opt) {
                case 1:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Item Records sorted by Item ID in Ascending Order");
                    Item.setSortByCriteria(Item.SortByCriteria.ITEMID_INASC);
                    break;
                case 2:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Item Records sorted by Item ID in Descending Order");
                    Item.setSortByCriteria(Item.SortByCriteria.ITEMID_INDESC);
                    break;
                case 3:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Item Records sorted by Item Type in Ascending Order");
                    Item.setSortByCriteria(Item.SortByCriteria.TYPE_INASC);
                    break;
                case 4:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Item Records sorted by Item Type in Descending Order");
                    Item.setSortByCriteria(Item.SortByCriteria.TYPE_INDESC);
                    break;
                case 5:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Item Records sorted by Item Description in Ascending Order");
                    Item.setSortByCriteria(Item.SortByCriteria.DESC_INASC);
                    break;
                case 6:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Item Records sorted by Item Description in Descending Order");
                    Item.setSortByCriteria(Item.SortByCriteria.DESC_INDESC);
                    break;
                case 7:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Item Records sorted by Item Total Amount in Ascending Order");
                    Item.setSortByCriteria(Item.SortByCriteria.TOTALAMOUNT_INASC);
                    break;
                case 8:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("All Item Records sorted by Item Total Amount in Descending Order");
                    Item.setSortByCriteria(Item.SortByCriteria.TOTALAMOUNT_INDESC);
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;

            }
            if (opt >= 1 && opt <= 8) {
                items.reSort();
                listAllItem(items);
            }
        } while (opt != 9);
        Item.setSortByCriteria(Item.SortByCriteria.ITEMID_INASC);
        items.reSort();
    }

    //2. add
    public void AddDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Donor> donors, SortedListSetInterface<Item> items, SortedListSetInterface<Donation> donationsHistory) {
        donationUI.printTitle("Add a new Donation");
        ClearScreen.clearJavaConsoleScreen();
        AddNewDonation(donations, donors, items, donationsHistory);
    }

    public void processDonation(SortedListSetInterface<Item> newDonatedItems, SortedListSetInterface<Donation> donationsHistory) {
        int n = 1;
        int opt;
        char keepDonate = 'Y';
        boolean isDiscontinue = false;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getItemTypeMenu());
            Item item = null;
            String itemId = generateNextItemId(donationsHistory, n);
            String type = StockUI.getItemType(opt);
            switch (opt) {
                case 1:
                    item = createMonetoryItem(itemId, type);
                    break;
                case 3:
                    donationUI.printText("Type: " + type);
                    item = createItemWithExpiryDate(itemId, type);
                    break;
                case 2:
                case 4:
                case 5:
                case 6:
                case 7:
                    donationUI.printText("Type: " + type);
                    item = createItem(itemId, type);
                    break;
                case 9:
                    if (validateYesNoOption("\nDiscontinue and cancel this donation (Y = yes, N = no)? ") == 'Y') {
                        isDiscontinue = true;
                        newDonatedItems.clear();
                    }
                    break;
            }

            if (item != null) {
                if (newDonatedItems.isEmpty()) {
                    newDonatedItems.add(item);
                    n++;
                } else {
                    if (!checkDuplicateItem(newDonatedItems, item)) {
                        newDonatedItems.add(item);
                        n++;
                    }
                }
                keepDonate = validateYesNoOption("Keep Donating (Y = yes, N = no)? ");
            }
        } while (keepDonate == 'Y' && !isDiscontinue);
    }

    public void AddNewDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Donor> donors, SortedListSetInterface<Item> items, SortedListSetInterface<Donation> donationsHistory) {
        donationUI.printTitle("Add New Donation");
        SortedListSetInterface<Item> newDonatedItems = new SortedDoublyLinkedListSet<>();
        processDonation(newDonatedItems, donationsHistory);

        if (!newDonatedItems.isEmpty()) {
            Date donatedDate = getCurrentDate();
            String nextDonationId = generateNextDonationId(donationsHistory);
            Donation newDonation = new Donation(nextDonationId, donatedDate, "Pending");
            newDonation.setDonatedItemList(newDonatedItems);

            listOneDonation(newDonation);

            if (validateYesNoOption("\nDonation Confirmation (Y = yes, N = No)? ") == 'Y') {
                String contact = getValidContactNumber();
                Donor donor = CommonUse.findDonor(contact, donors);
                if (donor != null) {
                    if (checkDonorStatus(donor)) {
                        newDonation.setDonor(donor);
                        donations.add(newDonation);
                        items.merge(newDonatedItems);
                        MessageUI.displaySuccessfulMessage();
                        Donation clonedDonation = newDonation.clone();
                        donor.addDonationToList(clonedDonation);
                        donationsHistory.add(clonedDonation);
                    } else {
                        MessageUI.displayDonorStatusUnsuccessfulMessage();
                    }
                } else {
                    donationUI.printText("This contact number not found, please register as a donor.");
                    donor = registeredNewDonor(contact, donors);
                    newDonation.setDonor(donor);
                    donors.add(donor);
                    donations.add(newDonation);
                    items.merge(newDonatedItems);
                    MessageUI.displaySuccessfulMessage();
                    Donation clonedDonation = newDonation.clone();
                    donor.addDonationToList(clonedDonation);
                    donationsHistory.add(clonedDonation);
                }
            } else {
                MessageUI.displayUnsuccessfulMessage();
            }
        }
    }

    //3. search
    public void SearchDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        donationUI.printTitle("Search a specific donation details");
        String input = donationUI.getInputString("Enter relevant donation/item ID or item description keyword > ");
        Donation currentDonation = CommonUse.findDonation(input, donations);
        Item currentItem = CommonUse.findItem(input, items);

        if (currentItem != null || currentDonation != null) {
            if (currentDonation == null) {
                currentDonation = CommonUse.findDonationByItem(currentItem, donations);
            }
            listOneDonation(currentDonation);
            donationUI.printDonorMade(currentDonation);
            donationUI.printText("\n");
        } else { //perform search keyword
            boolean found = false;
            performKeywordSearch(input, donations, found);
        }
        donationUI.getInputString("\nPress ENTER or any key to continue...\n\n");
    }

    public void performKeywordSearch(String keyword, SortedListSetInterface<Donation> donations, boolean found) {
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            if (itemMatchesKeyword(donation, keyword)) {
                listOneDonation(donation);
                donationUI.printDonorMade(donation);
                found = true;
                donationUI.printText("\n");
            }
        } while (iterator.hasNext());

        if (!found) {
            donationUI.printText("\nThis ID or keyword " + keyword + " Not Found !");
        }
    }

    public boolean itemMatchesKeyword(Donation donation, String keyword) {
        Iterator<Item> iterator = donation.getDonatedItemList().getIterator();
        do {
            Item item = iterator.next();
            if (item.getDesc().equalsIgnoreCase(keyword)) {
                return true;
            }
        } while (iterator.hasNext());
        return false;
    }

    //4. amend
    public void amendDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        donationUI.printTitle("Amend Donation and item");
        String input = donationUI.getInputString("Enter relevant donation/item ID > ");
        Donation currentDonation = CommonUse.findDonation(input, donations);
        Item currentItem = CommonUse.findItem(input, items);

        if (currentItem != null || currentDonation != null) {
            if (currentDonation == null) {
                currentDonation = CommonUse.findDonationByItem(currentItem, donations);
            }
            listOneDonation(currentDonation);
            donationUI.printText("\nDonation Found, continue amended...");
            processAmendMethod(currentDonation, donations, items);
        } else {
            donationUI.printText("\nThis ID " + input + " Not Found !");
        }
    }

    public void processAmendMethod(Donation currentDonation, SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getDonationAmendedMenu());
            switch (opt) {
                case 1:
                    Date date = getValidDonationDate("New Donation Date (dd-mm-yyyy): ");
                    if (validateYesNoOption("\nDonation date amended confirmation (Y = yes, N = No)? ") == 'Y') {
                        currentDonation.setDonationDate(date);
                        MessageUI.displaySuccessfulMessage();
                        checkDonationStatus(donations);
                        listOneDonation(currentDonation);
                    } else {
                        MessageUI.displayUnsuccessfulMessage();
                    }
                    break;
                case 2:
                    String id = donationUI.getInputString("Item Id: ");
                    Item item = CommonUse.findItem(id, currentDonation.getDonatedItemList());
                    if (item != null) {
                        processItemDetailsAmended(item, items, currentDonation, donations);
                    } else {
                        donationUI.printText("\nThis Item ID " + id + " Not Found in this donation !" + "\n");
                    }
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
        } while (opt != 9);
    }

    public void processItemDetailsAmended(Item item, SortedListSetInterface<Item> items, Donation donation, SortedListSetInterface<Donation> donations) {
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getItemAmendedMenu());
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
                    if (item.getType().equalsIgnoreCase("Monetary")) {
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
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
            if (opt >= 1 && opt <= 5) {
                checkDonationStatus(donations);
                listOneDonation(donation);
            }
        } while (opt < 1 || opt > 5 && opt != 9);
    }

    //5. remove
    public void RemoveDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items, SortedListSetInterface<Donor> donors) {
        donationUI.printTitle("Remove donation and item in different method");
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getRemoveDonationMenu());

            switch (opt) {
                case 1:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("Remove a specific donation");
                    String input = donationUI.getInputString("Enter relevant donation/item ID > ");
                    Donation currentDonation = CommonUse.findDonation(input, donations);
                    Item currentItem = CommonUse.findItem(input, items);

                    if (currentItem != null || currentDonation != null) {
                        if (currentDonation == null) {
                            currentDonation = CommonUse.findDonationByItem(currentItem, donations);
                        }
                        listOneDonation(currentDonation);
                        if (validateYesNoOption("\nDonation " + currentDonation.getDonationId() + "Removed Confirmation (Y = yes, N = No)? ") == 'Y') {
                            items.relativeComplement(currentDonation.getDonatedItemList());
                            donations.remove(currentDonation);
                            MessageUI.displaySuccessfulMessage();
                        } else {
                            MessageUI.displayUnsuccessfulMessage();
                        }
                    } else {
                        donationUI.printText("\nThis ID " + input + " Not Found !");
                    }
                    break;
                case 2:
                    ClearScreen.clearJavaConsoleScreen();
                    removedItemFromDonation(donations, items);
                    break;
                case 3:
                    ClearScreen.clearJavaConsoleScreen();
                    removedSpecificDate(donations, items);
                    break;
                case 4:
                    ClearScreen.clearJavaConsoleScreen();
                    removedAllBeforeSpecificDate(donations, items);
                    break;
                case 5:
                    ClearScreen.clearJavaConsoleScreen();
                    removedAllByDateRange(donations, items);
                    break;
                case 6:
                    ClearScreen.clearJavaConsoleScreen();
                    removedExpiredItem(donations, items);
                    break;
                case 7:
                    ClearScreen.clearJavaConsoleScreen();
                    removedAllDonationForDonor(donations, items, donors);
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
        } while (opt != 9);
    }

    public void removedItemFromDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        donationUI.printTitle("Remove item from a donation");
        String input = donationUI.getInputString("Enter relevant donation/item ID > ");
        Donation currentDonation = CommonUse.findDonation(input, donations);
        Item currentItem = CommonUse.findItem(input, items);

        if (currentItem != null || currentDonation != null) {
            if (currentDonation == null) {
                currentDonation = CommonUse.findDonationByItem(currentItem, donations);
            }
            listOneDonation(currentDonation);

            donationUI.printText("\n\nDonation Found, continue remove item...");
            String id = donationUI.getInputString("Item Id: ");
            Item item = CommonUse.findItem(id, currentDonation.getDonatedItemList());
            if (item != null) {
                if (validateYesNoOption("\nItem " + item.getItemId() + "Removed from this Donation Confirmation (Y = yes, N = No)? ") == 'Y') {
                    items.remove(item);
                    currentDonation.getDonatedItemList().remove(item);
                    MessageUI.displaySuccessfulMessage();
                } else {
                    MessageUI.displayUnsuccessfulMessage();
                }
            } else {
                donationUI.printText("\nThis Item ID " + id + " Not Found in this donation !" + "\n");
            }
        } else {
            donationUI.printText("\nThis ID " + input + " Not Found !");
        }
    }

    public void handleConfirmRemoveProcess(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items, SortedListSetInterface<Donation> donationRemovedList, SortedListSetInterface<Item> itemRemovedList, String text) {
        if (!donationRemovedList.isEmpty()) {
            listAllDonation(donationRemovedList);
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
        Date date = getValidDonationDate("Donation Date (dd-mm-yyyy): ");
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
        SortedListSetInterface<Donation> donationRemovedList = new SortedDoublyLinkedListSet<>();
        SortedListSetInterface<Item> itemRemovedList = new SortedDoublyLinkedListSet<>();
        filterByRangeOfDate(donations, donationRemovedList, itemRemovedList);

        String text = "\nNo donations were received within this range of date";
        handleConfirmRemoveProcess(donations, items, donationRemovedList, itemRemovedList, text);
    }

    public void removedExpiredItem(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items) {
        donationUI.printTitle("All Expired Item Record");
        SortedListSetInterface<Item> expiredItems = new SortedDoublyLinkedListSet<>();
        filterByExpiredItem(items, expiredItems);
        if (!expiredItems.isEmpty()) {
            listAllItem(expiredItems);
            if (validateYesNoOption("\nRemoved All Expired Items Confirmation (Y = yes, N = No)? ") == 'Y') {
                Iterator<Donation> iterator = donations.getIterator();
                do {
                    Donation donation = iterator.next();
                    donation.getDonatedItemList().relativeComplement(expiredItems);
                } while (iterator.hasNext());
                items.relativeComplement(expiredItems);
                MessageUI.displaySuccessfulMessage();
            } else {
                MessageUI.displayUnsuccessfulMessage();
            }
        }
    }

    public void removedAllDonationForDonor(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items, SortedListSetInterface<Donor> donors) {
        donationUI.printTitle("Remove all donation made by a donor");
        SortedListSetInterface<Donation> donationForOneDonor = new SortedDoublyLinkedListSet<>();
        SortedListSetInterface<Item> itemForOneDonor = new SortedDoublyLinkedListSet<>();
        String contactNumber = getValidContactNumber();
        Donor donor = CommonUse.findDonor(contactNumber, donors);
        if (donor != null) {
            filterByDonor(donor, donations, donationForOneDonor, itemForOneDonor);
            if (!donationForOneDonor.isEmpty()) {
                if (validateYesNoOption("\nRemoved All Donation Record For this donor Confirmation (Y = yes, N = No)? ") == 'Y') {
                    donations.relativeComplement(donationForOneDonor);
                    items.relativeComplement(itemForOneDonor);
                    MessageUI.displaySuccessfulMessage();
                } else {
                    MessageUI.displayUnsuccessfulMessage();
                }
            }
        } else {
            donationUI.printText("\nThis contact number " + contactNumber + " Not Found !");
        }
    }

    //6. filter
    public void FilterDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items, SortedListSetInterface<Donor> donors) {
        donationUI.printTitle("Filter donation and item based on specific criteria");
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getFilterDonationMenu());

            switch (opt) {
                case 1:
                    ClearScreen.clearJavaConsoleScreen();
                    filterByDonationStatus(donations);
                    break;
                case 2:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("Donation Made By this donor");
                    SortedListSetInterface<Donation> donationForOneDonor = new SortedDoublyLinkedListSet<>();
                    SortedListSetInterface<Item> itemForOneDonor = new SortedDoublyLinkedListSet<>();
                    String contactNumber = getValidContactNumber();
                    Donor donor = CommonUse.findDonor(contactNumber, donors);
                    if (donor != null) {
                        filterByDonor(donor, donations, donationForOneDonor, itemForOneDonor);
                    } else {
                        donationUI.printText("\nThis contact number " + contactNumber + " Not Found !");
                    }
                    break;
                case 3:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("Donation Record within the past week (Local Date: " + getCurrentDate() + ")");
                    filterByRecentDonation(opt, donations);
                    break;
                case 4:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("Donation Record within the past month (Local Date: " + getCurrentDate() + ")");
                    filterByRecentDonation(opt, donations);
                    break;
                case 5:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("Donation Record within a range of date");
                    filterByRecentDonation(opt, donations);
                    break;
                case 6:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("Expiry Items (Local Date: " + getCurrentDate() + ")");
                    SortedListSetInterface<Item> expiredItems = new SortedDoublyLinkedListSet<>();
                    filterByExpiredItem(items, expiredItems);
                    if (!expiredItems.isEmpty()) {
                        listAllItem(expiredItems);
                    } else {
                        donationUI.printText("\nNo expired item found !!\n");
                    }
                    break;
                case 7:
                    ClearScreen.clearJavaConsoleScreen();
                    donationUI.printTitle("Upcoming Expiry Items within next 15 days  (Local Date: " + getCurrentDate() + ")");
                    filterByUpcomingExpiryItem(items);
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
        } while (opt != 9);
    }

    public void filterByDonationStatus(SortedListSetInterface<Donation> donations) {
        donationUI.printTitle("Filter donation with different status");
        SortedListSetInterface<Donation> tempList = new SortedDoublyLinkedListSet<>();
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getDonationStatusOption());

            switch (opt) {
                case 1:
                    ClearScreen.clearJavaConsoleScreen();
                    getListByStatus("Pending", donations, tempList);
                    donationUI.printTitle("All Donation of Pending Status");
                    listAllDonation(tempList);
                    break;
                case 2:
                    ClearScreen.clearJavaConsoleScreen();
                    getListByStatus("Processing", donations, tempList);
                    donationUI.printTitle("All Donation of Processing Status");
                    listAllDonation(tempList);
                    break;
                case 3:
                    ClearScreen.clearJavaConsoleScreen();
                    getListByStatus("Distributing", donations, tempList);
                    getListByStatus("Fully Distributed", donations, tempList);
                    donationUI.printTitle("All Donation of Distributing or Fully Distributed Status");
                    listAllDonation(tempList);
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
            tempList.clear();
        } while (opt != 9);
    }

    public SortedListSetInterface<Donation> getListByStatus(String status, SortedListSetInterface<Donation> donations, SortedListSetInterface<Donation> tempList) {
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            if (donation.getStatus().equalsIgnoreCase(status)) {
                tempList.add(donation);
            }
        } while (iterator.hasNext());
        return tempList;
    }

    public void filterByDonor(Donor donor, SortedListSetInterface<Donation> donations, SortedListSetInterface<Donation> donationForOneDonor, SortedListSetInterface<Item> itemForOneDonor) {
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            if (donation.getDonor().equals(donor)) {
                donationForOneDonor.add(donation);
                itemForOneDonor.merge(donation.getDonatedItemList());
            }
        } while (iterator.hasNext());

        if (!donationForOneDonor.isEmpty()) {
            listAllDonation(donationForOneDonor);
            donationUI.printText("Total Value: RM" + getTotalValue(itemForOneDonor) + "\n");
        } else {
            donationUI.printText("\nNo donation record for this donor !!\n");
        }
    }

    public void filterByRecentDonation(int opt, SortedListSetInterface<Donation> donations) {
        SortedListSetInterface<Donation> filterDonation = new SortedDoublyLinkedListSet<>();
        SortedListSetInterface<Item> itemListed = new SortedDoublyLinkedListSet<>();
        Iterator<Donation> iterator = donations.getIterator();
        switch (opt) {
            case 3:
                do {
                    Donation donation = iterator.next();
                    if (donation.getDonationDate().withinPassWeek(getCurrentDate())) {
                        filterDonation.add(donation);
                        itemListed.merge(donation.getDonatedItemList());
                    }
                } while (iterator.hasNext());
                break;
            case 4:
                do {
                    Donation donation = iterator.next();
                    if (donation.getDonationDate().withinPassMonth(getCurrentDate())) {
                        filterDonation.add(donation);
                        itemListed.merge(donation.getDonatedItemList());
                    }
                } while (iterator.hasNext());
                break;
            default:
                filterByRangeOfDate(donations, filterDonation, itemListed);
                break;
        }
        if (!filterDonation.isEmpty()) {
            listAllDonation(filterDonation);
            donationUI.printText("Total Value: RM" + getTotalValue(itemListed) + "\n");
        } else {
            donationUI.printText("\nNo donation record found!!\n");
        }
    }

    public void filterByRangeOfDate(SortedListSetInterface<Donation> donations, SortedListSetInterface<Donation> filterDonation, SortedListSetInterface<Item> itemListed) {
        Date dateAfter = getValidDonationDate("Starting Date (dd-mm-yyyy): ");
        Date dateBefore = getValidDonationDate("Ending Date (dd-mm-yyyy): ");
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            if (donation.getDonationDate().afterDate(dateAfter) && donation.getDonationDate().beforeDate(dateBefore)) {
                filterDonation.add(donation);
                itemListed.merge(donation.getDonatedItemList());
            }
        } while (iterator.hasNext());
    }

    public void filterByExpiredItem(SortedListSetInterface<Item> items, SortedListSetInterface<Item> expiredItems) {
        Iterator<Item> iterator = items.getIterator();
        do {
            Item item = iterator.next();
            if (item.getType().equalsIgnoreCase("Food and Beverage") && item.getExpiryDate().beforeDate(getCurrentDate())) {
                expiredItems.add(item);
            }
        } while (iterator.hasNext());
    }

    public void filterByUpcomingExpiryItem(SortedListSetInterface<Item> items) {
        SortedListSetInterface<Item> expiredItem = new SortedDoublyLinkedListSet<>();
        Iterator<Item> iterator = items.getIterator();
        do {
            Item item = iterator.next();
            if (item.getType().equalsIgnoreCase("Food and Beverage") && item.getExpiryDate().next15Days(getCurrentDate())) {
                expiredItem.add(item);
            }
        } while (iterator.hasNext());
        if (!expiredItem.isEmpty()) {
            listAllItem(expiredItem);
        } else {
            donationUI.printText("\nNo upcoming expiry item found !!\n");
        }
    }

    //7. track item in different type
    public void TrackItemInDifferentType(SortedListSetInterface<Item> items, SortedListSetInterface<Donation> donations) {
        donationUI.printTitle("Track Donated Items In Different Type");
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getItemTypeMenu());
            String typeOpt = StockUI.getItemType(opt);

            if (typeOpt != null) {
                ClearScreen.clearJavaConsoleScreen();
                donationUI.printText("\n\nAll Donated Item In Type (" + typeOpt + ")");
                printItemsInType(donations, typeOpt);

                MessageUI.displayTextColorExplanation();

                donationUI.printText("\n(" + typeOpt + " Items Analysis)");
                int min = StockUI.minimunInventory(typeOpt);
                if (typeOpt.equalsIgnoreCase("Monetary")) {
                    double ttlAmount = StockUI.getTotalAmount(typeOpt, donations);
                    donationUI.printText("Total Number of Available Amount: RM" + ttlAmount);
                    donationUI.printText("Distribution Open Minimum Amount (Available): " + min);
                    if (ttlAmount < min) {
                        MessageUI.displayNotEnoughStockMessage(typeOpt);
                    } else {
                        MessageUI.displayEnoughStockMessage(typeOpt);
                    }
                } else {
                    int ttlQty = StockUI.getTotalInventory(typeOpt, donations);
                    donationUI.printText("Total Number of Available Quantity > " + ttlQty);
                    donationUI.printText("Distribution Open Minimum Available Quantity: " + min);
                    if (ttlQty < min) {
                        MessageUI.displayNotEnoughStockMessage(typeOpt);
                    } else {
                        MessageUI.displayEnoughStockMessage(typeOpt);
                    }
                }
                donationUI.getInputString("\n\nPress ENTER or any key to continue...");
                donationUI.printText("\n");
            }
        } while (opt != 9);
    }

    public void printItemsInType(SortedListSetInterface<Donation> donations, String type) {
        int numberOfEntries = 0;
        donationUI.printItemEnDash();
        donationUI.printItemTitle();
        donationUI.printItemEnDash();
        donationUI.printText("");
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            if (donation.getStatus().equalsIgnoreCase("Pending")) {
                numberOfEntries += printPendingItem(donation.getDonatedItemList(), type);
            } else {
                numberOfEntries += printStockItem(donation.getDonatedItemList(), type);
            }
        } while (iterator.hasNext());
        donationUI.printItemEnDash();
        donationUI.printText("\nTotal Number of Item in " + type + " > " + numberOfEntries + "\n");
    }

    public int printStockItem(SortedListSetInterface<Item> itemsInDonation, String type) {
        int count = 0;
        Iterator<Item> iterator = itemsInDonation.getIterator();
        do {
            Item item = iterator.next();

            if (type.equalsIgnoreCase("Food and Beverage") && item.getType().equalsIgnoreCase(type)) {
                SortedListSetInterface<Item> expiredItems = new SortedDoublyLinkedListSet<>();
                filterByExpiredItem(itemsInDonation, expiredItems);
                if (!expiredItems.isEmpty()) {
                    if (expiredItems.contains(item)) {
                        MessageUI.displayExpiredItemInRedColor(item);
                        count++;
                    } else {
                        donationUI.printOneItem(item);
                        count++;
                    }
                }
            } else {
                if (item.getType().equalsIgnoreCase(type)) {
                    donationUI.printOneItem(item);
                    count++;
                }
            }
        } while (iterator.hasNext());
        return count;
    }

    public int printPendingItem(SortedListSetInterface<Item> itemsInDonation, String type) {
        int count = 0;
        Iterator<Item> iterator = itemsInDonation.getIterator();
        do {
            Item item = iterator.next();
            if (item.getType().equalsIgnoreCase(type)) {
                MessageUI.displayPendingItemInBlueColor(item);
                count++;
            }
        } while (iterator.hasNext());
        return count;
    }

    //8. report
    public void summaryReport(SortedListSetInterface<Donation> donations, SortedListSetInterface<Item> items, SortedListSetInterface<Donation> donationHistory) {
        int opt;
        do {
            opt = validateMenuNumberFormatInput(donationUI.getSummaryReport());
            switch (opt) {
                case 1:
                    ClearScreen.clearJavaConsoleScreen();
                    donationStatusSummary(donations);
                    break;
                case 2:
                    ClearScreen.clearJavaConsoleScreen();
                    itemTypeReceivedSummary(donationHistory);
                    break;
                case 9:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }
            if (opt >= 1 && opt <= 2) {
                donationUI.getInputString("\n\n\nPress ENTER or any key to continue...");
                donationUI.printText("\n");
            }
        } while (opt != 9);
    }

    //Item Type reception
    public void itemTypeReceivedSummary(SortedListSetInterface<Donation> donationHistory) {
        SortedListSetInterface<Item> itemHistory = new SortedDoublyLinkedListSet<>();
        getAllItemHistory(itemHistory, donationHistory);
        CommonUse.getLogo();
        for (int i = 0; i < 110; i++) {
            donationUI.printTextWithoutNextLine("=");
        }
        donationUI.printText("\n\n\t\t\t\tDONATED ITEM TYPE RECEPTION ANALYSIS REPORT");
        donationUI.printText("\t\t\t\t------------------------------------------");
        donationUI.printText("\nGenerated at: " + getGeneratedReportDate());
        //dividing line
        for (int i = 0; i < 110; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        //dividing line

        donationUI.printText("\n\n\t       Table of number of reception and proportions of various types of donated items\n");
        donationUI.printTextWithoutNextLine("\t\t+");
        for (int i = 0; i < 74; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("+");
        donationUI.sentence1();
        donationUI.printTextWithoutNextLine("\t\t+");
        for (int i = 0; i < 74; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("+");
        for (int index = 1; index < 8; index++) {
            String type = StockUI.getItemType(index);
            int num = countNumberOfReceptionOfItemType(type, itemHistory);
            double percentage = (double) num * 100 / (double) itemHistory.getNumberOfEntries();
            donationUI.sentence2(index, type, num, percentage);
        }
        donationUI.printTextWithoutNextLine("\t\t+");
        for (int i = 0; i < 74; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("+");
        donationUI.printText("\nTotal number of Donation > " + itemHistory.getNumberOfEntries());
        donationUI.printText("");

        //dividing line
        for (int i = 0; i < 110; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        //dividing line

        donationUI.printText("\n\n\t\t\t\tBar chart of Number of Reception of various Item Type\n");
        donationUI.printText("Number of Reception");
        donationUI.printText("       ^");
        int y_axis = countNumberOfReceptionOfItemType(StockUI.getItemType(getMostAndLeastOfItemTypeReception("most", itemHistory)), itemHistory);
        for (int y = getY_axis(y_axis); y > 0; y--) {
            if (y % 5 == 0) {
                donationUI.sentence3(y);
            } else {
                donationUI.printTextWithoutNextLine("       | ");
            }
            for (int x = 1; x < 8; x++) {
                if (countNumberOfReceptionOfItemType(StockUI.getItemType(x), itemHistory) == y) {
                    donationUI.printTextWithoutNextLine("   +-----+   ");
                } else if (countNumberOfReceptionOfItemType(StockUI.getItemType(x), itemHistory) > y) {
                    donationUI.printTextWithoutNextLine("   |     |   ");
                } else {
                    donationUI.printTextWithoutNextLine("             ");
                }
            }
            donationUI.printText("");
        }
        donationUI.printTextWithoutNextLine("       +");
        for (int i = 0; i < 93; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("> Item Type");
        donationUI.printText("       0    Monetary     Clothing     Food and    Household   Educational   Electronic    Medical");
        donationUI.printText("                        and Apparel   Beverage      Items      Materials");
        donationUI.printText("\n\nThe most Number of Reception of Item Type > " + StockUI.getItemType(getMostAndLeastOfItemTypeReception("most", itemHistory)));
        donationUI.printText("The least Number of Reception of Item Type > " + StockUI.getItemType(getMostAndLeastOfItemTypeReception("least", itemHistory)));
        donationUI.printText("");

        //dividing line
        for (int i = 0; i < 110; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        //dividing line
        donationUI.printText("\n\n\t\t\t\tTop 3 Item Type of number of reception\n");
        SortedListSetInterface<Item> tempHistory = new SortedDoublyLinkedListSet<>();
        tempHistory.merge(itemHistory);
        String first = StockUI.getItemType(getTop3(tempHistory));
        String second = StockUI.getItemType(getTop3(tempHistory));
        String third = StockUI.getItemType(getTop3(tempHistory));
        donationUI.sentence4(first);
        donationUI.printText("\t\t                          +------------+               ");
        donationUI.printText("\t\t                          |    Top 1   | ");
        donationUI.sentence5(second, third);
        donationUI.printText("\t\t             +------------+            +------------+     ");
        donationUI.printText("\t\t             |   Top 2    |            |    Top 3   |     ");
        donationUI.printText("\t\t             |            |            |            |     ");
        donationUI.printText("");
        //dividing line
        for (int i = 0; i < 110; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        //dividing line

        donationUI.printText("\n\t\t\t\t\tEND OF THE REPORT");
        for (int i = 0; i < 110; i++) {
            donationUI.printTextWithoutNextLine("=");
        }
    }

    private void getAllItemHistory(SortedListSetInterface<Item> itemHistory, SortedListSetInterface<Donation> donationHistory) {
        Iterator<Donation> iterator = donationHistory.getIterator();
        do {
            Donation donation = iterator.next();
            itemHistory.merge(donation.getDonatedItemList());
        } while (iterator.hasNext());
    }

    private int countNumberOfReceptionOfItemType(String type, SortedListSetInterface<Item> itemList) {
        int count = 0;
        Iterator<Item> iterator = itemList.getIterator();
        do {
            Item item = iterator.next();
            if (item.getType().equalsIgnoreCase(type)) {
                count++;
            }
        } while (iterator.hasNext());
        return count;
    }

    private int getMostAndLeastOfItemTypeReception(String input, SortedListSetInterface<Item> itemList) {
        int index;
        if (input.equalsIgnoreCase("most")) {
            int most = countNumberOfReceptionOfItemType(StockUI.getItemType(1), itemList);
            index = 1;
            for (int i = 2; i < 8; i++) {
                int temp = countNumberOfReceptionOfItemType(StockUI.getItemType(i), itemList);
                if (temp > most) {
                    most = temp;
                    index = i;
                }
            }
            return index;
        } else {
            int least = countNumberOfReceptionOfItemType(StockUI.getItemType(1), itemList);
            index = 1;
            for (int i = 2; i < 8; i++) {
                int temp = countNumberOfReceptionOfItemType(StockUI.getItemType(i), itemList);
                if (temp < least) {
                    least = temp;
                    index = i;
                }
            }
            return index;
        }
    }

    private int getTop3(SortedListSetInterface<Item> itemList) {
        int index;
        int most = countNumberOfReceptionOfItemType(StockUI.getItemType(1), itemList);
        index = 1;
        for (int i = 2; i < 8; i++) {
            int temp = countNumberOfReceptionOfItemType(StockUI.getItemType(i), itemList);
            if (temp > most) {
                most = temp;
                index = i;
            }
        }
        removeAllItemForOneType(StockUI.getItemType(index), itemList);
        return index;
    }

    private void removeAllItemForOneType(String type, SortedListSetInterface<Item> itemList) {
        Iterator<Item> iterator = itemList.getIterator();
        do {
            Item item = iterator.next();
            if (item.getType().equalsIgnoreCase(type)) {
                itemList.remove(item);
            }
        } while (iterator.hasNext());
    }

    //donation status
    public void donationStatusSummary(SortedListSetInterface<Donation> donations) {
        CommonUse.getLogo();
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("=");
        }
        donationUI.printText("\n\n\t\t\t\tDONATION STATUS ANALYSIS REPORT");
        donationUI.printText("\t\t\t\t-------------------------------");
        donationUI.printText("\nGenerated at: " + getGeneratedReportDate());
        //dividing line
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        //dividing line

        donationUI.printText("\n\n\t\t     Table of the count and percentage of donations in each status\n");
        donationUI.printTextWithoutNextLine("\t\t+");
        for (int i = 0; i < 69; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("+");
        donationUI.sentence6();
        donationUI.printTextWithoutNextLine("\t\t+");
        for (int i = 0; i < 69; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("+");
        for (int index = 1; index < 5; index++) {
            String status = getStatus(index);
            int num = countDonationStatus(status, donations);
            double percentage = (double) num * 100 / (double) donations.getNumberOfEntries();
            donationUI.sentence7(index, status, num, percentage);
        }
        donationUI.printTextWithoutNextLine("\t\t+");
        for (int i = 0; i < 69; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("+");
        donationUI.printText("\nTotal number of Donation > " + donations.getNumberOfEntries());
        donationUI.printText("");

        //dividing line
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        //dividing line

        donationUI.printText("\n\n\t\t\t\tBar chart of All Donation Status\n");
        donationUI.printText("Number of Donation");
        donationUI.printText("       ^");
        int y_axis = countDonationStatus(getStatus(getMostAndLeastOfDonationStatus("most", donations)), donations);
        for (int y = getY_axis(y_axis); y > 0; y--) {
            if (y % 5 == 0) {
                donationUI.sentence8(y);
            } else {
                donationUI.printTextWithoutNextLine("       |   ");
            }
            for (int x = 1; x < 5; x++) {
                if (countDonationStatus(getStatus(x), donations) == y) {
                    donationUI.printTextWithoutNextLine("      +-----+      ");
                } else if (countDonationStatus(getStatus(x), donations) > y) {
                    donationUI.printTextWithoutNextLine("      |     |      ");
                } else {
                    donationUI.printTextWithoutNextLine("                   ");
                }
            }
            donationUI.printText("");
        }
        donationUI.printTextWithoutNextLine("       +");
        for (int i = 0; i < 80; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("> Status");
        donationUI.printText("       0         Pending           Processing        Distributing    Fully Distributed");
        donationUI.printText("\n\nThe most Donation Status > " + getStatus(getMostAndLeastOfDonationStatus("most", donations)));
        donationUI.printText("The least Donation Status > " + getStatus(getMostAndLeastOfDonationStatus("least", donations)));

        donationUI.printText("");
        //dividing line
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        //dividing line

        donationUI.printText("\n\t\t\t\t\tEND OF THE REPORT");
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("=");
        }
    }

    private String getStatus(int n) {
        switch (n) {
            case 1:
                return "Pending";
            case 2:
                return "Processing";
            case 3:
                return "Distributing";
            case 4:
                return "Fully Distributed";
            default:
                return null;
        }
    }

    private int countDonationStatus(String status, SortedListSetInterface<Donation> donations) {
        int count = 0;
        Iterator<Donation> iterator = donations.getIterator();
        do {
            Donation donation = iterator.next();
            if (donation.getStatus().equalsIgnoreCase(status)) {
                count++;
            }
        } while (iterator.hasNext());
        return count;
    }

    private int getMostAndLeastOfDonationStatus(String input, SortedListSetInterface<Donation> donations) {
        int index;
        if (input.equalsIgnoreCase("most")) {
            int most = countDonationStatus(getStatus(1), donations);
            index = 1;
            for (int i = 2; i < 5; i++) {
                int temp = countDonationStatus(getStatus(i), donations);
                if (temp > most) {
                    most = temp;
                    index = i;
                }
            }
            return index;
        } else {
            int least = countDonationStatus(getStatus(1), donations);
            index = 1;
            for (int i = 2; i < 5; i++) {
                int temp = countDonationStatus(getStatus(i), donations);
                if (temp < least) {
                    least = temp;
                    index = i;
                }
            }
            return index;
        }
    }

    private int getY_axis(int higher) {
        if (higher % 5 == 0) {
            return higher + 5;
        } else {
            while (higher % 5 != 0) {
                higher++;
            }
            return higher;
        }
    }

    private String getGeneratedReportDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, hh:mm a", Locale.ENGLISH);
        return now.format(formatter);
    }
}
