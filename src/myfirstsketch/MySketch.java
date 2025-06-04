/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;

import processing.core.PApplet;


/**
 *
 * @author ljphi
 */
public class MySketch extends PApplet{
    private Entity car1;
    private Entity car2;
    
    public void settings(){
        size(400,400);
    }
    
    public void setup(){
        background(255);
        car1 = new Entity(this, 50, 50, 1, "images/car.png");
        car2 = new Entity(this, 100, 100, 2, "images/car.png");
        
    }
    
    public void draw(){
        background(255);
        car1.draw();
        car2.draw();
        drawCollisions();
//        if (showInfo){
//            person1.displayInfo(this);
//        }
        if (keyPressed){
            if(keyCode == LEFT){
                car2.move(-5,0);
            }
            else if(keyCode == RIGHT){
                car2.move(5,0);
            }
            else if(keyCode == UP){
                car2.move(0,-5);
            }
            else if(keyCode == DOWN){
                car2.move(0,5);
            }
        }
       car2.draw();
//       
//       if (person1.isCollidiingWith(person2)){
//           fill(255,0,0);
//           this.text("oouch", person2.x, person2.y);
//       }
    }
    public void drawCollisions(){
        if (car1.isCollidingWith(car2)){
            fill(255, 0, 0);
            this.text("CRASH", 200, 20);
        }
    }
    public void mousePressed(){
//        if (person1.isClicked(mouseX, mouseY)){
//            showInfo = true;
//        } else{
//            showInfo = false;
//        }
    }
    
    public void keyPressed(){
//            if (stage == 0){
//                if (keyCode == ENTER){
//                    stage = 1;
//                    int speed = Integer.parseInt(inputString);
//                    car = new Car(this, 10,150, speed, "images/person.png");
//                }
//                else if (key != CODED){
//                    inputString += key;
//                }
//            }
    }
}
