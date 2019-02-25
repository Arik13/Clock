package clock;

/**
 * A Listener that listens for time changes in a time thread, and notifies it's controller
 * @author Arik Dicks
 */
public class TimeThreadListener implements TimeListener {
    private final ClockController controller;

    /**
     * Constructs a TimeThreadListener with a reference to its controller
     * @param controller
     */
    public TimeThreadListener(ClockController controller) {
        this.controller = controller;
    }
    @Override
    public void timeChanged() {
        controller.updateClocksTime();
    }
}