package view;

import model.SimulatorModel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BarChartView extends AbstractDisplayPane {

    private Dimension size;
    private double[] values;
    private String[] labels;
    private Color[] colors;
    private String title;

    public BarChartView(Color[] colors, String title, SimulatorModel model) {
        super(model);
        size = new Dimension(0, 0);
        this.labels = labels;
        this.values = values;
        this.colors = colors;
        this.title = title;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (values == null || values.length == 0) {
            return;
        }

        double minValue = 0;
        double maxValue = 0;
        for (int i = 0; i < values.length; i++) {
            if (minValue > values[i]) {
                minValue = values[i];
            }
            if (maxValue < values[i]) {
                maxValue = values[i];
            }
        }

        Dimension dim = getSize();
        int panelWidth = dim.width;
        int panelHeight = dim.height;
        int barWidth = panelWidth / values.length;

        Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);

        Font labelFont = new Font("Book Antiqua", Font.PLAIN, 14);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

        int titleWidth = titleFontMetrics.stringWidth(title);
        int stringHeight = titleFontMetrics.getAscent();
        int stringWidth = (panelWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, stringWidth, stringHeight);

        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        if (maxValue == minValue) {
            return;
        }
        double scale = (panelHeight - top - bottom) / (maxValue - minValue);
        stringHeight = panelHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);
        for (int j = 0; j < values.length; j++) {
            int valueP = j * barWidth + 1;
            int valueQ = top;
            int height = (int) (values[j] * scale);
            if (values[j] >= 0) {
                valueQ += (int) ((maxValue - values[j]) * scale);
            } else {
                valueQ += (int) (maxValue * scale);
                height = -height;
            }

            g.setColor(colors[j]);
            g.fillRect(valueP, valueQ, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueP, valueQ, barWidth - 2, height);

            int labelWidth = labelFontMetrics.stringWidth(labels[j]);
            stringWidth = j * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(labels[j], stringWidth, stringHeight);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

}