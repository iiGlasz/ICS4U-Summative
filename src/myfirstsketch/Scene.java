/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author ljphi
 */
public class Scene {
    private static int chapterScreen = 0;
    public boolean levelClear = true;
    private static PApplet app;
    
    public Scene (PApplet p){
        this.app = p;
    }
    
    // fix
    public void drawScreen(){
        int timer = 1;
        if (levelClear){
            chapterScreen ++;
            levelClear = false;
            timer--;
            for (int i = 0; i < 800; i += 20){
                System.out.println("should work");
                if (timer == 0){
                    app.fill(0);
                    app.rect(0,0,800, i);
                    timer = 1;
                }
                
            }
            
            switch (chapterScreen){
                case 0:
                   
                    
                case 1:

                case 2:

                case 3:

                case 4:

                case 5:
            
            }
        }
    }
    
}
