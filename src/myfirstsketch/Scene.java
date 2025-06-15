/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;
import processing.core.PApplet;
import static processing.core.PConstants.CENTER;
import processing.core.PImage;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author ljphi
 */
public class Scene {
    // variables for the scene
    public static int chapterScreen = 0;
    private static PApplet app;
    private int fillHeight;
    private boolean fillingScreen;
    private boolean hasBeenFilled;
    private int fillSpeed;
    
    // array list for reading text files
    ArrayList<String> lines = new ArrayList<String>();
    
    // if the dialogue has more than 1 state
    public static int dialogueState = 0;
    
    // images for the background
    PImage backgroundImage1;
    PImage backgroundImage2;
    PImage backgroundImage3;
    PImage backgroundImage4;
    PImage backgroundImage5;
    // the end screen image depending on the ending
    PImage endingScreen;
    
    // constructor
    public Scene (PApplet p){
        this.app = p;
        this.fillingScreen = false;
        this.hasBeenFilled = false;
        this.fillHeight = 0;
        this.fillSpeed = 8;
        this.backgroundImage1 = app.loadImage("images/backgrounds/background1.png");
        this.backgroundImage2 = app.loadImage("images/backgrounds/background2.png");
        this.backgroundImage3 = app.loadImage("images/backgrounds/background3.png");
        this.backgroundImage4 = app.loadImage("images/backgrounds/background4.png");
        this.backgroundImage5 = app.loadImage("images/backgrounds/background5.png");
    }
    
    /**
     * reads a file by calling the fileTextReader and draws buttons for the options
     * the player has, also outputs text based on what the file says for each
     * chapter.
     * @param gameState the chapter that the game is on
     */
    public void drawDialogue(int gameState){
        // draw the dialogue text box
        app.fill(150,75,0);
        app.rect(20, 180, 760, 215);
        
        // depending on the chapter, draw the right text
        switch (gameState){
            case 1:
                fileTextReader("text/dialogueC1.txt");
                // player options
                app.fill(160,80,0);
                app.rect(40, 280, 720, 25);
                app.rect(40, 320, 720, 25);
                
                // chapter text
                app.fill(255);
                app.textSize(18);
                
                app.text(lines.get(0), 400, 215);
                app.text(lines.get(1), 400, 245);
                
                app.text(lines.get(3), 400, 297);
                app.text(lines.get(4), 400, 337);
                break;
                
            case 2:
                fileTextReader("text/dialogueC2.txt");
                // player options
                app.fill(160,80,0);
                app.rect(40, 280, 720, 25);
                app.rect(40, 320, 720, 25);
                // chapter text
                app.fill(255);
                app.textSize(18);

                app.text(lines.get(0), 400, 215);

                app.text(lines.get(2), 400, 297);

                app.text(lines.get(3), 400, 337);
                
                break;
                
            case 3:
                fileTextReader("text/dialogueC3.txt");
                if (dialogueState == 0){
                // player options
                app.fill(160,80,0);
                app.rect(40, 280, 720, 25);
                app.rect(40, 320, 720, 25);
                
                // chapter text
                app.fill(255);
                app.textSize(18);
                
                app.text(lines.get(0), 400, 215);
                
                app.text(lines.get(2), 400, 297);
                
                app.text(lines.get(3), 400, 337);
                }
                else if (dialogueState == 1){
                    app.fill(150,75,0);
                    app.rect(20, 180, 760, 215);
                    
                    // player options
                    app.fill(160,80,0);
                    app.rect(40, 280, 720, 25);
                    app.rect(40, 320, 720, 25);

                    // chapter text
                    app.fill(255);
                    app.textSize(18);

                    app.text(lines.get(5), 400, 297);

                    app.text(lines.get(6), 400, 337);
                }
                break;
                
            case 4:
                fileTextReader("text/dialogueC4.txt");
                // player options
                app.fill(160,80,0);
                app.rect(40, 280, 720, 25);
                app.rect(40, 320, 720, 25);
                app.rect(40, 360, 720, 25);
                
                // chapter text
                app.fill(255);
                app.textSize(18);
                
                app.text(lines.get(0), 400, 215);
                app.text(lines.get(1), 400, 245);
                
                app.text(lines.get(3), 400, 297);
                app.text(lines.get(4), 400, 337);
                app.text(lines.get(5), 400, 377);
                break;
        }
    }
    
