/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstsketch;

/**
 *
 * @author ljphi
 */
public class TeamMembers{
    public static boolean pigsy = false;
    private static int healingCD = 1200; //time in frames
    
    public static boolean sandy = false;
    
    public static void buffs(Player player){
        if (pigsy){
            healingCD--;
            if (healingCD <= 0 && player.health < player.max_Health){
                player.health++;
                healingCD = 800;
            }
        }
       
        if (sandy){
            Player.damage = 20;
        }
    }
    
        
}
