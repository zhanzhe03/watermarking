/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import ADT.SortedDoublyLinkedListSet;
import ADT.SortedListSetInterface;
import Boundary.DonorUI;
import Boundary.DonationUI;
import DAO.EntityInitializer;
import Entity.Date;
import Entity.Donation;
import Entity.Donor;
import Entity.Item;
import Utility.ClearScreen;
import Utility.CommonUse;
import Utility.MessageUI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Locale;

/**
 *
 * @author Yeak Shao Rong
 */
public class DonorMaintenance {
        private DonorUI donorUI = new DonorUI();
        private DonationUI donationUI = new DonationUI();
        
        public void donorManagement(EntityInitializer entityInitialize) {
        SortedListSetInterface<Donor> donors = entityInitialize.getDonors();
        SortedListSetInterface<Donor> updatedDonors;
        Donor foundDonor = null;
        int opt = 0;
        do {
            updatedDonors = checkAndUpdateStatus(donors);

                opt = validateNumberFormatInput(donorUI.getDonorMenu());


                switch (opt) {
                    case 1:
                        ClearScreen.clearJavaConsoleScreen();
                        ListAllDonor(updatedDonors);                      
                        break;
                    case 2:
                        ClearScreen.clearJavaConsoleScreen();
                        AddNewDonor(updatedDonors);
                        break;
                    case 3:
                        ClearScreen.clearJavaConsoleScreen();
                        RemoveDonor(updatedDonors);
                        break;
                    case 4:
                        ClearScreen.clearJavaConsoleScreen();
                        UpdateDonor(updatedDonors);
                        break;
                    case 5:
                        ClearScreen.clearJavaConsoleScreen();
                        SearchDonor(updatedDonors);
                        break;
                    case 6:
                        ClearScreen.clearJavaConsoleScreen();
                        FilterDonor(updatedDonors);
                        break;  
                    case 7:
                        ClearScreen.clearJavaConsoleScreen();
                        foundDonor = searchDonorID(updatedDonors);  
                        if(foundDonor != null)
                        listAllDonation(foundDonor.getDonationList());
                        break;                  
                    case 8:
                        ClearScreen.clearJavaConsoleScreen();
                        DonorReports(updatedDonors);
                        break;
                    case 9:
                        break;
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
                }

            
        } while (opt != 9);
    }

    private void ListAllDonor(SortedListSetInterface<Donor> donors) {
        donorUI.printText("All Donors List");
        donorUI.printDonorEnDash();
        donorUI.printDonorTitle();
        donorUI.printDonorEnDash();
        donorUI.printAllDonors(donors);
        donorUI.printDonorEnDash();
        donorUI.printNumberOfEntries(donors);
    }

    private void AddNewDonor(SortedListSetInterface<Donor> donors) {
        boolean contLoop = false;
        do{
       
        String donorId = generateDonorID(donors);

        donorUI.printText("[ Add New Donor ]");
        
        String category = setDonorCategory();

        String name = getName();
        
        String contactName = "";
        if(category.contains("Organization")){
            contactName = getContactName();
        }else{
            contactName = "-";
        }
        
        String contact = getContact();
        
        String email = getEmail();
        
        String address = getAddress();
        
        Date registedDate = getCurrentDate();
        Donor newDonor = new Donor(donorId, category,name, contactName, contact, email, address,registedDate);

        String YesNo = "";
        do{
        YesNo = donorUI.getConfirmation("add");
        }while(!YesNo.equalsIgnoreCase("y") && !YesNo.equalsIgnoreCase("n"));

        if (YesNo.equalsIgnoreCase("y")) {
            donors.add(newDonor);
            donorUI.printDonorEnDash();
            donorUI.printDonorTitle();
            donorUI.printDonorEnDash();
            donorUI.printAllDonors(donors);
            donorUI.printDonorEnDash();
            donorUI.printText("Donor ID : " + newDonor.getDonorId() + " has been added successfully.");

        } else {
            donorUI.printText("Add new donor cancelled.");
        }
        
        String yesNo = "";

        do{
        yesNo = donorUI.getAgainOrBack("add");
        if(yesNo.equalsIgnoreCase("y")){
            contLoop = true;
        }else if (yesNo.equalsIgnoreCase("n")){
            ClearScreen.clearJavaConsoleScreen();
            contLoop = false;
        }
        }while(!yesNo.equalsIgnoreCase("y")&& !yesNo.equalsIgnoreCase("n" ));
        
        }while(contLoop == true);
    }

