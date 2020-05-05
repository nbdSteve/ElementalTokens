package gg.steve.elemental.tokens.utils;

import gg.steve.elemental.tokens.ElementalTokens;

public class LogUtil {

    public static void info(String message) {
        ElementalTokens.get().getLogger().info(message);
    }

    public static void warning(String message) {
        ElementalTokens.get().getLogger().warning(message);
    }
}
