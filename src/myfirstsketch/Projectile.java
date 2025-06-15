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
public class Projectile extends Entity{
    // projectile variables
    private PImage image;
    private PApplet app;
    
    private int xSpeed;
    private double ySpeed;
    private final double GRAVITY = 0.15;
    
    // constructor
    public Projectile(PApplet p, int x, int y, int width, int height, boolean used, int xSpeed, String imagePath){
        super(p, x, y, width, height, xSpeed, imagePath, used);
        this.app = p;
        this.image = app.loadImage(imagePath);
        this.xSpeed = xSpeed;
    }
    
    @Override
    /**
     * moves the projectile in x axis
     */
    public void move(){
        x += xSpeed;
    }
    
    /**
     * draws the projectile with projectile motion
     */
    public void drawGravity(){
        app.image(image, x, y);
        ySpeed += GRAVITY;
        y += ySpeed;
        move();
    }
    
    @Override
    /**
     * draws the projectile moving in a straight line
     */
    public void draw(){
        app.image(image, x, y);
        move();
    }
    
    /**
     * draws the projectile falling down with gravity, no x axis movement
     */
    public void drawGravityNoX(){
        app.image(image, x, y);
        ySpeed += GRAVITY;
        y += ySpeed;
    }
}
