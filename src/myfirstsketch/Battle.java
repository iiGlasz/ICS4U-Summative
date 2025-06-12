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
    
    public Battle(){
    }

    public void BattleStart(Player player, ArrayList<Projectile> projectiles, ArrayList<Entity> entities, Bosses boss, boolean keyPressed){
            TeamMembers.buffs(player);
            boss.draw(player);
            if (boss.currentBossHealth > 0){
                boss.attackPattern(player);
            }
            for (Entity e : entities){
                if (!e.used)
                    e.draw(player);
                else if (e instanceof Bosses){
                    e.used = false;
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

           for (Projectile p: projectiles){
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
            player.draw(projectiles, entities);
        }
    }
