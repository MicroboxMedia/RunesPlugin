package media.microbox.runes;

import media.microbox.runes.commands.RuneCommand;
import media.microbox.runes.items.Rune;
import media.microbox.runes.listeners.UseRune;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class RunesPlugin extends JavaPlugin {

    private static RunesPlugin instance;

    public static RunesPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable(){

        instance = this;
        Bukkit.getPluginCommand("rune").setExecutor(new RuneCommand());
        getServer().getPluginManager().registerEvents(new UseRune(), this);

    }

    @Override
    public void onDisable(){}

}

