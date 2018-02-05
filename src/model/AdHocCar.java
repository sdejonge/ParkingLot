package model;

import java.util.Random;
import java.awt.*;

/**
 * AdHocCar checks for the normal paying cars entering the
 * parkinglot. the class sets the color of the car the red
 * Is a subclass from the super class Car
 *
 * @author The Button Bashers
 * @version 1.2
 */
public class AdHocCar extends Car {
	private static final Color COLOR=Color.RED;
	private int stayMinutes;

    /**
     * AdHocCar constructor.
     * Creates stayMinutes and sets it to setMinutesLeft
     * Which is used in the super class Car.
     * Also sets setHasToPay to true,
     * which makes the cars pay while leaving
     */
    public AdHocCar() {
    	Random random = new Random();
    	stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    /**
     * Returns the number of StayMinutes
     *
     * @return the amount of minutes a car stays in the parking lot
     */
    public int getStayMinutes() {
        return stayMinutes;
    }

    /**
     * Returns the color of the cars
     *
     * @return the color of the cars, which is red in this case
     */
    public Color getColor(){
    	return COLOR;
    }
}