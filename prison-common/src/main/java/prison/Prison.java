package prison;

import prison.game.GameServer;
import prison.game.PlayerManager;

import java.util.logging.Logger;

/**
 * Bootstraps Prison's common functionality.
 *
 * @since 4.0
 */
public class Prison {

    public static final String VERSION = "4.0.0-SNAPSHOT";

    private static Prison instance;

    private Platform platform;

    public void init(Platform platform) {
        instance = this;
        this.platform = platform;

        logger().info("Enabled Prison v" + VERSION + ", running on " + getServer().getVersion() + " (" + getServer().getWorldList().size() + " worlds).");
    }

    public static Prison get() {
        return instance;
    }

    public Platform getPlatform() {
        return platform;
    }

    public Logger logger() {
        return platform.getLoggerSupplier().get();
    }

    public GameServer getServer() {
        return platform.getServerSupplier().get();
    }

    public PlayerManager getPlayerManager() {
        return platform.getPlayerManagerSupplier().get();
    }

}
