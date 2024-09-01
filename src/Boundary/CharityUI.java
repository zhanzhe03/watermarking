/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

/**
 *
 * @author USER
 */
import java.util.Scanner;

public class CharityUI {
    
    private Scanner scanner = new Scanner(System.in);
    
    public String getMenu(){
        System.out.println(""
                + "\nMAIN MENU"
                + "\n 1. Donor Management"
                + "\n 2. Donee Management"
                + "\n 3. Donation Management"
                + "\n 4. Donation Distribution"
                + "\n 9. Exit Program");
        System.out.print("\nopt > ");
        String opt = scanner.nextLine();
        return opt;
    }
    
    public void getLogo(){
        System.out.println(" _______         __        __      __   ___        __    __         __       ___________    __           ______     __    __         __        _______     __      ___________   ___  ___  ");
        System.out.println("|   _  \"\\       /\"\"\\      |\" \\    |/\"| /  \")      /\" |  | \"\\       /\"\"\\     (\"     _   \")  |\" \\         /\" _  \"\\   /\" |  | \"\\       /\"\"\\      /\"      \\   |\" \\    (\"     _   \") |\"  \\/\"  | ");
        System.out.println("(. |_)  :)     /    \\     ||  |   (: |/   /      (:  (__)  :)     /    \\     )__/  \\\\__/   ||  |       (: ( \\___) (:  (__)  :)     /    \\    |:        |  ||  |    )__/  \\\\__/   \\   \\  /  ");
        System.out.println("|:     \\/     /' /\\  \\    |:  |   |    __/        \\/      \\/     /' /\\  \\       \\\\_ /      |:  |        \\/ \\       \\/      \\/     /' /\\  \\   |_____/   )  |:  |       \\\\_ /       \\\\  \\/   ");
        System.out.println("(|  _  \\\\    //  __'  \\   |.  |   (// _  \\        //  __  \\\\    //  __'  \\      |.  |      |.  |        //  \\ _    //  __  \\\\    //  __'  \\   //      /   |.  |       |.  |       /   /    ");
        System.out.println("|: |_)  :)  /   /  \\\\  \\  /\\  |\\  |: | \\  \\      (:  (  )  :)  /   /  \\\\  \\     \\:  |      /\\  |\\      (:   _) \\  (:  (  )  :)  /   /  \\\\  \\ |:  __   \\   /\\  |\\      \\:  |      /   /     ");
        System.out.println("(_______/  (___/    \\___)(__\\_|_) (__|  \\__)      \\__|  |__/  (___/    \\___)     \\__|     (__\\_|_)      \\_______)  \\__|  |__/  (___/    \\___)|__|  \\___) (__\\_|_)      \\__|     |___/     ");
                
    }
}
