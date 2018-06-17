package prison.data;

import prison.Prison;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles the loading and saving of {@link Data}.
 *
 * @since 4.0
 */
public class DataManager {

    private Database db;

    public DataManager(Database db) {
        this.db = db;
    }

    /**
     * Reads data from the database and casts it to the specified type.
     *
     * @param id   the id of the data to fetch.
     * @param type the type of this data. Should extend {@link Data}.
     * @return either an optional containing the desired data, or an empty optional if the data couldn't be found or
     * if an error occurred.
     */
    public <T> Optional<T> readData(String id, Class<T> type) {
        String collection = getCollectionNameForType(type);
        Optional<Datastore> dataOptional = db.getData(collection, id);

        // If the datastore is present, we're gonna try constructing our Data type with it.
        if (dataOptional.isPresent()) try {
            T t = type.getConstructor(Datastore.class).newInstance(dataOptional.get());
            return Optional.of(t);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Prison.get().logger().warning("Failed to read data for type " + type.getName() + ". Is the data class valid?");
            e.printStackTrace();
            return Optional.empty();
        }

        // Otherwise, we're just gonna give up. Not much more we can do!
        return Optional.empty();
    }

    /**
     * Reads all the data for a specified type.
     *
     * @param type the type of this data.
     * @return a list containing all of the successfully loaded data. Data that is not successfully loaded is not included,
     * and no exceptions are thrown.
     */
    public <T> List<T> readAll(Class<T> type) {
        String collection = getCollectionNameForType(type);
        List<T> data = new ArrayList<>();

        List<Datastore> colDatastores = db.getCollection(collection);

        for (Datastore ds : colDatastores) {
            try {
                T t = type.getConstructor(Datastore.class).newInstance(ds);
                data.add(t);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Prison.get().logger().warning("Failed to read data for type " + type.getName() + ". Is the data class valid?");
                e.printStackTrace();
            }
        }

        return data;
    }

    /**
     * Writes data to the database.
     *
     * @param id the id of the data.
     * @param data the {@link Data} itself.
     */
    public void writeData(String id, Data data) {
        String collection = getCollectionNameForType(data.getClass());
        Datastore ds = data.getDatastore();

        // try creating a new collection if it doesn't exist
        // if we can't, then I guess we're just not writing this data (return)
        if (!db.hasCollection(collection) && !db.newCollection(collection)) return;

        // store the data and print something if we fail
        if (!db.storeData(collection, id, ds)) {
            Prison.get().logger().warning("Failed to save data @ " + collection + "." + id + "...");
        }
    }

    private String getCollectionNameForType(Class type) {
        return type.getSimpleName().toLowerCase();
    }

}
