package model;

import controller.*;
import view.*;

import java.util.Random;

/**
 * Main model class for the simulator.
 * This class takes care of almost all the calculations needed to run the simulator.
 *
 * @author The Button Bashers
 * @version 1.2
 */
public class SimulatorModel extends AbstractModel implements Runnable{

    private static final String AD_HOC = "1";
    private static final String PASS = "2";

    private boolean running = false;

    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    private SimulatorView simView;

    public int day = 0;
    public int hour = 0;
    public int minute = 0;
    public String day_text = "Monday";

    private int tickPause = 100;
    private int tick = 0;

    int weekDayArrivals = 0; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals = 50; // average number of arriving cars per hour
    int weekendPassArrivals = 100; // average number of arriving cars per hour

    int enterSpeed = 12; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute

    public float redCars;
    public float blueCars;
    public float totalCars;

    private int stayMinutes; //The amount of time a car stays in the parking lot
    private double prijs = 1.2 ; //The price per hour
    public double totalProfit; // Total amount of profit since the start of running the application
    public int minutesElapsed = 0; //Contains the amount of time ticks.
    public double nextProfit; //The amount of profit on a single day.
    public double estimateProfit; //The estimated amount of money per day.
    public int[] weekProfit;

    public Thread StartThread;
    private Object lock = new Object();

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int absReserv; //houd bij hoeveel plaatsen in elke rij worden gereserveerd voor abonnementhouders, wordt berekend in de constructor
    private int totalReserv;
    private int numberOfOpenSpots;
    private int numberOfOpenSpotsPublic;
    private Car[][][] cars;

    /**
     * Main constructor for the simulatorModel.
     * Calculates how many places for each row are for Subscriptions
     * The main constructor also created a link to the main view and the controller class
     *
     * @param numberOfFloors The number of floors the parkinglot contains
     * @param numberOfRows   The number of rows that the parking lot has for each floor
     * @param numberOfPlaces The number of places that the makinglot has for each row
     * @param Reserv         used for calculating the number of subscription based places for each row
     */
    public SimulatorModel(int numberOfFloors, int numberOfRows, int numberOfPlaces, int Reserv){
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        //bereken hoeveel plaatsen uit elke rij, worden gereserveerd voor abonnementhouders
        float a = Reserv;
        a = (a / 100);
        a = (getNumberOfPlaces() * a);
        absReserv = (int)a;
        System.out.println("absReserv: " + absReserv);

        weekProfit = new int[7];

        simView = new SimulatorView(this, numberOfFloors, numberOfRows, numberOfPlaces);
        Controller control = new Controller(this,simView);

        totalReserv = (numberOfRows * numberOfFloors) * absReserv;
        numberOfOpenSpotsPublic = numberOfOpenSpots - totalReserv;
        System.out.println(totalReserv);
    }

    /**
     * Start method for creating a new thread and starting it
     */
    public void start(){
        running=true;
        StartThread = new Thread(this);
        StartThread.start();
    }

    /**
     * Runs class for running the simulator
     */
    public void run() {
        tick(true);
        tickLeave();
    }

    /**
     *  Runs the application once to toggle all views. Otherwise the views would only be visible is start is clickedsss
     */
    public void runOnce(){
        while(running) {
            advanceTime();
            handleExit();
            handleEntrance();
        }
        notifyViews();
    }

    /**
     * Runs tick and tickleave 100 times.
     * Used for the +100 button in the controller
     */
    public void tickTimes100() {
        for (int i = 1; i <= 100; i++) {
            //If wait is true application will sleep till steps are done
            tick(false);
            tickLeave();
        }
    }

    /**
     * Runs tick and tickleave 10 times.
     * Used for the +10 button in the controller
     */
    public void tickTimes10() {
        for (int i = 1; i <= 10; i++) {
            tick(false);
            tickLeave();
        }
    }

