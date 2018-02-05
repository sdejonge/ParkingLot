package model;

import java.awt.*;
import java.util.Random;

/**
 * This class contains information regarding the cars in the parking lot.
 *
 * @author The Button Bashers
 * @version 30-01-2018
 */

public abstract class Car{

    //Location of the car object within the parking lot.
    private Location location;

    //Amount of minutes remaining of the car object that's staying in the parking lot.
    private int minutesLeft;

    //Contains information if the customer is currently paying or not.
    private boolean isPaying;

    //Contains information wether the customer has to pay or already paid.
    private boolean hasToPay;

    /**
     * Constructor for objects of class model.Car
     */
    public Car() {

    }

    /**
     * get the location of the car object.
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * set the location of the car object
     * @param location Location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * get the remaining amount of minutes left of the car object
     * @return minutesLeft
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }
    /**
     * set the remaining amount of minutes left of the car object
     * @param minutesLeft int
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    /**
     * get if the person associated with the car is paying
     * @return isPaying
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * set that the person associated with the car is paying
     * @param isPaying boolean
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * get if the person associated with the car still has to pay
     * @return hasToPay
     */
    public boolean getHasToPay() {
        return hasToPay;
    }

    /**
     * set if the person associated with the car still has to pay.
     * @param hasToPay boolean
     */
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    /**
     * tick that removes a minute of the remaining time of the car object
     */
    public void tick() {
        minutesLeft--;
    }

    public abstract Color getColor();
}