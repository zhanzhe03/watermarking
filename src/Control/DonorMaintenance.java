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
import Utility.ClearScreen;
import Utility.MessageUI;
import java.time.LocalDate;
import java.util.Iterator;

/**
 *
 * @author LENOVO
 */
public class DonorMaintenance {
        private DonorUI donorUI = new DonorUI();
        private DonationUI donationUI = new DonationUI();
        
        public void donorManagement(EntityInitializer entityInitialize) {
        SortedListSetInterface<Donor> donors = entityInitialize.getDonors();
        SortedListSetInterface<Donation> donationHistory = entityInitialize.getDonationsHistory();
        int opt = 0;
        do {
            checkAndUpdateStatus(donors);
            try {
                opt = donorUI.getDonorMenu();

                switch (opt) {
                    case 1:
                        ClearScreen.clearJavaConsoleScreen();
                        ListAllDonor(donors);                      
                        break;
                    case 2:
                        ClearScreen.clearJavaConsoleScreen();
                        AddNewDonor(donors);
                        break;
                    case 3:
                        ClearScreen.clearJavaConsoleScreen();
                        RemoveDonor(donors);
                        break;
                    case 4:
                        ClearScreen.clearJavaConsoleScreen();
                        UpdateDonor(donors);
                        break;
                    case 5:
                        ClearScreen.clearJavaConsoleScreen();
                        SearchDonor(donors);
                        break;
                    case 6:
                        ClearScreen.clearJavaConsoleScreen();
                        FilterDonor(donors);
                        break;  
                    case 7:
                        ClearScreen.clearJavaConsoleScreen();
                        listAllDonation(donationHistory);
                        break;                  
                    case 8:
                        ClearScreen.clearJavaConsoleScreen();
                        DonorReports(donors);
                        break;
                    case 9:
                        break;
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
                }

            } catch (NumberFormatException ex) {
                MessageUI.displayInvalidOptionMessage();
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
       
        String donorId = generateDonorID(donors);

        donorUI.printText("[ Add New Donor ]");
        
        String category = setDonorCategory();

        String name = donorUI.getDonorName();
        
        String contactName = "-";
        if(category.contains("Organisation")){
            contactName = donorUI.getDonorContactPerson();
        }
        
        String contact = getContact();
        
        String email = getEmail();
        
        String address = donorUI.getDonorAddress();
        
        Date registedDate = getCurrentDate();
        Donor newDonor = new Donor(donorId, category,name, contactName, contact, email, address,registedDate);

        String YesNo = donorUI.getConfirmation("add");

        if (YesNo.equalsIgnoreCase("y")) {
            donors.add(newDonor);
            donorUI.printDonorTitle();
            donorUI.printAllDonors(donors);
            donorUI.printText("Donor ID : " + newDonor.getDonorId() + " has been added successfully.");

        } else {
            donorUI.printText("Add new donor cancelled.");
        }
        
    }

    private void SearchDonor(SortedListSetInterface<Donor> donors) {
        
        Donor foundDonor = null;
        int opt = 0;
        do {
            try {
                MessageUI.diplayEnDash();
                opt = donorUI.getDonorSearchMenu();
                MessageUI.diplayEnDash();
                switch (opt) {
                    case 1:
                        ClearScreen.clearJavaConsoleScreen();
                        foundDonor = searchDonorID(donors);  
                        if(foundDonor != null)
                        listAllDonation(foundDonor.getDonationList());
             
                        break;
                    case 2:
                        ClearScreen.clearJavaConsoleScreen();
                        foundDonor = searchDonorName(donors);
                        if(foundDonor != null)
                        listAllDonation(foundDonor.getDonationList());
                        break;
                    case 3:
                        ClearScreen.clearJavaConsoleScreen();
                        foundDonor = searchContact(donors);
                        if(foundDonor != null)
                        listAllDonation(foundDonor.getDonationList());
                        break;
                    case 4: 
                        ClearScreen.clearJavaConsoleScreen();
                        break;
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
                }
            }catch(NumberFormatException ex){
                MessageUI.displayInvalidIntegerMessage();
            }
        }while(opt != 4);
    }

    private void RemoveDonor(SortedListSetInterface<Donor> donors) {
      Iterator<Donor> iterator = donors.getIterator();

        int opt = 0;
        do {
            try {
                MessageUI.diplayEnDash();
                opt = donorUI.getDonorDeleteMenu();
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
            }catch(NumberFormatException ex){
                MessageUI.displayInvalidOptionMessage();
            }
        }while(opt != 3);              
    }

    private void UpdateDonor(SortedListSetInterface<Donor> donors) {
 
        String newName;
        String newContactPerson;
        String newContact;
        String newEmail;
        String newAddress;
        String newStatus = "";
        int opt = 0;

        
        Donor foundDonor = searchDonorID(donors);
        String YesNo = "";

        
        if (foundDonor != null) {
            try{
            int choose = donorUI.getDonorUpdateMenu();          
            switch (choose) {
                case 1: 
                   
                    newName = donorUI.getDonorName();                 

                    YesNo = donorUI.getConfirmation("update");

                    if (YesNo.equalsIgnoreCase("y")) {
                        foundDonor.setName(newName);
                        updatedDonorInfo(foundDonor);
                    } else {
                        donorUI.printText("Update cancelled.");
                    }

                    break;

                case 2: 
                    
                    newContactPerson = donorUI.getDonorContactPerson();
                    
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
                    
                    newAddress = donorUI.getDonorAddress();

                    YesNo = donorUI.getConfirmation("update");
                    if (YesNo.equalsIgnoreCase("y")) {
                        foundDonor.setAddress(newAddress);
                        updatedDonorInfo(foundDonor);
                    } else {
                        donorUI.printText("Update cancelled.");
                    }
                    break;
                    
                case 6:
                    newStatus = setDonorStatus();
                    
                    YesNo = donorUI.getConfirmation("update");
                    if (YesNo.equalsIgnoreCase("y")) {
                        foundDonor.setStatus(newStatus);
                        updatedDonorInfo(foundDonor);
                    } else {
                        donorUI.printText("Update cancelled.");
                    }
                    break;

                case 7:
                    String newCategory = setDonorCategory();
                    YesNo = donorUI.getConfirmation("update");
                    if (YesNo.equalsIgnoreCase("y")) {
                        foundDonor.setCategory(newCategory);
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
            } catch(NumberFormatException ex){
                MessageUI.displayInvalidOptionMessage();
            }
        }

    }

    private void FilterDonor(SortedListSetInterface<Donor> donors){
        SortedListSetInterface<Donor> categorizedDonors = new SortedDoublyLinkedListSet<>();
        SortedListSetInterface<Donor> categorizedStatus = new SortedDoublyLinkedListSet<>();

        int opt = 0;
        int choice = 0;
        do{
        opt = donorUI.getFilterCategoryOption();
        
        switch(opt){
            case 1:
                categorizedDonors = getCategorizedDonorSet(donors,"Individual");
                break;
            case 2:
                categorizedDonors = getCategorizedDonorSet(donors,"Organisation");
                break;
            case 3:
                categorizedDonors = getCategorizedDonorSet(donors,"Public Organisation");
                break;
            case 4:
                categorizedDonors = getCategorizedDonorSet(donors,"Private Organisation");
                break;
            case 5:
                categorizedDonors = getCategorizedDonorSet(donors,"Government Organisation");
                break;
            case 6:
                categorizedDonors = donors;
                break;
            case 7:
                return;
            default:
                MessageUI.displayInvalidOptionMessage();
                continue;                
        }
        
        do{
            choice = donorUI.getFilterStatusOption();
            switch(choice){
                case 1: 
                categorizedStatus = getCategorizedStatusSet(donors,"Active");
                break;
            case 2:
                categorizedStatus = getCategorizedStatusSet(donors,"Inactive");
                break;
            case 3:
                categorizedStatus = getCategorizedStatusSet(donors,"Prospect");
                break;
            case 4:
                categorizedStatus = getCategorizedStatusSet(donors,"Banned");
                break;
            case 5:
                categorizedStatus = donors;
                break;
            case 6:
                return;
            default:
                MessageUI.displayInvalidOptionMessage();
                continue; 
                    
            }
            
            categorizedDonors.intersect(categorizedStatus);
            donorUI.printText("Filter Result :");
            donorUI.printDonorTitle();
            MessageUI.diplayEnDash();
            donorUI.printText(categorizedDonors.toString());
            
        }while(opt > 6 || opt < 1);
        }while(opt > 7 || opt < 1);
        
        
    }
    private void DonorReports(SortedListSetInterface<Donor> donors) {
        
    }
    
    private String getContact(){
        String contact = "";
        do{
        contact = donorUI.getDonorContact();
         if (contact.length() > 11 || !contact.startsWith("0") || !contact.matches("\\d+")) {
                MessageUI.displayInvalidContactMessage();  // Display an error message if invalid
                
            }
        }while(contact.length() > 11 || !contact.startsWith("0") || !contact.matches("\\d+"));
        return contact;
    }
    
    private String getEmail(){
     String email = "";
        do{
             email = donorUI.getDonorEmail();
            if(email.contains("@"))
                MessageUI.displayInvalidEmailMessage();
        }while(email.contains("@"));
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
                donorUI.printDonorTitle();
                MessageUI.diplayEnDash();
                donorUI.printText(donor.toString());
                MessageUI.diplayEnDash(); 
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
                donorUI.printDonorTitle();
                MessageUI.diplayEnDash(); 
                donorUI.printText(donor.toString());
                MessageUI.diplayEnDash(); 
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
               donorUI.printDonorTitle();
               MessageUI.diplayEnDash();              
               donorUI.printText(donor.toString());
               MessageUI.diplayEnDash(); 
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
    
   private void removeAllBannedDonors(SortedListSetInterface<Donor> donors){
       Iterator<Donor> iterator = donors.getIterator();
        SortedListSetInterface<Donor> foundDonors = new SortedDoublyLinkedListSet<>();

       iterator = donors.getIterator();
       while (iterator.hasNext()) {
           Donor donor = iterator.next();
           if (donor.getStatus().equals("Banned")) {
               foundDonors.add(donor);             
           }
       }
       if(foundDonors != null){
           donorUI.printText("Search Result : ");
               donorUI.printDonorTitle();
               MessageUI.diplayEnDash();              
               donorUI.printText(foundDonors.toString());
               MessageUI.diplayEnDash();
               donorUI.printNumberOfEntries(donors);
               
                String YesNo = donorUI.getConfirmation("remove");
                    if (YesNo.equalsIgnoreCase("y")) {
                        donors.relativeComplement(foundDonors);
                        donorUI.printText("All banned donors are removed");
                    } else {
                        donorUI.printText("Update cancelled.");
                    }
               
       }else{   
           donorUI.printText("\nNo results found for banned Donors \n");
       }
   }
   
   private void updatedDonorInfo(Donor foundDonor){
       donorUI.printText("After Update :");
       donorUI.printDonorTitle();
       MessageUI.diplayEnDash();
       donorUI.printText(foundDonor.toString());
       MessageUI.diplayEnDash();
   }
     
   private String setDonorCategory(){
       int inputType = 0;
       int inputCategory = 0;
       String category = "";
       try {
            do {
                inputType = donorUI.getDonorType();

                if (inputType == 1) {
                    category = "Individual";
                } else if (inputType == 2) {
                    do {

                        inputCategory = donorUI.getOrganisationCategory();

                        switch (inputCategory) {
                            case 1:
                                category = "Public Organisation";
                                break;
                            case 2:
                                category = "Private Organisation";
                                break;
                            case 3:
                                category = "Government Organisation";
                                break;
                            default:
                                MessageUI.displayInvalidOptionMessage();
                                break;
                        }
                    } while (inputCategory > 3 || inputCategory < 1);
                }

            } while (inputType > 2 || inputType < 1);

        } catch (Exception ex) {
            MessageUI.displayInvalidOptionMessage();
        }
       return category;
   }
   
   
   private String setDonorStatus(){
       String newStatus = "";
       int opt = 0;
       do {
           opt = donorUI.getDonorStatus();

           if (opt > 4 || opt < 1) {
               MessageUI.displayInvalidOptionMessage();
           }
       } while (opt > 4 || opt < 1);

       switch (opt) {
           case 1:
               newStatus = "Active";
               break;
           case 2:
               newStatus = "Inactive";
               break;
           case 3:
               newStatus = "Prospect";
               break;
           case 4:
               newStatus = "Banned";
               break;
           default:
               break;
       }
       return newStatus;
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
           if (donor.getStatus().equals(status)) {
               categorizedDonors.add(donor);             
           }
       }
       return categorizedDonors;
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
   
   private void checkAndUpdateStatus(SortedListSetInterface<Donor> donors) {
        Iterator<Donor> donorIterator = donors.getIterator();
        Date currentDate = getCurrentDate();

        while (donorIterator.hasNext()) {
            Donor donor = donorIterator.next();

            // Find the last donation in the donor's donation list
            if (!donor.getDonationList().isEmpty()) {
                Donation lastDonation = donor.getDonationList().getLastEntries();
                Date lastDonationDate = lastDonation.getDonationDate();

                if (lastDonationDate.withinPassWeek(currentDate) && !donor.getStatus().equals("active")) {
                    donor.setStatus("active");
                } else if (lastDonationDate.moreThanThreeMonthsAgo(currentDate) &&donor.getStatus() .equals("active")) {
                    donor.setStatus("inactive");
                }
            }
        }
    }
}
