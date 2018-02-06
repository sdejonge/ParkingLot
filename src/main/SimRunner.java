package main;

import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;


/**
 * The SimRunner class implements a main method to start the simulator.
 * @author The Button Bashers
 * @version 30-01-2018
 */
public class SimRunner {

    /**
     * Main method to start the application.
     * Starts with a home screen in which you need to specify the amount of rows.
     * Create a new simulator model and run the application with the set parameters.
     * If no parameters where given set the default values.
     *
     * @param args In Java args contains the supplied command-line arguments as an array of String objects.
     */
  
    public static void main(String[] args) {
        System.out.println("Main methode start");
        SetupView sview = new SetupView();
        sview.WachtenOpGebruiker(); //wacht totdat de gebruiker klaar is in de SetupView
        int[] setupWaardes = sview.setupWaardes();
        System.out.println("setup klaar vanuit de main methode");
        sview = null; //verwijderd het setupscherm om data vrij te maken
        System.out.println("SetupView is verwijderd");

        SimulatorModel sim = new SimulatorModel(setupWaardes[0], setupWaardes[1], setupWaardes[2], setupWaardes[3]);
        MainTread T1 = new MainTread(sim);
        T1.start();
    }
}
