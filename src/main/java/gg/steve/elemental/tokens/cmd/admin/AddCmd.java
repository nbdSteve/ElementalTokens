package gg.steve.elemental.tokens.cmd.admin;

import gg.steve.elemental.tokens.ElementalTokens;
import gg.steve.elemental.tokens.core.PlayerTokenManager;
import gg.steve.elemental.tokens.core.TokenType;
import gg.steve.elemental.tokens.event.AddMethodType;
import gg.steve.elemental.tokens.message.CommandDebug;
import gg.steve.elemental.tokens.message.MessageType;
import gg.steve.elemental.tokens.permission.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AddCmd {

    public static void add(CommandSender sender, String[] args) {
        // /eta add nbdsteve token 10
        if (!PermissionNode.ADD.hasPermission(sender)) {
            CommandDebug.INSUFFICIENT_PERMISSION.message(sender, PermissionNode.ADD.get());
            return;
        }
        if (args.length != 4) {
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
        int amount;
        try {
            amount = Integer.parseInt(args[3]);
        } catch (Exception e) {
            CommandDebug.INVALID_AMOUNT.message(sender);
            return;
        }
        if (amount < 1) {
            CommandDebug.INVALID_AMOUNT.message(sender);
            return;
        }
        PlayerTokenManager.addTokens(type, target, amount, AddMethodType.COMMAND);
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.getUniqueId().equals(target)) {
                MessageType.BALANCE_UPDATE_GIVER.message(player, Bukkit.getOfflinePlayer(target).getName(), type.name(), ElementalTokens.getNumberFormat().format(PlayerTokenManager.getTokens(target, type)));
            }
        } else {
            MessageType.BALANCE_UPDATE_GIVER.message(sender, Bukkit.getOfflinePlayer(target).getName(), type.name(), ElementalTokens.getNumberFormat().format(PlayerTokenManager.getTokens(target, type)));
        }
        if (Bukkit.getPlayer(target) != null) {
            MessageType.BALANCE_UPDATE_RECEIVER.message(Bukkit.getPlayer(target), type.name(), ElementalTokens.getNumberFormat().format(PlayerTokenManager.getTokens(target, type)));
        }
    }
}
