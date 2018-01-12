package labs.vex.coffeework.core.configuration;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConfiguration implements IConfiguration {
    protected Map<String, Object> store = new HashMap<>();
    private boolean inflated = false;

    public AbstractConfiguration() { }

    protected abstract void inflate();

    public Map<String, Object> access() {
        if(!this.inflated)
            this.inflate();

        return this.store;
    }

    @Override
    public Object get(String name) {
        return this.access().get(name);
    }

    @Override
    public void put(String name, Object object) {
        this.access().put(name, object);
    }

    public void include(IConfiguration configuration) {
        this.store.putAll(configuration.access());
    }
}
