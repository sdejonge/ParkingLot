package model;

import java.awt.*;

/**
<<<<<<< HEAD
=======
 * This class contains information regarding the cars in the parking lot.
 *
>>>>>>> f89fae7dbc323d29a8ba39eec13b11d4added201
 * @author S. de Jonge
 * @version 30-01-2018
 */

public abstract class Car {

<<<<<<< HEAD
    private Location location;
    private int minutesLeft;
    private boolean isPaying;
=======
    //Location of the car object within the parking lot.
    private Location location;

    //Amount of minutes remaining of the car object that's staying in the parking lot.
    private int minutesLeft;

    //Contains information if the customer is currently paying or not.
    private boolean isPaying;

    //Contains information wether the customer has to pay or already paid.
>>>>>>> f89fae7dbc323d29a8ba39eec13b11d4added201
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