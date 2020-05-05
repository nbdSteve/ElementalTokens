package gg.steve.elemental.tokens.cmd;

import gg.steve.elemental.tokens.cmd.admin.*;
import gg.steve.elemental.tokens.message.CommandDebug;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AdminCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("eta")) {
            if (args.length == 0) {
                AdminHelpCmd.help(sender);
                return true;
            }
            switch (args[0]) {
                case "pay":
                case "p":
                    // pay 2 players
                    break;
                case "balance":
                case "bal":
                case "b":
                    BalanceQueryCmd.balanceQuery(sender, args);
                    break;
                case "help":
                case "h":
                    AdminHelpCmd.help(sender);
                    break;
                case "add":
                case "a":
                    AddCmd.add(sender, args);
                    break;
                case "reload":
                    ReloadCmd.reload(sender);
                    break;
                case "reset":
                    ResetCmd.reset(sender, args);
                    break;
                case "remove":
                    RemoveCmd.remove(sender, args);
                    break;
                case "set":
                case "s":
                    SetCmd.set(sender, args);
                    break;
                default:
                    CommandDebug.INVALID_COMMAND.message(sender);
                    break;
            }
        }
        return true;
    }
}
