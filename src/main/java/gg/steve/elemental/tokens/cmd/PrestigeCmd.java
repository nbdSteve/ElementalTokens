package gg.steve.elemental.tokens.cmd;

import gg.steve.elemental.tokens.cmd.sub.BalanceCmd;
import gg.steve.elemental.tokens.cmd.sub.HelpCmd;
import gg.steve.elemental.tokens.cmd.sub.PayCmd;
import gg.steve.elemental.tokens.core.TokenType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PrestigeCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("prestige")) {
            if (args.length == 0) {
                BalanceCmd.balance(sender, TokenType.PRESTIGE);
                return true;
            }
            switch (args[0]) {
                case "pay":
                case "p":
                    PayCmd.pay(sender,args, TokenType.PRESTIGE);
                    break;
                case "balance":
                case "bal":
                case "b":
                    BalanceCmd.balance(sender, TokenType.PRESTIGE);
                    break;
                case "help":
                case "h":
                    HelpCmd.help(sender);
                    break;
            }
        }
        return true;
    }
}
