package clock;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.GregorianCalendar;
import javax.swing.*;

/**
 * A component that contains a clock, with a public interface for updating the view(analog/digital), skin, date, hour, minute and second
 * @author Arik Dicks
 */
public class ClockComponent extends JComponent { 
    ClockSettings settings;
    GregorianCalendar calendar;
    ClockDrawer drawer;

    /**
     * Constructs the clock component, drawing either a digital or analog clock depending on the input ClockSettings object
     * @param settings
     */
    public ClockComponent(ClockSettings settings) {
        calendar = new GregorianCalendar();
        this.settings = settings;
        super.setPreferredSize(new Dimension(settings.getWidth(), settings.getHeight()));
        if (settings.getIsAnalog()) {
            setDrawerToAnalog();
        } else {
            setDrawerToDigital();
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawer.draw(g2, calendar);  
    }

    /**
     * Updates the time of the clock and repaints the clock
     * @param calendar The new time of this clock
     */
    public void updateTime(GregorianCalendar calendar) {
        this.calendar = calendar;
        repaint();
    }

    /**
     * Updates the skin of the clock and repaints the clock
     * @param newSkin The new skin of this clock
     */
    public void updateSkin(Skin newSkin) {
        drawer.setSkin(newSkin);
        repaint();
    }

    /**
     * Sets the drawer to an Analog drawer
     */
    private void setDrawerToAnalog() {
        drawer = (ClockDrawer) new AnalogClockDrawer(settings.getSkin(), settings.getWidth(), settings.getHeight(), settings.getClockSize());
    }

    /**
     * Sets the drawer to a Digital drawer
     */
    private void setDrawerToDigital() {
        drawer = (ClockDrawer) new DigitalClockDrawer(settings.getSkin(), settings.getWidth(), settings.getHeight(), settings.getClockSize());
    }

    /**
     * Sets the clock to be analog and repaints the clock
     */
    public void swapToAnalog() {
        setDrawerToAnalog();
        repaint();
    }

    /**
     * Sets the clock to be digital and repaints the clock
     */
    public void swapToDigital() {
        setDrawerToDigital();
        repaint();
    }
}