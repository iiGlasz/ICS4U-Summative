/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ljphi
 */
public class MySketch extends PApplet{
    public Player player;
    public ArrayList<Entity> entities = new ArrayList <Entity>(); 
    private Scene scene;
    public Entity boss;
    Random rand = new Random();
    
    // variables for the player
    boolean canHeal = true;
    boolean canAttack = true;
    
    // variables to switch between parts of the game
    public static boolean combat = false;
    public static boolean dialogue = false;
    public static int gameState = 0;
    public static boolean canPressEnter = true;
    public static boolean battleWon = false;
    
    boolean resetStage = true;
    
    PImage enemyImage;
    String bossImage;
    
    
    public void settings(){
        size(800,400);
    }
    
    public void setup(){
        background(0);
        // se;t up the objects on the screen
        enemyImage = loadImage("images/enemy.png");
        player = new Player(this, 100, 250, "images/wukong/wukong-idle-right.png", "images/wukong/attack.png");
        scene = new Scene(this);
        
        entities.add(boss);
    }
    
    public void draw(){
        switch (gameState){
            case 0:  
                scene.drawMainMenu();    
                if (canPressEnter && keyCode == ENTER){
                    canPressEnter = false;
                    Scene.chapterScreen++;
                    gameState++;
                }
                break;
               
            case 1:
                bossImage = "images/demon1.png";
                levelLoad(gameState);
                if (resetStage){
                    resetStage();
                }
                if (battleWon){
                    gameState++;
                }
                break;
                
            case 2:
                bossImage = "images/demon2.png";
                battleWon = false;
                if (resetStage){
                    resetStage();
                }
                levelLoad(gameState);
                if (battleWon){
                    gameState++;
                }
                break;
            case 3:
                bossImage = "images/demon3.png";
                battleWon = false;
                if (resetStage){
                    resetStage();
                }
                levelLoad(gameState);
                if (battleWon){
                    gameState++;
                }
                break;
                
            case 4:
                bossImage = "images/demon4.png";
                battleWon = false;
                if (resetStage){
                    resetStage();
                }
                levelLoad(gameState);
                if (battleWon){
                    gameState++;
                }
                break;
            case 5: 
                bossImage = "images/demon5.png";
                // heavenly test  
                battleWon = false;
                if (resetStage){
                    resetStage();
                }
                levelLoad(gameState);
                if (battleWon){
                    gameState++;
                }
                break;
                
            case 6:
                //ending
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
        
        if (keyCode == ENTER && canPressEnter){
            canPressEnter = false;
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
        
        if (keyCode == ENTER){
            canPressEnter = true;
        }

    }
    
//    public void mousePressed(){
//        if (person1.isClicked(mouseX, mouseY)){
//            showInfo = true;
//        } else{
//            showInfo = false;
//        }
//    }
    
    public void levelLoad(int gameState){
        if (Scene.chapterScreen == gameState){    
            scene.drawChapterScreen();
            if (keyCode == ENTER && canPressEnter){
                canPressEnter = false;
                resetStage = true;
                dialogue = true;
                Scene.chapterScreen++;
                
            }
        }
        else if (dialogue){
            scene.drawBackground();
            scene.drawDialogue(gameState);
        }
        else if (combat){
            Battle battle = new Battle();
            battle.BattleStart(player, entities, (Bosses) boss, keyPressed);
        } 
        // death, then reset the player
        else if (player.health == 0 && !combat){
            scene.deathScreen();
            if (keyCode == ENTER && canPressEnter){
                canPressEnter = false;
                player.resetPlayer();
                resetStage = true;
                Scene.chapterScreen = gameState; 
            }
        }
    }
    
    public void resetStage(){
        // clear ArrayLists each stage
        entities.clear();
        // reset all the states of the things on screen
        player = new Player(this, 100, 250, "images/wukong/wukong-idle-right.png", "images/wukong/attack.png");
        scene = new Scene(this);
        if (bossImage == "images/demon1.png")
            boss = new Bosses(this, 545, 51, 200, 400, 300, bossImage);
        else if (bossImage == "images/demon2.png")
            boss = new Bosses(this, 565, 62, 200, 400, 300, bossImage);
        else if (bossImage == "images/demon3.png")
            boss = new Bosses(this, 545, 92, 200, 400, 300, bossImage);
        else if (bossImage == "images/demon4.png")
            boss = new Bosses(this, 545, 120, 200, 400, 300, bossImage);   
        else
            boss = new Bosses(this, 520, 160, 200, 400, 300, bossImage);  
                    
                    
        ((Bosses) boss).currentBossHealth = 300;

        for (int i = 0; i < 8; i++){
            entities.add(new Enemy(this, rand.nextInt(351) + 350, 292, 20, 50, rand.nextInt(1,3), "images/enemy.png", false));
        }

        entities.add(boss);
        resetStage = false;
    }
    
   
}


