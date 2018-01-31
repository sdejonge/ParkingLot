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
        JLabel label1 = new JLabel("Floors: ");
        JLabel label2 = new JLabel("Rows: ");
        JLabel label3 = new JLabel("Places: ");

        //Voeg invoervelden toe
        JTextField tekst1 = new JTextField();
        JTextField tekst2 = new JTextField();
        JTextField tekst3 = new JTextField();

        //maak panelen aan
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel mainPanel = new JPanel();

        //voeg de componenten toe aan de jpanelen
        panel1.add(label1);
        panel1.add(tekst1, BorderLayout.CENTER);

        panel2.add(label2);
        panel2.add(tekst2, BorderLayout.CENTER);

        panel3.add(label3);
        panel3.add(tekst3, BorderLayout.CENTER);

        //voeg de jpanelen toe aan de mainPanel
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.LINE_AXIS)); //Set de layout van de jpanel
        mainPanel.add(panel1);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.LINE_AXIS)); //Set de layout van de jpanel
        mainPanel.add(panel2);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.LINE_AXIS)); //Set de layout van de jpanel
        mainPanel.add(panel3);

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
                teksten[0] = tekst1.getText(); //leest de tekst geschreven in het tekstveld
                teksten[1] = tekst2.getText(); //leest de tekst geschreven in het tekstveld
                teksten[2] = tekst3.getText(); //leest de tekst geschreven in het tekstveld
                System.out.println(teksten[0]);
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


        a[0] = Parse(teksten[0], 3);
        a[1] = Parse(teksten[1], 6);
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

    private int Parse(String s, int Reserve) {
        try {
            int intP = Integer.parseInt(s);
            return intP; }
         catch (NumberFormatException e) {
            return Reserve;
        }
    }
}
