package gg.steve.elemental.tokens.papi;

import gg.steve.elemental.tokens.ElementalTokens;
import gg.steve.elemental.tokens.core.PlayerTokenManager;
import gg.steve.elemental.tokens.core.TokenPlayer;
import gg.steve.elemental.tokens.core.TokenType;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class ElementalTokensExpansion extends PlaceholderExpansion {
    private ElementalTokens instance;

    public ElementalTokensExpansion(ElementalTokens instance) {
        this.instance = instance;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return instance.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "elemental-tokens";
    }

    @Override
    public String getVersion() {
        return instance.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) return "";
        TokenPlayer tPlayer = PlayerTokenManager.getTokenPlayer(player.getUniqueId());
        if (identifier.equalsIgnoreCase("tokens")) {
            return ElementalTokens.getNumberFormat().format(tPlayer.getTokens(TokenType.TOKEN));
        }
        if (identifier.equalsIgnoreCase("prestige")) {
            return ElementalTokens.getNumberFormat().format(tPlayer.getTokens(TokenType.PRESTIGE));
        }
        return null;
    }
}
