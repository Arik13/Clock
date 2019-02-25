package clock;

/**
 * An interface for doing something upon changes in time
 * @author Arik Dicks
 */
public interface TimeListener {

    /**
     * Do something when a time change occurs
     */
    public void timeChanged();
}