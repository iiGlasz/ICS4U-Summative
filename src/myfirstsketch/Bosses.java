package myfirstsketch;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/**
 *
 * @author ljphi
 */
public class Bosses extends Entity{
    // variables for the boss
    private int bossHealth = 300;
    public int currentBossHealth;
    Random rand = new Random();
    private PImage image;
    
    // attack related variables
    public ArrayList<Projectile> projectiles;
    private int [][] projectilePattern;
    
    private int patternRow = 0;
    private int patternCooldown = 180; // 3s cooldown
    private int cooldownTracker = 0;
    
    // constructor 
    public Bosses (PApplet p, int x, int y, int width, int height, int maxHealth, String imagePath){
        super(p, x, y);
        this.bossHealth = maxHealth;
        this.currentBossHealth = bossHealth;
        this.image = app.loadImage(imagePath);
        this.width = image.pixelWidth;
        this.height = image.pixelHeight;
               
        
        this.projectilePattern = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1}, // more front heavy
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1}, // back heavy
            {0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1}, // back
            {1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0}, // front heavy
        };
        this.projectiles = new ArrayList<>();
    }
    
    /**
     * creates the attack pattern based on the array list of projectilepattern
     * appends to an arraylist of projectiles then draws them based on the phase
     * of the attack
     * @param player the player object 
     */
    public void attackPattern(Player player){
        // increase the cooldown tracker
        cooldownTracker++;

        // only attack when the stepcounter is equal to the cooldown
        if (cooldownTracker >= patternCooldown){
            cooldownTracker = 0;
            // loop over the projectile pattern rows
            if (patternRow < projectilePattern.length){
                // for each column of the pattern
                for (int i = 0; i < projectilePattern[patternRow].length; i++){
                    // spacing so that each projectile doesnt overlap 
                    int spacing = 20;
                    int x = 450 + i * spacing;
                    // if the column is equal to 1, create a projectile, if its the 4th attack, set it to be higher
                    if (projectilePattern[patternRow][i] == 1 && patternRow % 3 == 0 && patternRow != 0){
                        projectiles.add(new Projectile(app, x, rand.nextInt(150,300) - 100, 20, 20, false, 0, "images/enemies/projectile.png"));
                    }
                    else if (projectilePattern[patternRow][i] == 1){
                        projectiles.add(new Projectile(app, x + 200, rand.nextInt(150,300), 20, 20, false, -6, "images/enemies/projectile.png"));
                    }
                }
                patternRow++;
                
            }
            //loops the attack pattern
            else {
                patternRow = 0; 
            }
        }
        
        // draw the projectiles depending on the attack pattern row
        for (Projectile p : projectiles){
            // draw projectiles falling straight down
            if (!p.used && patternRow % 3 == 0 && patternRow != 0){
                p.drawGravityNoX();
            }
            // draw projectiles being shot forward
            else if (!p.used && patternRow % 2 == 0)
                p.drawGravity(); 
            // draw projectiles moving in a straight line
            else if (!p.used)
               p.draw();
            // if the projectile hits the player
            if (player.entityCollision(p) && !p.used && !player.isInvincible){
               player.takeDamage();
               p.used = true; 
           }
        }
    }
  
    @Override
    /**
     * draws the boss and its health bar
     */
    public void draw(){
        // draw the boss
        app.fill(255, 0, 0);
        app.image(image, x, y);
        
        // draw a health bar rectangle 
        app.fill(0,255,0);
        app.rect (130, 360, 300, 25);
    
        // if health drops to 0
        if (currentBossHealth <= 0){
            currentBossHealth = 0;
            this.used = true;
            app.fill(255,0,0);
            app.rect(130 + currentBossHealth, 360, bossHealth, 25);
            MySketch.battleWon = true;
            MySketch.combat = false;
        }
        else {
            // replace healthbar with red depending on health
            app.fill(255,0,0);
            app.rect(130 + currentBossHealth, 360, bossHealth - currentBossHealth, 25);
        }
        
    } 
}
