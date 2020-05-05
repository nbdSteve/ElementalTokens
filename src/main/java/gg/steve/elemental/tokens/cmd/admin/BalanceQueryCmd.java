package gg.steve.elemental.tokens.cmd.admin;

import gg.steve.elemental.tokens.ElementalTokens;
import gg.steve.elemental.tokens.core.PlayerTokenManager;
import gg.steve.elemental.tokens.core.TokenType;
import gg.steve.elemental.tokens.message.CommandDebug;
import gg.steve.elemental.tokens.message.MessageType;
import gg.steve.elemental.tokens.permission.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class BalanceQueryCmd {

    public static void balanceQuery(CommandSender sender, String[] args) {
        // /eta balance nbdsteve token
        if (!PermissionNode.ADD.hasPermission(sender)) {
            CommandDebug.INSUFFICIENT_PERMISSION.message(sender, PermissionNode.ADD.get());
            return;
        }
        if (args.length != 3) {
            CommandDebug.INVALID_NUMBER_OF_ARGUMENTS.message(sender);
            return;
        }
        TokenType type;
        try {
            type = TokenType.valueOf(args[2].toUpperCase());
        } catch (Exception e) {
            CommandDebug.INVALID_TOKEN_TYPE.message(sender);
            return;
        }
        UUID target = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
        PlayerTokenManager.getTokens(target, type);
        MessageType.BALANCE_QUERY.message(sender, Bukkit.getOfflinePlayer(target).getName(), type.name(), ElementalTokens.getNumberFormat().format(PlayerTokenManager.getTokens(target, type)));
    }
}
