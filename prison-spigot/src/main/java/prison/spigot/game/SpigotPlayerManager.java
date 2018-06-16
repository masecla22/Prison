package prison.spigot.game;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import prison.game.GamePlayer;
import prison.game.PlayerManager;

import java.util.*;
import java.util.stream.Collectors;

public class SpigotPlayerManager implements PlayerManager {

    private LoadingCache<UUID, GamePlayer> cache;

    public SpigotPlayerManager() {
        this.cache = CacheBuilder.newBuilder().build(new CacheLoader<UUID, GamePlayer>() {
            @Override
            public GamePlayer load(UUID key) {
                return new SpigotPlayer(Bukkit.getPlayer(key));
            }
        });
    }

    @Override
    public Optional<GamePlayer> getPlayer(UUID uid) {
        return Optional.ofNullable(cache.getUnchecked(uid));
    }

    @Override
    public Optional<GamePlayer> getPlayer(String player) {
        // this is less efficient than the UUID approach since it requires two server lookups
        // once in here to get the UUID and then again to get the player object
        // this could be remedied by creating two caches but that would end up using more memory
        // if we don't end up using player names - which by the way are deprecated anyway
        return Optional.ofNullable(cache.getUnchecked(Bukkit.getPlayer(player).getUniqueId()));
    }

    @Override
    public List<GamePlayer> getPlayersInWorld(String worldName) {
        Map<UUID, String> playersWorldMap = Bukkit.getOnlinePlayers().stream().collect(Collectors.toMap(Entity::getUniqueId, o -> o.getWorld().getName()));
        List<GamePlayer> ret = new ArrayList<>();

        for (UUID player : playersWorldMap.keySet()) {
            if (playersWorldMap.get(player).equalsIgnoreCase(worldName)) {
                ret.add(getPlayer(player).orElse(null));
            }
        }

        return ret;
    }

    @Override
    public List<GamePlayer> getPlayers() {
        List<UUID> players = Bukkit.getOnlinePlayers().stream().map(Entity::getUniqueId).collect(Collectors.toList());
        List<GamePlayer> ret = new ArrayList<>();

        for (UUID player : players) {
            getPlayer(player).ifPresent(ret::add);
        }

        return ret;
    }

}
