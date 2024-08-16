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
        boolean validInput = false;
        
        
         String lastId = "DR001"; // Default ID if no donees are present
        if (!donors.isEmpty()) {
            Donor lastDonor = donors.getLastEntries();
            lastId = lastDonor.getDonorId(); // 
        }
        int lastNumericPart = Integer.parseInt(lastId.substring(2));
        int nextNumericPart = lastNumericPart + 1; //
        String donorId = String.format("DR%03d", nextNumericPart);

        donorUI.printText("[ Add new donor ]");
        
        String category = "";
        do{
        String input = donorUI.getDonorCategory();
        if(input == "1"){
            category = "Public";
            validInput = true;
        }else if (input =="2"){
            category = "Private";
            validInput = true;
        }else if(input == "3"){
            category = "Government";
            validInput = true;
        }else{
            MessageUI.displayInvalidOptionMessage();
            validInput = false;
        }
        }while(validInput);
         
        String name = donorUI.getDonorName();
        
        String contactName = donorUI.getDonorContactPerson();
        String contact = "";
        validInput = false;
        do{
        contact = donorUI.getDonorContact();
         if (contact.length() > 11 || !contact.startsWith("0") || !contact.matches("\\d+")) {
                MessageUI.displayInvalidContactMessage();  // Display an error message if invalid
                validInput = false;
            }
        }while(validInput);
        
        String email = donorUI.getDonorEmail();
        
        String address = donorUI.getDonorAddress();
        
        Donor newDonor = new Donor(donorId, category,name, contactName, contact, email, address);
        donors.add(newDonor);
        donorUI.printDonorTitle();
        donorUI.printAllDonors(donors);
        
    }

    private void SearchDonor(SortedListSetInterface<Donor> donees) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void RemoveDonor(SortedListSetInterface<Donor> donees) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void UpdateDonor(SortedListSetInterface<Donor> donors) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
