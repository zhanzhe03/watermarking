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
        
        public void doneeManagement(EntityInitializer entityInitialize) {
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
                        SearchDonors(donors);
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

    private void ListAllDonor(SortedListSetInterface<Donor> donees) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void AddNewDonor(SortedListSetInterface<Donor> donees) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void SearchDonors(SortedListSetInterface<Donor> donees) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void RemoveDonor(SortedListSetInterface<Donor> donees) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void UpdateDonor(SortedListSetInterface<Donor> donors) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
