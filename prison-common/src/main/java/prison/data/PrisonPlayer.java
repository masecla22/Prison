package prison.data;

import prison.io.Datastore;

import java.util.UUID;

public class PrisonPlayer {

    private UUID uid;
    private String name;

    private Datastore data;

    public PrisonPlayer(UUID uid) {
        this.uid = uid;
    }



    public UUID getUniqueId() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
