package clock;

import java.awt.Color;

/**
 * An object that stores several colors used in drawing clocks
 * @author Arik Dicks
 */
public class Skin {
    private String skinName;

    /**
     * The background color
     */
    public Color BACKGROUND_COLOR;

    /**
     * The primary fill color
     */
    public Color PRIMARY_FILL_COLOR;

    /**
     * The secondary fill color
     */
    public Color SECONDARY_FILL_COLOR;

    /**
     * The tertiary fill color
     */
    public Color TERTIARY_FILL_COLOR;

    /**
     * The quaternary fill color
     */
    public Color QUATERNARY_FILL_COLOR;

    /**
     * The edge color
     */
    public Color EDGE_COLOR;

    /**
     * The date color
     */
    public Color DATE_COLOR;

    /**
     * The number color
     */
    public Color NUMBER_COLOR;

    /**
     * Constructs a default skin object
     */
    public Skin() {
        BACKGROUND_COLOR = Color.WHITE;
        PRIMARY_FILL_COLOR = Color.WHITE;
        SECONDARY_FILL_COLOR = Color.RED;
        TERTIARY_FILL_COLOR = Color.BLACK;
        QUATERNARY_FILL_COLOR = Color.BLACK;
        EDGE_COLOR = Color.BLACK;
        DATE_COLOR = Color.BLACK;
        NUMBER_COLOR = Color.BLACK;
        skinName = "Basic";
    }

    /**
     * Sets the name of the skin
     * @param skinName
     */
    public void setName(String skinName) {
        this.skinName = skinName;
    }

    /**
     * Sets the background color
     * @param color
     */
    public void setBackgroundColor(Color color) {
        BACKGROUND_COLOR = color;
    }

    /**
     * Sets the primary fill color
     * @param color
     */
    public void setPrimaryFillColor(Color color) {
        PRIMARY_FILL_COLOR = color;
    }

    /**
     * Sets the secondary fill color
     * @param color
     */
    public void setSecondaryFillColor(Color color) {
        SECONDARY_FILL_COLOR = color;
    }

    /**
     * Sets the tertiary fill color
     * @param color
     */
    public void setTertiaryFillColor(Color color) {
        TERTIARY_FILL_COLOR = color;
    }

    /**
     * Sets the quaternary fill color
     * @param color
     */
    public void setQuaternaryFillColor(Color color) {
        QUATERNARY_FILL_COLOR = color;
    }

    /**
     * Sets the edge color
     * @param color
     */
    public void setEdgeColorColor(Color color) {
        EDGE_COLOR = color;
    }

    /**
     * Sets the date color
     * @param color
     */
    public void setDateColor(Color color) {
        DATE_COLOR = color;
    }

    /**
     * Sets the number color
     * @param color
     */
    public void setNumberColor(Color color) {
        NUMBER_COLOR = color;
    }

    /**
     * Sets all the colors contained in the skin
     * @param backgroundColor The background color
     * @param primaryFillColor The primary fill color
     * @param secondaryFillColor The secondary fill color
     * @param tertiaryFillColor The tertiary fill color
     * @param quaternaryFillColor The quaternary fill color
     * @param edgeColor The edge color
     * @param dateColor The date color
     * @param numberColor The number color
     */
    public void setSkin(
            Color backgroundColor,
            Color primaryFillColor,
            Color secondaryFillColor,
            Color tertiaryFillColor,
            Color quaternaryFillColor,
            Color edgeColor,
            Color dateColor,
            Color numberColor) {
        BACKGROUND_COLOR = backgroundColor;
        PRIMARY_FILL_COLOR = primaryFillColor;
        SECONDARY_FILL_COLOR = secondaryFillColor;
        TERTIARY_FILL_COLOR = tertiaryFillColor;
        QUATERNARY_FILL_COLOR = quaternaryFillColor;
        EDGE_COLOR = edgeColor;
        DATE_COLOR = dateColor;
        NUMBER_COLOR = numberColor;
    }

    /**
     * Gets the name of this skin
     * @return the name of this skin
     */
    public String getName() {
        return skinName;
    }
}