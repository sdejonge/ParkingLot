package model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * creates a LinkedList called queue which contains the amount of cars in the queue
 */
public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();

    /**
     * adds a car to the queue LinkedList
     * @param car the car that is added to the queue
     * @return the car added to the queue
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * Removes a car from the queue
     * @return the car removed form the queue
     */
    public Car removeCar() {
        return queue.poll();
    }

    /**
     * checks the size of the queue
     * @return queue size
     */
    public int carsInQueue(){
    	return queue.size();
    }
}
