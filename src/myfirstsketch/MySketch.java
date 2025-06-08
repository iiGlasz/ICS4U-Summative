/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;

import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ljphi
 */
public class MySketch extends PApplet{
    private Player player;
    public ArrayList<Projectile> projectiles = new ArrayList <Projectile>();  
    public ArrayList<Entity> entities = new ArrayList <Entity>(); 
    Random rand = new Random();
    
    boolean canHeal = true;
    boolean canAttack = true;
    
    
    
    public void settings(){
        size(800,400);
    }
    
    public void setup(){
        background(255);
        player = new Player(this, 100, 100, "images/person.png");
//        for (int i = 0; i < 10; i++){
//            projectiles.add(new Projectile(this, rand.nextInt(301) + 400, rand.nextInt(100,300), 20, 20, false, rand.nextInt(-1,1)));
//        }
        
        for (int i = 0; i < 5; i++){
            entities.add(new Enemy(this, rand.nextInt(351) + 350, 300, 20, 50, rand.nextInt(1,3)));
        }
    }
    
    public void draw(){
        background(255);
        player.draw(projectiles, entities);
        TeamMembers.buffs(player);
        for (Projectile p : projectiles){
            if (!p.used)
            p.draw();
        }
        for (Entity e : entities){
            if (!e.used)
            e.draw(player);
        }
        
        
        if (keyPressed){
            if(player.movingLeft && player.playerX >= 0){
                player.move(-5);
            }
            if(player.movingRight && player.playerX <= 800 - player.width){
                player.move(5);
            }
        }
 
          
       for (Projectile p: projectiles){
            if (player.isCollidingWith(p) && !p.used){
               player.health -- ;
               System.out.println(player.health);
               p.used = true;
           }
       }
       for (Entity e: entities){
            if (player.entityColiision(e) && !e.used){
               player.health -- ;
               System.out.println(player.health);
               e.used = true;
           }
       }
       

    }

    
    public void keyPressed(){
        // make the player move left
        if(keyCode == LEFT ){
            player.movingLeft = true;
            player.facingLeft = true;
            player.facingRight = false;
        }
        //make the player move right
        if(keyCode == RIGHT ){
            player.movingRight = true;
            player.facingLeft = false;
            player.facingRight = true;
        }
        // make the player jump
        if (keyCode == UP && !player.jumpKeyHeld){
            player.keyPressed();
            player.jumpKeyHeld = true;
        }  
        
        //healing
        if (key == 'z' && canHeal && player.health > 0){
            player.heal();
            canHeal = false;
        }
        
        //attacking
        if (key == 'x' && canAttack && player.health > 0) {
            player.startAttack();
            player.canAttack = false;
        }
        
    }
    
    public void keyReleased() {
        // reset all the booleans for how the player is moving
        // prevents holding controls for healing and attacking
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
        
        if (key == 'z'){
            canHeal = true;
        }

        if (key == 'x'){
            canAttack = true;
        }

    }
    
    
    
    
    
    
    public void mousePressed(){
//        if (person1.isClicked(mouseX, mouseY)){
//            showInfo = true;
//        } else{
//            showInfo = false;
//        }
    }
    
    
}


