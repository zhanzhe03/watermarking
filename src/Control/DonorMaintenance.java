/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import ADT.SortedListSetInterface;
import Boundary.DonorUI;
import DAO.EntityInitializer;
import Entity.Donor;
import Utility.ClearScreen;
import Utility.MessageUI;
import java.util.Iterator;

/**
 *
 * @author LENOVO
 */
public class DonorMaintenance {
        private DonorUI donorUI = new DonorUI();
        
        public void donorManagement(EntityInitializer entityInitialize) {
        SortedListSetInterface<Donor> donors = entityInitialize.getDonors();

        int opt = 0;
        do {
            try {
                opt = Integer.parseInt(donorUI.getDonorMenu());

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

    private void ListAllDonor(SortedListSetInterface<Donor> donors) {
        donorUI.printText("All Donors List");
        MessageUI.diplayEnDash();
        donorUI.printDonorTitle();
        MessageUI.diplayEnDash();
        donorUI.printAllDonors(donors);
        MessageUI.diplayEnDash();
        donorUI.printNumberOfEntries(donors);
    }

    private void AddNewDonor(SortedListSetInterface<Donor> donors) {
       
         String lastId = "DR001"; 
        if (!donors.isEmpty()) {
            Donor lastDonor = donors.getLastEntries();
            lastId = lastDonor.getDonorId(); 
        }
        int lastNumericPart = Integer.parseInt(lastId.substring(2));
        int nextNumericPart = lastNumericPart + 1; 
        String donorId = String.format("DR%03d", nextNumericPart);

        donorUI.printText("[ Add new donor ]");
        
        String category = "";
        String input = "";
        try{
        do{
        input = donorUI.getDonorCategory();
        ;
        if(input == "1"){
            category = "Public";
            
        }else if (input == "2" ){
            category = "Private";
            
        }else if(input == "3"){
            category = "Government";
            
        }else{
            MessageUI.displayInvalidOptionMessage();
        }
        }while(Integer.parseInt(input) <= 3 && Integer.parseInt(input)>=1);
        }catch(Exception ex){
            MessageUI.displayInvalidOptionMessage();
        }
         
        String name = donorUI.getDonorName();
        
        String contactName = donorUI.getDonorContactPerson();
        String contact = "";
        
        do{
        contact = donorUI.getDonorContact();
         if (contact.length() > 11 || !contact.startsWith("0") || !contact.matches("\\d+")) {
                MessageUI.displayInvalidContactMessage();  // Display an error message if invalid
                
            }
        }while(contact.length() > 11 || !contact.startsWith("0") || !contact.matches("\\d+"));
        
        String email = donorUI.getDonorEmail();
        
        String address = donorUI.getDonorAddress();
        
        Donor newDonor = new Donor(donorId, category,name, contactName, contact, email, address);
        donors.add(newDonor);
        donorUI.printDonorTitle();
        donorUI.printAllDonors(donors);
        
    }

    private void SearchDonor(SortedListSetInterface<Donor> donors) {
        Iterator<Donor> iterator = donors.getIterator();

        boolean founded = false;
        int opt = 0;
        do {
            try {
                MessageUI.diplayEnDash();
                opt = Integer.parseInt(donorUI.getDonorSearchMenu());
                MessageUI.diplayEnDash();
                switch (opt) {
                    case 1:
                        String inputId = donorUI.getDonorID();
                        founded = false;
                        iterator = donors.getIterator();
                        while (iterator.hasNext()) {
                            Donor donor = iterator.next();
                            if (donor.getDonorId().equalsIgnoreCase(inputId)) {

                                donorUI.printText("Search Result : \n\n");
                                donorUI.printDonorTitle();
                                MessageUI.diplayEnDash();
                                founded = true;
                                donorUI.printText(donor.toString());
                                break;
                            }
                        }
                        if (!founded) {
                            donorUI.printText("\n\nNo results found for " + inputId + "\n\n");
                        }
                        break;
                    case 2:
                        String inputName = donorUI.getDonorName();
                        founded = false;
                        iterator = donors.getIterator();
                        while (iterator.hasNext()) {
                            Donor donor = iterator.next();
                            if (donor.getName().equalsIgnoreCase(inputName)) {

                                donorUI.printText("Search Result : \n\n");
                                donorUI.printDonorTitle();
                                MessageUI.diplayEnDash();
                                founded = true;
                                donorUI.printText(donor.toString());
                                break;
                            }
                        }
                        if (!founded) {
                            donorUI.printText("\n\nNo results found for " + inputName + "\n\n");
                        }
                        break;
                    case 3:
                        String inputContactName = donorUI.getDonorName();
                        founded = false;
                        iterator = donors.getIterator();
                        while (iterator.hasNext()) {
                            Donor donor = iterator.next();
                            if (donor.getContactPerson().equalsIgnoreCase(inputContactName)) {

                                donorUI.printText("Search Result : \n\n");
                                donorUI.printDonorTitle();
                                MessageUI.diplayEnDash();
                                founded = true;
                                donorUI.printText(donor.toString());
                                break;
                            }
                        }
                        if (!founded) {
                            donorUI.printText("\n\nNo results found for " + inputContactName + "\n\n");
                        }
                        break;
                    case 4: 
                        String inputContact = donorUI.getDonorName();
                        founded = false;
                        iterator = donors.getIterator();
                        while (iterator.hasNext()) {
                            Donor donor = iterator.next();
                            if (donor.getContact().equals(inputContact)) {

                                donorUI.printText("Search Result : \n\n");
                                donorUI.printDonorTitle();
                                MessageUI.diplayEnDash();
                                founded = true;
                                donorUI.printText(donor.toString());
                                break;
                            }
                        }
                        if (!founded) {
                            donorUI.printText("\n\nNo results found for " + inputContact + "\n\n");
                        }
                        break;
                    case 5:
                        break;
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
                }
            }catch(NumberFormatException ex){
                MessageUI.displayInvalidIntegerMessage();
            }
        }while(opt != 5);
    }

    private void RemoveDonor(SortedListSetInterface<Donor> donors) {
      Iterator<Donor> iterator = donors.getIterator();

        boolean founded = false;
        int opt = 0;
        do {
            try {
                MessageUI.diplayEnDash();
                opt = Integer.parseInt(donorUI.getDonorDeleteMenu());
                MessageUI.diplayEnDash();
                switch (opt) {
                    case 1:
                        String inputId = donorUI.getDonorID();
                        founded = false;
                        iterator = donors.getIterator();
                        while (iterator.hasNext()) {
                            Donor donor = iterator.next();
                            if (donor.getDonorId().equalsIgnoreCase(inputId)) {

                                donorUI.printText("Donor Found : \n\n");
                                donorUI.printDonorTitle();
                                MessageUI.diplayEnDash();
                                founded = true;
                                donorUI.printText(donor.toString());

                                String YesNo = donorUI.getDeleteConfirmation();

                                if (YesNo.equalsIgnoreCase("y")) {
                                    donors.remove(donor);
                                    donorUI.printText("Donor ID : " + inputId + " has been removed successfully.");
                                    
                                } else {
                                    donorUI.printText("Removal cancelled.");
                                }
                               
                                break;
                            }
                        }
                        
                        if (!founded) {
                            donorUI.printText("\n\nNo results found for " + inputId + "\n\n");
                        }
                        
                        break;
                    case 2 :
                        String inputName = donorUI.getDonorName();
                        founded = false;
                        iterator = donors.getIterator();
                        while (iterator.hasNext()) {
                            Donor donor = iterator.next();
                            if (donor.getName().equalsIgnoreCase(inputName)) {

                                donorUI.printText("Donor Found : \n\n");
                                donorUI.printDonorTitle();
                                MessageUI.diplayEnDash();
                                founded = true;
                                donorUI.printText(donor.toString());

                                String inputNameId = donorUI.getDonorID();
                                String YesNo = donorUI.getDeleteConfirmation();

                                if (YesNo.equalsIgnoreCase("y")) {
                                    donors.remove(donor);
                                    donorUI.printText("Donor ID : " + inputNameId + " has been removed successfully.");
                                    
                                } else {
                                    donorUI.printText("Removal cancelled.");
                                }
                               
                                break;
                            }
                        }
                        
                        if (!founded) {
                            donorUI.printText("\n\nNo results found for " + inputName + "\n\n");
                        }
                        break;
                        
                    case 3: 
                        String inputContact = donorUI.getDonorContact();
                        founded = false;
                        iterator = donors.getIterator();
                        while (iterator.hasNext()) {
                            Donor donor = iterator.next();
                            if (donor.getDonorId().equalsIgnoreCase(inputContact)) {

                                donorUI.printText("Donor Found : \n\n");
                                donorUI.printDonorTitle();
                                MessageUI.diplayEnDash();
                                founded = true;
                                donorUI.printText(donor.toString());

                                String YesNo = donorUI.getDeleteConfirmation();

                                if (YesNo.equalsIgnoreCase("y")) {
                                    donors.remove(donor);
                                    donorUI.printText("Donor with Contact : " + inputContact + " has been removed successfully.");
                                    
                                } else {
                                    donorUI.printText("Removal cancelled.");
                                }
                               
                                break;
                            }
                        }
                        
                        if (!founded) {
                            donorUI.printText("\n\nNo results found for " + inputContact + "\n\n");
                        }
                        break;
                        
                    case 4 :
                        String inputCuzId = donorUI.getDeleteDonorID();
                        String[] donorsID = inputCuzId.split("\\+");
                        String[] foundedID = new String[donorsID.length]; 
                        int foundCount = 0;
                        iterator = donors.getIterator();

                        for (String id : donorsID) {  // For-each loop to iterate over each ID
                            while (iterator.hasNext()) {  // Iterate through the donor list
                                Donor donor = iterator.next();
                                if (donor.getDonorId().equalsIgnoreCase(id)) {  // Check if the donor ID matches the current ID

                                    donorUI.printText("Donor Found : \n\n");
                                    donorUI.printDonorTitle();
                                    MessageUI.diplayEnDash();
                                    foundedID[foundCount] = id;  // Store the found ID in the array
                                    foundCount++;
                                    donorUI.printText(donor.toString());
                    
                                    String YesNo = donorUI.getDeleteConfirmation();

                                    if (YesNo.equalsIgnoreCase("y")) {
                                        donors.remove(donor);
                                        donorUI.printText("Donor with ID: " + id + " has been removed successfully.");
                                    } else {
                                        donorUI.printText("Removal cancelled.");
                                    }

                                    break;
                                    
                                }
                            }
                            // Reset the iterator to search through the donor list again for the next ID
                            iterator = donors.getIterator();
                        }

                        if (foundCount == 0) {
                            donorUI.printText("No donors were found with the provided IDs.");
                        }
                        break;
                    case 5:
                        break;
                        
                    default:
                        MessageUI.displayInvalidOptionMessage();
                        break;
               
                }
            }catch(NumberFormatException ex){
                MessageUI.displayInvalidOptionMessage();
            }
        }while(opt != 5);              
    }

    private void UpdateDonor(SortedListSetInterface<Donor> donors) {
        
    }


}
