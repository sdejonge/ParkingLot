package controller;

import model.SimulatorModel;
import view.SimulatorView;

import java.awt.event.ActionEvent;
/**
 * The main controller of the application.
 * The controller handles all actionListeners and ActionPreformed
 * The controllers knows both the model and the view and handles everything the buttons do.
 *
 * @author The Button Bashers
 * @version 1.2
 *
 */
public class Controller extends AbstractController{

    private SimulatorView mainView;
    /**
     * Main constructor method.
     * Register the model to the controller.
     * Registers the main views to the controller
     * calls for addButtonActionListener
     *
     * @param model the model
     * @param mainView The Simulator view
     */
    public Controller(SimulatorModel model, SimulatorView mainView) {
        super(model);
        this.mainView = mainView;
        addButtonActionListener();
    }

    /**
     * Adds all the actionListeners to the buttons created in SimulatorView
     */
    private void addButtonActionListener(){
        mainView.start.addActionListener(this);
        mainView.stop.addActionListener(this);
        mainView.tienKeer.addActionListener(this);
        mainView.honderdKeer.addActionListener(this);
    }

    /**
     *  ActionPerformed method checks for button clicks and adds methods to said buttons.
     *  The if, else if statement looks for the different buttons and adds methods
     *  from the Model class to the button
     *
     * @param e checks for the action event
     */
    public void actionPerformed(ActionEvent e) {
        //If start is pressed
        if(e.getSource() == mainView.start){
            model.setRunning(true);
            System.out.println("Startknop Ingedrukt, running = " + model.getRunning());
        }
        //If stop is pressed
        else if(e.getSource() == mainView.stop) {
            model.setRunning(false);
            System.out.println("Stopknop Ingedrukt, running = " + model.getRunning());
        }
        else if(e.getSource() == mainView.tienKeer){
            model.tickTimes10();
        }
        else if(e.getSource() == mainView.honderdKeer){
            model.tickTimes100();
        }
    }
}
