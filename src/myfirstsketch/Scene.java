/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;
import static myfirstsketch.MySketch.gameState;
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
    public static int chapterScreen = 0;
    private static PApplet app;
    private int fillHeight;
    private boolean fillingScreen;
    private boolean hasBeenFilled;
    private int fillSpeed;
    
    ArrayList<String> lines = new ArrayList<String>();
    
    public static int dialogueState = 0;
    
    PImage backgroundImage1;
    PImage backgroundImage2;
    PImage backgroundImage3;
    PImage backgroundImage4;
    PImage backgroundImage5;
    
    PImage endingScreen;
    
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
    
    public void drawDialogue(int gameState){
        app.fill(150,75,0);
        app.rect(20, 180, 760, 215);
        
//        app.fill(160,80,0);
//        app.rect(40, 280, 720, 25);
//        app.rect(40, 320, 720, 25);
//        app.rect(40, 360, 720, 25);
        
        
        
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
            
//            case 5:
//                fileTextReader("text/dialogueC5.txt");
//                break;

        }
    }
    
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
        // true enlightenment ending
        else if (Player.discipline == 4){
            this.endingScreen = app.loadImage("images/endings/wukongHeavenly.png");
            endingType = "True Enlighenment Ending";
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
    
    public void drawChapterScreen(){
        app.background(0);
        app.textAlign(CENTER);
        app.fill(255);
        app.textSize(40);
        app.text("Chapter " + chapterScreen, 400, 150);

        app.textSize(24);
        app.text("Press ENTER to Start", 400, 250);
    }
    
    public void drawMainMenu() {
        app.background(255);
        app.textAlign(CENTER);
        app.fill(0);
        app.textSize(40);
        app.text("Journey to the West", 400, 150);

        app.textSize(24);
        app.text("Press ENTER to Start",400, 250);
    }
    
    public void fileTextReader(String fileName){

        lines.clear();
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
   


