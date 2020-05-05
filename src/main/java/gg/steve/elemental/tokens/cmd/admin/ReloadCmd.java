package gg.steve.elemental.tokens.cmd.admin;

import gg.steve.elemental.tokens.ElementalTokens;
import gg.steve.elemental.tokens.managers.Files;
import gg.steve.elemental.tokens.message.CommandDebug;
import gg.steve.elemental.tokens.message.MessageType;
import gg.steve.elemental.tokens.permission.PermissionNode;
import org.bukkit.command.CommandSender;

public class ReloadCmd {

    public static void reload(CommandSender sender) {
        if (!PermissionNode.RELOAD.hasPermission(sender)) {
            CommandDebug.INSUFFICIENT_PERMISSION.message(sender, PermissionNode.RELOAD.get());
            return;
        }
        Files.reload();
        ElementalTokens.get().onDisable();
        ElementalTokens.get().onEnable();
        MessageType.RELOAD.message(sender);
    }
}
