package labs.vex.coffeework.core;

import labs.vex.coffeework.core.application.ExecutionSupervisor;
import labs.vex.coffeework.core.configuration.ConfigurationManager;
import labs.vex.coffeework.core.discovery.Curiosity;
import labs.vex.coffeework.core.resource.ResourceManager;
import labs.vex.coffeework.core.service.ServiceManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Core {
    private Class[] apps = null;
    private Class[] resources = null;
    private Class[] configs = null;

    public void register(String configurations, String resources, String applications) throws IOException, ClassNotFoundException {
        this.configs = Curiosity.explore(configurations);
        this.resources = Curiosity.explore(resources);
        this.apps = Curiosity.explore(applications);
    }

    public void init() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        for(Class config: this.configs) {
            ConfigurationManager.install(config);
        }

        for(Class resource: this.resources) {
            ResourceManager.forceResource(resource.getName(), resource);
        }

        for(Class app: this.apps) {
            ConfigurationManager.resolve(app);
            app.newInstance();
        }
    }

    public void execute(String scope) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ExecutionSupervisor.executeScope(scope);
    }

    public void execute() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ExecutionSupervisor.execute();
    }

    public void pulse(String scope) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ExecutionSupervisor.pulseScope(scope);
    }

    public void pulse() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ExecutionSupervisor.pulse();
    }

    public void end(String scope) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ExecutionSupervisor.endScope(scope);
    }

    public void end() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ExecutionSupervisor.end();
    }

    public void stop(){
        ServiceManager.killAll();
    }
}
