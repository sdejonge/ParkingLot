package controller;

import model.SimulatorModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends AbstractController implements ActionListener{

    /**
     * Register the model to the controller.
     *
     * @param model the model
     */
    public Controller(SimulatorModel model) {
        super(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
