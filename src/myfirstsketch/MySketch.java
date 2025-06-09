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
    public Player player;
    public ArrayList<Projectile> projectiles = new ArrayList <Projectile>();  
    public ArrayList<Entity> entities = new ArrayList <Entity>(); 
    private Scene scene;
    public Entity boss;
    Random rand = new Random();
    
    boolean canHeal = true;
    boolean canAttack = true;
    public static boolean combat = false;
    public static int gameState = 0;
    
    
    public void settings(){
        size(800,400);
    }
    
    public void setup(){
        background(255);
        player = new Player(this, 100, 100, "images/wukong/wukong-idle-right.png", "images/wukong/attack.png");
        scene = new Scene(this);
        boss = new Bosses(this, 600, 0, 200, 400);
        for (int i = 0; i < 10; i++){
            projectiles.add(new Projectile(this, rand.nextInt(301) + 400, rand.nextInt(100,300), 20, 20, false, rand.nextInt(-1,1)));
        }
        
//        for (int i = 0; i < 5; i++){
//            entities.add(new Enemy(this, rand.nextInt(351) + 350, 300, 20, 50, rand.nextInt(1,3)));
//        }
        entities.add(boss);
    }
    
    public void draw(){
        switch (gameState){
            case 0:
                drawMainMenu();
                break;
                
            case 1:
                background(255);
                Battle battle = new Battle();
                battle.BattleStart(player, projectiles, entities, (Bosses) boss, keyPressed);

                
                break;
                
            case 2:
                scene.drawScreen();
        }
        
        
    }

    
    public void keyPressed(){
        // make the player move left
        if(keyCode == LEFT){
            player.movingLeft = true;
            player.facingLeft = true;
            player.facingRight = false;
        }
        //make the player move right
        if(keyCode == RIGHT){
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
            canAttack = false;
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
    
    public void drawMainMenu() {
        textAlign(CENTER);
        fill(0);
        textSize(40);
        text("Journey to the West", width / 2, 150);

        textSize(24);
        text("Press ENTER to Start", width / 2, 250);
        if (keyCode == ENTER){
            combat = true;
            gameState ++;
        }
    }
}


