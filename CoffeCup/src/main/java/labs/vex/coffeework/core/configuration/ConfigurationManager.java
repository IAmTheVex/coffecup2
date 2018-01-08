package labs.vex.coffeework.core.configuration;

import labs.vex.coffeework.core.runtime.Configuraion;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager {
    private static Map<String, Class> configs = new HashMap<>();

    public static void install(Class clazz) {
        configs.put(clazz.getName(), clazz);
    }

    public static void resolve(Class clazz) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        for (Field field : clazz.getDeclaredFields()) {
            if (!IConfiguration.class.isAssignableFrom(field.getType()))
                continue;

            if (!Modifier.isStatic(field.getModifiers()) || !Modifier.isPublic(field.getModifiers()) || !Modifier.isFinal(field.getModifiers()))
                continue;

            if (!field.isAnnotationPresent(Configuraion.class))
                continue;

            IConfiguration configuration = IConfiguration.class.cast(configs.get(field.getType().getName()).newInstance());
            if (configuration == null)
                continue;

            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, configuration);
            field.setAccessible(accessible);
        }
    }
}
