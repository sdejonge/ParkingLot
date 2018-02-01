package view;

import controller.AbstractController;
import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends JFrame {

    private CarParkView carParkView;
    private TextView textView;
    private AbstractController controller;

    private SimulatorModel model;
    private JPanel carPanel;

    public SimulatorView(SimulatorModel model, int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.model = model;
        carParkView = new CarParkView(model, numberOfFloors, numberOfRows, numberOfPlaces);
        textView = new TextView(model);
        controller = new Controller(model);

        // Initiate frame
        JFrame frame = new JFrame("Parking simulator 1.0");
        this.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(900,500);

        carPanel = new JPanel(new BorderLayout());
        carPanel.add(carParkView, BorderLayout.CENTER);

        frame.getContentPane().add(textView, BorderLayout.PAGE_START);
        frame.getContentPane().add(carPanel, BorderLayout.CENTER);

        pack();
        frame.setVisible(true);
        updateView();
    }

    public void updateView() {
        textView.updateView(model);
        carParkView.updateView();
    }

}
