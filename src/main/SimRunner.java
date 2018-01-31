package main;

import model.*;
import view.*;

public class SimRunner {

    //public static void main(String[] args) {
    //    SimulatorModel sim = new SimulatorModel(3,6,30);
    //    sim.run();
    //}

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
