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
    public int projX, projY;
    public int pWidth, pHeight;
    private PImage image;
    private PApplet app;
    
    public boolean used = false;
    private int xSpeed;
    private int ySpeed;
    private final int XSPEED = -6; 
    private double yVelocity = 0;
    private final double GRAVITY = 0.15;
    
    
    public Projectile(PApplet p, int x, int y, int width, int height, boolean used, int ySpeed){
        this.app = p;
        this.projX = x;
        this.projY = y;
        this.pWidth = width;
        this.pHeight = height;
        this.used = used;
        this.xSpeed = XSPEED;
        this.ySpeed = ySpeed;
    }
    
    public void move(){
        projX += xSpeed;
    }
    
    public void moveGravity(){
        projX += xSpeed;
        yVelocity += GRAVITY;
        projY += yVelocity;
    }
    public void draw(){
        app.fill(255, 0, 0);
        app.rect(projX, projY, pWidth, pHeight);
        this.moveGravity();
    }
}
