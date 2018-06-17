package prison.data;

import java.util.List;
import java.util.Optional;

/**
 * The database provides an interface between the {@link DataManager} and the implementation's
 * storage solution(s). If a database is not provided, the data system will not work!
 *
 * @since 4.0
 */
public interface Database {

    List<String> getCollections();

    default boolean hasCollection(String name) {
        return getCollections().contains(name);
    }

    /**
     * Creates a new collection. If the collection already exists, nothing will happen.
     *
     * @param name the name of the collection.
     * @return true if the collection was created, false otherwise (either already exists or an error occurred).
     */
    boolean newCollection(String name);

    /**
     * Returns all of the {@link Datastore}s in a collection.
     *
     * @param name the name of the collection.
     * @return the list of {@link Datastore}s.
     */
    List<Datastore> getCollection(String name);

    /**
     * Returns a datastore from a specific collection.
     *
     * @param collection the name of the collection this data is stored in.
     * @param id         the id of the data.
     * @return either an optional containing the desired {@link Datastore}, or an empty one if it was not found or
     * if it could not be read.
     */
    Optional<Datastore> getData(String collection, String id);

    /**
     * Stores a datastore to a specific location.
     *
     * @param collection the name of the collection to store this data in.
     * @param id         the id of the data.
     * @param ds         the {@link Datastore} to be saved.
     * @return true if the data was successfully stored, and false otherwise.
     */
    boolean storeData(String collection, String id, Datastore ds);

}
