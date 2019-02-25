package clock;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Calculates several fields that are useful for all the digital drawers
 * @author Arik Dicks
 */
public class DigitalClockDrawer implements ClockDrawer {
    private static int WIDTH;
    private static int HEIGHT;
    private static int CLOCK_SIZE;
    private final Rectangle body;
    private final int hBorder;
    private final int vBorder;
    private final int numOfSymbols = 11;
    protected Skin skin;
    private Font font;

    /**
     * Constructs a drawer that can draw a digital clock
     * @param skin The skin of the clock
     * @param width The width of the clock container
     * @param height The height of the clock container
     * @param clockSize The size of the clock
     */
    public DigitalClockDrawer(Skin skin, int width, int height, int clockSize) {
        this.skin = skin;
        WIDTH = width;
        HEIGHT = height;
        CLOCK_SIZE = clockSize;
        hBorder = (int)Math.round(WIDTH*0.1);
        vBorder = (int)Math.round(HEIGHT*0.33);
        body = new Rectangle(hBorder, vBorder, WIDTH-2*hBorder, HEIGHT-2*vBorder);
        font = new Font(Font.MONOSPACED, Font.PLAIN, CLOCK_SIZE*11);
    }
    
    /**
     * Changes the skin of the clock
     * @param skin Any skin
     */
    @Override
    public void setSkin(Skin skin) {
        this.skin = skin;
    }
    /**
     * Draws the digital clock at the given time
     * @param g2 The Graphics2D object to be drawn on
     * @param calendar The given time
     */
    @Override
    public void draw(Graphics2D g2, GregorianCalendar calendar) {
        drawBody(g2);
        drawDate(g2, calendar);
        drawTime(g2, calendar);
    }
    
    /**
     * Draws the body of the digital clock
     * @param g2 The Graphics2D object to be drawn on
     */
    private void drawBody(Graphics2D g2) {
        g2.setColor(skin.BACKGROUND_COLOR);
        g2.fillRect(0, 0, WIDTH, HEIGHT);
        g2.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(skin.PRIMARY_FILL_COLOR);
        g2.fill(body);
        g2.setColor(skin.EDGE_COLOR);
        g2.draw(body);
        g2.setColor(skin.NUMBER_COLOR);
    }
    /**
     * Draws the date of the digital clock
     * @param g2 The Graphics2D object to be drawn on
     * @param calendar The given time
     */
    private void drawDate(Graphics2D g2, GregorianCalendar calendar) {     
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] monthsInYear = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String date = daysOfWeek[dayOfWeek-1] + " " + monthsInYear[month] + " " + dayOfMonth + ", " + year;
        Font dateFont = new Font(Font.MONOSPACED, Font.PLAIN, CLOCK_SIZE*4);
        g2.setFont(dateFont);
        FontMetrics metrics = g2.getFontMetrics(dateFont);
        int dateWidth = metrics.stringWidth(date);
        int dateHeight = metrics.getHeight();
        double bodyMinX = body.getMinX();
        double bodyWidth = body.getWidth();
        double bodyHeight = body.getHeight();
        double bodyMaxY = body.getMaxY();
        
        int dateX = (int)Math.round((bodyWidth/2 - dateWidth/2.0)+bodyMinX);
        int dateY = (int)Math.round(bodyMaxY - bodyHeight*0.15);
        g2.drawString(date, dateX, dateY);
    }
    
    /**
     * Draws the time of the digital clock
     * @param g2 The Graphics2D object to be drawn on
     * @param calendar The given time
     */
    private void drawTime(Graphics2D g2, GregorianCalendar calendar) {
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        
        double timeBoundsRatioX = 0.9;  // The ratio of the width of the time to the digital clock body
        double timeIndentRatioX = 0.05; // The ratio of the time's horizontal indent, to the body height
        double timeIndentRatioY = 0.40; // The ratio of the time's vertical indent from the top, to the body height
        int timeWidth = (int)Math.round(timeBoundsRatioX*body.getWidth()); 
        
        // The y at which each digit will be drawn
        int timeBottomY = (int)Math.round(body.getMaxY() - (timeIndentRatioY*body.getHeight())); 
        
        // The x at which the first digit will be drawn
        int timeLeftX = (int)Math.round(body.getMinX() + (timeIndentRatioX*body.getWidth()));
        
        // The increase in the x position of each consecutive digit 
        int incrementX = (int)Math.round(timeWidth/(double)numOfSymbols);
        
        // The formatted time
        String time = (hour > 9) ? ("" + hour) : (" " + hour);
        time += (minute > 9) ? (":" + minute) : (":0" + minute);
        time += (second > 9) ? (":" + second) : (":0" + second);
        time += (calendar.get(Calendar.AM_PM) == Calendar.AM) ? (" AM") : (" PM");
        
        // Initialize style
        font = new Font(Font.MONOSPACED, Font.PLAIN, CLOCK_SIZE*11);
        g2.setStroke(new BasicStroke(3));
        g2.setFont(font);
        g2.setColor(skin.NUMBER_COLOR);
        
        // Draw each digit
        for (int i = 0; i < time.length(); i++) {
            int x = (i)*incrementX + 2 + timeLeftX;
            g2.drawString("" + time.charAt(i), x, timeBottomY);
        }
    }   
}