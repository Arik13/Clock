package clock;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Responsible for drawing an analog clock on a Graphics2D object
 * @author Arik Dicks
 */
public class AnalogClockDrawer implements ClockDrawer {
    private static final Point[] CIRCLE_POINTS = new Point[60];
    private static final Point[] TICK_POINTS = new Point[60];
    private static final double THETA_INCREMENT = ((2*Math.PI)/60);
    private static final double INITIAL_THETA = Math.PI/2;
    private static final double MINUTE_TICK_RATIO = 19.0/20;
    private static final double HOUR_TICK_RATIO = 18.0/20; 
    private static final double HOUR_HAND_LENGTH_RATIO = 0.5;
    private static final double MINUTE_HAND_LENGTH_RATIO = 0.7;
    private static final double SECOND_HAND_LENGTH_RATIO = 0.8;
    private static int WIDTH;
    private static int HEIGHT;
    private static int CLOCK_SIZE;
    private static double hBorder;
    private static double vBorder;
    private static Point center;
    private static double radius;
    private static double centerX;
    private static double centerY;
    private Skin skin;


    /**
     * Constructs a drawer that can draw an analog clock
     * @param skin The skin of the clock
     * @param width The width of the clock
     * @param height The height of the clock
     * @param clockSize The general of the clock
     */
    public AnalogClockDrawer(Skin skin, int width, int height, int clockSize) {
        this.skin = skin;
        WIDTH = width;
        HEIGHT = height;
        CLOCK_SIZE = clockSize;
        hBorder = WIDTH/20.0;   // The width of the horizontal border around the clock
        vBorder = HEIGHT/20.0;   // The width of the vertical border around the clock     
        radius = (WIDTH-2*hBorder)/2.0; // The radius of the clock
        center = new Point((int)(WIDTH/2.0), (int)(HEIGHT/2.0)); // The center point of the clock
        centerX = center.getX(); // The x value of the center of the clock
        centerY = center.getY(); // The y value of the center of the clock
        setPoints();
    }

    /**
     * Draws an analog clock at the given time
     * @param g2 The Graphics2D object to be drawn on
     * @param calendar The given time
     */
    @Override
    public void draw(Graphics2D g2, GregorianCalendar calendar) {
        drawBody(g2);
        drawDate(g2, calendar);
        drawHand(g2, HOUR_HAND_LENGTH_RATIO, calendar.get(Calendar.HOUR)%12, 'h');
        drawHand(g2, MINUTE_HAND_LENGTH_RATIO, calendar.get(Calendar.MINUTE), 'm');
        drawHand(g2, SECOND_HAND_LENGTH_RATIO, calendar.get(Calendar.SECOND), 's');
    }
    
   /**
     * Changes the skin of this clock
     * @param newSkin The new skin
     */
    @Override
    public void setSkin(Skin newSkin) {
        skin = newSkin;
    }
    
