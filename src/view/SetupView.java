package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class SetupView extends JFrame {

    private boolean setupKlaar = false; //een boolean waarin wordt bijgehouden of de setup klaar is
    String[] teksten =  new String[3]; //maakt een array strings om de teksten van de invoervelden in op te slaan

    public SetupView(){
        //Voeg een knopje toe
        JButton button = new JButton("Start Simulator");
        this.add(button,BorderLayout.NORTH);

        //voeg textvelden (labels) toe (geen invoerveld)
        JLabel labelFloors = new JLabel("Floors: ");
        JLabel labelRows = new JLabel("Rows: ");
        JLabel labelPlaces = new JLabel("Places: ");

        //Voeg invoervelden toe
        JTextField invoerFloors = new JTextField();
        JTextField invoerRows = new JTextField();
        JTextField invoerPlaces = new JTextField();

        //maak panelen aan
        JPanel panelFloors = new JPanel();
        JPanel panelRows = new JPanel();
        JPanel panelPlaces = new JPanel();
        JPanel mainPanel = new JPanel();

        //voeg de componenten toe aan de jpanelen
        panelFloors.add(labelFloors);
        panelFloors.add(invoerFloors, BorderLayout.CENTER);

        panelRows.add(labelRows);
        panelRows.add(invoerRows, BorderLayout.CENTER);

        panelPlaces.add(labelPlaces);
        panelPlaces.add(invoerPlaces, BorderLayout.CENTER);

        //voeg de jpanelen toe aan de mainPanel
        panelFloors.setLayout(new BoxLayout(panelFloors, BoxLayout.LINE_AXIS)); //Set de layout van de jpanel
        mainPanel.add(panelFloors);
        panelRows.setLayout(new BoxLayout(panelRows, BoxLayout.LINE_AXIS)); //Set de layout van de jpanel
        mainPanel.add(panelRows);
        panelPlaces.setLayout(new BoxLayout(panelPlaces, BoxLayout.LINE_AXIS)); //Set de layout van de jpanel
        mainPanel.add(panelPlaces);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)); //Set de layout van de mainPanel

        //voeg de mainpanel toe aan de jframe
        this.add(mainPanel);
        pack(); //Maak een scherm van alle velden(knoppen, tekstvelden etc)
        this.setTitle("Simulator-inator");

        //Voegt een actionlissener to aan JButton button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);//sluit het setup venster
                setupKlaar = true;
                teksten[0] = invoerFloors.getText(); //leest de tekst geschreven in het tekstveld
                teksten[1] = invoerRows.getText(); //leest de tekst geschreven in het tekstveld
                teksten[2] = invoerPlaces.getText(); //leest de tekst geschreven in het tekstveld
                System.out.println("Setup is klaar vanuit de actionlistener");
            }
        });

        setSize(300, 150);
        setVisible(true); //Opent het scherm dat net aangemaakt is
    }

    //Wachten totdat de gebruiker klaar is in de SetupView
    public void WachtenOpGebruiker() {
        while (!setupKlaar) {Slaap(50);} //een lege while loop, wachten totdat de startknop is ingedrukt, slaapt herhaallijk 50 millisecondes om overbelasting te voorkomen
        System.out.println("setup klaar vanuit SetupView");
        setVisible(false);
    }

    public int[] setupWaardes() {
        int[] a = {0, 0, 0}; //Maakt een array aan met de waardes die gaan worden teruggegeven

        //TODO fix fouten bij hoger aantal rows
        a[0] = Parse(teksten[0], 3);
        //a[1] = Parse(teksten[1], 6); Rows instellen zorgt voor problemen, dit is uitgeschakeld totdat het kan worden gefixed
        a[1] = 6; //Rows krijgt nu altijd een standaardwaarde van 6
        a[2] = Parse(teksten[2], 30);

        return a;
    }

    private void Slaap(int millisecondes){ // een methode die een gegeven aantal millisecondes pauseerd
        try {
            Thread.sleep(millisecondes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int Parse(String s, int Reserve) { //een methode die een getal van een string in een int plaats, wanneer dit niet kan, geeft het de reserve waarde terug
        try {
            int intP = Integer.parseInt(s);
            return intP; }
         catch (NumberFormatException e) {
            return Reserve;
        }
    }
}
