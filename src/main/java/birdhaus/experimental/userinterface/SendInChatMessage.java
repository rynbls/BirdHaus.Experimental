package birdhaus.experimental.userinterface;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SendInChatMessage {

    /* SendInChatMessage.java
        Responsible for sending the specified player an in-game message through the chat pane.
        Params:
         - Player p: The player object to send the chat message to
         - String message: The message to send the player
     */
    public SendInChatMessage(Player p, String message) {
        p.sendMessage(ChatColor.DARK_RED + "BirdHaus >> " + ChatColor.WHITE + message);
    }
}