    private void SearchDonor(SortedListSetInterface<Donor> donors) {
        
        Donor foundDonor = null;
        int opt = 0;

            do {

                opt = validateNumberFormatInput(donorUI.getDonorSearchMenu());
                switch (opt) {
                    case 1:
                        ClearScreen.clearJavaConsoleScreen();
                        foundDonor = searchDonorID(donors);
                        if (foundDonor != null) {
                            listAllDonation(foundDonor.getDonationList());
                        }

                        break;
                    case 2:
                        ClearScreen.clearJavaConsoleScreen();
                        foundDonor = searchDonorName(donors);
                        if (foundDonor != null) {
                            listAllDonation(foundDonor.getDonationList());
                        }
                        break;
                    case 3:
                        ClearScreen.clearJavaConsoleScreen();
                        foundDonor = searchContact(donors);
                        if (foundDonor != null) {
                            listAllDonation(foundDonor.getDonationList());
                        }
                        break;
                    case 4:
                        ClearScreen.clearJavaConsoleScreen();
                        break;
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
                }
            } while (opt != 4);
       
    }

    private void RemoveDonor(SortedListSetInterface<Donor> donors) {
      Iterator<Donor> iterator = donors.getIterator();

        int opt = 0;
        do {

                MessageUI.diplayEnDash();
                opt = validateNumberFormatInput(donorUI.getDonorDeleteMenu());
                MessageUI.diplayEnDash();
                switch (opt) {
                    case 1:
                        Donor foundDonor = searchDonorID(donors);
                        if (foundDonor != null) {
                            String YesNo = donorUI.getConfirmation("delete");

                            if (YesNo.equalsIgnoreCase("y")) {
                                donors.remove(foundDonor);
                                donorUI.printText("Donor ID : " + foundDonor.getDonorId() + " has been removed successfully.");

                            } else {
                                donorUI.printText("Removal cancelled.");
                            }

                        }
                        break;

                    case 2 :
                        removeAllBannedDonors(donors);
                        break;
                        
                    case 3: 
                        break;
    
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;              
                }

        }while(opt != 3);              
    }

    private void UpdateDonor(SortedListSetInterface<Donor> donors) {
 
        String newName;
        String newContactPerson;
        String newContact;
        String newEmail;
        String newAddress;
        boolean contLoop = false;
        do{
        Donor foundDonor = searchDonorID(donors);
        String YesNo = "";

        
        if (foundDonor != null) {
 
            int choose = validateNumberFormatInput(donorUI.getDonorUpdateMenu());          
            switch (choose) {
                case 1: 
                   
                    newName = getName();                 

                    YesNo = donorUI.getConfirmation("update");

                    if (YesNo.equalsIgnoreCase("y")) {
                        foundDonor.setName(newName);
                        updatedDonorInfo(foundDonor);
                    } else {
                        donorUI.printText("Update cancelled.");
                    }

                    break;

                case 2: 
                    
                    newContactPerson = getContactName();
                    
                    YesNo = donorUI.getConfirmation("update");
                    if (YesNo.equalsIgnoreCase("y")) {
                        foundDonor.setContactPerson(newContactPerson);
                        updatedDonorInfo(foundDonor);
                    } else {
                        donorUI.printText("Update cancelled.");
                    }
                    break;
                    
                case 3:
                    
                    newContact = getContact();

                    YesNo = donorUI.getConfirmation("update");
                    if (YesNo.equalsIgnoreCase("y")) {
                        foundDonor.setContact(newContact);
                        updatedDonorInfo(foundDonor);
                    } else {
                        donorUI.printText("Update cancelled.");
                    }
                    break;
                    
                case 4:
                   
                    newEmail = getEmail();
                          
                    YesNo = donorUI.getConfirmation("update");
                    if (YesNo.equalsIgnoreCase("y")) {
                        foundDonor.setEmail(newEmail);
                        updatedDonorInfo(foundDonor);
                    } else {
                        donorUI.printText("Update cancelled.");
                    }
                    break;
                    
                case 5:
                    
                    newAddress = getAddress();

                    YesNo = donorUI.getConfirmation("update");
                    if (YesNo.equalsIgnoreCase("y")) {
                        foundDonor.setAddress(newAddress);
                        updatedDonorInfo(foundDonor);
                    } else {
                        donorUI.printText("Update cancelled.");
                    }
                    break;
                    
                case 6:
                    String newCategory = setDonorCategory();
                    YesNo = donorUI.getConfirmation("update");
                    if (YesNo.equalsIgnoreCase("y")) {
                        if(newCategory.equals("Individual")){
                            foundDonor.setContactPerson("-");
                        }else if(newCategory.contains("Organization") && foundDonor.getCategory().equalsIgnoreCase("Individual")){
                            newContactPerson = getContactName();
                            foundDonor.setContactPerson(newContactPerson);
                        }
                        foundDonor.setCategory(newCategory);
                        updatedDonorInfo(foundDonor);
                    } else {
                        donorUI.printText("Update cancelled.");
                    }
                    break;

                case 7:
                    YesNo = donorUI.getConfirmation("update");
                    if (YesNo.equalsIgnoreCase("y")) {               
                        foundDonor.setStatus("Banned");
                        updatedDonorInfo(foundDonor);
                    } else {
                        donorUI.printText("Update cancelled.");
                    }                   
                    break;
                    
                case 8:
                    break;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    break;
            }

            } else {
                donorUI.printText("Donor are not found ! Please try again !");
            }

            String yesNo = "";

            do {
                yesNo = donorUI.getAgainOrBack("update");
                if (yesNo.equalsIgnoreCase("y")) {
                    contLoop = true;
                } else if (yesNo.equalsIgnoreCase("n")){
                    ClearScreen.clearJavaConsoleScreen();
                    contLoop = false;
                }
            } while (!yesNo.equalsIgnoreCase("y") && !yesNo.equalsIgnoreCase("n"));

        } while (contLoop == true);
        
    }

