package labs.vex.coffeework.core.configuration;

import java.util.Map;

public interface IConfiguration {
    Object get(String name);
    void put(String name, Object object);
    Map<String, Object> access();
}
