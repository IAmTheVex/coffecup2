package labs.vex.coffeework.core.resource.engine;

import labs.vex.coffeework.core.configuration.IConfiguration;
import labs.vex.coffeework.core.resource.IResource;

import java.lang.reflect.InvocationTargetException;

public interface IEngine {
    boolean insert(String cell, Class resource, boolean forced);
    IResource resolve(String cell, IConfiguration config) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
