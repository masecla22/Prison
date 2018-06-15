package prison.game;

import prison.data.PrisonPlayer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerProvider {

    Optional<PrisonPlayer> getPlayer(UUID uid);

    Optional<PrisonPlayer> getPlayerByName(String name);

    List<PrisonPlayer> getPlayers(String world);

    List<PrisonPlayer> getPlayers();

}
