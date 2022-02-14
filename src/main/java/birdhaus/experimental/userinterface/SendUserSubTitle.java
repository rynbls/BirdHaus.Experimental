package birdhaus.experimental.userinterface;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

public class SendUserSubTitle {
    public SendUserSubTitle(Player p, String message) {
        p.showTitle(Title.title(Component.text(""), Component.text(message)));
    }
}
