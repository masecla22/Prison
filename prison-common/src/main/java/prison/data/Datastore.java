package prison.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A glorified {@link java.util.HashMap} for storing and accessing data,
 * that's friendly with Prison's data system.
 *
 * @since 4.0
 */
public class Datastore extends HashMap<String, Object>  {

    private HashMap<String, Object> data;

    public Datastore() {
        this.data = new HashMap<>();
    }

    public Datastore(HashMap<String, Object> def) {
        this.data = def;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public Object get(Object key) {
        return this.data.get(key);
    }

    @Override
    public boolean containsKey(Object key) {
        return this.data.containsKey(key);
    }

    @Override
    public Object put(String key, Object value) {
        return this.data.put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        this.data.putAll(m);
    }

    @Override
    public Object remove(Object key) {
        return this.data.remove(key);
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public boolean containsValue(Object value) {
        return this.data.containsValue(value);
    }

    @Override
    public Set<String> keySet() {
        return this.data.keySet();
    }

    @Override
    public Collection<Object> values() {
        return this.data.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return this.data.entrySet();
    }

    @Override
    public Object getOrDefault(Object key, Object defaultValue) {
        return this.data.getOrDefault(key, defaultValue);
    }

    @Override
    public Object putIfAbsent(String key, Object value) {
        return this.data.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return this.data.remove(key, value);
    }

    @Override
    public boolean replace(String key, Object oldValue, Object newValue) {
        return this.data.replace(key, oldValue, newValue);
    }

    @Override
    public Object replace(String key, Object value) {
        return this.data.replace(key, value);
    }

    @Override
    public Object computeIfAbsent(String key, Function<? super String, ?> mappingFunction) {
        return this.data.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public Object computeIfPresent(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
        return this.data.computeIfPresent(key, remappingFunction);
    }

    @Override
    public Object compute(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
        return this.data.compute(key, remappingFunction);
    }

    @Override
    public Object merge(String key, Object value, BiFunction<? super Object, ? super Object, ?> remappingFunction) {
        return this.data.merge(key, value, remappingFunction);
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super Object> action) {
        this.data.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super Object, ?> function) {
        this.data.replaceAll(function);
    }

    @Override
    public Object clone() {
        return this.data.clone();
    }
}
