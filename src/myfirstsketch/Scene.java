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
    public boolean levelClear;
    private static PApplet app;
    private int fillHeight;
    private boolean fillingScreen;
    private int fillSpeed;
    
    public Scene (PApplet p){
        this.app = p;
        this.levelClear = true;
        this.fillingScreen = false;
        this.fillHeight = 0;
        this.fillSpeed = 8;
    }
    
    // fix
    public void drawScreen(){
        if (levelClear && !fillingScreen) {
//            chapterScreen++;
            levelClear = false;
            fillingScreen = true;
            fillHeight = 0;  // reset
            
        }
        if (fillingScreen) {
            app.fill(0);
            app.rect(0, 0, 800, fillHeight);
            fillHeight += fillSpeed;


            if (fillHeight >= 800) {
                fillingScreen = false;

                // Now do something depending on chapterScreen
//                switch (chapterScreen) {
//                    case 0:
//                        // ...
//                        break;
//                    case 1:
//                        // ...
//                        break;
//                    case 2:
//                        // ...
//                        break;
                    // etc.
//                }
            }
        }
    }
}
   
        
//        if (levelClear){
//            chapterScreen ++;
//            levelClear = false;
//            timer--;
//            for (int i = 0; i < 800; i += 20){
//                if (timer == 0){
//                    app.fill(0);
//                    app.rect(0,0,800, i);
//                    timer = 1;
//                }
//                
//            }
//            
//            switch (chapterScreen){
//                case 0:
//                   
//                    break; 
//                case 1:
//                    
//                    break;
//                case 2:
//                    
//                    break;
//                case 3:
//                    
//                    break;
//                case 4:
//                    
//                    break;
//                case 5:
//                    
//                    break;
//            }
//        }
//    }
    

