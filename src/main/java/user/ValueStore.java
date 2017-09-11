package user;

import java.util.HashMap;

/**
 * Created by aleksandrs on 10/09/2017.
 */
public class ValueStore {


    public static HashMap<String, Object> valueStore = new HashMap<>();

    public void reset() {
        valueStore = new HashMap<>();
    }

    public static void store(String key, Object value) {
        valueStore.remove(key);
        valueStore.put(key, value);
    }

    public <T> T get(String key) {
        return (T) valueStore.get(key);
    }
}

