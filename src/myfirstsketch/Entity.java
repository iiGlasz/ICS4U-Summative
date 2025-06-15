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
    public PApplet app;
    private PImage image;
    public boolean used;
    public boolean eCanBeHit;
    
   
    // constructor
    public Entity(PApplet p, int x, int y, int width, int height, int speed, String imagePath, boolean used){
        this.app = p;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.image = app.loadImage(imagePath);
        this.used = false;
    }
    
    
    // constructor for the boss
    public Entity(PApplet p, int x, int y){
        this.app = p;
        this.x = x;
        this.y = y;
        this.eCanBeHit = true;
    }
    
    /**
     * Move the entity closer towards the player
     */
    public void move(){
        // follow the player depending on their x position
        if (this.x > Player.playerX){
            x -= 2 * speed;
        }
        else if (this.x < Player.playerX){
            x += 2 * speed;
        }
    }
    
    /**
     * draws the entity and moves it towards the player
     * @param player player object
     */
    public void draw(){
        app.fill(255, 0, 0);
        app.image(image, x, y);
        this.move();
    }
    

}
