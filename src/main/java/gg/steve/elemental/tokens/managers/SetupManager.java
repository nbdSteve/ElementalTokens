package gg.steve.elemental.tokens.managers;

import gg.steve.elemental.tokens.ElementalTokens;
import gg.steve.elemental.tokens.cmd.AdminCmd;
import gg.steve.elemental.tokens.cmd.GuiCmd;
import gg.steve.elemental.tokens.cmd.PrestigeCmd;
import gg.steve.elemental.tokens.cmd.TokensCmd;
import gg.steve.elemental.tokens.core.PlayerTokenManager;
import gg.steve.elemental.tokens.gui.GuiClickListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * Class that handles setting up the plugin on start
 */
public class SetupManager {

    private SetupManager() throws IllegalAccessException {
        throw new IllegalAccessException("Manager class cannot be instantiated.");
    }

    /**
     * Loads the files into the file manager
     *
     * @param fileManager FileManager, the plugins file manager
     */
    public static void setupFiles(FileManager fileManager) {
        // general files
        for (Files file : Files.values()) {
            file.load(fileManager);
        }
    }

    public static void registerCommands(ElementalTokens instance) {
//        instance.getCommand("ece").setExecutor(new EceCmd());
        instance.getCommand("tokens").setExecutor(new TokensCmd());
        instance.getCommand("prestige").setExecutor(new PrestigeCmd());
        instance.getCommand("eta").setExecutor(new AdminCmd());
        instance.getCommand("tshop").setExecutor(new GuiCmd());
    }

    /**
     * Register all of the events for the plugin
     *
     * @param instance Plugin, the main plugin instance
     */
    public static void registerEvents(Plugin instance) {
        PluginManager pm = instance.getServer().getPluginManager();
        pm.registerEvents(new PlayerTokenManager(), instance);
        pm.registerEvents(new GuiClickListener(), instance);
    }
}
