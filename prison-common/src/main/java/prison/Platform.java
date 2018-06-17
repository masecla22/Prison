package prison;

import prison.data.Database;
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
    private Supplier<Database> databaseSupplier;

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

    public Supplier<Logger> logger() {
        return loggerSupplier;
    }

    public void supplyLogger(Supplier<Logger> loggerSupplier) {
        this.loggerSupplier = loggerSupplier;
    }

    public Supplier<GameServer> server() {
        return serverSupplier;
    }

    public void supplyServer(Supplier<GameServer> serverSupplier) {
        this.serverSupplier = serverSupplier;
    }

    public Supplier<PlayerManager> playerManager() {
        return playerSupplier;
    }

    public void supplyPlayerManager(Supplier<PlayerManager> playerSupplier) {
        this.playerSupplier = playerSupplier;
    }

    public Supplier<Database> database() {
        return databaseSupplier;
    }

    public void supplyDatabase(Supplier<Database> databaseSupplier) {
        this.databaseSupplier = databaseSupplier;
    }
}
