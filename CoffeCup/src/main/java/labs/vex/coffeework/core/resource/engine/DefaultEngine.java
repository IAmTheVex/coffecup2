package labs.vex.coffeework.core.resource.engine;

import labs.vex.coffeework.core.resource.IResource;

import java.util.HashMap;
import java.util.Map;

public class DefaultEngine extends AbstractEngine {
    private Map<String, Class> storage = new HashMap<>();

    @Override
    public boolean put(String cell, Class resource) {
        if(!IResource.class.isAssignableFrom(resource))
            return false;

        if(this.storage.containsKey(cell))
            return false;

        this.storage.put(cell, resource);
        return true;
    }

    @Override
    public void force(String cell, Class resource) {
        this.storage.put(cell, resource);
    }

    @Override
    public Class solve(String cell) {
        return this.storage.get(cell);
    }
}
