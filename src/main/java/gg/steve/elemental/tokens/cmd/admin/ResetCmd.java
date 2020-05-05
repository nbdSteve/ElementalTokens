package gg.steve.elemental.tokens.cmd.admin;

import gg.steve.elemental.tokens.ElementalTokens;
import gg.steve.elemental.tokens.core.PlayerTokenManager;
import gg.steve.elemental.tokens.core.TokenType;
import gg.steve.elemental.tokens.message.CommandDebug;
import gg.steve.elemental.tokens.message.MessageType;
import gg.steve.elemental.tokens.permission.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ResetCmd {

    public static void reset(CommandSender sender, String[] args) {
        // /eta reset nbdsteve token
        if (!PermissionNode.RESET.hasPermission(sender)) {
            CommandDebug.INSUFFICIENT_PERMISSION.message(sender, PermissionNode.RESET.get());
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
        PlayerTokenManager.resetTokens(type, target);
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.getUniqueId().equals(target)) {
                MessageType.BALANCE_UPDATE_GIVER.message(player, Bukkit.getOfflinePlayer(target).getName(), type.name(), ElementalTokens.getNumberFormat().format(PlayerTokenManager.getTokens(target, type)));
            }
        } else {
            MessageType.BALANCE_UPDATE_GIVER.message(sender, Bukkit.getOfflinePlayer(target).getName(), type.name(), ElementalTokens.getNumberFormat().format(PlayerTokenManager.getTokens(target, type)));
        }
        if (Bukkit.getPlayer(target) != null) {
            MessageType.BALANCE_UPDATE_RECEIVER.message(sender, type.name(), ElementalTokens.getNumberFormat().format(PlayerTokenManager.getTokens(target, type)));
        }
    }
}
