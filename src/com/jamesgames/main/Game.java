 /**
 * This class manages the game window and level panel.
 */
package com.jamesgames.main;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;*/

/**
 * @author James
 */
public class Game
{ 
    public static final int WINDOW_WIDTH = 800; // Make this public so that other parts of the game can access
    public static final int WINDOW_HEIGHT = 600; // Make this public so that other parts of the game can access
    private static final String TITLE = "New and Improved Game";

    private JFrame mainWindow;
    private LevelPanel lp;
    private JPanel moviePanel;
    
    public Game()
    {       
        initGame();
        initComponents();
        initWindow();  
        //playMovie();
        
        lp.startGame();
    }
    
    /**
     * Initialise the game related objects
     */
    private void initGame()
    {       
        lp = new LevelPanel();
        mainWindow = new JFrame();
    }
    
    /**
     * Initialise any other GUI components such as buttons or menus
     */
    private void initComponents()
    {  
        mainWindow.add(lp);
    }
    
    private void initWindow()
    {
        mainWindow.setTitle(Game.TITLE);
        mainWindow.setSize(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
    }
    
    /**
     * Play the Intro Movie - need a few hacks to make this work
     */
//    private void playMovie()
//    {
//        boolean found = new NativeDiscovery().discover();
//        System.out.println(found);
//        System.out.println(LibVlc.INSTANCE.libvlc_get_version());
//        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C://Program Files//VideoLAN//VLC");
//        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
//        moviePanel = new JPanel();
//        mainWindow.add(moviePanel);
//        
//       EmbeddedMediaPlayerComponent emp = new EmbeddedMediaPlayerComponent();
//       moviePanel.setLayout(new BorderLayout());
//       moviePanel.add(emp, BorderLayout.CENTER);
//       // Set this to the file you want to play
//       emp.getMediaPlayer().playMedia("C:\\ed-cap.mp4");
//    }
        

    public static void main(String[] args) 
    {
        Game g = new Game();
    }
}
