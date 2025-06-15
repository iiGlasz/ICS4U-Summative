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
    // variables
    private int x, y;
    private int width, height;
    private PApplet app;
    private boolean inUse;
    
    // constructor for a button
    public Button(PApplet p, int x, int y, int width, int height){
        this.app = p;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.inUse = false;
    }
    
    /**
     * Sets the button to be active
     */
    public void setInUse(){
        this.inUse = true;
    }
    
    /**
     * Sets the button to be inactive
     */
    public void setFalse(){
        this.inUse = false;
    }
    
    /**
     * Checks that the button has been pressed
     * @param mouseX x position of the mouse
     * @param mouseY y position of the mouse
     * @return true if the mouse was clicked within the borders of the button
     */
    public boolean isClicked(int mouseX, int mouseY){
        return (mouseX >= x && mouseX <= x + width) && (mouseY >= y && mouseY <= y + height);
    }
}
