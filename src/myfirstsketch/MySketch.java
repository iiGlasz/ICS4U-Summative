/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;

import processing.core.PApplet;


/**
 *
 * @author ljphi
 */
public class MySketch extends PApplet{
    private Player player;
    
    public void settings(){
        size(800,400);
    }
    
    public void setup(){
        background(255);
        player = new Player(this, 100, 100, "images/person.png");
        
    }
    
    public void draw(){
        background(255);
        player.draw();
        
        if (keyPressed){
            if(player.movingLeft){
                player.move(-5, 0);
            }
            if(player.movingRight){
                player.move(5, 0);
            }
        }
//        drawCollisions();

//        if (showInfo){
//            person1.displayInfo(this);
//        }
        
        
        
        
          
//       
//       if (person1.isCollidiingWith(person2)){
//           fill(255,0,0);
//           this.text("oouch", person2.x, person2.y);
//       }
    }
//    public void drawCollisions(){
//        if (player.isCollidingWith(car2)){
//            fill(255, 0, 0);
//            this.text("CRASH", 200, 20);
//        }
//    }
    public void mousePressed(){
//        if (person1.isClicked(mouseX, mouseY)){
//            showInfo = true;
//        } else{
//            showInfo = false;
//        }
    }
    
    public void keyPressed(){
        // make the player move left
        if(keyCode == LEFT){
            player.movingLeft = true;
            System.out.println("moving left");
        }
        //make the player move right
        if(keyCode == RIGHT){
            player.movingRight = true;
            System.out.println("moving right");
        }
        // make the player jump
        if (keyCode == UP && !player.jumpKeyHeld){
            player.keyPressed();
            player.jumpKeyHeld = true;
        }  
        
    }
    
    public void keyReleased() {
        // reset all the booleans for how the player is moving
        if (keyCode == UP) {
          player.jumpKeyHeld = false;
          player.isJumping = false;
        }

        if (keyCode == LEFT) {
          player.movingLeft = false;

        }
        
        if (keyCode == RIGHT) {
           player.movingRight = false;
        }
}
    
    
    
    
}


