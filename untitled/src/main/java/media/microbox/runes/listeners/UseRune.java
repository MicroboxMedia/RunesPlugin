package media.microbox.runes.listeners;

import media.microbox.runes.RunesPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static java.lang.Thread.sleep;


public class UseRune implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) throws InterruptedException {

        Player player = event.getPlayer();
        Action action = event.getAction();



        if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {

            if (player.getInventory().getItemInMainHand() != null) {

                ItemStack item = player.getInventory().getItemInMainHand();
                if (item.getType() != Material.AIR) {

                    if (item.hasItemMeta()) {

                        if (item.getItemMeta().hasDisplayName()) {

                            String display = item.getItemMeta().getDisplayName();

                            if (display.contains("§")) {

                                String strippedDisplay = ChatColor.stripColor(display);
                                String[] lore = item.getItemMeta().getLore().toArray(new String[0]);
                                String durationLine = lore[2];
                                int duration = Integer.parseInt(durationLine.split(" ")[2]);

                                if (display.contains("Rune of Recall")){
                                    player.teleport(player.getWorld().getSpawnLocation());

                                    if(item.getAmount() != 1) {
                                        item.setAmount(item.getAmount() - 1);
                                        player.updateInventory();
                                    }else{
                                        player.getInventory().removeItem(item);
                                    }
                                }else if (display.contains("Rune of Flight")){

                                    int flightTime = duration;
                                    player.setAllowFlight(true);
                                    player.setFlying(true);
                                    if(item.getAmount() != 1) {
                                        item.setAmount(item.getAmount() - 1);
                                        player.updateInventory();
                                    }else{
                                        player.getInventory().removeItem(item);
                                    }
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            player.setAllowFlight(false);
                                            player.setFlying(false);
                                        }
                                    }.runTaskLater(RunesPlugin.getInstance(),flightTime * 20);
                                } else if (strippedDisplay.contains("Rune of")) {

                                    String runeType = strippedDisplay.split(" ") [2];
                                    PotionEffectType effectToGive = getType(runeType);


                                    player.addPotionEffect(effectToGive.createEffect(duration*20, 1));

                                    if(item.getAmount() != 1) {
                                        item.setAmount(item.getAmount() - 1);
                                        player.updateInventory();
                                    }else{
                                        player.getInventory().removeItem(item);
                                    }
                                }
                                }
                            }
                        }
                    }
                }
            }
        }



        private PotionEffectType getType(String runeType) {
            switch (runeType) {
                case "Haste": {
                    return PotionEffectType.FAST_DIGGING;
                }
                case "Speed": {
                    return PotionEffectType.SPEED;
                }
                case "Strength": {
                    return PotionEffectType.INCREASE_DAMAGE;
                }
                case "Resistance": {
                    return PotionEffectType.DAMAGE_RESISTANCE;
                }
                case "Fire": {
                    return PotionEffectType.FIRE_RESISTANCE;
                }
                case "Jump": {
                    return PotionEffectType.JUMP;
                }
                case "Invisibility": {
                    return PotionEffectType.INVISIBILITY;
                }
                case "Luck":{
                    return PotionEffectType.LUCK;
                }
                default: {
                    return null;
                }
            }
        }

}
