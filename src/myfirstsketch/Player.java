/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
/**
 *
 * @author ljphi
 */
public class Player extends MySketch{
    // combat variables & stats
    public static int max_Health = 5;
    public int health = 5;
    private int discipline = 0;
    public int healthPots = 3;
    
    // drawing variables
    public int playerX, playerY;
    public int width, height;
    private PImage image;
    private PApplet app;
    
    // movement variables
    private int yVelocity = 0;
    public boolean isJumping = false;
    private final int GRAVITY = 1;
    private final int JUMP_STRENGTH = -15;
    
    public boolean movingRight = false;
    public boolean movingLeft = false;
    public boolean jumpKeyHeld = false;
    public boolean facingLeft = false;
    public boolean facingRight = true;
    public int numberJumps = 0;
    
    // attack variables
    private boolean isAttacking = false;
    private int attackTimer = 0;
    private int attackDuration = 10;
    
    public boolean canAttack = true;
    private int cooldownTimer = 0;
    private int cooldownDuration = 20;
    public int damage = 15;
    
    
    
    // constructor
    public Player(PApplet p, int x, int y, String imagePath){
        this.app = p;
        this.playerX = x;
        this.playerY = y;
        this.max_Health = 5;
        this.health = 5;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    public void move(int dx){
        playerX += dx;
    }
    
    public void heal(){
        if (healthPots > 0 && health < max_Health){
            health++;
            healthPots--;
        }
    }
    
    public void startAttack(){
        if (canAttack){
            isAttacking = true;
            canAttack = false;
            attackTimer = attackDuration;
            cooldownTimer = cooldownDuration;
        }
    }
    
    @Override
    public void keyPressed() {
        if (!isJumping && numberJumps < 2) {
            yVelocity = JUMP_STRENGTH;
            isJumping = true;
            numberJumps++;
        }
    }
    
    public boolean isCollidingWith(Projectile other){
        boolean isLeftOfOtherRight = playerX < other.projX + other.pWidth;
        boolean isRightOfOtherLeft = playerX + width > other.projX;
        boolean isAboveOtherBottom = playerY < other.projY + other.pHeight;
        boolean isBelowOtherTop = playerY + height > other.projY;
        
        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }
    
    public boolean entityColiision(Entity other){
        boolean isLeftOfOtherRight = playerX < other.x + other.width;
        boolean isRightOfOtherLeft = playerX + width > other.x;
        boolean isAboveOtherBottom = playerY < other.y + other.height;
        boolean isBelowOtherTop = playerY + height > other.y;
        
        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }

    public boolean attackCollidingWith(Projectile other, int attackX, int attackY, int attackW, int attackH){
        boolean isLeftOfOtherRight = attackX < other.projX + other.pWidth;   
        boolean isRightOfOtherLeft = attackX + attackW > other.projX;   
        boolean isAboveOtherBottom = attackY < other.projY + other.pHeight;
        boolean isBelowOtherTop = attackY + attackH > other.projY;

        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }
    
    public boolean attackCollidingWith(Entity entity, int attackX, int attackY, int attackW, int attackH){ 
        boolean  eIsLeftOfOtherRight = attackX < entity.x + entity.width;
        boolean  eisRightOfOtherLeft = attackX + attackW > entity.x;
        boolean  eisAboveOtherBottom = attackY < entity.y + entity.height;
        boolean  eisBelowOtherTop = attackY + attackH > entity.y;
        
        return eIsLeftOfOtherRight && eisRightOfOtherLeft && eisAboveOtherBottom && eisBelowOtherTop;
    }
    
    public void draw(ArrayList<Projectile> projectiles, ArrayList<Entity> entities){
        if (isJumping || playerY < 300) {
            yVelocity += GRAVITY;
            playerY += yVelocity;
        
            // Land on ground
            if (playerY >= 300) {
              playerY = 300;
              yVelocity = 0;
              isJumping = false;
              numberJumps = 0;
            }
        }
        
        
        // draw a health bar rectangle with sectioned parts
        app.fill(0,255,0);
        app.rect (20, 30, 10, 80);
        app.rect (20, 30, 10, 64);
        app.rect (20, 30, 10, 48);
        app.rect (20, 30, 10, 32);
        app.rect (20, 30, 10, 16); 
        
        // replace healthbar with red depending on health
        app.fill(255,0,0);
        switch (health){
            case -1:
                app.rect (20, 30, 10, 80);
            case 0:
                app.rect (20, 30, 10, 80);
            case 1:
                app.rect (20, 30, 10, 64);
            case 2:
                app.rect (20, 30, 10, 48);
            case 3:
                app.rect (20, 30, 10, 32);
            case 4:
               app.rect (20, 30, 10, 16); 
            
        }
        // if health drops to 0
        if (health <= 0){
            health = 0;
            app.fill(0);
            app.rect(400,0,400,400);
        }
        
        
        // Attacking mechanics
        if (isAttacking){
            int attackX = playerX + width;
            int attackY = playerY -5;
            int attackW = 40;
            int attackH = 50;
            // switch the direction of the attack based on the direction the player is moving
            if (facingLeft){
                attackX = playerX - attackW;
            }
            
            app.fill(255,0,0);
            app.rect(attackX, attackY, attackW, attackH);

            for (Projectile p : projectiles){
                if (attackCollidingWith(p, attackX, attackY, attackW, attackH)) {
                    p.used = true;
                }
            }
            
            for (Entity e : entities){
                if (attackCollidingWith(e, attackX, attackY, attackW, attackH)) {
                    e.used = true;
                    if (e instanceof Bosses && e.eCanBeHit){
                        Bosses boss = (Bosses) e; 
                        boss.currentBossHealth -= damage;
                        e.eCanBeHit = false;
                    }
                }
            }
            attackTimer --;
            if (attackTimer <= 0){
                isAttacking = false;
            }
        }
        
        if (!canAttack){
            cooldownTimer --;
            System.out.println(cooldownTimer);
            
            if (cooldownTimer <= 0){
                canAttack = true;
                // make it so that bosses can be hit
                for (Entity e : entities){
                    if (e instanceof Bosses)
                        e.eCanBeHit = true;     
                }
            }
        }  
        
        // draw the character sprite
        app.image(image, playerX, playerY);
    }
  
}