    private void FilterDonor(SortedListSetInterface<Donor> donors) {
        SortedListSetInterface<Donor> categorizedDonors = new SortedDoublyLinkedListSet<>();
        SortedListSetInterface<Donor> categorizedStatus = new SortedDoublyLinkedListSet<>();

        int opt;
        int choice;
        do {
            opt = validateNumberFormatInput(donorUI.getFilterCategoryOption());

            switch (opt) {
                case 1:
                    categorizedDonors = getCategorizedDonorSet(donors, "Individual");
                    break;
                case 2:
                    categorizedDonors = getCategorizedDonorSet(donors, "Organization");
                    break;
                case 3:
                    categorizedDonors = getCategorizedDonorSet(donors, "Public Organization");
                    break;
                case 4:
                    categorizedDonors = getCategorizedDonorSet(donors, "Private Organization");
                    break;
                case 5:
                    categorizedDonors = getCategorizedDonorSet(donors, "Government Organization");
                    break;
                case 6:
                    categorizedDonors = cloneDonorSet(donors); // Clone the original donors set
                    break;
                case 7:
                    return;
                default:
                    MessageUI.displayInvalidOptionMessage();
                    continue;
            }

            do {
                choice = validateNumberFormatInput(donorUI.getFilterStatusOption());

                switch (choice) {
                    case 1:
                        categorizedStatus = getCategorizedStatusSet(donors, "Active");
                        break;
                    case 2:
                        categorizedStatus = getCategorizedStatusSet(donors, "Inactive");
                        break;
                    case 3:
                        categorizedStatus = getCategorizedStatusSet(donors, "Prospect");
                        break;
                    case 4:
                        categorizedStatus = getCategorizedStatusSet(donors, "Banned");
                        break;
                    case 5:
                        categorizedStatus = cloneDonorSet(donors); // Clone the original donors set
                        break;
                    case 6:
                        return;
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        continue;
                }

                SortedListSetInterface<Donor> tempDonors = cloneDonorSet(categorizedDonors); // Clone before intersection
                tempDonors.intersect(categorizedStatus);

                if (!tempDonors.isEmpty()) {
                    donorUI.printText("Filter Result:");
                    donorUI.printDonorEnDash();
                    donorUI.printDonorTitle();
                    donorUI.printDonorEnDash();
                    donorUI.printText(tempDonors.toString());
                    donorUI.printDonorEnDash();
                    donorUI.printNumberOfEntries(tempDonors);
                } else {
                    donorUI.printText("No results found for the filter, please try again!");
                }

            } while (choice < 1 || choice > 6);

        } while (opt < 1 || opt > 7);
    }

    
    private void DonorReports(SortedListSetInterface<Donor> donors) {
        int opt = 0;

        
        do{

           opt = validateNumberFormatInput(donorUI.getReportOption());

        switch(opt){
            case 1:
                showReportforTopFiveHighestDonatedValue(donors, "Individual");
                break;
            case 2:
                showReportforTopFiveHighestDonatedValue(donors, "Organization");
                break;
            case 3:
                showReportforNumberOfDonorAndTotalDonatedValuePerCategory(donors);
                break;
            case 4:
                showNewRegisteredDonorReport(donors);
                break;
            case 5:
                showActiveDonorPercentageReport(donors);
                break;
            case 6:
                ClearScreen.clearJavaConsoleScreen();
                return;
           default:
                MessageUI.displayInvalidOptionMessage();
                continue;
        }
        
        }while(opt != 6);


    }
    
