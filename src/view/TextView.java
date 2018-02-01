package view;

import javax.swing.*;
import model.*;

import java.awt.*;

public class TextView extends JPanel {

    JLabel tekst1 = new JLabel("Dit Is Tekst 1");
    JLabel tekst2 = new JLabel("Dit Is Tekst 2");
    JLabel tekst3 = new JLabel("Dit Is Tekst 3");

    public TextView(){

    }

    public void paintComponent(Graphics g) {
        this.add(tekst1);
        this.add(tekst2);
        this.add(tekst3);
    }

    public void updateView(){
        repaint();
    }
}