    /**
     * Draws the body of the analog clock
     * @param g2 The Graphics2D object to be drawn on
     */
    private void drawBody(Graphics2D g2) {
        // BACKGROUND
        g2.setColor(skin.BACKGROUND_COLOR);
        g2.fillRect(0, 0, WIDTH, HEIGHT);
        
        // BODY

        Ellipse2D.Double clockBody = new Ellipse2D.Double(hBorder, vBorder, radius*2, radius*2);
        g2.setStroke(new BasicStroke(10));
        g2.setColor(skin.EDGE_COLOR);
        g2.draw(clockBody);
        g2.setColor(skin.PRIMARY_FILL_COLOR);
        g2.fill(clockBody);
        
        // TICK MARKS
        g2.setStroke(new BasicStroke(2));
        g2.setColor(skin.EDGE_COLOR);
        
        // Access the tick points defined by the constructor, and draw each tick mark
        for(int i = 0; i < TICK_POINTS.length; i++) {
            Line2D.Double line = new Line2D.Double(
                    CIRCLE_POINTS[i].getX(), 
                    CIRCLE_POINTS[i].getY(), 
                    TICK_POINTS[i].getX(), 
                    TICK_POINTS[i].getY()
            );
            g2.draw(line);
        }
        // HOUR NUMBERS
        String[] numeralStrings = {"XII", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI"};
        Font font = new Font("Times New Roman", Font.BOLD, CLOCK_SIZE*6);
        g2.setColor(skin.NUMBER_COLOR);
        g2.setFont(font);
        
        // For each numeral, calculate the drawing point that causes the numeral to be centered 
        // on the line between it's tick and the center of the circle, at a distance from the center,
        // which is proportional to the numeralPositionRatio
        FontMetrics metrics = g2.getFontMetrics(font);
        double numeralRadiusRatio = 0.8; // ratio of the radius to the numeral, to the radius of the circle
        for(int i = 0; i < numeralStrings.length; i++) {
            int numeralCenterX = (int)Math.round(Math.cos(INITIAL_THETA-(i*THETA_INCREMENT*5))*radius*(numeralRadiusRatio)+centerX);
            int numeralCenterY = (int)Math.round(-1*Math.sin(INITIAL_THETA-(i*THETA_INCREMENT*5))*radius*(numeralRadiusRatio)+centerY);
            int numeralWidth = metrics.stringWidth(numeralStrings[i]);
            int newX = numeralCenterX-(numeralWidth/2);
            g2.drawString(numeralStrings[i], newX, numeralCenterY+6);
        }
    }
    
    /**
     * Draws the date of the analog clock
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
        Font font = new Font("Times New Roman", Font.BOLD, CLOCK_SIZE*4);
        g2.setFont(font);
        
        FontMetrics metrics = g2.getFontMetrics(font);
        int dateWidth = metrics.stringWidth(date);
        int dateHeight = metrics.getHeight();
        int dateX = (int)centerX-(dateWidth/2);
        int dateY = (int)(centerY*1.20);
        g2.setStroke(new BasicStroke(CLOCK_SIZE));
        g2.setColor(skin.DATE_COLOR);
        g2.drawString(date, dateX, dateY);
    }
    
    /**
     * Draws a hand of the Analog Clock at the given time value
     * @param g2 The Graphics2D object to be drawn on
     * @param handLengthRatio The ratio of the length of the hand to the radius of the clock
     * @param timeValue The value of the time denomination being drawn (e.g. 5, for the 5th hour)
     * @param timeUnit The char representation of the time unit of the hand to be drawn (e.g. 's' for the second hand)
     */
    private void drawHand(Graphics2D g2, double handLengthRatio, int timeValue, char timeUnit) {
        int index;
        if (timeUnit == 'h') // The index of the circle point corresponding to each hour, is 5 times the hour
            index = (timeValue*5)%60;   // Modulus 60 handles the 12th hour, which should access the point at index 0
        else 
            index = timeValue;
        // Get the slope of the hand
        double run = CIRCLE_POINTS[index].getX()-centerX;
        double rise = CIRCLE_POINTS[index].getY()-centerY;
        
        // Calculate the point at the tip of the hand
        int outerX = (int)Math.round(centerX+(run*handLengthRatio));
        int outerY = (int)Math.round(centerY+(rise*handLengthRatio));
        
        // If it's the second hand, set the color and stroke then draw it
        g2.setColor(skin.SECONDARY_FILL_COLOR);
        g2.setStroke(new BasicStroke(4));
        if (timeUnit == 's') {
            g2.drawLine(outerX, outerY, (int)center.getX(), (int)center.getY());
            return;
        }
        
        // For the minute and hour hands, draw the arrow at the end of the hand
        double arrowLengthRatio = 0.1;
        double arrowWidthRatio = 0.05;
        
        // Get the point at the center of the base of the arrow
        int arrowX1 = (int)Math.round(outerX-(run*arrowLengthRatio));
        int arrowY1 = (int)Math.round(outerY-(rise*arrowLengthRatio));
        double inverseRun = rise*-1;
        double inverseRise = run;
        
        // Get the points at the left and right of the base of the arrow
        int arrowX2 = (int)Math.round(arrowX1+(inverseRun*arrowWidthRatio/2));
        int arrowY2 = (int)Math.round(arrowY1+(inverseRise*arrowWidthRatio/2));
        int arrowX3 = (int)Math.round(arrowX1-(inverseRun*arrowWidthRatio/2));
        int arrowY3 = (int)Math.round(arrowY1-(inverseRise*arrowWidthRatio/2));
        
        // Draw the hand (from the center of the clock to the base of the arrow)
        g2.setColor(skin.TERTIARY_FILL_COLOR);
        g2.setStroke(new BasicStroke(4, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        g2.drawLine(arrowX1, arrowY1, (int)center.getX(), (int)center.getY());
        
        // Draw the arrow
        g2.drawLine(arrowX1, arrowY1, arrowX2, arrowY2);
        g2.drawLine(arrowX1, arrowY1, arrowX3, arrowY3);
        g2.drawLine(outerX, outerY, arrowX2, arrowY2);
        g2.drawLine(outerX, outerY, arrowX3, arrowY3);
    }
    
    /**
     * Initializes all points within the clock that will be used for drawing the body and ticks
     */
    private void setPoints() {
        // Walk around the circle by the radian increment between each tick mark (2PI/60),
        // and apply trig definitons to get all 60 points on the circle
        for(int i = 0; i < CIRCLE_POINTS.length; i++) {
            int newX = (int)Math.round(Math.cos(INITIAL_THETA-(i*THETA_INCREMENT))*radius+center.getX());
            int newY = (int)Math.round(-1*Math.sin(INITIAL_THETA-(i*THETA_INCREMENT))*radius+center.getY());
            CIRCLE_POINTS[i] = new Point(newX, newY);
        }
        // Do the same as for the circle points, but with a smaller radius
        for(int i = 0; i < TICK_POINTS.length; i++) {
            int newX;
            int newY;
            if ((i)%5==0) { // For the hour tick marks, use a shorter radius, to get a longer tick mark
                newX = (int)Math.round(Math.cos(INITIAL_THETA-(i*THETA_INCREMENT))*radius*(HOUR_TICK_RATIO)+centerX);
                newY = (int)Math.round(-1*Math.sin(INITIAL_THETA-(i*THETA_INCREMENT))*radius*(HOUR_TICK_RATIO)+centerY);
            } else { // For the minute tick marks, user a longer radius, to get a shorter tick mark
                newX = (int)Math.round(Math.cos(INITIAL_THETA-(i*THETA_INCREMENT))*radius*(MINUTE_TICK_RATIO)+centerX);
                newY = (int)Math.round(-1*Math.sin(INITIAL_THETA-(i*THETA_INCREMENT))*radius*(MINUTE_TICK_RATIO)+centerY);
            }
            TICK_POINTS[i] = new Point(newX, newY);
        }
    }
}