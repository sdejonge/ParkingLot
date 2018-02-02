package view;

import javax.swing.*;
import model.*;

import java.awt.*;

public class TextView extends AbstractDisplayPane {

    private SimulatorModel model;

    private JLabel redLabel = new JLabel("Red cars: ");
    private JLabel blueLabel = new JLabel("Blue cars: ");
    private JLabel totalCarsLabel = new JLabel("Totaal aantal auto's: ");
    private JLabel red = new JLabel("");
    private JLabel blue = new JLabel("");
    private JLabel totalCars = new JLabel("");
    private JLabel time = new JLabel("");
    private JLabel day = new JLabel("");

    public TextView(SimulatorModel model){
        super(model);
        this.model = model;
    }
    
    public void updateView(){
        this.add(redLabel);
        this.add(red);
        this.add(blueLabel);
        this.add(blue);
        this.add(totalCarsLabel);
        this.add(totalCars);
        this.add(day);
        this.add(time);
        red.setSize(50, 25);
        blue.setSize(50,25);
        totalCars.setSize(50,25);
        day.setSize(65,25);
        time.setSize(40,50);
        red.setText(String.valueOf(model.redCars));
        blue.setText(String.valueOf(model.blueCars));
        totalCars.setText(String.valueOf(model.totalCars));
        day.setText(String.valueOf(model.day_text));
        time.setText(String.valueOf(model.hour) + ":" + String.valueOf(model.minute));
    }
}