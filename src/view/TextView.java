package view;

import javax.swing.*;
import model.*;

import java.awt.*;

public class TextView extends JPanel {

    private SimulatorModel model;

    private JLabel redLabel = new JLabel("Red cars: ");
    private JLabel blueLabel = new JLabel("Blue cars: ");
    private JLabel totalLabel = new JLabel("Total cars: ");
    private JLabel queueLabel = new JLabel("Cars in queue: ");
    private JLabel red = new JLabel("");
    private JLabel blue = new JLabel("");
    private JLabel total = new JLabel("");
    private JLabel queue = new JLabel("");

    private int totalCars;

    public TextView(SimulatorModel model){
        this.model = model;
    }

    public void paintComponent(Graphics g) {
        this.add(redLabel);
        this.add(red);
        this.add(blueLabel);
        this.add(blue);
        this.add(totalLabel);
        this.add(total);
        this.add(queueLabel);
        this.add(queue);
        red.setPreferredSize(new Dimension(50, 25));
        blue.setPreferredSize(new Dimension(50, 25));
        total.setPreferredSize(new Dimension(50, 25));
        queue.setPreferredSize(new Dimension(50, 25));
    }

    public void updateView(SimulatorModel model){
        totalCars = model.redCars + model.blueCars;
        red.setText(String.valueOf(model.redCars));
        blue.setText(String.valueOf(model.blueCars));
        total.setText(String.valueOf(totalCars));
        repaint();
    }
}

