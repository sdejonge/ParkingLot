package model;

public class MainTread extends Thread {

    private SimulatorModel sim;

    public MainTread(SimulatorModel sim) {
        System.out.println("Hello from a thread");
        this.sim = sim;
    }

    public void run() {
        System.out.println("MainThread.run() uitgevoerd");
        for( ; ; ) { //een oneindige loop
            if (sim.getRunning()) {
                sim.run();
            }
            else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}