package view;

import model.SimulatorModel;


import javax.swing.*;
import java.util.*;
import java.awt.*;

class PieChartView extends AbstractDisplayPane {
    private Dimension size;


    public PieChartView(SimulatorModel model) {
        super(model);
        size = new Dimension(0, 0);
    }

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

    public Dimension getPreferredSize() {
        return new Dimension(300, 100);
    }

}