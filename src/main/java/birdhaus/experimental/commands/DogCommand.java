package birdhaus.experimental.commands;

import birdhaus.experimental.Experimental;
import birdhaus.experimental.handlers.EntityWolfHandler;
import birdhaus.experimental.userinterface.SendUserErrSubTitle;
import birdhaus.experimental.userinterface.SendUserSubTitle;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DogCommand extends EntityWolfHandler implements CommandExecutor {
    private final Experimental experimental;

    public DogCommand(Experimental experimental) {
        this.experimental = experimental;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length > 0){
                if (args[0].equalsIgnoreCase("call")){
                    callNewCompanionDog(player);
                    return true;
                }

                else if (args[0].equalsIgnoreCase("stable")){
                    removePlayerTamedDogs(player);
                    return true;
                }

                else if (args[0].equalsIgnoreCase("move")){
                    moveTamedWolfToLocation(player);
                    return true;
                }

                else if (args[0].equalsIgnoreCase("sit")){
                    alterTameWolfSittingPosition(player);
                    return true;
                }

                else if (args[0].equalsIgnoreCase("brain")){
                    alterTameWolfBrain(player);
                    return true;
                }

                else if (args[0].equalsIgnoreCase("attack")){
                    attackNearestHostileEntity(player);
                    return true;
                }

                else if (args[0].equalsIgnoreCase("tp")){
                    teleportWolfToPlayer(player);
                    return true;
                }

                else if (args[0].equalsIgnoreCase("reload")){

                }
            }
        }

        return false;
    }

    /*
    Teleport all player tamed wolves to the player
    */
    private void teleportWolfToPlayer(Player p){
        List<Entity> playerTamedWolves = getPlayerTamedWolves(p);

        if (playerTamedWolves.size() > 0){
            for (Entity wolf : playerTamedWolves){
                wolf.teleport(p.getLocation());
            }
        }

        else{
            new SendUserSubTitle(p, "You don't have any wolves to teleport");
        }
    }

    private void attackNearestHostileEntity(Player p){
        new EntityWolfHandler().attackEntityInLineOfSight(p);
    }

    /*
    Summon a new tamed wolf
    */
    private void callNewCompanionDog(Player p) {
        String playerName = PlainTextComponentSerializer.plainText().serialize(p.name());

        if (getPlayerTamedWolves(p).size() > 2){
            new SendUserSubTitle(p, "You have too many tamed wolves");
        }

        else {
            Wolf newWolf = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);
            newWolf.setOwner(p);
            newWolf.setCustomName(playerName + "'s Tamed Wolf");
            new SendUserSubTitle(p, "Companion wolf was called");
        }
    }

    /*
    Stable the player tamed wolves that are currently called
    */
    private void removePlayerTamedDogs(Player p) {
        List<Entity> playerTamedWolves = getPlayerTamedWolves(p);

        if (playerTamedWolves != null && playerTamedWolves.size() != 0){
            for (Entity tamedWolf : playerTamedWolves) {
                tamedWolf.remove();
                new SendUserSubTitle(p, "Companion wolf was stabled");
            }
        }

        else
        {
            new SendUserSubTitle(p, "You have no active wolf companions");
        }
    }

    public void alterTameWolfSittingPosition(Player p){
        new SendUserErrSubTitle(p, "This is unavailable right now");
//        if (this.compWolf != null) {
//            if (this.compWolf.isSitting()) {
//                new SendUserSubTitle(p, "You commanded your companion to stand");
//                this.compWolf.setSitting(false);
//            } else {
//                new SendUserSubTitle(p, "You commanded your companion to sit");
//                this.compWolf.setSitting(true);
//            }
//        }
//
//        else {
//            new SendUserSubTitle(p, "You don't have a companion wolf");
//        }
    }

    public void alterTameWolfBrain(Player p){
        new SendUserErrSubTitle(p, "This is unavailable right now");
//        if (this.compWolf != null){
//            if (this.compWolf.hasAI())
//            {
//                new SendUserSubTitle(p, "You turned your companions brain off");
//                this.compWolf.setJumping(true);
//                this.compWolf.setAI(false);
//            }
//
//            else{
//                new SendUserSubTitle(p, "You turned your companions brain on");
//                this.compWolf.setAI(true);
//            }
//        }
//
//        else{
//            new SendUserSubTitle(p, "You don't have a companion wolf");
//        }
    }

    public void moveTamedWolfToLocation(Player p){
//        Block block = p.getTargetBlock(120);
//        Location location = block.getLocation();
//        this.compWolf.setSitting(false);
//
//        Entity wolf = this.compWolf;
//        PathfinderGoal
//        this.compWolf.setSitting(true);
    }
}

//            playing around with nearby entities
//            if (sender instanceof Player){
//               p = (Player) sender;
//
//                List<Entity> nearbyEntities = p.getNearbyEntities(50.0d, 50.0d, 50.0d);
//
//                for (Entity entity : nearbyEntities){
//                    getLogger().info("[BirdHause.Experimental] Nearby Entity: " + entity.toString());
//                }