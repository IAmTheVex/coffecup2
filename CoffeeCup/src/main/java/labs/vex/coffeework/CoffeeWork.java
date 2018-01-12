package labs.vex.coffeework;

import labs.vex.coffeework.core.Core;

/**
 * CoffeeWork Core handler
 * (MAin entry point)
 *
 * @author: Laurentiu Ciobanu | vex
 */
public class CoffeeWork {
    private Core core;

    public CoffeeWork() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        this.core = new Core();
    }

    public Core getCore() {
        return this.core;
    }
}
