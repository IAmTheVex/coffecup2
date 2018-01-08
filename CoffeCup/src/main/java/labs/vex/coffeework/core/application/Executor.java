package labs.vex.coffeework.core.application;

import labs.vex.coffeework.core.configuration.IConfiguration;

import java.lang.reflect.Method;

public class Executor {
    private Method execution = null;
    private Class[] types = null;
    private IConfiguration configuration = null;

    public Executor(Method execution, IConfiguration configuration) {
        this.types = execution.getParameterTypes();
        this.execution = execution;
        this.configuration = configuration;
    }

    public Method getExecution() {
        return execution;
    }

    public Class[] getTypes() {
        return types;
    }

    public IConfiguration getConfiguration() {
        return this.configuration;
    }

    public String[] getTypesAsLiteral() {
        String[] list = new String[this.types.length];
        int index = 0;
        for(Class c: this.types) {
            list[index] = c.getName();
            index++;
        }

        return list;
    }
}
