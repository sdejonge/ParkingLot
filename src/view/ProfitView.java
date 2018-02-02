package view;

import model.SimulatorModel;

import javax.swing.*;
import java.awt.*;

public class ProfitView extends AbstractDisplayPane {
    private SimulatorModel model;
    private JLabel profitLabel = new JLabel("Current profit: ");
    private JLabel profit = new JLabel("");
    private JLabel mondayProfit = new JLabel("");

    public ProfitView(SimulatorModel model) {
        super(model);
        this.model = model;
    }


    public void updateView(){
        this.add(profitLabel);
        this.add(profit);
        this.add(mondayProfit);
        profit.setSize(100,25);
        mondayProfit.setSize(40,25);
        double profitValue = model.profit; //Save the model.profit value into the variable profitValue to work with.
        double roundProfit = Math.round(profitValue * 100.0) / 100.0; //Round the value of ProfitValue and display it with 2 decimals
        profit.setText("€ " + (String.valueOf(roundProfit)));
        mondayProfit.setText(String.valueOf(model.weekProfit[0]));
    }
}
