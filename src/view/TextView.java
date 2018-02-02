package view;

import javax.swing.*;
import model.*;

import java.awt.*;

public class TextView extends JPanel {

    private SimulatorModel model;

    private JLabel redLabel = new JLabel("Red cars: ");
    private JLabel blueLabel = new JLabel("Blue cars: ");
    private JLabel totalCarsLabel = new JLabel("Totaal aantal auto's: ");
    private JLabel profitLabel = new JLabel("Current profit: ");
    private JLabel red = new JLabel("");
    private JLabel blue = new JLabel("");
    private JLabel totalCars = new JLabel("");
    private JLabel profit = new JLabel("");
    private JLabel time = new JLabel("");
    private JLabel day = new JLabel("");

    public TextView(SimulatorModel model){
        this.model = model;
    }

    public void paintComponent(Graphics g) {
        this.add(redLabel);
        this.add(red);
        this.add(blueLabel);
        this.add(blue);
        this.add(totalCarsLabel);
        this.add(totalCars);
        this.add(profitLabel);
        this.add(profit);
        this.add(day);
        this.add(time);
        red.setPreferredSize(new Dimension(50, 25));
        blue.setPreferredSize(new Dimension(50, 25));
        totalCars.setPreferredSize(new Dimension(50, 25));
        profit.setPreferredSize(new Dimension(100,25));
        day.setPreferredSize(new Dimension(65,25));
        time.setPreferredSize(new Dimension(40,25));
    }

    public void updateView(SimulatorModel model){
        red.setText(String.valueOf(model.redCars));
        blue.setText(String.valueOf(model.blueCars));
        totalCars.setText(String.valueOf(model.totalCars));
        double profitValue = model.profit; //Save the model.profit value into the variable profitValue to work with.
        double roundProfit = Math.round(profitValue * 100.0) / 100.0; //Round the value of ProfitValue and display it with 2 decimals
        profit.setText("€ " + (String.valueOf(roundProfit)));
        day.setText(String.valueOf(model.day_text));
        time.setText(String.valueOf(model.hour) + ":" + String.valueOf(model.minute));
        repaint();
    }
}