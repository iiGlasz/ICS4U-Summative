package myfirstsketch;


import processing.core.PApplet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ljphi
 */
public class Button {
    private int x, y;
    private int width, height;
    private PApplet app;
    private boolean inUse;
    
    public Button(PApplet p, int x, int y, int width, int height){
        this.app = p;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.inUse = false;
    }
    
    public void setInUse(){
        this.inUse = true;
    }
    
    public void setFalse(){
        this.inUse = false;
    }
    
    public boolean isClicked(int mouseX, int mouseY){
        return (mouseX >= x && mouseX <= x + width) && (mouseY >= y && mouseY <= y + height);
    }
//    
//    public void buttonDraw(){
//        app.fill(255);
//        app.rect(x, y, width, height);
//    }
}
