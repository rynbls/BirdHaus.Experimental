package birdhaus.experimental;

import birdhaus.experimental.commands.DogCommand;
import birdhaus.experimental.listeners.OpenInventoryListener;
import birdhaus.experimental.listeners.PlayerHandListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Experimental extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new OpenInventoryListener(), this);
        getCommand("wolf").setExecutor(new DogCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerHandListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
