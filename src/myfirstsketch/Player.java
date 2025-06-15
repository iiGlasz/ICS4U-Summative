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
    public static int discipline = 0;
    public int health = 5;
    public int healthPots = 3;
    
    // drawing variables
    public int playerX, playerY;
    public int width, height;
    private PImage image;
    private PApplet app;
    private PImage attackImage;
    
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
    private int attackDuration = 10; // 0.16s
    
    public boolean canAttack = true;
    public static int damage = 15;
    private int cooldownTimer = 0;
    private int cooldownDuration = 20; // .33s
    
    // variables for i-frames
    public boolean isInvincible = false;
    private int invincibilityDuration = 40; // 0.75s
    private int invincibilityTimer = 0;
    
    
    
    // constructor
    public Player(PApplet p, int x, int y, String imagePath, String attackImage){
        this.app = p;
        this.playerX = x;
        this.playerY = y;
        this.max_Health = 5;
        this.health = 5;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
        this.attackImage = app.loadImage(attackImage);
    }
    
    /**
     * resets the player's position and health
     */
    public void resetPlayer(){
        playerX = 100;
        health = 5;
        facingRight = true;
    }
    
    /**
     * controls the player's x-axis movement
     * @param dx 
     */
    public void move(int dx){
        playerX += dx;
    }
    
    /**
     * heal the player
     */
    public void heal(){
        // heal if the player has health pots and is under their max health
        if (healthPots > 0 && health < max_Health){
            health++;
            healthPots--;
        }
    }
    
    /**
     * the player takes damage and gets i-frames
     */
    public void takeDamage() {
    if (!isInvincible) {
        this.health --;
        isInvincible = true;
        invincibilityTimer = invincibilityDuration;
    }
}
    /**
     * the player begins to attack and a cooldown is started
     */
    public void startAttack(){
        if (canAttack){
            isAttacking = true;
            canAttack = false;
            attackTimer = attackDuration;
            cooldownTimer = cooldownDuration;
        }
    }
    
    @Override
    /**
     * the player begins to jump and can double jump
     */
    public void keyPressed() {
        // jump if the player is not already jumping and if they have less than 2 current jumps
        if (!isJumping && numberJumps < 2) {
            yVelocity = JUMP_STRENGTH;
            isJumping = true;
            numberJumps++;
        }
    }
    
    /**
     * check if the player is colliding with a projectile
     * @param other projectile object
     * @return true if the user is colliding with a projectile
     */
    public boolean isCollidingWith(Projectile other){
        boolean isLeftOfOtherRight = playerX < other.projX + other.pWidth;
        boolean isRightOfOtherLeft = playerX + width > other.projX;
        boolean isAboveOtherBottom = playerY < other.projY + other.pHeight;
        boolean isBelowOtherTop = playerY + height > other.projY;
        
        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }
    
    /**
     * check if the player is colliding with an entity
     * @param other entity object
     * @return true if the user is colliding with an entity
     */
    public boolean entityColiision(Entity other){
        boolean isLeftOfOtherRight = playerX < other.x + other.width;
        boolean isRightOfOtherLeft = playerX + width > other.x;
        boolean isAboveOtherBottom = playerY < other.y + other.height;
        boolean isBelowOtherTop = playerY + height > other.y;
        
        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }

    /**
     * check if the player's attack is colliding with a projectile
     * @param other projectile object
     * @return true if the attack is colliding with a projectile
     */
    public boolean attackCollidingWith(Projectile other, int attackX, int attackY, int attackW, int attackH){
        boolean isLeftOfOtherRight = attackX < other.projX + other.pWidth;   
        boolean isRightOfOtherLeft = attackX + attackW > other.projX;   
        boolean isAboveOtherBottom = attackY < other.projY + other.pHeight;
        boolean isBelowOtherTop = attackY + attackH > other.projY;

        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }
    
    /**
     * check if the player's attack is colliding with an entity
     * @param other entity object
     * @return true if the attack is colliding with an entity
     */
    public boolean attackCollidingWith(Entity entity, int attackX, int attackY, int attackW, int attackH){ 
        boolean  eIsLeftOfOtherRight = attackX < entity.x + entity.width;
        boolean  eisRightOfOtherLeft = attackX + attackW > entity.x;
        boolean  eisAboveOtherBottom = attackY < entity.y + entity.height;
        boolean  eisBelowOtherTop = attackY + attackH > entity.y;
        
        return eIsLeftOfOtherRight && eisRightOfOtherLeft && eisAboveOtherBottom && eisBelowOtherTop;
    }
    
    /**
     * draw the player, the healthbar, and mechanics such as jumping, attacking, and handling i-frames
     * @param projectiles projectile objects
     * @param entities entity objects
     */
    public void draw(ArrayList<Projectile> projectiles, ArrayList<Entity> entities){
        // if health drops to 0
        if (health <= 0){
            health = 0;
            this.image = app.loadImage("images/wukong/dead.png");
            MySketch.combat = false;
        }
        // change how the player and attack looks if they are facing left
        else if (facingLeft){
            this.attackImage = app.loadImage("images/wukong/attackLeft.png");
            this.image = app.loadImage("images/wukong/wukong-idle-left.png");
        }
        // change how to player and attack looks if they are facing right
        else { 
            this.attackImage = app.loadImage("images/wukong/attackRight.png");
            this.image = app.loadImage("images/wukong/wukong-idle-right.png");
        }
        
        // draw a health bar rectangle with sectioned parts
        app.fill(0,255,0);
        app.rect (20, 40, 10, 80);
        app.rect (20, 40, 10, 64);
        app.rect (20, 40, 10, 48);
        app.rect (20, 40, 10, 32);
        app.rect (20, 40, 10, 16); 
        
        // replace healthbar with red depending on health
        app.fill(255,0,0);
        switch (health){
            case -1:
                app.rect (20, 40, 10, 80);
            case 0:
                app.rect (20, 40, 10, 80);
            case 1:
                app.rect (20, 40, 10, 64);
            case 2:
                app.rect (20, 40, 10, 48);
            case 3:
                app.rect (20, 40, 10, 32);
            case 4:
               app.rect (20, 40, 10, 16); 
        }
        
        // jumping mechanics
        // apply gravity if the player is above the ground
        if (isJumping || playerY < 300) {
            yVelocity += GRAVITY;
            playerY += yVelocity;
        
            // reset the player's jumping when they land on the eground
            if (playerY >= 300) {
              playerY = 300;
              yVelocity = 0;
              isJumping = false;
              numberJumps = 0;
            }
        }
        
        // i-frames, decrease the time for i-frames every frame, and make the
        // player able to take damage once it ends
        if (isInvincible) {
            invincibilityTimer--;
            if (invincibilityTimer <= 0) {
                isInvincible = false;
            }
        }
        
        // attacking mechanics
        if (isAttacking){
            // variables for the player's attack drawing
            int attackX = playerX + width - 30;
            int attackY = playerY ;
            int attackW = 40;
            int attackH = 60;
            
            // switch the direction of the attack based on the direction the player is moving
            if (facingLeft){
                attackX = playerX - attackW + 20;
            }
            
            // draw the attack
            app.fill(255,0,0);
            app.image(attackImage, attackX, attackY, attackW, attackH);

            // loop through the projectiles and check if the attack is colliding
            for (Projectile p : projectiles){
                // destroy the projectile if it is colliding
                if (attackCollidingWith(p, attackX, attackY, attackW, attackH)) {
                    p.used = true;
                }
            }
            
            // loop through the entities and check if the attack is colliding
            for (Entity e : entities){
                // destroy the entity if it is colliding
                if (attackCollidingWith(e, attackX, attackY, attackW, attackH)) {
                    e.used = true;
                    // if the entity is a boss, make it take damage and let it 
                    // be possible to hit again
                    if (e instanceof Bosses && e.eCanBeHit){
                        Bosses boss = (Bosses) e; 
                        boss.currentBossHealth -= damage;
                        e.eCanBeHit = false;
                        e.used = false;
                    }
                }
            }
            // rdeuce the time for how long the attack is on screen
            attackTimer --;
            // when the attack ends, the player is no longer attacking
            if (attackTimer <= 0){
                isAttacking = false;
            }
        }
        
        // if the player cant attack since they are already attacking
        if (!canAttack){
            // reduce their cooldown timer every frame
            cooldownTimer --;
            
            // when the cooldown is done
            if (cooldownTimer <= 0){
                // player can attack again
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
