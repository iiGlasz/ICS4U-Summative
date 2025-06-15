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
    // declare the objects that will be used
    public Player player;
    public ArrayList<Entity> entities = new ArrayList <Entity>(); 
    private Scene scene;
    public Entity boss;
    
    GameSaver save;
    String bossImage;
    
    // declare button objects for dialogue, save menu, and load menu
    private Button button1, button2, button3;
    private Button saveButton1, saveButton2, saveButton3;
    private Button loadButton1, loadButton2, loadButton3;
    
    // define a random variable
    Random rand = new Random();
    
    // variables for the player when actions are on cooldown
    boolean canHeal = true;
    boolean canAttack = true;
    
    // variables to switch between parts of the game
    public static boolean combat = false;
    public static boolean dialogue = false;
    public static int gameState = 0;
    public static boolean canPressEnter = true;
    public static boolean battleWon = false;
    
    // variables for handling options and game choices
    boolean resetStage = true;
    boolean choice1, choice2, choice3 = false;
    boolean savingMenu = false;
    boolean loadMenu = false;
    public static boolean mainMenu = true;
    
    
    public void settings(){
        size(800,400);
    }
    
    public void setup(){
        background(0);
        // instantiate the objects
        player = new Player(this, 100, 250, "images/wukong/wukong-idle-right.png", "images/wukong/attackRight.png");
        scene = new Scene(this);
        save = new GameSaver(gameState, TeamMembers.pigsy, TeamMembers.sandy, Player.damage, Player.discipline);
        
        button1 = new Button(this, 40, 280, 720, 25);
        button2 = new Button(this, 40, 320, 720, 25);
        button3 = new Button(this, 40, 360, 720, 25);
                
        saveButton1 = new Button(this, 250, 60, 290, 50);
        saveButton2 = new Button(this, 250, 160, 290, 50);
        saveButton3 = new Button(this, 250, 260, 290, 50);
        
        loadButton1 = new Button(this, 250, 60, 290, 50);
        loadButton2 = new Button(this, 250, 160, 290, 50);
        loadButton3 = new Button(this, 250, 260, 290, 50);
    }
    
    /**
     * draw the game scene and handle dialogue/game decisions
     */
    public void draw(){
        // depending on the player's progression select the right part of the game
        // in each chapter, choose the correct boss image and load the level
        // set battleWon to false so that the player doesn't immediately progress
        // has decisions based on dialogue
        // reset the stage if needed, and increase the gameState when a battle is won
        switch (gameState){
            // main menu screen
            case 0:  
                if (mainMenu)
                    scene.drawMainMenu();
                    
                // progress if they press enter
                if (canPressEnter && keyCode == ENTER){
                    mainMenu = false;
                    canPressEnter = false;
                    Scene.chapterScreen++;
                    gameState++;
                }
                break;
            // chapter 1
            case 1:
                // choose the correct boss image
                bossImage = "images/enemies/demon1.png";
                // load the level
                levelLoad(gameState);
                
                // check the user's choice in the dialogue
                if (choice1){
                    if (Player.discipline < 1)
                        Player.discipline++;
                    choice1 = false;
                }
                else if(choice2){
                    Player.discipline--;
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
                
            //chapter 2
            case 2:
                bossImage = "images/enemies/demon2.png";
                battleWon = false;
                levelLoad(gameState);
                
                // decisions based on the dialogue
                if (choice1){
                    if (Player.discipline < 2)
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
                
            //chapter 3
            case 3:
                bossImage = "images/enemies/demon3.png";
                battleWon = false;
                levelLoad(gameState);
                
                // decisions based on the dialogue
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
                    if (Player.discipline < 3)
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
                
            //chapter 4
            case 4:
                Scene.dialogueState = 0;
                bossImage = "images/enemies/demon4.png";
                battleWon = false;
                levelLoad(gameState);
                
                if (choice1){
                    if (Player.discipline < 4)
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
            
            //chapter 5
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
           
            //ending
            case 6:
                scene.drawEnding();
        }
        
       
    }
    
    

    /**
    * Depending on the key being pressed, perform the correct actions
    */
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
        
        // for pressing enter when needed
        if (keyCode == ENTER && canPressEnter){
            canPressEnter = false;
        }
        
        // for opening the save menu
        if (key == 'p'){
            mainMenu = false;
            combat = false;
            dialogue = false;
            loadMenu = false;
            savingMenu = true;
            save = new GameSaver(gameState, TeamMembers.pigsy, TeamMembers.sandy, Player.damage, Player.discipline);
            scene.drawSaveMenu();
        }
        
        // for opening the load menu
        if (key == 'l'){
            mainMenu = false;
            combat = false;
            dialogue = false;
            savingMenu = false;
            loadMenu = true;
            scene.drawLoadMenu();
        }
        
    }
    
    /**
     * When a key is released perform the right actions
     */
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
    
 
    /**
     * Load the scenes of the game as well as whether the game is in combat stage,
     * dialogue stage, if the player dies, and the starting screen of each chapter
     * @param gameState the game's chapter
     */
    public void levelLoad(int gameState){
        if (Scene.chapterScreen == gameState && !savingMenu && !loadMenu){    
            scene.drawChapterScreen();
            if (keyCode == ENTER && canPressEnter){
                canPressEnter = false;
                resetStage = true;
                dialogue = true;
                Scene.chapterScreen++;
            }
        }
        else if (savingMenu){
            saveButton1.setInUse();
            saveButton2.setInUse();
            saveButton3.setInUse();
                    
            scene.drawSaveMenu();
        }
        else if (loadMenu){
            loadButton1.setInUse();
            loadButton2.setInUse();
            loadButton3.setInUse();
        }
        else if (dialogue && !savingMenu && !loadMenu){
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
        
        // if in combat
        else if (combat && !savingMenu && !loadMenu){
            
            // draw the chapter's bacakground
            scene.drawBackground();
            
            // start the battle
            Battle.BattleStart(player, entities, (Bosses) boss, keyPressed);
        } 
        
        // death, then reset the player if they retry
        else if (player.health == 0 && !combat){
            scene.deathScreen();
            if (keyCode == ENTER && canPressEnter){
                canPressEnter = false;
                player.resetPlayer();
                resetStage = true;
                Scene.chapterScreen = gameState; 
            }
        }
        // if the save menu is not active, make the buttons inactive
        if (!savingMenu){
            saveButton1.setFalse();
            saveButton2.setFalse();
            saveButton3.setFalse();
        }
        // if the load menu is not active, make the buttons inactive
        if (!loadMenu){
            loadButton1.setFalse();
            loadButton2.setFalse();
            loadButton3.setFalse();
        }
        // if the game is not in dialogye, make the buttons inactive
        if (!dialogue){
            button1.setFalse();
            button2.setFalse();
            button3.setFalse();
        }
    }
    
    /**
     * reset each stage when called, starting the player at the correct position,
     * using the correct boss image, and adding new entities
     */
    public void resetStage(){
        // clear ArrayLists each stage
        entities.clear();
        // reset all the states of the things on screen
        player = new Player(this, 100, 250, "images/wukong/wukong-idle-right.png", "images/wukong/attackRight.png");
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
        // downcast the boss entity to set its health
        ((Bosses) boss).currentBossHealth = 300;

        // add 8 new enemies
        for (int i = 0; i < 8; i++){
            entities.add(new Entity(this, rand.nextInt(351) + 350, 292, 20, 50, rand.nextInt(1,3), "images/enemies/enemy.png", false));
        }
        entities.add(boss);
        
        resetStage = false;
    }
    
        /**
         * Called whenever the mouse is pressed and buttons are on the screen
         * Used for dialogue, saving and loading menus
         */
       public void mousePressed(){
        //check the buttons if dialogue is active
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
        
        // check the buttons for saving menu
        if (saveButton1.isClicked(mouseX, mouseY) && savingMenu){
            save.saveGame(1);
            savingMenu = false;
        } 
        else if (saveButton2.isClicked(mouseX, mouseY) && savingMenu){
            save.saveGame(2);
            savingMenu = false;
        }
        else if (saveButton3.isClicked(mouseX, mouseY) && savingMenu){
            save.saveGame(3);
            savingMenu = false;
        }
        
        // check the buttons for loading menu, then load the save files
        if (loadButton1.isClicked(mouseX, mouseY) && loadMenu){
            save.loadGame(1);
            loadMenu = false;
        } 
        else if (loadButton2.isClicked(mouseX, mouseY) && loadMenu){
            save.loadGame(2);
            loadMenu = false;
        }
        else if (loadButton3.isClicked(mouseX, mouseY) && loadMenu){
            save.loadGame(3);
            loadMenu = false;
        }
    }
}