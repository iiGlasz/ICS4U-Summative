/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ljphi
 */
public class Battle extends PApplet{
     Random rand = new Random();
    
    public Battle(){
    }

    public void BattleStart(Player player, ArrayList<Entity> entities, Bosses boss, boolean keyPressed){
            
            
            TeamMembers.buffs(player);
            boss.draw(player);
            
            if (boss.currentBossHealth > 0){
                boss.attackPattern(player);
            }
            for (Entity e : entities){
                if (e instanceof Bosses){
                    e.used = false;
                }
                else if (!e.used){
                    e.draw(player);
                }
            }

            if (keyPressed){
                if(player.movingLeft && player.playerX >= 0){
                    player.move(-5);
                }
                if(player.movingRight && player.playerX <= 800 - player.width){
                    player.move(5);
                }
            }

           for (Projectile p: boss.projectiles){
                if (player.isCollidingWith(p) && !p.used && !player.isInvincible){
                   player.takeDamage();
                   p.used = true; 
               }
           }
           
           for (Entity e: entities){
                if (player.entityColiision(e) && !e.used && !player.isInvincible){
                   player.takeDamage();
                   
               }
           }
           // draw player last so it's on top
            player.draw(boss.projectiles, entities);
        }
    }
