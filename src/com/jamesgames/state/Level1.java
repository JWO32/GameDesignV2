package com.jamesgames.state;

import com.jamesgames.entity.Player;
import com.jamesgames.entity.Enemy;
import com.jamesgames.entity.Collectable;
import com.jamesgames.main.LevelPanel;
import com.jamesgames.tilemap.TileMapManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Level1 extends LevelState
{
    // declare characters and objects
    private Player p;
    private boolean win;
      
    // Enemies
    Enemy[] enemies;
    
    //Collectables
    Collectable[] collectables;
    
    public Level1(LevelManager lsm)
    {
        super(lsm);
        tmm = new TileMapManager();
        p = new Player("", tmm);

        win=false;
        init();
        initEnemies();
        initCollectables();
    }
    
    /**
     * This method is used to initialise the enemy objects
     */
    private void initEnemies()
    {
        enemies = new Enemy[2];
        
        enemies[0] = new Enemy("/Images/blah", tmm, 100, 300);
        enemies[1] = new Enemy("/Images/blah", tmm, 500, 200);
      
    }
    
    /**
     * This method is used to initialise the collectable objects
     */
    private void initCollectables()
    {
         collectables = new Collectable[5];
         
         for(int i = 0; i < collectables.length; i++)
             collectables[i] = new Collectable("/Images/collectable.png", tmm);
    }
    
    private void init()
    {
        tmm.setCameraPosition(0,0);
    }
    
    private void win()
    {
        lm.goToState(LevelManager.LEVEL_2);
    }

    @Override
    public void keyPressed(int keyCode) 
    {
        if(keyCode == KeyEvent.VK_A)
            p.moveLeft(true);
        
        if(keyCode == KeyEvent.VK_D)
            p.moveRight(true);
        
        if(keyCode == KeyEvent.VK_SPACE)
            p.jump();
    }

    @Override
    public void keyReleased(int keyCode)
    {
       if(keyCode == KeyEvent.VK_A)
           p.moveLeft(false);
       
       if(keyCode == KeyEvent.VK_D)
           p.moveRight(false);
    }

    @Override
    public void update() 
    {
        p.update();
        p.checkEnemyCollision(enemies);
        p.checkCollectableCollision(collectables);
        
        for(Enemy e: enemies)
        {
            e.update((int)p.getX(), (int)p.getY());
        }
        
        tmm.setCameraPosition((int)LevelPanel.PANEL_WIDTH/2 - p.getX(), (int)LevelPanel.PANEL_HEIGHT/2 - p.getY());
        //Update Computer moves
        
        //Check collisions
        
        //Other updates        
        if(win)
            win();
    }

    /**
     * This method coordinates all drawing
     * The Graphics2D parameter originates from the LevelPanel and is passed
     * to the Level.  It is subsequently passed to all drawable items
     * @param g 
     */
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(Color.BLUE);
        
        //Clears the graphics buffer - this should probbaly be moved elsewhere
        g.fillRect(0, 0, LevelPanel.PANEL_WIDTH, LevelPanel.PANEL_HEIGHT);
        
        //Following a layred approach - 
        // Background image(s)
        //Tilemap
        // Objects + Player
        
        // Set the camera position so that it follows the player - half of the Panel width - current x and y position
        // then draw the tilemap
        
        tmm.draw(g);
                
        //draw player
        
        p.draw(g);
        
        for(Enemy current: enemies)
        {
            current.draw(g);
        }
        
        //draw enemies
    }
}
