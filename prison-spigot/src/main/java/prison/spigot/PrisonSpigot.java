package prison.spigot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import prison.Platform;
import prison.Prison;
import prison.spigot.game.SpigotPlayerManager;
import prison.spigot.game.SpigotServer;

/**
 * Implementation of Prison on the Spigot platform.
 */
public class PrisonSpigot extends JavaPlugin {

    private Platform platform;

    @Override
    public void onEnable() {
        Prison prison = new Prison(); // we keep instance here since Prison.get doesn't return anything until after init()

        initPlatform();
        prison.init(platform);
    }

    private void initPlatform() {
        this.platform = new Platform("Spigot", Bukkit.getVersion());
        this.platform.supplyLogger(Bukkit::getLogger);
        this.platform.supplyServer(SpigotServer::new);
        this.platform.supplyPlayerManager(SpigotPlayerManager::new);
    }

}
