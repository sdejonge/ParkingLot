package model;

/**
 * Creates a mainThread for the Simulation. The model of the Simulation runs in this thread.
 */
public class MainTread extends Thread {

    private SimulatorModel sim;

    /**
     * Created constructor for the main thread
     * @param sim The main model
     */
    public MainTread(SimulatorModel sim) {
        System.out.println("Hello from a thread");
        this.sim = sim;
    }

    /**
     * Runs the main thread
     * Sleeps when running is false
     * This is used to pause the simulation
     */
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