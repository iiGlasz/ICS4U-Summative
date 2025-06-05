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
public class Player extends MySketch{
    // combat variables & stats
    private int max_Health = 5;
    public int health = 5;
    private int discipline = 0;
    public int healthPots = 3;
    
    // drawing variables
    public int x, y;
    private int width, height;
    private PImage image;
    private PApplet app;
    
    // movement variables
    private int yVelocity = 0;
    public boolean isJumping = false;
    private final int GRAVITY = 1;
    private final int JUMP_STRENGTH = -15;
    
    public boolean movingRight = false;
    public boolean movingLeft = false;
    public boolean jumpKeyHeld = false;
    public int numberJumps = 0;
    
    
    
    
    public Player(PApplet p, int x, int y, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.max_Health = 5;
        this.health = 5;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    public void move(int dx){
        x += dx;
    }
    
    public void heal(){
        if (healthPots > 0){
            health++;
            healthPots--;
        }
    }
    
    public void attack(){
        app.fill(255,0,0);
        app.rect(x+width, y+2, 40, 50);
    }
  
    @Override
    public void keyPressed() {
        if (!isJumping && numberJumps < 2) {
            yVelocity = JUMP_STRENGTH;
            isJumping = true;
            numberJumps++;
        }
    }
    
    public boolean isCollidingWith(Projectile other){
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + width > other.x;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;
        
        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }
    
    
    public void draw(){
        if (isJumping || y < 300) {
            yVelocity += GRAVITY;
            y += yVelocity;
        
            // Land on ground
            if (y >= 300) {
              y = 300;
              yVelocity = 0;
              isJumping = false;
              numberJumps = 0;
            }
        }
        // draw a health bar rectangle
        app.fill(0,255,0);
        app.rect (20, 30, 10, 80);
        app.rect (20, 30, 10, 64);
        app.rect (20, 30, 10, 48);
        app.rect (20, 30, 10, 32);
        app.rect (20, 30, 10, 16); 
        app.fill(255,0,0);
        switch (health){
            case -1:
                app.rect (20, 30, 10, 80);
            case 0:
                app.rect (20, 30, 10, 80);
            case 1:
                app.rect (20, 30, 10, 64);
            case 2:
                app.rect (20, 30, 10, 48);
            case 3:
                app.rect (20, 30, 10, 32);
            case 4:
               app.rect (20, 30, 10, 16); 
            
        }
        if (health <= 0){
            health = 0;
            app.fill(0);
            app.rect(400,0,400,400);
        }
        
        
                   
        
        // draw the character sprite
        app.image(image, x, y);
    }
    
}
