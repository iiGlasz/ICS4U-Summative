/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;
import processing.core.PApplet;
import java.util.ArrayList;

/**
 *
 * @author ljphi
 */
public class Battle extends PApplet{

    /**
     * Handles all of the player interactions and drawing of entities in every battle
     * @param player the player object
     * @param entities list of entity objects
     * @param boss the boss object
     * @param keyPressed whether or not a key has been pressed
     */
    public static void BattleStart(Player player, ArrayList<Entity> entities, Bosses boss, boolean keyPressed){
            // call the buffs from TeamMembers if they are active
            TeamMembers.buffs(player);
            // draw the boss
            boss.draw();
            
            // if the boss is alive, make it attack
            if (boss.currentBossHealth > 0){
                boss.attackPattern(player);
            }
            
            // loop through all entities
            for (Entity e : entities){
                //draw them if they aren't dead
                if (!e.used){
                    e.draw();
                }
            }

            // move and make the player face the correct direction
            if (keyPressed){
                if(player.movingLeft && player.playerX >= 0){
                    player.move(-5);
                }
                if(player.movingRight && player.playerX <= 800 - player.width){
                    player.move(5);
                }
            }

           // loop through all the boss' projectiles and check if they hit the player
           for (Projectile p: boss.projectiles){
                if (player.entityCollision(p) && !p.used && !player.isInvincible){
                   player.takeDamage();
                   p.used = true; 
               }
           }
           
           // check if the entities have hit the player
           for (Entity e: entities){
                if (player.entityCollision(e) && !e.used && !player.isInvincible){
                   player.takeDamage();
                }
                if (e instanceof Bosses){
                    e.used = false;
                }
           }
           // draw player last so it's on top
            player.draw(boss.projectiles, entities);
        }
    }
