package view;

import model.*;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends JFrame {

    private CarParkView carParkView;

    private SimulatorModel model;
    private JPanel carPanel;

    public SimulatorView(SimulatorModel model, int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.model = model;

        // Initiate frame
        JFrame frame = new JFrame("Parking simulator 1.0");
        this.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(900,500);

        carParkView = new CarParkView(model, numberOfFloors, numberOfRows, numberOfPlaces);
        carPanel = new JPanel(new BorderLayout());
        carPanel.add(carParkView, BorderLayout.CENTER);

        frame.add(carPanel);

        pack();
        frame.setVisible(true);
        updateView();
    }

    public void updateView() {
        carParkView.updateView();
    }

}
