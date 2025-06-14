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
    
    private PImage image;
    
    public Enemy(PApplet p, int x, int y, int width, int height, int speed, String imagePath, boolean used){ 
        super(p, x, y, width, height, speed, imagePath, used);

//        this.image = app.loadImage(imagePath);
    }
    
    
}
