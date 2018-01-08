package labs.vex.coffeework.core.resource;

import labs.vex.coffeework.core.configuration.IConfiguration;
import labs.vex.coffeework.core.resource.engine.DefaultEngine;
import labs.vex.coffeework.core.resource.engine.IEngine;

import java.lang.reflect.InvocationTargetException;

public class ResourceManager {
    private static IEngine engine = new DefaultEngine();

    public static void installEngine(IEngine engine) {
        ResourceManager.engine = engine;
    }

    public static boolean storeResource(String cell, Class resource) {
        return ResourceManager.engine.insert(cell, resource, false);
    }

    public static void forceResource(String cell, Class resource) {
        ResourceManager.engine.insert(cell, resource, true);
    }

    public static IResource resolveResource(String cell, IConfiguration config) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return ResourceManager.engine.resolve(cell, config);
    }
}
