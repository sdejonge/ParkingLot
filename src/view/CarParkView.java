package view;

import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * The view where the simulator gets drawn
 *
 * @author The Button Bashers
 * @version 1.2
 *
 */

public class CarParkView extends AbstractDisplayPane {

    private Dimension size;
    private Image carParkImage;
    private SimulatorModel model;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int floorWidth;

    /**
     * Constructor of the class
     * Also sets the size for the simulator
     *
     * @param model the model
     * @param numberOfFloors amount of floors
     * @param numberOfRows amount of rows
     * @param numberOfPlaces amount of places per row
     */

    public CarParkView(SimulatorModel model, int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        super(model);
        this.model = model;
        size = new Dimension(0, 0);

        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;

        //floorWidth = 260;


        if((numberOfRows%2)==0) {
            // even
            floorWidth = ((numberOfRows -2)/2)*75 + 110;
        }
        else {
            // oneven
            floorWidth = ((numberOfRows - 1)*75 + 90);
        }
        System.out.println("floorWith: " + floorWidth);


    }

    /**
     * Overridden. Tell the GUI manager how big we would like to be.
     */
    public Dimension getPreferredSize() {
        return new Dimension((floorWidth*numberOfFloors + 20), 500);
    }

    /**
     * Overriden. The car park view component needs to be redisplayed. Copy the
     * internal image to screen.
     */
    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    /**
     * Updates the simulator view
     */

    public void updateView() {
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < model.getNumberOfFloors(); floor++) {
            for(int row = 0; row < model.getNumberOfRows(); row++) {
                for(int place = 0; place < model.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = model.getCarAt(location);

                    Color color = setColor(car, location);
                    drawPlace(graphics, location, color);
                }
            }
        }
        repaint();
    }

    /**
     * Sets the color of the car
     * @param car the car
     * @param location the location to park in
     * @return return the color
     */

    private Color setColor(Car car, Location location) {
        if (car == null) {

            if (location.getPlace() < (model.getNumberOfPlaces() - model.getReserv())){
                return(Color.GRAY);
            }
            else {
                return(Color.CYAN);
            }
        }
        else {
             return(car.getColor());
        }
    }

    /**
     * Paint a place on this car park view in a given color.
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * floorWidth + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }
}