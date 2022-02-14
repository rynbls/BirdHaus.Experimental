package birdhaus.experimental.listeners;

import birdhaus.experimental.handlers.EntityWolfHandler;
import birdhaus.experimental.userinterface.SendUserErrSubTitle;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerHandListener implements Listener {

    /*
    This method is responsible for the PlayerInteractEvent that is fired from the server when the player right or left clicks with
    an item in the main hand. If the item is a bone, instruct the player tamed wolves to attack a hostile entity
    */
    @EventHandler
    public void onMove (PlayerInteractEvent event){
        Player p = event.getPlayer();

        if (p.getInventory().getItemInMainHand().getType() == Material.BONE){
            new EntityWolfHandler().attackEntityInLineOfSight(p);
        }
    }
}
