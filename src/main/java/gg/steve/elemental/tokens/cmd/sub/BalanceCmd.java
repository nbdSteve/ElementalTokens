package gg.steve.elemental.tokens.cmd.sub;

import gg.steve.elemental.tokens.ElementalTokens;
import gg.steve.elemental.tokens.core.PlayerTokenManager;
import gg.steve.elemental.tokens.core.TokenType;
import gg.steve.elemental.tokens.message.CommandDebug;
import gg.steve.elemental.tokens.message.MessageType;
import gg.steve.elemental.tokens.permission.PermissionNode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BalanceCmd {

    public static void balance(CommandSender sender, TokenType type) {
        // need to view balance of other player
        if (!(sender instanceof Player)) {
            CommandDebug.ONLY_PLAYERS_ACCESSIBLE.message(sender);
            return;
        }
        UUID playerId = ((Player) sender).getUniqueId();
        switch (type) {
            case TOKEN:
                if (!PermissionNode.BALANCE_TOKEN.hasPermission(sender)) {
                    CommandDebug.INSUFFICIENT_PERMISSION.message(sender, PermissionNode.BALANCE_TOKEN.get());
                    return;
                }
                break;
            case PRESTIGE:
                if (!PermissionNode.BALANCE_PRESTIGE.hasPermission(sender)) {
                    CommandDebug.INSUFFICIENT_PERMISSION.message(sender, PermissionNode.BALANCE_PRESTIGE.get());
                    return;
                }
                break;
        }
        MessageType.BALANCE.message(sender, type.name(), ElementalTokens.getNumberFormat().format(PlayerTokenManager.getTokens(playerId, type)));
    }
}
