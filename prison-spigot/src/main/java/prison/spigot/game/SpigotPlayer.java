package prison.spigot.game;

import org.bukkit.entity.Player;
import prison.game.GamePlayer;
import prison.util.Text;

import java.util.UUID;

public class SpigotPlayer implements GamePlayer {

    private Player player; // bukkit instance

    public SpigotPlayer(Player from) {
        this.player = from;
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public String getDisplayName() {
        return player.getDisplayName();
    }

    @Override
    public void setDisplayName(String displayName) {
        player.setDisplayName(Text.color(displayName));
    }

    public Player getHandle() {
        return player;
    }

}
