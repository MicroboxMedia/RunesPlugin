package media.microbox.runes.commands;

import media.microbox.runes.items.Rune;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;

public class RuneCommand implements CommandExecutor {

    private String usage;

    public RuneCommand(){
        usage = Utils.translateColorCodes("&6Usage: &7/rune give <player> <haste|speed|strength|res|fres|jboost|recall|invis|fly|luck> <duration> <amount>");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("quantumrunes.give") && !sender.isOp()) {
            return true;
        }

        if (args.length != 5) {
            sender.sendMessage(usage);
            return true;
        }

        if (!args[0].equalsIgnoreCase("give")) {
            sender.sendMessage(usage);
            return true;
        }

        if (Bukkit.getPlayer(args[1]) == null) {
            sender.sendMessage(Utils.translateColorCodes("&6Error: &7That player does not exist."));
            return true;
        }

        Player player = Bukkit.getPlayer(args[1]);

        String rune = args[2];

        if (!validRune(rune)) {
            sender.sendMessage(Utils.translateColorCodes("&6Error: &7That rune is not valid."));
            return true;
        }

        if (!isInteger(args[3])) {
            sender.sendMessage(Utils.translateColorCodes("&6Error: &7Invalid duration."));
            return true;
        }

        int duration = Integer.parseInt(args[3]);

        if (!isInteger(args[4])) {
            sender.sendMessage(Utils.translateColorCodes("&6Error: &7Invalid amount."));
            return true;
        }

        int amount = Integer.parseInt(args[4]);

        PotionEffectType effectType = getType(rune);

        String[] lore = new String[] {
                Utils.translateColorCodes("&7&m+--------------------------------------------+"),
                Utils.translateColorCodes("&7&oA mysterious rune that gives special powers..."),
                Utils.translateColorCodes("&aLasts for " + duration + " seconds..."),
                Utils.translateColorCodes("&7&m+--------------------------------------------+")
        };

        Rune runeItem = new Rune(getMaterial(rune), amount, Utils.translateColorCodes(getDisplayName(rune)), lore, effectType, duration);

        assert player != null;

        sender.sendMessage(Utils.translateColorCodes("&aSuccessfully gave " + amount + " " + rune + " runes to " + player.getName() + "."));

        player.getInventory().addItem(runeItem.getRune());

        return true;
    }

    private boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validRune(String rune) {
        switch (rune) {
            case "haste":
            case "speed":
            case "strength":
            case "res":
            case "fres":
            case "jboost":
            case "recall":
            case "invis":
            case "fly":
            case "luck":{
                return true;
            }
            default: {
                return false;
            }
        }
    }

    private PotionEffectType getType(String rune) {
        switch (rune) {
            case "haste": {
                return PotionEffectType.FAST_DIGGING;
            }
            case "speed": {
                return PotionEffectType.SPEED;
            }
            case "strength": {
                return PotionEffectType.INCREASE_DAMAGE;
            }
            case "res": {
                return PotionEffectType.DAMAGE_RESISTANCE;
            }
            case "fres": {
                return PotionEffectType.FIRE_RESISTANCE;
            }
            case "jboost": {
                return PotionEffectType.JUMP;
            }
            case "recall":{
                return PotionEffectType.DOLPHINS_GRACE;
            }
            case "invis":{
                return PotionEffectType.INVISIBILITY;
            }
            case "fly":{
                return PotionEffectType.LEVITATION;
            }
            case "luck":{
                return PotionEffectType.LUCK;
            }
            default: {
                return null;
            }
        }
    }

    private Material getMaterial(String rune) {
        switch (rune) {
            case "haste": {
                return Material.BLUE_DYE;
            }
            case "speed": {
                return Material.LIME_DYE;
            }
            case "strength": {
                return Material.ORANGE_DYE;
            }
            case "res": {
                return Material.BLACK_DYE;
            }
            case "fres": {
                return Material.RED_DYE;
            }
            case "jboost": {
                return Material.CYAN_DYE;
            }
            case "recall": {
                return Material.PURPLE_DYE;
            }
            case "invis": {
                return Material.WHITE_DYE;
            }
            case "fly":{
                return Material.FEATHER;
            }
            case "luck":{
                return Material.YELLOW_DYE;
            }
            default: {
                return null;
            }
        }
    }

    private String getDisplayName(String rune) {
        switch (rune) {
            case "haste": {
                return "&9Rune of Haste";
            }
            case "speed": {
                return "&aRune of Speed";
            }
            case "strength": {
                return "&6Rune of Strength";
            }
            case "res": {
                return "&0Rune of Resistance";
            }
            case "fres": {
                return "&cRune of Fire Resistance";
            }
            case "jboost": {
                return "&3Rune of Jump Boost";
            }
            case "recall":{
                return "&5Rune of Recall";
            }
            case "invis": {
                return "&fRune of Invisibility";
            }
            case "fly":{
                return "&fRune of Flight";
            }
            case "luck":{
                return "&6Rune of Luck";
            }
            default: {
                return null;
            }
        }
    }
}
