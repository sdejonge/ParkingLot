package view;

import model.SimulatorModel;

import javax.swing.*;
import java.awt.*;

public class LegendView extends AbstractDisplayPane{

    private Dimension size;
    private JLabel grey, cyan, red, blue;

    /**
     * Constructor of the class
     * Sends view to add to the ArrayList of views
     *
     * @param model
     */
    public LegendView(SimulatorModel model) {
        super(model);
        size = new Dimension(0, 0);
        grey = new JLabel("Open spots");
        cyan = new JLabel("Subscriber spots");
        red = new JLabel("Regular visitors");
        blue = new JLabel("Subcription visitors");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor(Color.WHITE);
//        g.fillRect(0, 0, 150, 100);

        grey.setBounds(20, 5, 200, 20);
        this.add(grey);
        g.setColor(Color.GRAY);
        g.fillRect(5, 10, 10, 10);

        cyan.setBounds(20, 25, 200, 20);
        this.add(cyan);
        g.setColor(Color.cyan);
        g.fillRect(5, 30, 10, 10);

        blue.setBounds(20, 45, 200, 20);
        this.add(blue);
        g.setColor(Color.blue);
        g.fillRect(5, 50, 10, 10);

        red.setBounds(20, 65, 200, 20);
        this.add(red);
        g.setColor(Color.red);
        g.fillRect(5, 70, 10, 10);

    }


    /**
     * Sets the preferred size for the pie chart
     *
     * @return new Dimension
     */

    public Dimension getPreferredSize() {
        return new Dimension(200, 100);
    }
}
