/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesgames.entity;

import com.jamesgames.tilemap.TileMapManager;

/**
 *
 * @author James
 */
public class Collectable extends GameObject
{
    public Collectable(String fileName, TileMapManager tmm)
    {
        super(fileName, tmm);
    }
    
    public Collectable(String fileName, TileMapManager tmm, int score, double x, double y)
    {
        super(fileName, tmm);
        this.x = x;
        this.y = y;
    }
    
}
