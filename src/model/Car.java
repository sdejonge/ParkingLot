package model;

import java.awt.*;

/**
 * @author S. de Jonge
 * @version 30-01-2018
 */

public abstract class Car {

    //Location of the car object
    private Location location;


    private int minutesLeft;


    private boolean isPaying;


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