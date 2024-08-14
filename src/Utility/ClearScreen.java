/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author USER
 */
public class ClearScreen {
    public static void clearJavaConsoleScreen() {
        try {
            Robot rob = new Robot();
            try {
                rob.keyPress(KeyEvent.VK_CONTROL); // press "CTRL"
                rob.keyPress(KeyEvent.VK_L); // press "L"
                rob.keyRelease(KeyEvent.VK_L); // unpress "L"
                rob.keyRelease(KeyEvent.VK_CONTROL); // unpress "CTRL"
                Thread.sleep(10); // add delay in milisecond, if not there will automatically stop after clear
            } catch (InterruptedException e) {
                //;e.printStackTrace();
            }
        } catch (AWTException e) {
            //e.printStackTrace();
        }
        double[] num = new double[100];
        for (int i = 0; i < num.length; i++) {

        }
    }
}
