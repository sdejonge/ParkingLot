package view;


import javax.swing.*;
import java.util.*;
import java.awt.*;


//WORK IN PROGRESS
class PieChartView extends JPanel {
    String name1 = "1";
    String name2 = "2";
    String name3 = "3";
    int count1 = 1;   // optional
    int count2 = 1;   // optional
    int count3 = 0;   // optional

    public PieChartView() {}

    // Redefines JPanel's paintComponent to draw this pie chart
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        int x = w/2;
        int y = h/2;
        int r = Math.min(w, h) / 4;
        drawPieChart(g, x, y, r);
        drawLegend(g, x, y, r);
    }

    // Draws the counts and the corresponding color squares.
    private void drawLegend(Graphics g, int x, int y, int r)
    {
        // Display the counts:
        y += (r + 20);
        g.setColor(Color.BLACK);
        g.drawString("" + count1, x - r, y);
        g.drawString("" + count2, x, y);
        g.drawString("" + count3, x + r, y);
        // Display the color squares:
        y += 5;
        x -= 2;
        g.setColor(Color.RED);
        g.fillRect(x - r, y, 10, 10);
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 10, 10);
        g.setColor(Color.BLUE);
        g.fillRect(x + r, y, 10, 10);
    }


    private void drawPieChart(Graphics g, int x, int y, int r)
    {
        int total = count1 + count2 + count3;
        int fromDegree = 0;
        if (total > 0)
        {
            int degrees;
            g.setColor(Color.RED);
            degrees = countToDegrees(count1, total);
            drawSector(g, x, y, r, fromDegree, degrees);
            fromDegree += degrees;
            g.setColor(Color.GREEN);
            degrees = countToDegrees(count2, total);
            drawSector(g, x, y, r, fromDegree, degrees);
            fromDegree += degrees;
            g.setColor(Color.BLUE);
            degrees = Math.max(360 - fromDegree, 0);
            drawSector(g, x, y, r, fromDegree, degrees);
        }
        else
        {
            g.setColor(Color.LIGHT_GRAY);
            drawSector(g, x, y, r, fromDegree, 360);
        }
    }
    // Returns the number of degrees in a pie slice that
    // corresponds to count / total, rounded to the nearest integer.
    private int countToDegrees(double count, double total)
    {
        return (int)(360.0 + count / total + 0.5);
    }
    // Draws a sector, centered at x, y, of radius r,
    // of angle measure degrees, starting at fromDegree.
    private void drawSector(Graphics g, int x, int y, int r, int fromDegree, int degrees)
    {
        if (degrees > 359)
            g.fillOval(x - r, y - r, 2 * r, 2 * r);
        else
            g.fillArc(x - r, y - r, 2 * r, 2 * r, fromDegree, degrees);
    }

}


