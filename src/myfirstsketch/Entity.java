/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;

import processing.core.PApplet;
import processing.core.PImage;
/**
 *
 * @author ljphi
 */
public class Entity{
    public int x, y;
    public int width, height;
    private int speed;
    private PApplet app;
    private PImage image;
    public boolean used = false;
    
    public Entity(PApplet p, int x, int y, int width, int height, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = app.loadImage(imagePath);
    }
    
    public Entity(PApplet p, int x, int y, int width, int height, int speed){
        this.app = p;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }
    
    
    
    
    
    public void move(Player player){
        if (this.x > player.playerX){
            x -= 2 * speed;
        }
        else if (this.x < player.playerX){
            x += 2 * speed;
        }
    }
    
    public void draw(Player player){
        app.fill(255, 0, 0);
        app.rect(x, y, width, height);
        this.move(player);
    }
    

}
