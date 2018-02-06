package model;

import java.util.Random;
import java.awt.*;
/**
 * ParkingPassCar checks for the subscription based cars entering the parkinglot. the class sets the color of the car the blue.
 * Is a subclass from the super class Car
 *
 * @author The Button Bashers
 * @version 1.2
 */
public class ParkingPassCar extends Car {
	private static final Color COLOR=Color.blue;

    /**
     * ParkingPassCar constructor.
     * Creates stayMinutes and sets it to setMinutesLeft
     * Which is used in the super class Car.
     * Also sets setHasToPay to false
     */
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    /**
     * Returns the color of the cars
     *
     * @return the color of the cars, which is blue in this case
     */
    public Color getColor(){
    	return COLOR;
    }
}
