package gg.steve.elemental.tokens.cmd.admin;

import gg.steve.elemental.tokens.message.CommandDebug;
import gg.steve.elemental.tokens.message.MessageType;
import gg.steve.elemental.tokens.permission.PermissionNode;
import org.bukkit.command.CommandSender;

public class AdminHelpCmd {

    public static void help(CommandSender sender) {
        if (!PermissionNode.ADMIN_HELP.hasPermission(sender)) {
            CommandDebug.INSUFFICIENT_PERMISSION.message(sender, PermissionNode.ADMIN_HELP.get());
            return;
        }
        MessageType.HELP.message(sender);
    }
}
