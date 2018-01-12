package coffecup.concretes.configurations;

import labs.vex.coffeework.core.configuration.AbstractConfiguration;

/**
 * Created by law on 08.01.2018.
 */

public class DefaultConfiguration extends AbstractConfiguration {
    @Override
    protected void inflate() {
        store.put("test.a", 6);
        store.put("test.a.b", "c");
    }
}
