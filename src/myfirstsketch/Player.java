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
    private int max_Health = 5;
    private int health = 5;
    private int discipline = 0;
    public int x, y;
    private int width, height;
    private PImage image;
    private PApplet app;
    
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
    
    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }
    
  
    @Override
    public void keyPressed() {
        if (!isJumping && numberJumps < 2) {
            yVelocity = JUMP_STRENGTH;
            isJumping = true;
            numberJumps++;
        }
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
        
        app.rect (10, 10, 20, 20);
        app.image(image, x, y);
    }
    
}
