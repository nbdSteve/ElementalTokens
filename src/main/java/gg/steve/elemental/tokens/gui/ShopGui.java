package gg.steve.elemental.tokens.gui;

import gg.steve.elemental.bps.api.BackpacksApi;
import gg.steve.elemental.tokens.utils.GuiItemUtil;
import org.bukkit.configuration.ConfigurationSection;

public class ShopGui extends AbstractGui {
    private ConfigurationSection section;

    /**
     * Constructor the create a new Gui
     *
     * @param section
     */
    public ShopGui(ConfigurationSection section) {
        super(section, section.getString("type"), section.getInt("size"));
        this.section = section;
        refresh();
    }

    public void refresh() {
        for (String entry : section.getKeys(false)) {
            try {
                Integer.parseInt(entry);
            } catch (Exception e) {
                continue;
            }
            setItemInSlot(section.getInt(entry + ".slot"), GuiItemUtil.createItem(section, entry), player -> {
                switch (section.getString(entry + ".action")) {
                    case "none":
                        break;
                    case "close":
                        player.closeInventory();
                        break;
                    case "token-enchant":
                        break;
                    case "backpack":
                        if (BackpacksApi.getInstance() != null) {
                            player.closeInventory();
                            BackpacksApi.openBackpack(player);
                        }
                        break;
                    case "prestige-enchant":
                        break;
                }
            });
        }
    }
}
