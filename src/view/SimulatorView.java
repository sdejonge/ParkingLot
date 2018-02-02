package view;

import model.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SimulatorView extends JFrame {

    private CarParkView carParkView;
    private TextView textView;
    private ProfitView profitView;

    private SimulatorModel model;

    private JPanel topPanel;

    private JPanel carPanel;
    private JPanel buttonPanel;

    //    Create Jbuttons
    public JButton start;
    public JButton Pause;
    public JButton honderdKeer;
    public JButton tienKeer;

    public SimulatorView(SimulatorModel model, int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.model = model;

        //Create buttons to show on JPanel
        start = new JButton("start");
        Pause = new JButton("Pause");
        honderdKeer = new JButton("+100");
        tienKeer = new JButton("+10");

        carParkView = new CarParkView(model, numberOfFloors, numberOfRows, numberOfPlaces);
        textView = new TextView(model);
        profitView = new ProfitView(model);

        //Add buttons to JPanel
        buttonPanel = new JPanel();
        buttonPanel.add(start);
        buttonPanel.add(Pause);
        buttonPanel.add(honderdKeer);
        buttonPanel.add(tienKeer);

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
      
        //add panels to frame
        frame.getContentPane().add(textView, BorderLayout.PAGE_START);
        frame.getContentPane().add(carPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
        frame.getContentPane().add(carParkView, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}
