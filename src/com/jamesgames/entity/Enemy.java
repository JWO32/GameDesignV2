package com.jamesgames.entity;

import com.jamesgames.main.LevelPanel;
import com.jamesgames.tilemap.TileMapManager;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author James
 */
public class Enemy extends GameObject
{
    private int damage;
    private int speed;
      
    public Enemy(String spriteFileName, TileMapManager tmm)
    {
        super(spriteFileName, tmm);
    }
    
    public Enemy(String spriteFileName, TileMapManager tmm, int x, int y)
    {
        super(spriteFileName, tmm);   
        
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        super.draw(g);
        // Modify the drawing coordinates to take the camera position into account       
        //g.drawImage(sprite, (int) (x + tmm.getCameraX() - cWidth/2), (int) (y + tmm.getCameraY() - cHeight /2), null);
        g.drawRect((int)(x + tmm.getCameraX() ), (int)(y+tmm.getCameraY()), cWidth, cHeight);
    }
    
    // Movement - default implementation
    @Override
    public void update()
    {
        
    }
    
    // Overloaded method provide X and Y coordinates for the 
    // Enemy to move towards
    public void update(int moveTowardsX, int moveTowardsY)
    {
        if(x < moveTowardsX)
            x+= speed;
        else
            x-=speed;
        
        if(y < moveTowardsY)
            y-=speed;
        else
            y+=speed;
    }
    
    public void setDamage(int damage)
    {
        this.damage = damage;
    }
    
    public int getDamage()
    {
        return this.damage;
    }
    
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    
    public int getSpeed()
    {
        return this.speed;
    }    
}
