package controller;

import model.SimulatorModel;
import view.SimulatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends AbstractController{

    private SimulatorView mainView;
    /**
     * Register the model to the controller.
     *
     * @param model the model
     */
    public Controller(SimulatorModel model, SimulatorView mainView) {
        super(model);
        this.mainView = mainView;
        addButtonActionListener();
    }

    public void addButtonActionListener(){
        //Add action listeners to the buttons created in SimulatorView.
        mainView.start.addActionListener(this);
        mainView.stop.addActionListener(this);
        mainView.tienKeer.addActionListener(this);
        mainView.honderdKeer.addActionListener(this);
    }

    @Override
    //Check for preformed actions
    public void actionPerformed(ActionEvent e) {
        //If start is pressed
        if(e.getSource() == mainView.start){
        //Check running to make sure Sim is not already running
            while(!model.running){
                model.start();
            }
        }
        //If stop is pressed
        else if(e.getSource() == mainView.stop) {
            System.out.println("Thread should now be stopped.");
            model.pause();
        }
        else if(e.getSource() == mainView.tienKeer){

        }
        else if(e.getSource() == mainView.honderdKeer){

        }
        else{
            System.out.println("Something went wrong");
        }
    }
}
