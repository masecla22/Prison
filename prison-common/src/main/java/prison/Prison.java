package prison;

import prison.data.DataManager;
import prison.data.Database;
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
    private DataManager dataManager;

    public void init(Platform platform) {
        instance = this;
        this.platform = platform;

        initData();

        logger().info("Enabled Prison v" + VERSION + ", running on " + getServer().getVersion() + " (" + getServer().getWorldList().size() + " worlds).");
    }

    private void initData() {
        Database db = getPlatform().database().get();
        this.dataManager = new DataManager(db);
    }

    public static Prison get() {
        return instance;
    }

    public Platform getPlatform() {
        return platform;
    }

    public Logger logger() {
        return platform.logger().get();
    }

    public GameServer getServer() {
        return platform.server().get();
    }

    public PlayerManager getPlayerManager() {
        return platform.playerManager().get();
    }

}
