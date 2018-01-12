package labs.vex.coffeework.core.runtime;

import labs.vex.coffeework.core.application.App;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface End {
    String scope() default App.DEFAULT_SCOPE;
    int order() default 0;
}
