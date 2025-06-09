package myfirstsketch;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import processing.core.PApplet;
import processing.core.PImage; 

/**
 *
 * @author ljphi
 */
public class Bosses extends Entity{
    
    private int bossHealth = 300;
    public int currentBossHealth;
    private Projectile projectiles;
    private int [][] attackPattern;
    
    public Bosses (PApplet p, int x, int y, int width, int height){
        super(p, x, y, width, height);  
        this.bossHealth = 300;
        this.currentBossHealth = bossHealth;
    }

    @Override
    public void draw(Player player){
        // draw the boss
        app.fill(255, 0, 0);
        app.rect(x, y, width, height);
        
        // draw a health bar rectangle 
        
        app.fill(0,255,0);
        app.rect (130, 360, 300, 25);
    
        // if health drops to 0
        if (currentBossHealth <= 0){
            currentBossHealth = 0;
            this.used = true;
            app.fill(255,0,0);
            app.rect(130 + currentBossHealth, 360, bossHealth, 25);
            MySketch.combat = false; // change this so you can get a chest or smth
            MySketch.gameState++;
        }
        else {
            // replace healthbar with red depending on health
            app.fill(255,0,0);
            app.rect(130 + currentBossHealth, 360, bossHealth - currentBossHealth, 25);
            
        }
    }
    
    
}
