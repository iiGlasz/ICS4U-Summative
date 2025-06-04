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
public class Person {
    private String name;
    private int age;
    public int x, y;
    private int width, height;
    private PImage image;
    private PApplet app;
    
    public Person(PApplet p, int x, int y, String name, int age, String imagePath){
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.age = age;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }
    
    public void draw(){
        app.image(image, x, y);
    }
    
    public void moveTo(int dx, int dy){
        x = dx;
        y = dy;
    }
    
    
    
    public boolean isCollidingWith(Person other){
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + width > other.x;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;
        
        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }
    
    public boolean isClicked(int mouseX, int mouseY){
        int centerX = x + (image.pixelWidth/2);
        int centerY = y + (image.pixelHeight/2);
        float d = PApplet.dist(mouseX, mouseY, centerX, centerY);
        
        System.out.println("Image height " + image.pixelHeight);
        System.out.println("Image width " + image.pixelWidth);
        
        return d < 16;
    }
    
    public void displayInfo(PApplet p){
        app.fill(0);
        app.text("Name: " + name, x, y -50);
        app.text("Age: " + age, x, y -30);
    }
}


// circular collision
// calculate center of the image
//        int centerX = x + (image.pixelWidth/2);
//        int centerY = y + (image.pixelHeight/2);
//        
//        // calculate center of the other image
//        int otherCenterX = other.x + (other.image.pixelWidth/2);
//        int otherCenterY = other.y + (other.image.pixelHeight/2);
//
//        // calculate distance between the two center points
//        float d = PApplet.dist(otherCenterX, otherCenterY, centerX, centerY);
//        // return true if distance between center points is < 32 pixels
//        return d < 32;
