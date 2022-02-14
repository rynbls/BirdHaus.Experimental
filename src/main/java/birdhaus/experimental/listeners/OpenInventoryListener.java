package birdhaus.experimental.listeners;

import birdhaus.experimental.userinterface.SendInChatMessage;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class OpenInventoryListener implements Listener {

    /* OpenInventoryListener.java
    This event handler listener will be triggered from the server everytime the player opens their inventory.
     Params:
      - InventoryOpenEvent e: The server-side NMS packet event that is sent
     WORK IN PROGRESS
    */
    @EventHandler
    public void onMove(InventoryOpenEvent e){
        Player player = (Player) e.getPlayer();

        if (e.getInventory().contains(Material.BEDROCK)){
            // Work in progress
        }

        else
        {
            // Work in progress
        }
    }
}
