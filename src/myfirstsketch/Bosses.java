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
//        this.
    }

    @Override
    public void draw(Player player){
        app.fill(255, 0, 0);
        app.rect(x, y, width, height);
        
        // draw a health bar rectangle 
        app.fill(0,255,0);
        app.rect (130, 360, 300, 25);
        
        // if health drops to 0
        if (bossHealth <= 0){
            bossHealth = 0;
            currentBossHealth = 0;
            this.used = true;
            app.rect(400,0,400,400);
        }
        else {
            // replace healthbar with red depending on health
            app.fill(255,0,0);
            app.rect(130 + currentBossHealth, 360, bossHealth - currentBossHealth, 25);
        }
    }
    
    
}
