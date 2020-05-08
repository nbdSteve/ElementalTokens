package gg.steve.elemental.tokens.message;

import gg.steve.elemental.tokens.managers.Files;
import gg.steve.elemental.tokens.utils.ColorUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public enum MessageType {
    RELOAD("reload"),
    HELP("help"),
    PAY_PAYER("pay-payer", "{player}", "{amount}", "{token-type}"),
    PAY_RECEIVER("give-piece-receiver", "{player}", "{amount}", "{token-type}"),
    INSUFFICIENT_TOKENS("insufficient-tokens", "{token-type}"),
    BALANCE("balance", "{token-type}", "{amount}"),
    MINE_ADD_TOKENS("mine-add-tokens", "{found}", "{token-type}", "{amount}"),
    TOKENATOR_ADD_TOKENS("tokenator-add-tokens", "{found}", "{token-type}", "{amount}"),
    // admin messages
    BALANCE_UPDATE_GIVER("balance-update-giver", "{player}", "{token-type}", "{amount}"),
    BALANCE_UPDATE_RECEIVER("balance-update-receiver", "{token-type}", "{amount}"),
    BALANCE_QUERY("admin.balance", "{player}", "{token-type}", "{amount}"),
    ADMIN_HELP("admin.help");

    private String path;
    private List<String> placeholders;

    MessageType(String path, String... placeholders) {
        this.path = path;
        this.placeholders = Arrays.asList(placeholders);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void message(Player receiver, String... replacements) {
        List<String> data = Arrays.asList(replacements);
        for (String line : Files.MESSAGES.get().getStringList(this.path)) {
            for (int i = 0; i < this.placeholders.size(); i++) {
                line = line.replace(this.placeholders.get(i), data.get(i));
            }
            receiver.sendMessage(ColorUtil.colorize(line));
        }
    }

    public void message(CommandSender receiver, String... replacements) {
        List<String> data = Arrays.asList(replacements);
        for (String line : Files.MESSAGES.get().getStringList(this.path)) {
            for (int i = 0; i < this.placeholders.size(); i++) {
                line = line.replace(this.placeholders.get(i), data.get(i));
            }
            receiver.sendMessage(ColorUtil.colorize(line));
        }
    }

    public static void doMessage(Player receiver, List<String> lines) {
        for (String line : lines) {
            receiver.sendMessage(ColorUtil.colorize(line));
        }
    }

    public static void doProcMessage(ConfigurationSection section, String entry, Player receiver) {
        if (section.getBoolean(entry + ".message.enabled")) {
            doMessage(receiver, section.getStringList(entry + ".message.text"));
        }
    }

    public static void doAttackedMessage(ConfigurationSection section, String entry, Player receiver) {
        if (section.getBoolean(entry + ".message.enabled")) {
            doMessage(receiver, section.getStringList(entry + ".message.attacked"));
        }
    }

    public static void doAttackerMessage(ConfigurationSection section, String entry, Player receiver) {
        if (section.getBoolean(entry + ".message.enabled")) {
            doMessage(receiver, section.getStringList(entry + ".message.attacker"));
        }
    }
}