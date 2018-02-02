package controller;

import model.SimulatorModel;
import view.SimulatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends AbstractController implements ActionListener{

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
        mainView.start.addActionListener(this);
        System.out.println("test");
        mainView.Pause.addActionListener(this);
        mainView.tienKeer.addActionListener(this);
        mainView.honderdKeer.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mainView.start){
            System.out.println("start knop pressed");
            model.running = true;
            System.out.println(model.running);
            model.start();
        }
        else if(e.getSource() == mainView.Pause) {
            System.out.println("Thread should now be stopped.");
            if(model.running) {
                model.Paused = true;
                System.out.println(model.Paused);
                System.out.println("Model paused");
            }
            else if(model.Paused){
                model.Paused = false;
                System.out.println(model.Paused);
                System.out.println("Model resumed");
            }

        }
        else if(e.getSource() == mainView.tienKeer){
//            statement here
        }
        else if(e.getSource() == mainView.honderdKeer){
//            statement here
        }
        else{
            System.out.println("something went wrong");
        }
    }
}
