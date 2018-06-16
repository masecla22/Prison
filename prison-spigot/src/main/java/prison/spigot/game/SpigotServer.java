package prison.spigot.game;

import org.bukkit.Bukkit;
import org.bukkit.World;
import prison.game.GameServer;

import java.util.List;
import java.util.stream.Collectors;

public class SpigotServer implements GameServer {

    @Override
    public String getVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public List<String> getWorldList() {
        return Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.toList());
    }

}
