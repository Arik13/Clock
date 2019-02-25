package clock;

/**
 *
 * @author Arik Dicks
 */
public class ClockSettings {
    private final int clockSize = 5;
    private final int width = clockSize*100;
    private final int height = clockSize*100;
    private boolean isAnalog;
    private Skin skin;

    /**
     * Constructs a clock settings object with the default settings
     */
    public ClockSettings() {
        isAnalog = true;
        skin = new Skin();
    }

    /**
     * Sets this clocks analog setting to be true or false
     * @param bool True for this clock to be analog, false for digital
     */
    public void setIsAnalog(boolean bool) {
        this.isAnalog = bool;
    }

    /**
     * Sets the skin setting of this clock
     * @param skin The new skin setting of this clock
     */
    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    /**
     * Gets the clock size clock setting
     * @return the clock size setting of this clock
     */
    public int getClockSize() {
        return clockSize;
    }

    /**
     * Gets the width clock setting
     * @return the width setting of this clock
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height clock setting
     * @return the height setting of this clock
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the isAnalog (boolean) clock setting
     * @return true if the clock setting is set to analog, false if it is digital
     */
    public boolean getIsAnalog() {
        return isAnalog;
    }

    /**
     * Gets the Skin clock setting
     * @return the skin setting of this clock
     */
    public Skin getSkin() {
        return skin;
    }
}