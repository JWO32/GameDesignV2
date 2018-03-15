/**
 * Player - represents the player in the game
 */
package com.jamesgames.entity;
import com.jamesgames.tilemap.TileMapManager;
import com.jamesgames.tilemap.Tile;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *  
 * 
 */
public class Player extends GameObject
{
    private int health;
        
    private double xSpeed = 1.2;
    private double gravity = 0.8;

    // Displacement from current X and Y
    private double dx;
    private double dy;
        
    private int countDown = 0;
    //Sprites and Animations
    Animation animation;
    
    // Player States
    // Simple implementation of a state machine
    private boolean STANDING;
    private boolean MOVE_LEFT;
    private boolean MOVE_RIGHT;
    private boolean JUMP;
    private boolean ATTACK;
    private boolean BLOCKED_X;
    private boolean BLOCKED_Y;
    private boolean FALLING;
    
    private final double GRAVITY = 0.5;
    
    public Player(String spriteFile, TileMapManager tm)
    {
        super(spriteFile, tm);
        tmm = tm; // Gives the player a link to the tile map (so we can find out if we are interacting with anything)
        
        x = 100; // This should be changed so that the start coordinates are passed into the object
        y = 100;
        dx = 0;
        dy = 0;
        
        //Temp
        cHeight = 50;
        cWidth = 50;
        
        FALLING = true;
    }
    
    @Override
    public void update()
    {
        double checkX;
        double checkY;
        
        checkX = x +dx;
        checkY = y +dy;
        
        checkTileMapCollision(checkX, checkY);
        
        if(cTopLeft && cBottomLeft)
        {
            System.out.println("Collision Left");
            checkX = x; // Reset X position
        }
        
        if(cBottomRight && cTopRight)
        {
            System.out.println("Collision Right");
            checkX = x; // Reset X position
        }
        
        if(cTopRight || cTopLeft)
        {
            System.out.println("Collision Top");
            checkY = y;  // Reset Y position
        }
         
        if(cBottomLeft && cBottomRight)
        {
            System.out.println("Collision Bottom");
            checkY = y;
            FALLING = false;
        }else
            FALLING = true;
        
        if(JUMP)
        {
            checkY-=15;
            JUMP = false;            
        }else if(countDown > 0)
        {
            checkY -=15;
            countDown--;
        }
        
        if(FALLING)
        {
            gravity = GRAVITY;
        }else
        {
            gravity = 0;
        }        
                  
        x=checkX;        
        y=checkY;
        
        //Apply gravity
        y+= gravity;
    }

    /**
     * Use the reference to the tile map to find out if the next position
     * we wish to move into is blocked by a tile.
     * @param checkX
     * @param checkY 
     */
    public void checkNextPosition(double checkX, double checkY)
    {
        Tile t = tmm.getTileAt(checkX, checkY);// get the current tile
         
        if(t.getType()== Tile.TYPE_BLOCKED && FALLING == true)
        {
            FALLING = false;
        }
        
        if(t.getType() == Tile.TYPE_NORMAL && FALLING == false)
        {
            FALLING = true;
        }
    }
    
    /**
     * Check whether we have a collision with other game objects
     * (enemies in particular)
     * 
     * @param enemies 
     */
    public void checkEnemyCollision(Enemy[] enemies)
    {
        for(Enemy current: enemies)
        {
            // We have a collision
            // What do we do next?
            if(intersects(current))
            {
                System.out.println("Collision!!");
            }
        }
    }
    
    public void checkCollectableCollision(Collectable[] collectables)
    {
        for(Collectable current: collectables)
        {
            if(intersects(current))
            {
                
            }
        }
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(Color.yellow);
        //g.drawString("X: "+ (int)x+ " Y: "+ (int)y, (int) x-20, (int)y-20); // Draw coordinates on screen as debug info
        g.drawRect((int) x, (int) y, (int) cWidth, (int) cHeight); // draw a simple shape on screen to represent character
        
        //g.drawImage(animation.getNextFrame(), (int) x, (int) y, null);        
    }
    
    public void moveLeft(boolean move)
    {
        if(move == true)
        {        
            MOVE_LEFT = true;
            dx = -xSpeed;
        }else
        {
            STANDING = true;
            MOVE_LEFT = false;
            dx = 0;
        }
    }
    
    public void moveRight(boolean move)
    {
        if(move == true)
        {
            MOVE_RIGHT = true;
            dx = xSpeed;
        }else
        {
            STANDING = true;
            MOVE_RIGHT = false;
            dx = 0;
        }   
    }
    
    public void jump()
    {
      if(FALLING == false)
      {
          JUMP = true;        
          countDown = 3;
      }
    }
    
    public void setHealth(int health)
    {
        this.health = health;
    }
    
    public int getHealth()
    {
        return this.health;
    }
    
    public double getX()
    {
        return this.x;
    }
    
    public double getY()
    {
        return this.y;
    }
}
