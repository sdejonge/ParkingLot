package view;

import model.*;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends JFrame {

    private AbstractDisplayPane carParkView;
    private AbstractDisplayPane textView;
    private AbstractDisplayPane profitView;
    private AbstractDisplayPane pieChart;

    private SimulatorModel model;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel buttonPanel;

    //    Create Jbuttons
    public JButton start;
    public JButton Pause;
    public JButton honderdKeer;
    public JButton tienKeer;

    public SimulatorView(SimulatorModel model, int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.model = model;
        carParkView = new CarParkView(model, numberOfFloors, numberOfRows, numberOfPlaces);
        textView = new TextView(model);
        profitView = new ProfitView(model);
        pieChart = new PieChartView(model);

        // Initiate frame
        JFrame frame = new JFrame("Parking simulator 1.0");
        this.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);
        frame.setLayout(new BorderLayout());

        topPanel = new JPanel(new BorderLayout());
        topPanel.setBounds(10, 10, 800, 200);
        topPanel.setSize(800, 200);

        topPanel.add(textView, BorderLayout.PAGE_START);
        topPanel.add(profitView, BorderLayout.CENTER);

        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBounds(10, 10, 800, 500);
        centerPanel.setSize(800, 500);

        centerPanel.add(pieChart, BorderLayout.CENTER);
        centerPanel.add(carParkView, BorderLayout.LINE_START);

        //Create buttons to show on JPanel
        start = new JButton("start");
        Pause = new JButton("Pause");
        honderdKeer = new JButton("+100");
        tienKeer = new JButton("+10");

        //  Add buttons to JPanel
        buttonPanel = new JPanel();
        buttonPanel.add(start);
        buttonPanel.add(Pause);
        buttonPanel.add(honderdKeer);
        buttonPanel.add(tienKeer);

        frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        frame.pack();
        frame.setVisible(true);
        model.runOnce();
    }

    public void updateView(){
        repaint();
    }
}
