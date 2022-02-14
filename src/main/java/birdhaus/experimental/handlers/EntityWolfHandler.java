package birdhaus.experimental.handlers;

import birdhaus.experimental.userinterface.SendInChatMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

/* EntityWolfHandler.java
Responsible for handling all logic related to player entity tamed wolf control.
*/
public class EntityWolfHandler {

    /*
    This method is responsible for instructing the player entity tamed wolves to attack a specified target. The target is generated
    from the getTarget method, which performs vectoring to determine the target in line of sight.
    The Player utilizes a bone that is held in hand to command the wolves.
        Params:
        - Player p: The player object that sent the instruction
    */
    public void attackEntityInLineOfSight(Player player){
        Entity hostileToAttack = getTarget(player, player.getWorld().getEntities());
        List<Entity> playerTamedWolves = getPlayerTamedWolves(player);

        World w = player.getWorld();

        try {
            if (hostileToAttack != null && isSelectedEntityHositle(hostileToAttack, player)) {
                w.spawnParticle(Particle.FLASH, (hostileToAttack).getLocation(), 1);

                for (Entity wolf : playerTamedWolves) {
                    Wolf _wolf = (Wolf) wolf;
                    _wolf.setTarget((LivingEntity) hostileToAttack);
                    _wolf.setAngry(true);
                    _wolf.setHealth(20d);
                    _wolf.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,1000, 2));
                }

                // new SendUserSubTitle(p, "Instructing wolf to attack");
            }

            else {
                for (Entity wolf : playerTamedWolves) {
                    Wolf _wolf = (Wolf) wolf;
                    _wolf.setTarget(null);
                    _wolf.setAngry(false);
                    _wolf.setHealth(20d);
                    w.spawnParticle(Particle.GLOW, _wolf.getLocation(), 5);
                }
                // new SendInChatMessage(p, "You're not looking at a hostile");
            }
        }

        catch (ClassCastException cce) {
            new SendInChatMessage(player, "There was an issue path finding to the requested entity");
            getLogger().warning("[BirdHaus.Experimental] Illegal entity selected for wolf: \n" + cce);

        }
    }

    /*
    This method is responsible for determining if the selected entity is a damageable Entity (mobs, players, etc) as well as veryifing that
    the selected entity to attack isn't the players own wolf.
    The Player utilizes a bone that is held in hand to command the wolves.
        Params:
        - Entity e: The selected entity targeted for attack instruction
        - Player p: The player object that sent the instruction
    */
    public boolean isSelectedEntityHositle(Entity e, Player p){
        if (e instanceof Damageable){

            // prevent wolf from attacking players own tamable wolf
            if (e instanceof Wolf){
                if (((Wolf) e).getOwner() != null && ((Wolf) e).getOwner().getName() == p.getName()){
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    /*
    This method is responsible for vectoring the current in-line-of-sight target through math.
    This method determines what entity the player is looking at in the game world.
    The Player utilizes a bone that is held in hand to command the wolves.
        Params:
        - final Entity entity: the player instructing the command
        - final Iterable<T> entities: the entities in the world, around the player (this can be made more efficient by getting # of entities within
                                       a certain distance from the player
    */
    private static <T extends Entity> T getTarget(final Entity entity, final Iterable<T> entities) {
        if (entity == null)
            return null;
        T target = null;
        final double threshold = 1;
        for (final T other : entities) {
            final Vector n = other.getLocation().toVector()
                    .subtract(entity.getLocation().toVector());
            if (entity.getLocation().getDirection().normalize().crossProduct(n)
                    .lengthSquared() < threshold
                    && n.normalize().dot(
                    entity.getLocation().getDirection().normalize()) >= 0) {
                if (target == null
                        || target.getLocation().distanceSquared(
                        entity.getLocation()) > other.getLocation()
                        .distanceSquared(entity.getLocation()))
                    target = other;
            }
        }
        return target;
    }

    /*
    DEPRECATED
    Old method of selecting nearby hostile entity based on nearby player entity location
    */
    public Entity identifyHostileEntity(Player p){
        List<Entity> nearbyEntities = p.getNearbyEntities(50.0d, 50.0d, 50.0d);

        for (Entity entity : nearbyEntities){
            if (entity instanceof Zombie){
                return entity;
            }
        }

        return null;
    }

    /*
    This method is responsible for getting a list of all of the player-tamed wolves
        Params:
        - Player p: the player to get all player-tamed wolves
        Returns:
        - List<Entity> playerTamedWolves: a list of the wolves in the current game world that the player has tamed
    */
    public List<Entity> getPlayerTamedWolves(Player p) {
        List<Entity> playerTamedWolves = new ArrayList<>();

        for (World w : Bukkit.getWorlds()) {
            for (Entity entity : w.getEntitiesByClasses(Wolf.class)) {
                if (entity instanceof Wolf) {
                    if (((Tameable) entity).isTamed()) {
                        if (((Tameable) entity).getOwner().getName().equals(PlainTextComponentSerializer.plainText().serialize(p.name()))) {
                            playerTamedWolves.add(entity);
                        }
                    }
                }
            }
        }

        return playerTamedWolves;
    }

    /*
    DEPCRECATED
    */
    public boolean playerOwnsTamedWolves(Player p){
        if (getPlayerTamedWolves(p).size() > 0){
            return true;
        }

        return false;
    }
}
