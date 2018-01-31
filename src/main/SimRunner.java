package main;

import model.*;
import view.*;


/**
 * The SimRunner class implements a main method to start the simulator.
 * @version 30-01-2018
 */
public class SimRunner {

    /**
      * Create a new instance of the SimulatorModel() class.
      */
  
    public static void main(String[] args) {
        System.out.println("Main methode start");
        SetupView sview = new SetupView();
        sview.WachtenOpGebruiker(); //wacht totdat de gebruiker klaar is in de SetupView
        int[] setupWaardes = sview.setupWaardes();
        System.out.println("setup klaar vanuit de main methode");
        sview = null; //verwijderd het setupscherm om data vrij te maken
        System.out.println("SetupView is verwijderd");

        SimulatorModel sim = new SimulatorModel(setupWaardes[0], setupWaardes[1], setupWaardes[2]);
        sim.run();
    }
}
