package labs.vex.coffeework.core.service;

import java.util.HashMap;
import java.util.Map;

public class ServicePacket {
    private Map<String, Object> storage = new HashMap<>();

    public ServicePacket(HashMap<String, Object> storage) {
        this.storage = storage;
    }

    public ServicePacket() { }

    public Object get(String key) {
        return this.storage.get(key);
    }

    public void force(String key, Object value) {
        this.storage.put(key, value);
    }

    public boolean install(String key, Object value) {
        if(this.storage.containsKey(key)) {
            return false;
        }

        this.force(key, value);
        return true;
    }
}