    /**
     * manages the ticks used for filling up the simulation
     * uses a thread sleep to make the Simulation not fill in 1 second
     * @param wait allows tickTimes100 and Ticktimes10 to instantly work. Otherwise the application would stop for a few seconds
     */
    private void tick(boolean wait) {
        advanceTime();
        handleExit();
        handleEntrance();
        notifyViews();
        if(wait){
            try {
                Thread.sleep(tickPause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  Makes the cars leave the Simulator.
     *  Without this class the simulator would never have leaving cars.
     */
    private void tickLeave() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    /**
     * calculated the amount of places the blue cars have taken up
     * after calculating is generated a degree amount for the piechart
     * @return The degree of blue for use in a piechart
     */
    public int getTotalBlueSpots(){
        int totalSpots = this.numberOfFloors * this.numberOfRows * this.numberOfPlaces;
        float blueCalc = (blueCars / totalSpots);
        int blueDegree = Math.round(360 * blueCalc);
        return blueDegree;
    }
    /**
     * calculated the amount of places the red cars have taken up
     * after calculating is generated a degree amount for the piechart
     * @return The degree of red for use in a piechart
     */
    public int getTotalRedSpots(){
        int totalSpots = this.numberOfFloors * this.numberOfRows * this.numberOfPlaces;
        float redCalc = redCars / totalSpots;
        int redDegree = Math.round(360 * redCalc);
        return redDegree;
    }

    /**
     * sets the day, which is a number, to a word.
     */
    private void dayToText(){
        if(day == 0){
            day_text = "Monday";
        }
        else if(day == 1){
            day_text = "Tuesday";
            weekProfit[0] = (int) totalProfit;
            totalProfit = 0;
        }
        else if(day == 2){
            day_text = "Wednesday";
        }
        else if(day == 3){
            day_text = "Thursday";
        }
        else if(day == 4){
            day_text = "Friday";
        }
        else if(day == 5){
            day_text = "Saturday";
        }
        else if(day == 6){
            day_text = "Sunday";
        }
    }

    /**
     * calculates the time and day
     */
    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        minutesElapsed++;

        while (minute > 59) {
            minute -= 60;
            hour++;
            setIncomingValues();
        }
        while (hour > 23) {
            hour -= 24;
            day++;
            dayToText();
        }
        while (day > 6) {
            day -= 7;
        }
    }

    /**
     * sets the incoming values of the arrivals
     * This makes the parking lot more realistic.
     * When it's night there are normally not a lot of people parking
     */
    private void setIncomingValues() {
        if (hour >= 2 && hour < 5) {
            weekDayArrivals = 0;
            weekDayPassArrivals = 0;
        }
        else if (hour >= 5 && hour < 9) {
            weekDayArrivals = 200;
            weekDayPassArrivals = 100;
        }
        else if (hour >= 9 && hour < 13) {
            weekDayArrivals = 50;
            weekDayPassArrivals = 25;
        }
        else if (hour >= 13 && hour < 17) {
            weekDayArrivals = 150;
            weekDayPassArrivals = 75;
        }
        else if (hour >= 17 && hour < 22) {
            weekDayArrivals = 100;
            weekDayPassArrivals = 50;
        }
        else if (hour >= 22) {
            weekDayArrivals = 50;
            weekDayPassArrivals = 50;
        }
    }

    /**
     * handles the entering cars
     */
    private void handleEntrance(){
        carsArriving();
        carsEntering(entranceCarQueue, 1);
        carsEntering(entrancePassQueue, 2);
    }

    /**
     * Handles the cars leaving
     */
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }

    /**
     * calculated the arriving cars to the parking lot
     */
    private void carsArriving(){
        int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);
        numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);
    }

