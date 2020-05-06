package gg.steve.elemental.tokens.cmd;

import com.google.gson.internal.$Gson$Types;
import gg.steve.elemental.tokens.cmd.sub.BalanceCmd;
import gg.steve.elemental.tokens.cmd.sub.HelpCmd;
import gg.steve.elemental.tokens.cmd.sub.PayCmd;
import gg.steve.elemental.tokens.core.TokenType;
import gg.steve.elemental.tokens.message.CommandDebug;
import gg.steve.elemental.tokens.message.MessageType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TokensCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tokens")) {
            if (args.length == 0) {
                BalanceCmd.balance(sender, TokenType.TOKEN);
                return true;
            }
            switch (args[0]) {
                case "pay":
                case "p":
                    PayCmd.pay(sender,args, TokenType.TOKEN);
                    break;
                case "balance":
                case "bal":
                case "b":
                    BalanceCmd.balance(sender, TokenType.TOKEN);
                    break;
                case "help":
                case "h":
                    HelpCmd.help(sender);
                    break;
                default:
                    CommandDebug.INVALID_COMMAND.message(sender);
                    break;
            }
        }
        return true;
    }
}
