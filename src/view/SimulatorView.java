package view;

import model.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * This is the main view, all views get displayed here
 *
 * @author The Button Bashers
 * @version 1.2
 */

public class SimulatorView extends JFrame {

    private CarParkView carParkView;
    private TextView textView;
    private ProfitView profitView;
    private PieChartView pieChart;

    private SimulatorModel model;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel buttonPanel;

    //    Create Jbuttons
    public JButton start;
    public JButton stop;
    public JButton honderdKeer;
    public JButton tienKeer;

    /**
     * The frame gets made and displayed and all the views get added
     *
     * @param model the model
     * @param numberOfFloors amount of floors
     * @param numberOfRows amount of rows
     * @param numberOfPlaces amount of places per row
     */

    public SimulatorView(SimulatorModel model, int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.model = model;
        carParkView = new CarParkView(model, numberOfFloors, numberOfRows, numberOfPlaces);
        textView = new TextView(model);
        profitView = new ProfitView(model);
        pieChart = new PieChartView(model);

        // Initiate frame
        JFrame frame = new JFrame("Parking simulator 1.0");
        this.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(900,500);
        frame.setLayout(new BorderLayout());

        topPanel = new JPanel(new BorderLayout());
        topPanel.setBounds(10, 10, 800, 200);
        topPanel.setSize(800, 200);

        topPanel.add(textView, BorderLayout.PAGE_START);
        topPanel.add(profitView, BorderLayout.PAGE_END);

        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBounds(10, 10, 800, 500);
        centerPanel.setSize(800, 500);

        centerPanel.add(pieChart, BorderLayout.CENTER);
        centerPanel.add(carParkView, BorderLayout.LINE_START);

        //Create buttons to show on JPanel
        start = new JButton("Start");
        stop = new JButton("Pause");
        honderdKeer = new JButton("+100");
        tienKeer = new JButton("+10");

        //  Add buttons to JPanel
        buttonPanel = new JPanel();
        buttonPanel.add(start);
        buttonPanel.add(stop);
        buttonPanel.add(honderdKeer);
        buttonPanel.add(tienKeer);

        frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        frame.pack();
        frame.setVisible(true);
        model.runOnce();
    }
}
