package gg.steve.elemental.tokens.cmd;

import gg.steve.elemental.tokens.ElementalTokens;
import gg.steve.elemental.tokens.message.CommandDebug;
import gg.steve.elemental.tokens.message.MessageType;
import gg.steve.elemental.tokens.permission.PermissionNode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuiCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tshop")) {
            if (!(sender instanceof Player)) {
                CommandDebug.ONLY_PLAYERS_ACCESSIBLE.message(sender);
                return true;
            }
            if (args.length != 0) {
                CommandDebug.INVALID_COMMAND.message(sender);
                return true;
            }
            if (!PermissionNode.SHOP_GUI.hasPermission(sender)) {
                CommandDebug.INSUFFICIENT_PERMISSION.message(sender, PermissionNode.SHOP_GUI.get());
                return true;
            }
            Player player = (Player) sender;
            ElementalTokens.openShopGui(player);
        }
        return true;
    }
}
