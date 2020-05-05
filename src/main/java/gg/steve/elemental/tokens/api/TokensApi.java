package gg.steve.elemental.tokens.api;

import gg.steve.elemental.tokens.ElementalTokens;
import gg.steve.elemental.tokens.core.PlayerTokenManager;
import gg.steve.elemental.tokens.core.TokenPlayer;
import gg.steve.elemental.tokens.core.TokenType;

import java.util.UUID;

public class TokensApi {

    public static ElementalTokens getInstance() {
        return ElementalTokens.get();
    }

    public static int getTokens(UUID playerId, TokenType tokenType) {
        return PlayerTokenManager.getTokens(playerId, tokenType);
    }

    public static TokenPlayer getTokenPlayer(UUID playerId) {
        return PlayerTokenManager.getTokenPlayer(playerId);
    }

    public static void addTokens(TokenType type, UUID playerId, int amount) {
        PlayerTokenManager.addTokens(type, playerId, amount);
    }

    public static void removeTokens(TokenType type, UUID playerId, int amount) {
        PlayerTokenManager.removeTokens(type, playerId, amount);
    }

    public static void setTokens(TokenType type, UUID playerId, int amount) {
        PlayerTokenManager.setTokens(type, playerId, amount);
    }

    public static void resetTokens(TokenType type, UUID playerId) {
        PlayerTokenManager.resetTokens(type, playerId);
    }

    public static void payTokens(TokenType type, UUID from, UUID to, int amount) {
        PlayerTokenManager.pay(type, from, to, amount);
    }
}
