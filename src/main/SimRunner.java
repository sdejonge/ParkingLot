package main;

import model.*;

public class SimRunner {

    public static void main(String[] args) {
        SimulatorModel sim = new SimulatorModel(3,6,30);
        sim.run();
    }

}