    /**
     * draws the background depending on the chapter
     */
    public void drawBackground(){
        app.background(0);
        switch (MySketch.gameState){
            case 1:
                app.image(backgroundImage1, 0,40, 800,400);
                break;
            case 2:
                app.image(backgroundImage2, 0,40, 800,400);
                break;
            case 3:
                app.image(backgroundImage3, 0,40, 800,414);
                break;
            case 4:
                app.image(backgroundImage4, 0,40, 800,414);
                break;
            case 5:
                app.image(backgroundImage5, 0,-20, 800,414);
                break;
        }
    }
    
    /**
     * draws the death screen and prompts the user if they want to retry
     */
    public void deathScreen(){
        if (!fillingScreen) {
            fillingScreen = true;
            fillHeight = 0;  // reset
        }
        else if (fillingScreen && !hasBeenFilled){
            app.fill(0);
            app.rect(0, 0, 800, fillHeight);
            fillHeight += fillSpeed;


            if (fillHeight >= 800) {
                app.textAlign(CENTER);
                app.background(0);
                app.fill(255);
                app.textSize(40);
                app.text("You Died, Retry?", 400, 150);

                app.textSize(24);
                app.text("Press ENTER to Retry", 400, 250);
                hasBeenFilled = true;   
            }
        }
    }
    
    /**
     * draws the ending screen depending on the user's stats
     */
    public void drawEnding(){
        String endingType;
        // aggression ending
        if (Player.discipline == -3){
            this.endingScreen = app.loadImage("images/endings/wukongAggression.png");
            endingType = "Aggression Ending";
        }
        // corrupted ending
        else if (Player.damage == 30){
            this.endingScreen = app.loadImage("images/endings/wukongCorrupted.png");
            endingType = "Corruption Ending";
        }
        // enlightenment ending
        else if (Player.discipline == 4){
            this.endingScreen = app.loadImage("images/endings/wukongHeavenly.png");
            endingType = "Enlightenment Ending";
        }
        // neutral ending
        else {
            this.endingScreen = app.loadImage("images/endings/wukongNeutral.png");
            endingType = "Neutral Ending";
        }
        app.image(endingScreen, 0, 0);
        app.fill(0);
        app.rect(0, 0, 200, 400);
        app.fill(255);
        app.textSize(20);
        app.text("Thanks for Playing!", 100, 150);
        app.text(endingType, 100, 220);
    }
    
    /**
     * draws the chapter screen for the user to press enter
     */
    public void drawChapterScreen(){
        app.background(0);
        app.textAlign(CENTER);
        app.fill(255);
        app.textSize(40);
        app.text("Chapter " + chapterScreen, 400, 150);

        app.textSize(24);
        app.text("Press ENTER to Start", 400, 250);
    }
    
    /**
     * draws the main menu
     */
    public void drawMainMenu() {
        app.background(50);
        
        app.textAlign(CENTER);
        app.fill(255);
        app.textSize(40);
        app.text("Journey to the West", 400, 150);

        app.textSize(24);
        app.text("Press ENTER to Start",400, 250);
        
        app.textSize(20);
        app.text("Press p to save game\n Press l to load save file",100, 320);
        app.text("Press z to heal\n Press x to attack",700, 320);
    }
    
    /**
     * draws the save menu
     */
    public void drawSaveMenu(){
        app.background(0);
        app.fill(50);
        app.rect(250, 60, 290, 50);
        app.rect(250, 160, 290, 50);
        app.rect(250, 260, 290, 50);
        app.fill(255);
        app.textSize(40);
        app.text("Save File 1", 400, 100);
        app.text("Save File 2", 400, 200);
        app.text("Save File 3", 400, 300);
    }
    
    /**
     * draws the load menu
     */
    public void drawLoadMenu(){
        app.background(0);
        app.fill(50);
        app.rect(250, 60, 290, 50);
        app.rect(250, 160, 290, 50);
        app.rect(250, 260, 290, 50);
        app.fill(255);
        app.textSize(40);
        app.text("Load File 1", 400, 100);
        app.text("Load File 2", 400, 200);
        app.text("Load File 3", 400, 300);
    }
    
    /**
     * reads text files for the dialogue
     * @param fileName file location for the file being read
     */
    public void fileTextReader(String fileName){
        // clear out the array list
        lines.clear();
        // read from the file and append to the array list
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
   


