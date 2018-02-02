package view;

import model.*;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends JFrame {

    private CarParkView carParkView;
    private TextView textView;

    private SimulatorModel model;
    private JPanel carPanel;
    private JPanel buttonPanel;

    //    Create Jbuttons
    public JButton start;
    public JButton Pause;
    public JButton honderdKeer;
    public JButton tienKeer;

    public SimulatorView(SimulatorModel model, int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.model = model;

        start = new JButton("start");
        Pause = new JButton("Pause");
        honderdKeer = new JButton("+100");
        tienKeer = new JButton("+10");

        carParkView = new CarParkView(model, numberOfFloors, numberOfRows, numberOfPlaces);
        textView = new TextView(model);

        buttonPanel = new JPanel();
        buttonPanel.add(start);
        buttonPanel.add(Pause);
        buttonPanel.add(honderdKeer);
        buttonPanel.add(tienKeer);

        // Initiate frame
        JFrame frame = new JFrame("Parking simulator 1.0");
        this.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(900,500);

        carPanel = new JPanel(new BorderLayout());
        carPanel.add(carParkView, BorderLayout.CENTER);

        frame.getContentPane().add(textView, BorderLayout.PAGE_START);
        frame.getContentPane().add(carPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        pack();
        frame.setVisible(true);
        updateView();
    }

    public void updateView() {
        carParkView.updateView();
        textView.updateView(model);
    }

}
