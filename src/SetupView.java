import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SetupView extends JFrame {

    private boolean setupKlaar = false; //een boolean waarin wordt bijgehouden of de setup klaar is

    public SetupView(){
        JButton button = new JButton("Start Simulator");
        this.add(button);
        pack();
        this.setTitle("Simulator-inator");

        //Voegt een actionlissener to aan JButton button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);//sluit het setup venster
                setupKlaar = true;
                System.out.println("Setup is klaar vanuit de actionlistener");
            }
        });

        setVisible(true);

        while (!setupKlaar) {Slaap(50);} //een lege while loop, wachten totdat de knop is ingedrukt
        System.out.println("setup klaar vanuit SetupView");
        setVisible(false);
    }

    public boolean setupIsKlaar() {
        if (setupKlaar == true){
            setVisible(false);
        }

        return setupKlaar;
    }

    private void Slaap(int millisecondes){
        try {
            Thread.sleep(millisecondes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
