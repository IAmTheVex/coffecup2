package labs.vex.coffeework.core.resource.engine;

import labs.vex.coffeework.core.configuration.IConfiguration;
import labs.vex.coffeework.core.resource.IResource;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractEngine implements IEngine {
    public abstract boolean put(String cell, Class resource);
    public abstract void force(String cell, Class resource);
    public abstract Class solve(String cell);

    @Override
    public boolean insert(String cell, Class resource, boolean forced) {
        if(forced) {
            this.force(cell, resource);
            return true;
        }

        return this.put(cell, resource);
    }

    @Override
    public IResource resolve(String cell, IConfiguration config) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (IResource) this.solve(cell).getMethod("self", IConfiguration.class).invoke(null, new Object[] { null });
    }
}
