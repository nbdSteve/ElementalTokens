package gg.steve.elemental.tokens;

import gg.steve.elemental.tokens.db.ConnectionManager;
import gg.steve.elemental.tokens.db.DatabaseUtil;
import gg.steve.elemental.tokens.gui.ShopGui;
import gg.steve.elemental.tokens.managers.FileManager;
import gg.steve.elemental.tokens.managers.Files;
import gg.steve.elemental.tokens.managers.SetupManager;
import gg.steve.elemental.tokens.papi.ElementalTokensExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;

public final class ElementalTokens extends JavaPlugin {
    private static ElementalTokens instance;
    private static DecimalFormat numberFormat = new DecimalFormat("#,###.##");
    private static ShopGui shopGui;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        SetupManager.setupFiles(new FileManager(instance));
        SetupManager.registerCommands(instance);
        SetupManager.registerEvents(instance);
        DatabaseUtil.setupConnection();
        // register placeholders with papi
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new ElementalTokensExpansion(instance).register();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        DatabaseUtil.saveTokenData();
    }

    public static ElementalTokens get() {
        return instance;
    }

    public static DecimalFormat getNumberFormat() {
        return numberFormat;
    }

    public static void openShopGui(Player player) {
        if (shopGui == null) {
            shopGui = new ShopGui(Files.CONFIG.get().getConfigurationSection("gui"));
        } else {
            shopGui.refresh();
        }
        shopGui.open(player);
    }
}
