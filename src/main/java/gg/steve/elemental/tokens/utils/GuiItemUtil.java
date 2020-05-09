package gg.steve.elemental.tokens.utils;

import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class GuiItemUtil {

    public static ItemStack createItem(ConfigurationSection section, String entry) {
        ItemBuilderUtil builder;
        if (section.getString(entry + ".material").startsWith("hdb")) {
            String[] parts = section.getString(entry + ".material").split("-");
            builder = new ItemBuilderUtil(new HeadDatabaseAPI().getItemHead(parts[1]));
        } else {
            builder = new ItemBuilderUtil(section.getString(entry + ".material"), section.getString(entry + ".data"));
        }
        builder.addName(section.getString(entry + ".name"));
        builder.addLore(section.getStringList(entry + ".lore"));
        builder.addEnchantments(section.getStringList(entry + ".enchantments"));
        builder.addItemFlags(section.getStringList(entry + ".item-flags"));
        builder.addNBT();
        return builder.getItem();
    }
}
