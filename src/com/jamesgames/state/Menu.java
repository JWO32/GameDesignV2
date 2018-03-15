/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesgames.state;

import com.jamesgames.main.LevelPanel;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *
 * @author James
 */
public class Menu extends LevelState
{
    
    
    public Menu(LevelManager lsm)
    {
        super(lsm);
    }

    public void initComponent()
    {

        
    }
    
    @Override
    public void keyPressed(int keyCode) 
    {
       
    }

    @Override
    public void keyReleased(int keyCode)
    {
    
    }

    @Override
    public void update() 
    {
        
    }

    @Override
    public void draw(Graphics2D g) 
    {
        Random rand = new Random();
        
        int x = rand.nextInt(500);
        int y = rand.nextInt(600);
        
         Font f = new Font("Arial", Font.BOLD, 15);
        //Draw background  
        g.clearRect(0, 0, LevelPanel.PANEL_WIDTH, LevelPanel.PANEL_HEIGHT);
        g.drawString("Menu", x, y);
        //update player
        
        //update enemies
    }
}
