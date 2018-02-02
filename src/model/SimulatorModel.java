package model;

import view.*;

import java.util.Random;

public class SimulatorModel {

	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	
	
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

    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour

    int enterSpeed = 12; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute

    public int redCars;
    public int blueCars;
    public int totalCars;

    private int stayMinutes; //The amount of time a car stays in the parking lot
    private double prijs = 1.2 ; //The price per hour
    public double profit;

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int absReserv; //houd bij hoeveel plaatsen in elke rij worden gereserveerd voor abonnementhouders, wordt berekend in de constructor
    private int numberOfOpenSpots;
    private Car[][][] cars;

    public SimulatorModel(int numberOfFloors, int numberOfRows, int numberOfPlaces, int Reserv){
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        //bereken hoeveel plaatsen uit elke rij, worden gereserveerd voor abonnementhouders
        float a = Reserv;
        a = (a / 100);
        a = (getNumberOfPlaces() * a);
        absReserv = (int)a;
        System.out.println("absReserv: " + absReserv);

        simView = new SimulatorView(this, numberOfFloors, numberOfRows, numberOfPlaces);

    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
            tickLeave();
        }
    }
    
    private void tick() {
    	advanceTime();
    	handleExit();
        simView.updateView();
        minute += 20;
        if(minute == 59){
            hour += 1;
        }
        if(hour == 23 && minute == 59){
            day += 1;
            dayToText();
        }
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	handleEntrance();
    }

    public void dayToText(){
        if(day == 1){
            day_text = "Tuesday";
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

    public void tickLeave() {
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

    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }
    }

    private void handleEntrance(){
    	carsArriving();
    	carsEntering(entranceCarQueue, 1);
        carsEntering(entrancePassQueue, 2);
    }
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);    	
    }

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

    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed) {
            Car car = paymentCarQueue.removeCar();
            stayMinutes = ((AdHocCar) car).getStayMinutes(); //Set the minutes a car stays in the parking lot.
            profit += stayMinutes * prijs / 60; //Formula to calculate the amount of money to be paid.
            carLeavesSpot(car);
            i++;
        }
    }
    
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
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
    
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
                if (numberOfOpenSpots > 0) {
                    entranceCarQueue.addCar(new AdHocCar());
                    redCars++;
                    totalCars = redCars + blueCars;
                }
            }
            break;
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
                if (numberOfOpenSpots > 0) {
                    entrancePassQueue.addCar(new ParkingPassCar());
                    blueCars++;
                    totalCars = redCars + blueCars;
                }
            }
            break;	            
    	}
    }
    
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

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
        return numberOfOpenSpots;
    }

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
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

    public Location getFirstFreeLocation(int locType) {
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

    public Car getFirstLeavingCar() {
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

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    public int getReserv() {
        return(absReserv);
    }
}