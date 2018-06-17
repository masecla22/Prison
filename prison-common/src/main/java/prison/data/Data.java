package prison.data;

/**
 * Designates a data class.
 *
 * @since 4.0
 */
public abstract class Data {

    private Datastore datastore;

    public Data(Datastore datastore) {
        this.datastore = datastore;
        load();
    }

    public Data() {
    }

    /**
     * Loads up the object from the data store.
     */
    abstract void load();

    /**
     * Sets data store keys to their proper values.
     */
    abstract void save();

    public Datastore getDatastore() {
        return datastore;
    }

}
