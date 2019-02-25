package clock;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * The controller for the clock, receives updates when the time changes or the user requests a UI change, and then updates the view
 * @author Arik Dicks
 */
public class ClockController {
    private static TimeThread timeThread;
    private final JFrame frame;
    private final ClockComponent clock;
    private final ClockSettings settings;
    private final JPanel panel;
    private final SkinList skins;

    /**
     * Constructs a Clock Controller
     */
    public ClockController() {
        settings = new ClockSettings();
        clock = new ClockComponent(settings);
        panel = new JPanel(new BorderLayout());
        
        // Add Menu Bar
        JMenuBar menuBar = new JMenuBar();
        
        // Add View Mode Menu
        JMenu viewMenu = new JMenu("View");
        JMenuItem analog = new JMenuItem("Analog");
        JMenuItem digital = new JMenuItem("Digital")
                ;
        ViewMenuListener viewMenuListener = new ViewMenuListener();
        analog.addActionListener(viewMenuListener);
        digital.addActionListener(viewMenuListener);
        
        analog.setActionCommand("Analog");
        digital.setActionCommand("Digital");
        
        viewMenu.add(analog);
        viewMenu.add(digital);
        
        // Add Skin Menu
        JMenu skinMenu = new JMenu("Skins");
        skins = new SkinList();
        SkinMenuListener listener = new SkinMenuListener();
        for (int i = 0; i < skins.size(); i++) {
            String name = skins.getSkin(i).getName();
            JMenuItem skinMenuItem = new JMenuItem(name);
            skinMenuItem.setActionCommand(name);
            skinMenuItem.addActionListener(listener);
            skinMenu.add(skinMenuItem);
        }
        
        // Add Menus to MenuBar
        menuBar.add(viewMenu);
        menuBar.add(skinMenu);

        // Add menu and clock to panel
        panel.add(menuBar, BorderLayout.NORTH);
        panel.add(clock, BorderLayout.CENTER);
        
        // Setup Frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Clock");
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        
        timeThread = new TimeThread();
        timeThread.setName("TimeThread");
        timeThread.addTimeListener(new TimeThreadListener(this));
        timeThread.start();
    }
    /**
     * Gets the current time from the time thread and passes the time to the clock components
     */
    public void updateClocksTime() {
        GregorianCalendar calendar = timeThread.getCalendar();
        clock.updateTime(calendar);
    }
    /**
     * Listens for skin change events and updates the clock with the new skin
     */
    private class SkinMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            settings.setSkin(skins.getSkin(e.getActionCommand()));
            clock.updateSkin(skins.getSkin(e.getActionCommand()));
        }
    }
    /**
     * Listens for view change events and updates the clock with the different view
     */
    private class ViewMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Analog") && !settings.getIsAnalog()) {
                settings.setIsAnalog(true);
                clock.swapToAnalog();
            } else if (e.getActionCommand().equals("Digital") && settings.getIsAnalog()) {
                settings.setIsAnalog(false);
                clock.swapToDigital();
            }
        }
    }
}