    private void showReportforTopFiveHighestDonatedValue(SortedListSetInterface<Donor> donors, String category) {
        SortedListSetInterface<Donor> selectedDonors = new SortedDoublyLinkedListSet<>();

        CommonUse.getLogo();
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("=");
        }
        donationUI.printText("\n\n\t\t TOP 5 DONOR WITH HIGHEST TOTAL DONATED VALUE");
        donationUI.printText("\t\t---------------------------------------------");
        donationUI.printText("\nGenerated at: " + getGeneratedReportDate());
        //dividing line
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("\n\n\n");
        
        Donor.setSortByCriteria(Donor.SortByCriteria.TOTALDONATEDAMT_INDESC);
        while (selectedDonors.getNumberOfEntries() < 5) {
            Donor highestDonor = null;
            double highestTotalDonatedAmount = 0;

            Iterator<Donor> donorIterator = donors.getIterator();
            donorIterator = donors.getIterator();

            while (donorIterator.hasNext()) {
                Donor donor = donorIterator.next();
                 double totalDonatedAmount = getTotalDonatedAmountforDonor(donor);
                 donor.setTotalDonatedAmount(totalDonatedAmount);
                if (donor.getCategory().contains(category) && !selectedDonors.contains(donor)) {
                   
                    if (totalDonatedAmount > highestTotalDonatedAmount) {
                        highestTotalDonatedAmount = totalDonatedAmount;
                        highestDonor = donor;
                    }
                }
            }

            if (highestDonor != null) {
                selectedDonors.add(highestDonor);
            } else {
                break;
            }
        }

        if (category.equals("Individual")) {
            donorUI.printEqualsDash();
            donorUI.printText(String.format("%-7s | %-10s | %-20s | %-20s | %-20s", "Rank", "Donor ID", "Donor Name", "Category", "Total Donation Value"));
            donorUI.printEqualsDash();
        } else {
            donorUI.printEqualsDash();
            donorUI.printText(String.format("%-7s | %-10s | %-40s | %-30s | %-20s", "Rank", "Donor ID", "Donor Name", "Category", "Total Donation Value"));
            donorUI.printEqualsDash();
        }

        int rank = 1;
        
        Iterator<Donor> selectedDonorIterator = selectedDonors.getIterator();
        while (selectedDonorIterator.hasNext()) {
            Donor donor = selectedDonorIterator.next();
            double totalDonatedAmount = donor.getTotalDonatedAmount();  

            if (category.equalsIgnoreCase("Individual")) {
                donorUI.printText(String.format("[Top %d] : %-10s | %-20s | %-20s | RM%-20.2f",
                        rank,
                        donor.getDonorId(),
                        donor.getName(),
                        donor.getCategory(),
                        totalDonatedAmount));
            } else {
                donorUI.printText(String.format("[Top %d] : %-10s | %-40s | %-30s | RM%-20.2f",
                        rank,
                        donor.getDonorId(),
                        donor.getName(),
                        donor.getCategory(),
                        totalDonatedAmount));
            }
            rank++;
        }

        donorUI.printEqualsDash();

