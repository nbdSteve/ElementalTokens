package gg.steve.elemental.tokens.permission;

import gg.steve.elemental.tokens.managers.Files;
import org.bukkit.command.CommandSender;

public enum PermissionNode {
    // admin perms
    RELOAD("admin.reload"),
    ADD("admin.add"),
    SET("admin.set"),
    RESET("admin.reset"),
    REMOVE("admin.remove"),
    ADMIN_HELP("admin.help"),
    // regular perms
    HELP("command.help"),
    PAY_TOKEN("command.pay.token"),
    PAY_PRESTIGE("command.pay.prestige"),
    SHOP_GUI("command.shop-gui"),
    BALANCE_TOKEN("command.balance.token"),
    BALANCE_PRESTIGE("command.balance.prestige");

    private String path;

    PermissionNode(String path) {
        this.path = path;
    }

    public String get() {
        return Files.PERMISSIONS.get().getString(this.path);
    }

    public boolean hasPermission(CommandSender sender) {
        return sender.hasPermission(get());
    }
}
