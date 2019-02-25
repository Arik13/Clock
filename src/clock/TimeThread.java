package clock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A thread that watches the time and notifies its listeners when the seconds change
 * @author Arik Dicks
 */
public class TimeThread extends Thread {
    private static GregorianCalendar calendar;
    private long lastSecond = 0;
    private final ArrayList<TimeListener> listenerArrayList= new ArrayList<>();
    @Override
    public void run() {
        try {
            while(true) {
                calendar = (GregorianCalendar)Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                int currentSecond = calendar.get(Calendar.SECOND);
                if(currentSecond != lastSecond) {
                    lastSecond = currentSecond;
                    notifyListeners();
                    sleep(900);
                }
            }
        } catch (InterruptedException e) {}  
    }

    /**
     * Adds a TimeListener to this thread
     * @param listener
     */
    public void addTimeListener(TimeListener listener) {
        listenerArrayList.add(listener);
    }

    /**
     * Gets the current time as a Gregorian Calendar
     * @return the current time as a Gregorian Calendar
     */
    public GregorianCalendar getCalendar() {
        return calendar;
    }
    private void notifyListeners() {
        if (listenerArrayList.isEmpty())
            return;
        for (TimeListener listener : listenerArrayList) {
            listener.timeChanged();
        }
    }
}