/**
 * Game Object
 * 
 * Serves as a base class for characters and other game objects
 */
package com.jamesgames.entity;

import com.jamesgames.tilemap.Tile;
import com.jamesgames.tilemap.TileMapManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Superclass for all objects that can take part in the game
 * 
 * @author James
 */
public class GameObject 
{    
    //Object X and Y Coordinates
    protected double x;
    protected double y;

    // Sprite image
    protected BufferedImage sprite;
    
    protected TileMapManager tmm;
    
    // Collision box width and height
    // 
    protected int cWidth;
    protected int cHeight;
    
    //Collision directions
    // These values are set to true if a collision
    // is detected in any of the directions
    protected boolean cTopLeft;
    protected boolean cBottomRight;
    protected boolean cTopRight;
    protected boolean cBottomLeft;
    
    public GameObject(String fileName, TileMapManager tmm)
    {
        this.tmm = tmm;
        
        loadSprite(fileName);
    }
    
    /**
     * This method loads the appropriate sprite for this game object
     * unless an alternate constructor is available, this method must be called
     * with an appropriate file
     * @param fileName 
     */
    private void loadSprite(String fileName)
    {
        cWidth = 50;
        cHeight= 50;
        /*try
        {
            sprite = ImageIO.read(getClass().getResourceAsStream(fileName));
        }catch(IOException ex)
        {
            System.err.println("Error: Unable to load Game Object Sprite");
        }
        
        cWidth = sprite.getWidth();
        cHeight = sprite.getHeight();*/
    }
    
    /**
     * This method is called to update the GameObject's state in relation to the game
     * 
     */
    public void update()
    {
        
        
    }
   
    /**
     * This method will check the top and bottom right and left coordinates
     * of the sprite so that any collisions are flagged by the boolean collision
     * values
     * @param x
     * @param y 
     */
    public void checkTileMapCollision(double x, double y)
    {
        double currXPos;
        double currYPos;
        Tile tile;
        
        // Top Left
        currXPos = x;
        currYPos = y;
        tile = tmm.getTileAt(currXPos, currYPos);
        
        cTopLeft = tile.getType() == Tile.TYPE_BLOCKED;
        
        // Top Right
        currYPos = y;
        currXPos = x + cWidth;
        tile = tmm.getTileAt(currXPos, currYPos);
        
        cTopRight = tile.getType() == Tile.TYPE_BLOCKED;
        
        //Bottom Right
        currXPos = x + cWidth;
        currYPos = y + cHeight;
        tile = tmm.getTileAt(currXPos, currYPos);
        
        cBottomRight = tile.getType() == Tile.TYPE_BLOCKED;
        
        //Bottom Left        
        currXPos = x;
        currYPos = y + cHeight;
        tile = tmm.getTileAt(currXPos, currYPos);
        
        cBottomLeft = tile.getType() == Tile.TYPE_BLOCKED;
    }
        
    /**
     * This method determines if the current object has an intersection with another
     * gameobject.
     * 
     * @param obj
     * @return 
     */
    public boolean intersects(GameObject obj)
    {
        Rectangle r1 = getBounds(); // The current game object
        Rectangle r2 = obj.getBounds(); // The object we're checking
                
        return r1.intersects(r2);
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle((int)(x + tmm.getCameraX()), (int)(y + tmm.getCameraY()), cWidth, cHeight);
    }
        
    /**
     * Pass the graphics canvas from the GUI to the GameObject so that it 
     * can draw itself into the correct place.
     * Sub classes can override this if appropriate
     * @param g 
     */
    public void draw(Graphics2D g)
    {
        // Basic drawing - draw the sprite at current X and Y
       // g.drawImage(sprite, (int)x, (int)y, null);
        g.setColor(Color.GREEN);
        g.draw(getBounds());
    }
        
    /**
     * Returns true if the game object intersects another game object
     * @param g
     * @return 
     */
    public boolean collidesWith(GameObject g)
    {                
        return this.getBounds().intersects(g.getBounds());
    }
}
