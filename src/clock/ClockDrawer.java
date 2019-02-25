package clock;

import java.awt.Graphics2D;
import java.util.GregorianCalendar;

public interface ClockDrawer {
    /**
     * Draws the body of a clock
     * @param g2 The Graphics2D object that will be drawn on
     * @param calendar The given time
     */
    public void draw(Graphics2D g2, GregorianCalendar calendar);

    /**
     * Sets the skin of the body of the clock
     * @param skin
     */
    public void setSkin(Skin skin);
}