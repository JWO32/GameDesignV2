/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesgames.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author James
 */
public class Animation
{
    private int currentStep;
    private int numberOfFrames;
    private ArrayList<BufferedImage> animation;
    
    public Animation()
    {
        currentStep = 0;
        numberOfFrames = 0;
        
        animation = new ArrayList<>();        
    }
    
    public void addFrame(BufferedImage frame)
    {
        if(frame != null)
        {
            animation.add(frame);
            numberOfFrames = animation.size();
        }        
    }
    
    public void addFrame(String frameURI)
    {
        BufferedImage tempImage = null;
        
        try
        {
            tempImage = ImageIO.read(getClass().getResourceAsStream(frameURI));
        }catch(IOException ex)
        {
            System.err.println("Error adding frame to animation");
            System.err.println("URI Provided: " + frameURI);
        }
        
        animation.add(tempImage);
        numberOfFrames = animation.size();
    }
    
    public BufferedImage getNextFrame()
    {
        BufferedImage animationStep = animation.get(currentStep);
        
        currentStep++;
        
        if(currentStep > numberOfFrames)
            currentStep = 0;
        
        return animationStep;
    }
    
    
}
