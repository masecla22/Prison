package prison.spigot.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import prison.Prison;
import prison.data.Database;
import prison.data.Datastore;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A file-based database implementation.
 *
 * @since 4.0
 */
public class FileDatabase implements Database {

    private File root;
    private List<String> collections;
    private Gson gson;

    public FileDatabase(File root) {
        this.root = root;
        this.collections = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

        this.initCollections();
    }

    private void initCollections() {
        File[] files = root.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) collections.add(file.getName().toLowerCase());
        }
    }

    /**
     * Reads a {@link Datastore} from a JSON file.
     *
     * @param jsonFile the file to read.
     * @return the {@link Datastore} or null if the read failed.
     */
    private Datastore readDatastore(File jsonFile) {
        try {
            return gson.fromJson(new FileReader(jsonFile), Datastore.class);
        } catch (IOException e) {
            Prison.get().logger().warning("Failed to read " + jsonFile.getName() + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Writes a {@link Datastore} to a JSON file.
     *
     * @param ds the {@link Datastore}.
     * @param to the file to write out to.
     * @return true if the write succeeds, false if not (a message will print to the console).
     */
    private boolean writeDatastore(Datastore ds, File to) {
        try {
            if (!to.exists()) to.createNewFile();

            FileWriter fw = new FileWriter(to);
            fw.write(gson.toJson(ds));
            fw.close();
            return true;
        } catch (IOException e) {
            Prison.get().logger().warning("Failed to write to " + to.getName() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getCollections() {
        return collections;
    }

    @Override
    public boolean newCollection(String name) {
        File dir = new File(root, name.toLowerCase());
        if (dir.mkdir()) {
            collections.add(name);
            return true;
        }
        return false;
    }

    @Override
    public List<Datastore> getCollection(String name) {
        // just get all JSON Files from the collection's folder
        File[] dsFiles = new File(root, name.toLowerCase()).listFiles((dir, fname) -> fname.endsWith(".json"));
        List<Datastore> ret = new ArrayList<>();

        for (File dsFile : Objects.requireNonNull(dsFiles)) {
            Datastore datastore = readDatastore(dsFile);
            ret.add(datastore);
        }

        return ret;
    }

    @Override
    public Optional<Datastore> getData(String collection, String id) {
        File dsFile = new File(new File(root, collection), id.toLowerCase() + ".json");
        return Optional.ofNullable(readDatastore(dsFile));
    }

    @Override
    public boolean storeData(String collection, String id, Datastore ds) {
        File dsFile = new File(new File(root, collection), id.toLowerCase() + ".json");
        return writeDatastore(ds, dsFile);
    }

}
