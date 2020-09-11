package media.microbox.runes.commands;

import org.bukkit.ChatColor;

public class Utils {

    public static String translateColorCodes(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
