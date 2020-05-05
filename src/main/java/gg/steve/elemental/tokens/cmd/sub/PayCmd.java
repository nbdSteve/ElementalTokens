package gg.steve.elemental.tokens.cmd.sub;

import gg.steve.elemental.tokens.core.PlayerTokenManager;
import gg.steve.elemental.tokens.core.TokenType;
import gg.steve.elemental.tokens.message.CommandDebug;
import gg.steve.elemental.tokens.message.MessageType;
import gg.steve.elemental.tokens.permission.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PayCmd {

    public static void pay(CommandSender sender, String[] args, TokenType type) {
        // /token pay nbdsteve 10
        if (!(sender instanceof Player)) {
            CommandDebug.ONLY_PLAYERS_ACCESSIBLE.message(sender);
            return;
        }
        if (args.length != 3) {
            CommandDebug.INVALID_NUMBER_OF_ARGUMENTS.message(sender);
            return;
        }
        UUID playerId = ((Player) sender).getUniqueId();
        switch (type) {
            case TOKEN:
                if (!PermissionNode.PAY_TOKEN.hasPermission(sender)) {
                    CommandDebug.INSUFFICIENT_PERMISSION.message(sender, PermissionNode.PAY_TOKEN.get());
                    return;
                }
                break;
            case PRESTIGE:
                if (!PermissionNode.PAY_PRESTIGE.hasPermission(sender)) {
                    CommandDebug.INSUFFICIENT_PERMISSION.message(sender, PermissionNode.PAY_PRESTIGE.get());
                    return;
                }
                break;
        }
        UUID target = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
        if (playerId.equals(target)) {
            CommandDebug.TARGET_CAN_NOT_BE_SELF.message(sender);
            return;
        }
        int amount;
        try {
            amount = Integer.parseInt(args[2]);
        } catch (Exception e) {
            CommandDebug.INVALID_AMOUNT.message(sender);
            return;
        }
        if (amount < 1) {
            CommandDebug.INVALID_AMOUNT.message(sender);
            return;
        }
        if (PlayerTokenManager.getTokens(playerId, type) < amount) {
            MessageType.INSUFFICIENT_TOKENS.message(sender, type.name());
            return;
        }
        if (PlayerTokenManager.pay(type, playerId, target, amount)) {
            MessageType.PAY_PAYER.message(sender, Bukkit.getOfflinePlayer(target).getName(), args[2], type.name());
            if (Bukkit.getPlayer(target) != null) {
                MessageType.PAY_RECEIVER.message(Bukkit.getPlayer(target), sender.getName(), args[2], type.name());
            }
        } else {
            // player not loaded
        }
    }
}
