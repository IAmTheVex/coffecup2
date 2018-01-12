package coffecup.concretes.applications;

import labs.vex.coffeework.core.application.App;
import labs.vex.coffeework.core.application.Application;
import labs.vex.coffeework.core.runtime.Step;

@Application
public class TestApp extends App {
    public TestApp() throws NoSuchFieldException, IllegalAccessException {
        super();


    }

    @Step(order = 0, scope = AUTONOMOUS_SCOPE)
    public static void start() {



    }

    @Step(order = 1, scope = AUTONOMOUS_SCOPE)
    public static void next() {

    }
}
