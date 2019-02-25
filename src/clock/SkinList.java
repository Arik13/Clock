package clock;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A list of various skins
 * @author Arik Dicks
 */
public class SkinList {
    private final ArrayList<Skin> skins = new ArrayList<>();

    /**
     * Constructs the default skin list
     */
    public SkinList() {
        // BASIC
        Skin basic = new Skin();
        basic.setSkin(
                Color.WHITE,        // background
                Color.WHITE,        // primary fill
                Color.RED,          // secondary fill
                Color.BLACK,        // tertiary fill
                Color.BLACK,        // quaternary fill
                Color.BLACK,        // edge
                Color.BLACK,        // date
                Color.BLACK);       // numbers
        basic.setName("Basic");
        skins.add(basic);

        // INVERTED
        Skin inverted = new Skin();
        inverted.setSkin(
                Color.BLACK,        // background
                Color.BLACK,        // primary fill
                Color.BLUE,          // secondary fill
                Color.WHITE,        // tertiary fill
                Color.WHITE,        // quaternary fill
                Color.WHITE,        // edge
                Color.WHITE,        // date
                Color.WHITE);       // numbers
        inverted.setName("Inverted");
        skins.add(inverted);
        
        // NIGHT MODE
        Skin nightMode = new Skin();
        nightMode.setSkin(
                Color.BLACK,        // background
                Color.DARK_GRAY,    // primary fill
                Color.BLUE,         // secondary fill
                Color.WHITE,        // tertiary fill
                Color.WHITE,        // quaternary fill
                Color.WHITE,        // edge
                Color.GREEN,        // date
                Color.GREEN);       // numbers
        nightMode.setName("Night Mode");
        skins.add(nightMode);
        
        // OCEAN
        Skin ocean = new Skin();
        ocean.setSkin(
                Color.BLACK,        // background
                Color.CYAN,         // primary fill
                Color.BLUE,        // secondary fill
                Color.WHITE,        // tertiary fill
                Color.WHITE,        // quaternary fill
                Color.BLUE,         // edge
                Color.BLACK,        // date
                Color.BLACK);       // numbers
        ocean.setName("Ocean");
        skins.add(ocean);
        
        // HULK
        Skin hulk = new Skin();
        hulk.setSkin(
                Color.BLACK,        // background
                Color.GREEN,         // primary fill
                Color.BLUE,         // secondary fill
                Color.BLACK,        // tertiary fill
                Color.BLACK,        // quaternary fill
                Color.BLACK,         // edge
                Color.BLACK,        // date
                Color.BLACK);       // numbers
        hulk.setName("HULK");
        skins.add(hulk);
        
        // CAPTAIN AMERICA
        Skin captainAmerica = new Skin();
        captainAmerica.setSkin(
                Color.BLACK,        // background
                Color.BLUE,         // primary fill
                Color.WHITE,        // secondary fill
                Color.RED,        // tertiary fill
                Color.RED,        // quaternary fill
                Color.WHITE,         // edge
                Color.WHITE,        // date
                Color.RED);       // numbers
        captainAmerica.setName("Captain America");
        skins.add(captainAmerica);
        
        // IRON MAN
        Skin ironMan = new Skin();
        ironMan.setSkin(
                Color.BLACK,        // background
                Color.RED,         // primary fill
                Color.YELLOW,         // secondary fill
                Color.DARK_GRAY,        // tertiary fill
                Color.DARK_GRAY,        // quaternary fill
                Color.YELLOW,         // edge
                Color.DARK_GRAY,        // date
                Color.DARK_GRAY);       // numbers
        ironMan.setName("Iron Man");
        skins.add(ironMan);
        
        // THOR
        Skin thor = new Skin();
        thor.setSkin(
                Color.BLACK,        // background
                Color.DARK_GRAY,         // primary fill
                Color.YELLOW,         // secondary fill
                Color.RED,        // tertiary fill
                Color.RED,        // quaternary fill
                Color.RED,         // edge
                Color.GRAY,        // date
                Color.YELLOW);       // numbers
        thor.setName("Thor");
        skins.add(thor);
        
        // BARBIE
        Skin barbie = new Skin();
        barbie.setSkin(
                Color.BLACK,        // background
                Color.PINK,         // primary fill
                Color.MAGENTA,         // secondary fill
                Color.MAGENTA,        // tertiary fill
                Color.MAGENTA,        // quaternary fill
                Color.MAGENTA,         // edge
                Color.YELLOW,        // date
                Color.YELLOW);       // numbers
        barbie.setName("Barbie");
        skins.add(barbie);
    }

    /**
     * Returns the skin of the given name
     * @param skinName The name of the skin being looked for
     * @return the skin of the given name
     */
    public Skin getSkin(String skinName) {
        for (Skin skin : skins) {
            if (skin.getName().equals(skinName)) 
                return skin;
        }
        return null;
    }

    /**
     * Returns the skin at a given index
     * @param index An index
     * @return the skin at the given index
     */
    public Skin getSkin(int index) {
        if (index < 0 || index > skins.size()-1) {
            return null;
        }
        return skins.get(index);
    }

    /**
     * Returns the size of this list
     * @return the size of this list
     */
    public int size() {
        return skins.size();
    }
}