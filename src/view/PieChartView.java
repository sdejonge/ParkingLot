package view;

import model.SimulatorModel;


import javax.swing.*;
import java.util.*;
import java.awt.*;

/**
 * The view where the pie chart gets generated
 *
 * @author The Button Bashers
 * @version 1.2
 */

class PieChartView extends AbstractDisplayPane {
    private Dimension size;

    /**
     * Calls constructor of super class and set size
     *
     * @param model the model
     */

    public PieChartView(SimulatorModel model) {
        super(model);
        size = new Dimension(0, 0);
    }

    /**
     * Paints the pie chart
     *
     * @param g the graphics
     */

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 200, 200);
        if(model.totalCars > 0) {
            int blueDegree = model.getTotalBlueSpots();
            int redDegree = model.getTotalRedSpots();
            g.setColor(Color.BLUE);
            g.fillArc(10, 10, 180, 180, 0, blueDegree);
            g.setColor(Color.RED);
            g.fillArc(10, 10, 180, 180, blueDegree, redDegree);
        }
    }

    /**
     * Sets the preferred size for the pie chart
     *
     * @return new Dimension
     */

    public Dimension getPreferredSize() {
        return new Dimension(210, 100);
    }

}