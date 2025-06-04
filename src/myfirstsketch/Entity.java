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
public class Entity {
    private int x;
    private int y;
    private int speed;
    private PApplet app;
    private PImage image;
    
    public Entity(PApplet p, int x, int y, int speed, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = app.loadImage(imagePath);
        
    }
    
    public void move(int dx,int dy){
        x += dx * speed;
        y += dy * speed;
    }
    
    public void draw(){
        app.fill(255, 0,0);
        app.rect(10+x, y, 40,20);
        app.fill(0);
        app.ellipse(10+x, y + 20, 10,10);
        app.ellipse(50+x,y + 20, 10,10);
    }
    
    public boolean isCollidingWith(Entity other){
        boolean isLeftOfOtherRight = x < other.x + other.image.pixelWidth;
        boolean isRightOfOtherLeft = x + image.pixelWidth > other.x;
        boolean isAboveOtherBottom = y < other.y + other.image.pixelHeight;
        boolean isBelowOtherTop = y + image.pixelHeight > other.y;
        
        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }
}