    /**
     * Does the calculations of cars entering the parking lot
     * @param queue gets cars from the queue
     * @param carType determines is the car is Subscription based or regular
     */
    private void carsEntering(CarQueue queue, int carType){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue()>0 &&
                getNumberOfOpenSpots()>0 &&
                i<enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = getFirstFreeLocation(carType);
            if (freeLocation != null) {
                setCarAt(freeLocation, car);
            }
            if (freeLocation == null && carType == 2) {
                freeLocation = getFirstFreeLocation(3);
                setCarAt(freeLocation, car);
            }
            i++;
        }
    }

    /**
     * Calculated the cars that are ready to leave the parkinglot
     */
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();
        while (car!=null) {
            if (car.getHasToPay()){
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            }
            else {
                carLeavesSpot(car);
            }
            car = getFirstLeavingCar();
        }
    }

    /**
     * Calculated the paying cars
     */
    private void carsPaying(){
        // Let cars pay.
        int i=0;
        while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed) {
            Car car = paymentCarQueue.removeCar();
            stayMinutes = ((AdHocCar) car).getStayMinutes(); //Set the minutes a car stays in the parking lot.
            totalProfit += stayMinutes * prijs / 60; //Formula to calculate the amount of money to be paid.

            if (minutesElapsed > 1439) {
                minutesElapsed = 0;
                nextProfit = 0;
                estimateProfit = 0;
            }
            else {
                nextProfit += stayMinutes * prijs / 60;
                estimateProfit = nextProfit / minutesElapsed * 1440;
            }

            carLeavesSpot(car);
            i++;
        }
    }

    /**
     * Removes the car from the parking lot
     */
    private void carsLeaving(){
        // Let cars leave.
        int i=0;
        while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
        }
    }

    /**
     * Gets the total number of cars
     * @param weekDay  the day of the week
     * @param weekend  determines weekends
     * @return         the number of cars that arrive this minute.
     */
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);
    }

    /**
     * Add the cars to the back of the queue.
     * @param numberOfCars  int amount of the cars
     * @param type          regular car of subscription
     */

    private void addArrivingCars(int numberOfCars, String type){
        switch(type) {
            case AD_HOC:
                for (int i = 0; i < numberOfCars; i++) {
                    if (redCars < numberOfOpenSpotsPublic) {
                        entranceCarQueue.addCar(new AdHocCar());
                        redCars++;
                        totalCars = redCars + blueCars;
                    }
                }
                break;
            case PASS:
                for (int i = 0; i < numberOfCars; i++) {
                    if (blueCars < totalReserv) {
                        entrancePassQueue.addCar(new ParkingPassCar());
                        blueCars++;
                        totalCars = redCars + blueCars;
                    }
                }
                break;
        }
    }

    /**
     * makes a car leave it's spot when leaving
     * @param car checks for the car type
     */
    private void carLeavesSpot(Car car){
        if (car instanceof AdHocCar) {
            redCars--;
        }
        else if (car instanceof ParkingPassCar) {
            blueCars--;
        }
        removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }

    /**
     * Get the number of floors
     * @return the amount of floors
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * gets the number of rows
     * @return the number of rows
     */

    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Gets the number of places
     * @return The number of places
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * Gets the number of open spots in the parking lot
     * @return the number of open spots
     */
    private int getNumberOfOpenSpots(){
        return numberOfOpenSpots;
    }

    /**
     * Determines the location of the car
     * @param location the location
     * @return         spot of the car
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * Puts a car in a location
     * @param location the location
     * @param car      The car
     * @return         returns true of false for if the location is valid or not
     */
    private boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    /**
     * Removes a car from it's spot
     * @param location The location
     * @return         The car
     */
    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    /**
     * returns the first free location possible
     * @param locType Location type
     * @return Returns the valid location
     */
    private Location getFirstFreeLocation(int locType) {
        if (locType == 1){
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < (getNumberOfPlaces() - absReserv); place++) {
                        Location location = new Location(floor, row, place);
                        if (getCarAt(location) == null) {
                            return location;
                        }
                    }
                }
            }
        }

        if (locType == 2){
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = (getNumberOfPlaces() - absReserv); place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        if (getCarAt(location) == null) {
                            return location;
                        }
                    }
                }
            }
        }

        if (locType == 3) {
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        if (getCarAt(location) == null) {
                            return location;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns the first car that is going to leave
     * @return The car or NULL
     */
    private Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Checks if the location is valid
     * @param location the location
     * @return         True of false depending on if the location is valid or not
     */

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    /**
     * Returns the subscription amount
     * @return The amount of subscriptions
     */
    public int getReserv() {
        return(absReserv);
    }

    /**
     * Sets running to either true or false
     * @param value The value of running (true or false)
     */
    public void setRunning(boolean value) {running = value;}

    /**
     * Returns running
     * @return True of false
     */
    public boolean getRunning() {return running;}
}
