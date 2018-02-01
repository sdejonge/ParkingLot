package view;

import javax.swing.*;
import model.*;

import java.awt.*;

public class TextView extends JPanel {

    private SimulatorModel model;

    private JLabel redLabel = new JLabel("Red cars: ");
    private JLabel blueLabel = new JLabel("Blue cars: ");
    private JLabel red = new JLabel("");
    private JLabel blue = new JLabel("");

    public TextView(SimulatorModel model){
        this.model = model;
        new GridBagLayout();
    }

    public void paintComponent(Graphics g) {
        this.add(redLabel);
        this.add(red);
        this.add(blueLabel);
        this.add(blue);
        red.setPreferredSize(new Dimension(50, 25));
        blue.setPreferredSize(new Dimension(50, 25));
    }

    public void updateView(SimulatorModel model){
        red.setText(String.valueOf(model.redCars));
        blue.setText(String.valueOf(model.blueCars));
        repaint();
    }
}

