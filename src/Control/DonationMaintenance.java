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

        int opt = 0;
        do {
            try {
                opt = Integer.parseInt(donationUI.getDonationMenu());

                switch (opt) {
                    case 1:
                        ClearScreen.clearJavaConsoleScreen();
                        //ListAllDonation(donations);
                        break;
                    case 2:
                        ClearScreen.clearJavaConsoleScreen();
                        //AddNewDonation(donations, donors);
                        break;
                    case 3:
                        ClearScreen.clearJavaConsoleScreen();
                        //SearchDonation(donations, donors);
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

//    public void ListAllDonation(SortedListSetInterface<Donation> donations) {
//        donationUI.printText("All Donation Records");
//        MessageUI.diplayEnDash();
//        donationUI.printDonationTitle();
//        MessageUI.diplayEnDash();
//        donationUI.printAllDonations(donations);
//        MessageUI.diplayEnDash();
//        donationUI.printNumberOfEntries(donations);
//    }
//
//    public void AddNewDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Donor> donors) {
//        int localDay = donationUI.getLocalDate().getDayOfMonth();
//        int localMonth = donationUI.getLocalDate().getMonthValue();
//        int localYear = donationUI.getLocalDate().getYear();
//        Date donatedDate = new Date(localDay, localMonth, localYear);
//
//        int lastDonationId = Integer.parseInt(donations.getLastEntries().getDonationId().substring(1)) + 1;
//        String newId = "D" + String.format("%03d", lastDonationId);
//
//        Donation newDonation = new Donation(newId, donatedDate);
//
//        donationUI.printText("Add New Donation");
//        int n = 1;
//        double cashTotalAmount = 0;
//        do {
//            String itemId = "I" + String.format("%03d", n);
//            String type = donationUI.getInputString("Type : ");
//            try {
//                if (type.equalsIgnoreCase("Cash")) {
//                    double amount = Double.parseDouble(donationUI.getInputString("Amount : "));
//                    cashTotalAmount += amount;
//                } else {
//                    Item item;
//                    int quantity = Integer.parseInt(donationUI.getInputString("Quantity : "));
//                    double valuePerItem = Double.parseDouble(donationUI.getInputString("Value Per Item : "));
//                    if (type.equalsIgnoreCase("Food") || type.equalsIgnoreCase("Water")) {
//                        Date expiryDate = new Date(0, 0, 0);
//                        item = new Item(itemId, type, quantity, valuePerItem, expiryDate);
//                    } else {
//                        item = new Item(itemId, type, quantity, valuePerItem);
//                    }
//                    n++;
//                    newDonation.assignItems(item);
//                }
//            } catch (NumberFormatException ex) {
//                MessageUI.displayInvalidIntegerMessage();
//            }
//        } while (donationUI.yesNoOption("Keep Donating") == 'Y');
//
//        if (cashTotalAmount != 0) {
//            String id = "I" + String.format("%03d", n);
//            Item cashItem = new Item(id, "Cash", cashTotalAmount);
//            newDonation.assignItems(cashItem);
//        }
//
//        if (newDonation.isIsAssigned()) {
//            MessageUI.diplayEnDash();
//            donationUI.printDonationTitle();
//            MessageUI.diplayEnDash();
//            donationUI.printOneDonation(newDonation);
//            MessageUI.diplayEnDash();
//
//            if (donationUI.yesNoOption("Donation Confirmation") == 'Y') {
//                String contact = donationUI.getInputString("Contact Number :");
//                Iterator<Donor> iterator = donors.getIterator();
//                do {
//                    Donor donor = iterator.next();
//                    if (donor.getContact().equals(contact)) {
//                        newDonation.setDonor(donor);
//                        break;
//                    }
//                } while (iterator.hasNext());
//
////                if (newDonation.getDonor() == null) {
////                    int lastDonorId = Integer.parseInt(donors.getLastEntries().getDonorId().substring(2)) + 1;
////                    String newDonorId = "DR" + String.format("%03d", lastDonorId);
////                    int opt = 0;
////                    do {
////                        try {
////                            opt = Integer.parseInt(donationUI.getRegisteredMenu());
////                            switch (opt) {
////                                case 1:
////                                    String name = donationUI.getInputString("Name : ");
////                                    String email = donationUI.getInputString("Email : ");
////                                    newDonor = new Donor(newDonorId, name, email, contact);
////                                    newDonation.setDonor(newDonor);
////                                    donors.add(newDonor);
////                                    break;
////                                case 2:
////                                    Donor anonymousDonor = new Donor(newDonorId, "Anonymous", "Anonymous", contact);
////                                    newDonation.setDonor(anonymousDonor);
////                                    donors.add(anonymousDonor);
////                                    break;
////                                default:
////                                    MessageUI.displayInvalidOptionMessage();
////                                    break;
////                            }
////                        } catch (NumberFormatException ex) {
////                            MessageUI.displayInvalidIntegerMessage();
////                        }
////                    } while (opt < 1 || opt > 2);
////                }
//                donations.add(newDonation);
//                MessageUI.displaySuccessfulMessage();
//            } else {
//                MessageUI.displayUnsuccessfulMessage();
//            }
//        } else {
//            MessageUI.displayUnsuccessfulMessage();
//        }
//    }
//
//    public void SearchDonation(SortedListSetInterface<Donation> donations, SortedListSetInterface<Donor> donors) {
//        donationUI.printText("Searh a donation details (can amend or remove)");
//        String id = donationUI.getInputString("Donation ID : ");
//        int n = 0;
//        Iterator<Donation> iterator = donations.getIterator();
//        do {
//            Donation donation = iterator.next();
//            if (donation.getDonationId().equalsIgnoreCase(id)) {
////                donationUI.printDonationTitle();
////                MessageUI.diplayEnDash();
////                donationUI.printOneDonation(donation);
////                donationUI.printDonorMade(donation);
//
//                int opt = 0;
//                boolean isRemoved = false;
//                do {
//                    MessageUI.diplayEnDash();
//                    donationUI.printDonationTitle();
//                    MessageUI.diplayEnDash();
//                    donationUI.printOneDonation(donation);
//                    donationUI.printDonorMade(donation);
//                    try {
//                        opt = Integer.parseInt(donationUI.getAmendRemoveMenu());
//
//                        switch (opt) {
//                            case 1:
//                                String date;
//                                int day = 00;
//                                int month = 00;
//                                int year = 0000;
//                                boolean isValid = false;
//                                do {
//                                    date = donationUI.getInputString("New Donation Date (dd-mm-yyyy): ");
//                                    if (!dateFormat(date)) {
//                                        MessageUI.displayInvalidDateFormatMessage();
//                                    } else {
//                                        day = Integer.parseInt(date.substring(0, 2));
//                                        month = Integer.parseInt(date.substring(3, 5));
//                                        year = Integer.parseInt(date.substring(6));
//                                        if (!dateValid(day, month, year)) {
//                                            MessageUI.displayInvalidDateMessage();
//                                        } else {
//                                            isValid = true;
//                                        }
//                                    }
//                                } while (!isValid);
//                                if (donationUI.yesNoOption("Donation date amended confirmation") == 'Y') {
//                                    donation.setDonationDate(new Date(day, month, year));
//                                    MessageUI.displaySuccessfulMessage();
//                                } else {
//                                    MessageUI.displayUnsuccessfulMessage();
//                                }
//                                break;
//                            case 2:
//                                break;
//                            case 3:
//                                if (donationUI.yesNoOption("Donation removed confirmation") == 'Y') {
//                                    donations.remove(donation);
//                                    MessageUI.displaySuccessfulMessage();
//                                    isRemoved = true;
//                                } else {
//                                    MessageUI.displayUnsuccessfulMessage();
//                                }
//                                break;
//                            case 9:
//                                break;
//                            default:
//                                MessageUI.displayInvalidOptionMessage();
//                                break;
//                        }
//
//                    } catch (NumberFormatException ex) {
//                        MessageUI.displayInvalidIntegerMessage();
//                    }
//
//                } while (opt != 9 && !isRemoved);
//
//                break;
//            } else {
//                n++;
//            }
//        } while (iterator.hasNext());
//
//        if (donations.getNumberOfEntries() == n) {
//            donationUI.printText("Donation ID " + id + " Not Found !");
//        }
//    }
//
//    public boolean dateFormat(String date) {
//        String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(date);
//        return matcher.matches();
//    }
//
//    public boolean dateValid(int day, int month, int year) {
//        int localDay = donationUI.getLocalDate().getDayOfMonth();
//        int localMonth = donationUI.getLocalDate().getMonthValue();
//        int localYear = donationUI.getLocalDate().getYear();
//        if (year < 2000 || year > localYear) {
//            return false;
//        } else if (year <= localYear && month > localMonth) {
//            return false;
//        } else if (year <= localYear && month <= localMonth && day > localDay) {
//            return false;
//        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31)) {
//            return false;
//        } else if (month == 2 && day > 29 && (year % 4 == 0)) {
//            return false;
//        } else if (month == 2 && day > 28 && (year % 4 != 0)) {
//            return false;
//        }
//        return true;
//
//    }
}
