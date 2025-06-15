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
public class Projectile{
    // projectile variables
    public int projX, projY;
    public int pWidth, pHeight;
    private PImage image;
    private PApplet app;
    
    public boolean used = false;
    private int xSpeed;
    private double ySpeed;
    private final double GRAVITY = 0.15;
    
    // constructor
    public Projectile(PApplet p, int x, int y, int width, int height, boolean used, int xSpeed, String imagePath){
        this.app = p;
        this.projX = x;
        this.projY = y;
        this.pWidth = width;
        this.pHeight = height;
        this.used = used;
        this.xSpeed = xSpeed;
        this.image = app.loadImage(imagePath);
    }
    
    /**
     * moves the projectile by its x-axis speed
     */
    public void move(){
        projX += xSpeed;
    }
    
    /**
     * moves the projectile with projectile motion, x and y axis movement
     */
    public void moveGravity(){
        projX += xSpeed;
        ySpeed += GRAVITY;
        projY += ySpeed;
    }
    
    /**
     * draws the projectile with projectile motion
     */
    public void drawGravity(){
        app.fill(255, 0, 0);
        app.image(image, projX, projY);
        this.moveGravity();
    }
    
    /**
     * draws the projectile moving in a straight line
     */
    public void drawStraight(){
        app.fill(255, 0, 0);
        app.image(image, projX, projY);
        this.move();
    }
    
    /**
     * draws the projectile falling down with gravity, no x axis movement
     */
    public void drawGravityNoX(){
        app.fill(255, 0, 0);
        app.image(image, projX, projY);
        this.moveGravity();
    }
}
