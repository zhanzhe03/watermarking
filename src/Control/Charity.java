/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author USER
 */
import Boundary.CharityUI;
import Utility.*;
import DAO.EntityInitializer;

public class Charity {

    private CharityUI charityUI = new CharityUI();
    private EntityInitializer entityInitialize = new EntityInitializer();

    private DonationMaintenance donationMaintenance = new DonationMaintenance();
    private DoneeMaintenance doneeMaintenance = new DoneeMaintenance();

    //szewen
    private DistributionManager distributionManager = new DistributionManager();

    public void runCharityProgram() {
        entityInitialize.entityEnitialize();

        int opt = 0;

        do {
            ClearScreen.clearJavaConsoleScreen();
            try {
                opt = Integer.parseInt(charityUI.getMenu());

                switch (opt) {
                    case 1:
                        break;
                    case 2:
                        doneeMaintenance.doneeManagement(entityInitialize);
                        break;
                    case 3:
                        donationMaintenance.donationManagement(entityInitialize);
                        break;
                    case 4:
                        distributionManager.distributionManager(entityInitialize);
                        break;
                    case 9:
                        MessageUI.displayExitMessage();
                        System.exit(0);
                    default:
                        MessageUI.displayInvalidOptionMessage();
                }
            } catch (NumberFormatException ex) {
                System.out.println("1");
                MessageUI.displayInvalidIntegerMessage();
            }

        } while (opt != 9);
    }

    public static void main(String[] args) {
        Charity charityProgram = new Charity();
        charityProgram.runCharityProgram();
    }
}
