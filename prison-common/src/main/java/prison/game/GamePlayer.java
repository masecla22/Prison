package prison.game;

import java.util.UUID;

public interface GamePlayer {

    UUID getUniqueId();

    String getDisplayName();

    void setDisplayName(String displayName);

}
