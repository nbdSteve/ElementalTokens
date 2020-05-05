package gg.steve.elemental.tokens.db;

import gg.steve.elemental.tokens.core.PlayerTokenManager;
import gg.steve.elemental.tokens.core.TokenType;
import gg.steve.elemental.tokens.managers.Files;
import gg.steve.elemental.tokens.utils.LogUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DatabaseUtil {
    public static ConnectionManager connectionManager;

    public static ConnectionManager setupConnection() {
        ConfigurationSection section = Files.CONFIG.get().getConfigurationSection("database");
        connectionManager = new ConnectionManager(section.getString("host"),
                section.getString("database"),
                section.getString("username"),
                section.getString("password"),
                section.getInt("port"));
        connectionManager.connect();
        LogUtil.info("Successfully connected to the SQL database.");
        generateTables();
        loadTokenData();
        return connectionManager;
    }

    public static void generateTables() {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement tokens =
                    connection.prepareStatement("CREATE TABLE IF NOT EXISTS tokens(uuid VARCHAR(255) NOT NULL, balance INT NOT NULL, prestige INT NOT NULL, PRIMARY KEY (uuid))");
            tokens.execute();
            tokens.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadTokenData() {
        PlayerTokenManager.initialise();
        if (Bukkit.getOnlinePlayers().isEmpty()) return;
        Connection connection = connectionManager.getConnection();
//        try {
//            PreparedStatement query = connection.prepareStatement("SELECT * FROM tokens");
//            ResultSet rs = query.executeQuery();
//
//            while (rs.next()) {
//                LogUtil.info(rs.getString("uuid"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            try {
                LogUtil.info(player.getUniqueId().toString());
                PreparedStatement query = connection.prepareStatement("SELECT * FROM tokens WHERE uuid='" + player.getUniqueId() + "'");
                ResultSet rs = query.executeQuery();
                if (rs.next()) {
                    PlayerTokenManager.addTokenPlayer(player.getUniqueId(), rs.getInt("balance"), rs.getInt("prestige"));
                } else {
                    PlayerTokenManager.addTokenPlayer(player.getUniqueId(), 0, 0);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveTokenData() {
        Connection connection = connectionManager.getConnection();
        for (UUID uuid : PlayerTokenManager.getTokenPlayers().keySet()) {
            try {
                PreparedStatement set = connection.prepareStatement("REPLACE INTO tokens(uuid, balance, prestige) VALUES (?, ?, ?);");
                set.setString(1, String.valueOf(uuid));
                set.setInt(2, PlayerTokenManager.getTokens(uuid, TokenType.TOKEN));
                set.setInt(3, PlayerTokenManager.getTokens(uuid, TokenType.PRESTIGE));
                set.executeUpdate();
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadPlayerTokenData(UUID playerId) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM tokens WHERE uuid='" + playerId + "'");
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                PlayerTokenManager.addTokenPlayer(playerId, rs.getInt("balance"), rs.getInt("prestige"));
            } else {
                PlayerTokenManager.addTokenPlayer(playerId, 0, 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void savePlayerTokenData(UUID playerId) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement set = connection.prepareStatement("REPLACE INTO " +
                    "tokens(uuid, balance, prestige) VALUES (?, ?, ?);");
            set.setString(1, String.valueOf(playerId));
            set.setInt(2, PlayerTokenManager.getTokens(playerId, TokenType.TOKEN));
            set.setInt(3, PlayerTokenManager.getTokens(playerId, TokenType.PRESTIGE));
            set.executeUpdate();
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PlayerTokenManager.removeTokenPlayer(playerId);
    }
}