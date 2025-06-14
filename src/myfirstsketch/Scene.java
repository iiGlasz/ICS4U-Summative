/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;
import processing.core.PApplet;
import static processing.core.PConstants.CENTER;
import processing.core.PImage;

/**
 *
 * @author ljphi
 */
public class Scene {
    public static int chapterScreen = 0;
    public static boolean levelClear;
    private static PApplet app;
    private int fillHeight;
    private boolean fillingScreen;
    private boolean hasBeenFilled;
    private int fillSpeed;
    
    PImage backgroundImage1;
    PImage backgroundImage2;
    PImage backgroundImage3;
    PImage backgroundImage4;
    PImage backgroundImage5;
    
    public Scene (PApplet p){
        this.app = p;
        this.levelClear = false;
        this.fillingScreen = false;
        this.hasBeenFilled = false;
        this.fillHeight = 0;
        this.fillSpeed = 8;
        this.backgroundImage1 = app.loadImage("images/background1.png");
        this.backgroundImage2 = app.loadImage("images/background2.png");
        this.backgroundImage3 = app.loadImage("images/background3.png");
        this.backgroundImage4 = app.loadImage("images/background4.png");
        this.backgroundImage5 = app.loadImage("images/background5.png");
    }
    
    public void drawDialogue(int gameState){
        app.fill(150,75,0);
        app.rect(20, 200, 760, 180);
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
    
    public void drawChapterScreen(){
        switch (chapterScreen){
                case 1:
                    app.background(0);
                    app.textAlign(CENTER);
                    app.fill(255);
                    app.textSize(40);
                    app.text("Chapter 1", 400, 150);

                    app.textSize(24);
                    app.text("Press ENTER to Start", 400, 250);

                    break; 
                case 2:
                    app.background(0);
                    app.textAlign(CENTER);
                    app.fill(255);
                    app.textSize(40);
                    app.text("Chapter 2", 400, 150);

                    app.textSize(24);
                    app.text("Press ENTER to Start", 400, 250);

                    break;
                case 3:
                    app.background(0);
                    app.textAlign(CENTER);
                    app.fill(255);
                    app.textSize(40);
                    app.text("Chapter 3", 400, 150);

                    app.textSize(24);
                    app.text("Press ENTER to Start", 400, 250);

                    break;
                case 4:
                    app.background(255);
                    app.textAlign(CENTER);
                    app.fill(0);
                    app.textSize(40);
                    app.text("Chapter 4", 400, 150);

                    app.textSize(24);
                    app.text("Press ENTER to Start", 400, 250);

                    break;  
                case 5:
                    app.background(0);
                    app.textAlign(CENTER);
                    app.fill(255);
                    app.textSize(40);
                    app.text("Chapter 5", 400, 150);

                    app.textSize(24);
                    app.text("Press ENTER to Start", 400, 250);

                    break;  
        }   
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
    
    
}
   


