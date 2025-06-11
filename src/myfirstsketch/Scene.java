/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;
import static myfirstsketch.MySketch.canPressEnter;
import static myfirstsketch.MySketch.gameState;
import processing.core.PApplet;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.ENTER;
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
    
    public Scene (PApplet p){
        this.app = p;
        this.levelClear = false;
        this.fillingScreen = false;
        this.hasBeenFilled = false;
        this.fillHeight = 0;
        this.fillSpeed = 8;
    }
    
    public void drawScreen(){
        if (levelClear && !fillingScreen) {
            chapterScreen++;
            levelClear = false;
            fillingScreen = true;
            fillHeight = 0;  // reset
            fillingScreen = true;
        }
        else if (fillingScreen) {
            app.fill(0);
            app.rect(0, 0, 800, fillHeight);
            fillHeight += fillSpeed;
            if (fillHeight >= 800) {
                fillingScreen = false;
            }
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
                    app.background(255);
                    app.textAlign(CENTER);
                    app.fill(0);
                    app.textSize(40);
                    app.text("Chapter 1", 400, 150);

                    app.textSize(24);
                    app.text("Press ENTER to Start", 400, 250);

                    break; 
                case 2:
                    app.background(255);
                    app.textAlign(CENTER);
                    app.fill(0);
                    app.textSize(40);
                    app.text("Chapter 2", 400, 150);

                    app.textSize(24);
                    app.text("Press ENTER to Start", 400, 250);

                    break;
                case 3:
                    app.background(255);
                    app.textAlign(CENTER);
                    app.fill(0);
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
   


