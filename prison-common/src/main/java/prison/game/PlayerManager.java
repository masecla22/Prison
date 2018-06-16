package prison.game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerManager {

    Optional<GamePlayer> getPlayer(UUID uid);

    Optional<GamePlayer> getPlayer(String player);

    List<GamePlayer> getPlayersInWorld(String worldName);

    List<GamePlayer> getPlayers();

}
