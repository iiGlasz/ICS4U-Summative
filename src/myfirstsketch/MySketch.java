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
    
    private Button button1, button2, button3;
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
    boolean choice1, choice2, choice3 = false;
    
    PImage enemyImage;
    String bossImage;
    
    int combatStartTime = 0;
    int combatEndTime = 0;
    boolean checkTime = true;
    
    
    public void settings(){
        size(800,400);
    }
    
    public void setup(){
        background(0);
        // se;t up the objects on the screen
        enemyImage = loadImage("images/enemies/enemy.png");
        player = new Player(this, 100, 250, "images/wukong/wukong-idle-right.png", "images/wukong/attack.png");
        scene = new Scene(this);
        
        button1 = new Button(this, 40, 280, 720, 25);
        button2 = new Button(this, 40, 320, 720, 25);
        button3 = new Button(this, 40, 360, 720, 25);
                
        
                
        
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
                // choose the correct boss image
                bossImage = "images/enemies/demon1.png";
                // load the level
                levelLoad(gameState);
                
                // check the user's choice in the dialogue
                if (choice1){
                    Player.discipline++;
                    choice1 = false;
                }
                else if(choice2){
                    Player.discipline--;
                    combatStartTime = millis();
                    System.out.println(millis());
                    choice2 = false;
                }
                
                // reset the stage if needed
                if (resetStage){
                    resetStage();
                }
                // when the battle is won, up the gamestate
                if (battleWon){
                    gameState++;
                }
                break;
                
            case 2:
                if (checkTime){
                    combatEndTime = millis();
                    checkTime = false;
                }
                bossImage = "images/enemies/demon2.png";
                battleWon = false;
                levelLoad(gameState);
                
                if (choice1){
                    Player.discipline++;
                    TeamMembers.pigsy = true;
                    TeamMembers.sandy = true;
                    choice1 = false;
                }
                else if(choice2){
                    choice2 = false;
                }
                
                if (resetStage){
                    resetStage();
                }
                
                if (battleWon){
                    gameState++;
                }
                
                
                
                break;
            case 3:
                bossImage = "images/enemies/demon3.png";
                battleWon = false;
                levelLoad(gameState);
                
                
                if (choice1 && Scene.dialogueState == 0){
                    Player.damage = 30;
                    scene.chapterScreen = 5;
                    gameState = 5;
                    choice1 = false;
                }
                else if (choice2 && Scene.dialogueState == 0){
                    Scene.dialogueState++;
                    choice2 = false;
                }
                
                else if (choice1 && Scene.dialogueState == 1){
                    Player.discipline++;
                    scene.chapterScreen = 4;
                    gameState = 4;
                    choice1 = false;
                }
                else if (choice2 && Scene.dialogueState == 1){
                    Player.discipline--;
                    combat = true;
                    choice2 = false;
                }
                
                
                if (resetStage){
                    resetStage();
                }
                
                if (battleWon){
                    gameState++;
                }
                break;
                
            case 4:
                Scene.dialogueState = 0;
                bossImage = "images/enemies/demon4.png";
                battleWon = false;
                levelLoad(gameState);
                
                if (choice1){
                    Player.discipline++;
                    scene.chapterScreen = 5;
                    gameState = 5;
                    
                    choice1 = false;
                }
                    
                else if (choice2){
                    Player.discipline--;
                    choice2 = false;
                }
                        
                        
                else if (choice3){
                    scene.chapterScreen = 5;
                    gameState++;
                    choice3 = false; 
                        
                }
                
                if (resetStage){
                    resetStage();
                }
                
                if (battleWon){
                    gameState++;
                }
                break;
            case 5: 
                // heavenly test  
                bossImage = "images/enemies/demon5.png";
                battleWon = false;
                dialogue = false;
                combat = true;
                
                if (resetStage){
                    resetStage();
                }
                levelLoad(gameState);
                if (battleWon){
                    gameState++;
                }
                break;
                
            case 6:
                scene.drawEnding();
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
            button1.setInUse();
            button2.setInUse();
            if (gameState == 4)
                button3.setInUse();

            scene.drawBackground();
            scene.drawDialogue(gameState);
            
            if (Player.discipline <= 0 && gameState == 2){
                dialogue = false;
                combat = true;
            }
            

        }
        else if (combat){
            button1.setFalse();
            button2.setFalse();
            button3.setFalse();
            scene.drawBackground();
            
            
            
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
        if (bossImage == "images/enemies/demon1.png")
            boss = new Bosses(this, 545, 51, 200, 400, 300, bossImage);
        else if (bossImage == "images/enemies/demon2.png")
            boss = new Bosses(this, 565, 62, 200, 400, 300, bossImage);
        else if (bossImage == "images/enemies/demon3.png")
            boss = new Bosses(this, 545, 92, 200, 400, 300, bossImage);
        else if (bossImage == "images/enemies/demon4.png")
            boss = new Bosses(this, 545, 120, 200, 400, 300, bossImage);   
        else
            boss = new Bosses(this, 520, 160, 200, 400, 300, bossImage);  
                    
                    
        ((Bosses) boss).currentBossHealth = 300;

        for (int i = 0; i < 8; i++){
            entities.add(new Enemy(this, rand.nextInt(351) + 350, 292, 20, 50, rand.nextInt(1,3), "images/enemies/enemy.png", false));
        }

        entities.add(boss);
        resetStage = false;
    }
    
    
       public void mousePressed(){
        if (button1.isClicked(mouseX, mouseY)){
            dialogue = false;
            combat = true;
            choice1 = true;
        } 
        else if (button2.isClicked(mouseX, mouseY)){
            // if statement for chapter 3 
            if (Scene.dialogueState == 1){
                dialogue = false;
                combat = true;
            }
            else if (gameState != 3){
                dialogue = false;
                combat = true;
            }
            
            choice2 = true;
        }
        else if (button3.isClicked(mouseX, mouseY)){
            dialogue = false;
            combat = true;
            choice3 = true;
        }
    }
   
}