        printReportFooter();
        Donor.setSortByCriteria(Donor.SortByCriteria.DONORID_INASC);
    }
    
    private void showReportforNumberOfDonorAndTotalDonatedValuePerCategory(SortedListSetInterface<Donor> donors) {
        
        
        String[] categories = {"Public Organization", "Private Organization", "Government Organization", "Individual"};

        CommonUse.getLogo();
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("=");
        }
        donationUI.printText("\n\n\t\tTOTAL DONATED VALUE & NUMBER OF DONORS PER CATEGORY REPORT");
        donationUI.printText("\t\t--------------------------------------------------------------");
        donationUI.printText("\nGenerated at: " + getGeneratedReportDate());
        //dividing line
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("\n\n\n");
        donorUI.printEqualsDash();
        donorUI.printText(String.format("%-40s | %-30s | %-30s", "Category", "Total Number of Donors", "Total Donated Amount"));
        donorUI.printEqualsDash();

        for (String category : categories) {
            int numberOfDonors = getNumberOfDonorPerCategory(donors, category);
            double totalDonationValue = getTotalDonationValuePerCategory(donors,category);
            donorUI.printText(String.format("%-40s | %-30d | %-30.2f", category, numberOfDonors, totalDonationValue));
        }

        donorUI.printEqualsDash();
        donorUI.printNumberOfEntries(donors);
        
        printReportFooter();
         
    }
    
    
    private int getNumberOfDonorPerCategory(SortedListSetInterface<Donor> donors, String category){
        
        SortedListSetInterface<Donor> selectedDonors = new SortedDoublyLinkedListSet<>();;
        Iterator<Donor> donorIterator = donors.getIterator();
        donorIterator = donors.getIterator();
        int totalNumberOfDonors = 0;
        while (donorIterator.hasNext()) {
                Donor donor = donorIterator.next();
                if (donor.getCategory().contains(category)) {
                    selectedDonors.add(donor);
                }
        }
        
        totalNumberOfDonors = selectedDonors.getNumberOfEntries();
        return totalNumberOfDonors;
    }
    
    public double getTotalDonatedAmountforDonor (Donor donor){
        double totalDonatedAmount = 0;
        Iterator<Donation> donationIterator = donor.getDonationList().getIterator();
        donationIterator = donor.getDonationList().getIterator();

        while (donationIterator.hasNext()) {
            Donation donation = donationIterator.next();
            Iterator<Item> itemIterator = donation.getDonatedItemList().getIterator();
            itemIterator = donation.getDonatedItemList().getIterator();

            while (itemIterator.hasNext()) {
                Item donatedItem = itemIterator.next();
                totalDonatedAmount += donatedItem.getTotalAmount();
            }
        }
      
        return totalDonatedAmount;
    }
    
    private double getTotalDonationValuePerCategory(SortedListSetInterface<Donor> donors, String category) {

        Iterator<Donor> donorIterator = donors.getIterator();
        donorIterator = donors.getIterator();
        double totalDonatedAmount = 0;

        while (donorIterator.hasNext()) {
            Donor donor = donorIterator.next();
            if (donor.getCategory().contains(category)) {

               totalDonatedAmount += getTotalDonatedAmountforDonor(donor);
            }
        }

        return totalDonatedAmount;
    }
    
    private void showActiveDonorPercentageReport(SortedListSetInterface<Donor> donors) {
        SortedListSetInterface<Donor> categorizedStatusDonors = getCategorizedStatusSet(donors, "Active");
        SortedListSetInterface<Donor> categorizedDonors;
        
        String[] categories = {"Public Organization", "Private Organization", "Government Organization", "Individual"};

        CommonUse.getLogo();
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("=");
        }
        donationUI.printText("\n\n\t\t PERCENTAGES OF ACTIVE DONORS REPORT");
        donationUI.printText("\t\t----------------------------------------");
        donationUI.printText("\nGenerated at: " + getGeneratedReportDate());
        //dividing line
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("\n\n\n");
        donorUI.printEqualsDash();
        donorUI.printText(String.format("%-30s | %-20s | %-20s | %-15s", "Category", "Active Donors", "Total Donors", "Active  ( % )"));
        donorUI.printEqualsDash();

        // Calculate for each category
        for (String category : categories) {
            int totalDonors = getNumberOfDonorPerCategory(donors, category);
            double activePercentage = calculateActiveDonorPercentagePerCategory(donors, category);
            categorizedDonors = getCategorizedDonorSet(donors,category);
            categorizedDonors.intersect(categorizedStatusDonors);
            int activeDonors = categorizedDonors.getNumberOfEntries();

            donorUI.printText(String.format("%-30s | %-20d | %-20d | %-10.2f%%", category, activeDonors, totalDonors, activePercentage));
        }

        // Calculate overall
        int totalDonorsOverall = donors.getNumberOfEntries();
        double activePercentageOverall = calculateActiveDonorPercentage(donors);
        int activeDonorsOverall = categorizedStatusDonors.getNumberOfEntries();

        donorUI.printEqualsDash();
        donorUI.printText(String.format("%-30s | %-20d | %-20d | %-10.2f%%", "All", activeDonorsOverall, totalDonorsOverall, activePercentageOverall));
        donorUI.printEqualsDash();
        printReportFooter();
    }

    
    private double calculateActiveDonorPercentage(SortedListSetInterface<Donor> donors) {

        int activeDonors = 0;
        int totalDonors = donors.getNumberOfEntries();

        Iterator<Donor> donorIterator = donors.getIterator();

        while (donorIterator.hasNext()) {
            Donor donor = donorIterator.next();

            if (donor.getStatus().equalsIgnoreCase("Active")) {
                activeDonors++;
            }
        }

        // Calculate percentage
        return (totalDonors > 0) ? ((double) activeDonors / totalDonors) * 100 : 0;
    }
    
    private double calculateActiveDonorPercentagePerCategory(SortedListSetInterface<Donor> donors, String category) {

        int activeDonors = 0;
        int categorizedDonors = 0;

        Iterator<Donor> donorIterator = donors.getIterator();

        while (donorIterator.hasNext()) {
            Donor donor = donorIterator.next();

            if(donor.getCategory().equals(category)){
                categorizedDonors++;
                if (donor.getStatus().equalsIgnoreCase("Active")) {
                activeDonors++;
              }
            }
            
        }

        return (categorizedDonors > 0) ? ((double) activeDonors / categorizedDonors) * 100 : 0;

    }

    private void showNewRegisteredDonorReport(SortedListSetInterface<Donor> donors) {
        SortedListSetInterface<Donor> newDonors = getNumberOfNewRegisteredDonorWithinMonth(donors);

        if(!newDonors.isEmpty()){
        CommonUse.getLogo();
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("=");
        }
        donationUI.printText("\n\n\t\tNEW REGISTERED DONOR IN RECENT MONTH REPORT");
        donationUI.printText("\t\t-----------------------------------------------");
        donationUI.printText("\nGenerated at: " + getGeneratedReportDate());
        //dividing line
        for (int i = 0; i < 100; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("\n\n\n");
        donorUI.printEqualsDash();
        donorUI.printText(String.format("%-10s | %-30s | %-30s | %-20s | %-10s", "Donor ID", "Name", "Category", "Registered Date", "Status"));
        donorUI.printEqualsDash();

        Iterator<Donor> donorIterator = newDonors.getIterator();
        while (donorIterator.hasNext()) {
            Donor donor = donorIterator.next();
            donorUI.printText(String.format("%-10s | %-30s | %-30s | %-20s | %-10s",
                    donor.getDonorId(),
                    donor.getName(),
                    donor.getCategory(),
                    donor.getRegisteredDate().toString(), 
                    donor.getStatus()));
        }

        donorUI.printEqualsDash();
        donorUI.printNumberOfEntries(newDonors);
        printReportFooter();
        }else{
            donorUI.printText("No new registered donor in this month !");
        }
    }
    private SortedListSetInterface<Donor> getNumberOfNewRegisteredDonorWithinMonth(SortedListSetInterface<Donor> donors){
        SortedListSetInterface<Donor> selectedDonors = new SortedDoublyLinkedListSet<>();;
        Iterator<Donor> donorIterator = donors.getIterator();
        donorIterator = donors.getIterator();

        while (donorIterator.hasNext()) {
                Donor donor = donorIterator.next();
                if (donor.getRegisteredDate().withinPassMonth(getCurrentDate())) {
                    selectedDonors.add(donor);
                }
        }
        
        return selectedDonors;
    }
    
    private String getContact(){
        String contact = "";
        boolean correct = false;
        do {
            contact = donorUI.getDonorContact();
            String lines[] = contact.split("-");

            if (contact.length() > 12 || !contact.startsWith("0") || !contact.matches("[\\d-]+")) {
                MessageUI.displayInvalidContactMessage();  // Display an error message if invalid       
            } else if (lines[0].length() != 3) {
                MessageUI.displayInvalidContactFormatMessage();
            }else{
                correct = true;
            }

        } while (correct == false);
        return contact;
    }
    
    private String getEmail(){
     String email = "";
        do{
             email = donorUI.getDonorEmail();
            if(!email.contains("@"))
                MessageUI.displayInvalidEmailMessage();
        }while(!email.contains("@"));
        return email;
    }
    
    private Donor searchDonorID(SortedListSetInterface<Donor> donors){
        Iterator<Donor> iterator = donors.getIterator();
        Donor foundDonor = null;
        String inputId = donorUI.getDonorID( );
        iterator = donors.getIterator();
        while (iterator.hasNext()) {
            Donor donor = iterator.next();
            if (donor.getDonorId().equalsIgnoreCase(inputId)) {
                
                foundDonor = donor;
                donorUI.printText("Search Result :");
                donorUI.printDonorEnDash();
                donorUI.printDonorTitle();
                donorUI.printDonorEnDash();
                donorUI.printText(donor.toString());
                donorUI.printDonorEnDash(); 
                break;
            }
        }
        if (foundDonor == null) {
            donorUI.printText("\n\nNo results found for " + inputId + "\n\n");
        }

        return foundDonor;
    }
    
    private Donor searchDonorName(SortedListSetInterface<Donor> donors){
        Iterator<Donor> iterator = donors.getIterator();

        String inputName = donorUI.getDonorName();
        Donor foundDonor = null;
        iterator = donors.getIterator();
        while (iterator.hasNext()) {
            Donor donor = iterator.next();
            if (donor.getName().contains(inputName)) {

                foundDonor = donor;
                donorUI.printText("Search Result : ");
                donorUI.printDonorEnDash();
                donorUI.printDonorTitle();
                donorUI.printDonorEnDash(); 
                donorUI.printText(donor.toString());
                donorUI.printDonorEnDash(); 
                break;
            }
        }
        if (foundDonor == null) {
            donorUI.printText("\n\nNo results found for " + inputName + "\n\n");
        }
        return foundDonor;
    }
   
   private Donor searchContact(SortedListSetInterface<Donor> donors){
       Iterator<Donor> iterator = donors.getIterator();
       String inputContact = donorUI.getDonorContact();
       Donor foundDonor = null;
       iterator = donors.getIterator();
       while (iterator.hasNext()) {
           Donor donor = iterator.next();
           if (donor.getContact().equals(inputContact)) {

               foundDonor = donor;
               donorUI.printText("Search Result : ");
               donorUI.printDonorEnDash();
               donorUI.printDonorTitle();
               donorUI.printDonorEnDash();              
               donorUI.printText(donor.toString());
               donorUI.printDonorEnDash(); 
               break;
           }
       }
       if (foundDonor == null) {
           donorUI.printText("\n\nNo results found for " + inputContact + "\n\n");
       }
       return foundDonor;
   }
   
   private String generateDonorID(SortedListSetInterface<Donor> donors){
       String lastId = "DR001"; 
        if (!donors.isEmpty()) {
            Donor lastDonor = donors.getLastEntries();
            lastId = lastDonor.getDonorId(); 
        }
        int lastNumericPart = Integer.parseInt(lastId.substring(2));
        int nextNumericPart = lastNumericPart + 1; 
        String donorId = String.format("DR%03d", nextNumericPart);
       return donorId;
   }
    
    private void removeAllBannedDonors(SortedListSetInterface<Donor> donors) {
        Iterator<Donor> iterator = donors.getIterator();
        SortedListSetInterface<Donor> foundDonors = new SortedDoublyLinkedListSet<>();

        while (iterator.hasNext()) {
            Donor donor = iterator.next();
            if (donor.getStatus().equals("Banned")) {
                foundDonors.add(donor);
            }
        }

        if (!foundDonors.isEmpty()) {
            donorUI.printText("Search Result:");
            donorUI.printDonorEnDash();
            donorUI.printDonorTitle();
            donorUI.printDonorEnDash();
            donorUI.printText(foundDonors.toString());
            donorUI.printDonorEnDash();
            donorUI.printNumberOfEntries(foundDonors);

            String YesNo = donorUI.getConfirmation("remove");
            if (YesNo.equalsIgnoreCase("y")) {
                Iterator<Donor> foundIterator = foundDonors.getIterator();
                while (foundIterator.hasNext()) {
                    Donor donor = foundIterator.next();
                    donors.remove(donor);
                }
                donorUI.printText("All banned donors are removed.");
            } else {
                donorUI.printText("Update cancelled.");
            }
        } else {
            donorUI.printText("\nNo results found for banned Donors.\n");
        }
    }

    private void updatedDonorInfo(Donor foundDonor) {
        donorUI.printText("After Update :");
        donorUI.printDonorTitle();
        donorUI.printDonorEnDash();
        donorUI.printText(foundDonor.toString());
        donorUI.printDonorEnDash();
    }

    private String setDonorCategory() {
        int inputType = 0;
        int inputCategory = 0;
        String category = "";

        do {
                inputType = validateNumberFormatInput(donorUI.getDonorType());

                if (inputType == 1) {
                    category = "Individual";
                } else if (inputType == 2) {
                    do {

                            inputCategory = validateNumberFormatInput(donorUI.getOrganizationCategory());

                            switch (inputCategory) {
                                case 1:
                                    category = "Public Organization";
                                    break;
                                case 2:
                                    category = "Private Organization";
                                    break;
                                case 3:
                                    category = "Government Organization";
                                    break;
                                default:
                                    MessageUI.displayInvalidOptionMessage();
                                    break;
                            }
                       
                    } while (inputCategory > 3 || inputCategory < 1);
                }        

        } while (inputType > 2 || inputType < 1);

        return category;
    }
   
   private SortedListSetInterface<Donor>  getCategorizedDonorSet(SortedListSetInterface<Donor> donors, String category){
       Iterator<Donor> iterator = donors.getIterator();
        SortedListSetInterface<Donor> categorizedDonors = new SortedDoublyLinkedListSet<>();

       iterator = donors.getIterator();
       while (iterator.hasNext()) {
           Donor donor = iterator.next();
           if (donor.getCategory().contains(category)) {
               categorizedDonors.add(donor);             
           }
       }
       return categorizedDonors;
   }
   
   private SortedListSetInterface<Donor>  getCategorizedStatusSet(SortedListSetInterface<Donor> donors, String status){
       Iterator<Donor> iterator = donors.getIterator();
        SortedListSetInterface<Donor> categorizedDonors = new SortedDoublyLinkedListSet<>();

       iterator = donors.getIterator();
       while (iterator.hasNext()) {
           Donor donor = iterator.next();
           if (donor.getStatus().equalsIgnoreCase(status)) {
               categorizedDonors.add(donor);             
           }
       }
       return categorizedDonors;
   }
   
    private SortedListSetInterface<Donor> cloneDonorSet(SortedListSetInterface<Donor> originalSet) {
        SortedListSetInterface<Donor> clonedSet = new SortedDoublyLinkedListSet<>();
        Iterator<Donor> iterator = originalSet.getIterator();

        while (iterator.hasNext()) {
            Donor donor = iterator.next();
                clonedSet.add(donor.clone()); 
        }

        return clonedSet;
    }
      
   private void listAllDonation(SortedListSetInterface<Donation> donations) {
        donorUI.printText("Donation History : ");
        donationUI.printDonationEnDash();
        donationUI.printDonationTitle();
        donationUI.printDonationEnDash();
        donationUI.printAllDonations(donations);
        donationUI.printDonationEnDash();
        donationUI.printTotalDonation(donations);
        
    }
       
   private Date getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        int localDay = localDate.getDayOfMonth();
        int localMonth = localDate.getMonthValue();
        int localYear = localDate.getYear();
        return new Date(localDay, localMonth, localYear);
    }
   
   private  SortedListSetInterface<Donor> checkAndUpdateStatus(SortedListSetInterface<Donor> donors) {
        Iterator<Donor> donorIterator = donors.getIterator();
        Date currentDate = getCurrentDate();

        while (donorIterator.hasNext()) {
            Donor donor = donorIterator.next();

            // Find the last donation in the donor's donation list
            if (!donor.getDonationList().isEmpty()) {
                Donation lastDonation = donor.getDonationList().getLastEntries();
                Date lastDonationDate = lastDonation.getDonationDate();

                if (lastDonationDate.withinPassWeek(currentDate) && (donor.getStatus().equals("Inactive") ||donor.getStatus().equals("Prospect") )) {
                    donor.setStatus("Active");
                } else if (lastDonationDate.moreThanThreeMonthsAgo(currentDate) &&donor.getStatus() .equals("Active")) {
                    donor.setStatus("Inactive");
                }
            }
        }
        
        return donors;
    }
   
   private String getAddress(){
        String address = "";
        do{
        address = donorUI.getDonorAddress();
         if (address.length() > 70) {
                MessageUI.displayInvalidAddressMessage();  // Display an error message if invalid 
            }
        }while(address.length() > 70);
        return address;
   }
   
   private String getName(){
        String name = "";
        do{
        name = donorUI.getDonorName();
         if (name.length() > 40) {
                MessageUI.displayInvalidDonorNameMessage();  // Display an error message if invalid 
            }
        }while(name.length() > 40);
        return name;
   }
   
   private String getContactName(){
        String contactName = "";
        do{
        contactName = donorUI.getDonorContactPerson();
         if (contactName.length() > 30) {
                MessageUI.displayInvalidDonorContactNameMessage();  // Display an error message if invalid 
            }
        }while(contactName.length() > 30);
        return contactName;
   }
   
   private String getGeneratedReportDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, hh:mm a", Locale.ENGLISH);
        return now.format(formatter);
    }
   
   private int validateNumberFormatInput(String input) {
        int num;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return 0;
        }
        return num;
    }
   
   private void printReportFooter(){
       // dividing line
        donationUI.printText("\n\n\n");
        for (int i = 0; i < 110; i++) {
            donationUI.printTextWithoutNextLine("-");
        }
        donationUI.printText("\n\t\t\tEND OF THE REPORT");
        for (int i = 0; i < 110; i++) {
            donationUI.printTextWithoutNextLine("=");
        }
   }
}
