package prison;

import prison.game.GameServer;
import prison.game.PlayerManager;

import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Implementations should provide as many suppliers as possible in here.
 *
 * @since 4.0
 */
public class Platform {

    private String platformName, platformVersion;

    private Supplier<Logger> loggerSupplier;
    private Supplier<GameServer> serverSupplier;
    private Supplier<PlayerManager> playerSupplier;

    public Platform(String platformName, String platformVersion) {
        this.platformName = platformName;
        this.platformVersion = platformVersion;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public Supplier<Logger> getLoggerSupplier() {
        return loggerSupplier;
    }

    public void setLoggerSupplier(Supplier<Logger> loggerSupplier) {
        this.loggerSupplier = loggerSupplier;
    }

    public Supplier<GameServer> getServerSupplier() {
        return serverSupplier;
    }

    public void setServerSupplier(Supplier<GameServer> serverSupplier) {
        this.serverSupplier = serverSupplier;
    }

    public Supplier<PlayerManager> getPlayerManagerSupplier() {
        return playerSupplier;
    }

    public void setPlayerManagerSupplier(Supplier<PlayerManager> playerSupplier) {
        this.playerSupplier = playerSupplier;
    }

}
