package view;

import model.SimulatorModel;

import javax.swing.*;

public class ProfitView extends AbstractDisplayPane {
    private SimulatorModel model;
    private JLabel profitLabel = new JLabel("Current total profit: ");
    private JLabel estimatedLabel = new JLabel("Estimated profit: ");
    private JLabel nextLabel = new JLabel("Next profit: ");
    private JLabel profit = new JLabel("");
    private JLabel mondayProfit = new JLabel("");
    private JLabel estimatedProfit = new JLabel("");
    private JLabel nextProfit = new JLabel("");

    public ProfitView(SimulatorModel model) {
        super(model);
        this.model = model;
    }

    public void updateView(){
        this.add(profitLabel);
        this.add(profit);
        this.add(mondayProfit);
        this.add(estimatedLabel);
        this.add(estimatedProfit);
        this.add(nextLabel);
        this.add(nextProfit);
        profit.setSize(100,25);
        mondayProfit.setSize(40,25);
        double profitValue = model.totalProfit; //Save the model.totalProfit value into the variable profitValue to work with.
        double roundProfit = Math.round(profitValue * 100.0) / 100.0; //Round the value of ProfitValue and display it with 2 decimals
        profit.setText("€ " + (String.valueOf(roundProfit)));
        //mondayProfit.setText(String.valueOf(model.weekProfit[0]));
        double estValue = model.estimateProfit;
        double roundEstimate = Math.round(estValue * 100.0) / 100.0;
        estimatedProfit.setText(String.valueOf(roundEstimate));
        double dayValue = model.nextProfit;
        double roundDay = Math.round(dayValue * 100.0) / 100.0;
        nextProfit.setText(String.valueOf(roundDay));
    }
}