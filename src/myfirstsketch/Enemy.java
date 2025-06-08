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
public class Enemy extends Entity{
    private int enemyHealth;
    
    public Enemy(PApplet p, int x, int y, int width, int height, int speed){ //, String imagePath
        super(p, x, y, width, height, speed);
//        this.image = app.loadImage(imagePath);
    }
    
    
}
