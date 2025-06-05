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
public class Projectile {
    public int x, y;
    public int width, height;
    private PImage image;
    private PApplet app;
    public boolean used = false;
    private int xSpeed;
    private int ySpeed;
    private final int XSPEED = -2;    
    
    
    public Projectile(PApplet p, int x, int y, int width, int height, boolean used, int ySpeed){
        this.app = p;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.used = used;
        this.xSpeed = XSPEED;
        this.ySpeed = ySpeed;
    }
    
    public void move(){
        x += xSpeed;
        y += ySpeed;
    }
    public void draw(){
        app.fill(255, 0, 0);
        app.rect(x, y, width, height);
        this.move();
    }
}
