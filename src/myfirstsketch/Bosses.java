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
    
    private int bossHealth = 300;
    public int currentBossHealth;
    Random rand = new Random();
    private PImage image;
    
    // attack related variables
    public ArrayList<Projectile> projectiles;
    private int [][] projectilePattern; // [row][column]
    
    private int patternStep = 0;
    private int patternCooldown = 180; // delay between steps
    private int stepCounter = 0;
    
    
    public Bosses (PApplet p, int x, int y, int width, int height, int maxHealth, String imagePath){
        super(p, x, y);
        this.bossHealth = maxHealth;
        this.currentBossHealth = bossHealth;
        this.image = app.loadImage(imagePath);
        this.width = image.pixelWidth;
        this.height = image.pixelHeight;
               
        
        this.projectilePattern = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
        this.projectiles = new ArrayList<>();
    }
    
    public void attackPattern(Player player){
        stepCounter++;

        // Only trigger every `patternCooldown` frames
        if (stepCounter >= patternCooldown){
            stepCounter = 0;

            // Loop over the current pattern row
            if (patternStep < projectilePattern.length){
                for (int i = 0; i < projectilePattern[patternStep].length; i++){
                    if (projectilePattern[patternStep][i] == 1){
                        projectiles.add(new Projectile(app, rand.nextInt(501, 780), rand.nextInt(150,300), 20, 20, false, rand.nextInt(-1,1), "images/enemies/projectile.png"));
                    }
                }
                patternStep++;
            } else {
                //loops the attack pattern
                patternStep = 0; 
            }
        }
        // draw the projectiles
        for (Projectile p : projectiles){
            if (!p.used && patternStep % 2 == 0)
                p.drawGravity(); 
            else if (!p.used)
                p.drawStraight();

            if (player.isCollidingWith(p) && !p.used && !player.isInvincible){
               player.takeDamage();
               p.used = true; 
           }
        }
    }
  

    @Override
    public void draw(Player player){
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
