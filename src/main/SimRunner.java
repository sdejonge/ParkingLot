package main;

import model.*;


/**
 * The SimRunner class implements a main method to start the simulator.
 * @version 30-01-2018
 */
public class SimRunner {

    public static void main(String[] args) {

        /**
         * Create a new instance of the SimulatorModel() class.
         * @param numberOfFloors int
         * @param numberOfRows int
         * @param numberOfPlaces int
         */
        SimulatorModel sim = new SimulatorModel(3,6,30);
        sim.run();
    }

}
