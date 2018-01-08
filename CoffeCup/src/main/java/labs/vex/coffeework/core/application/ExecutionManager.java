package labs.vex.coffeework.core.application;

import labs.vex.coffeework.core.configuration.IConfiguration;
import labs.vex.coffeework.core.resource.ResourceManager;
import labs.vex.coffeework.core.runtime.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExecutionManager {
    private Map<Integer, Executor> steps = new TreeMap<>();
    private Map<Integer, Executor> loops = new TreeMap<>();
    private Map<Integer, Executor> stops = new TreeMap<>();

    private String scope = "";

    public ExecutionManager(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

    public Map<Integer, Executor> getLoops() {
        return loops;
    }

    public Map<Integer, Executor> getSteps() {
        return steps;
    }

    public Map<Integer, Executor> getStops() {
        return stops;
    }

    public void forceScope(String scope) {
        this.scope = scope;
    }

    public void load(Class clazz) throws NoSuchFieldException, IllegalAccessException {
        if(!clazz.isAnnotationPresent(Application.class))
            return;

        if(clazz.isAnnotationPresent(Halt.class))
            return;

        this.loops.clear();
        this.steps.clear();

        Field config = null;
        for(Field field: clazz.getFields()) {
            if(field.getName().equals("config")){
                if(IConfiguration.class.isAssignableFrom(field.getType()))
                    config = field;
            }
        }

        IConfiguration configuration;
        if(config == null)
            configuration = null;
        else
            configuration = (IConfiguration) config.get(null);

        for(Method m : clazz.getDeclaredMethods()) {
            if(!Modifier.isPublic(m.getModifiers()) || !Modifier.isStatic(m.getModifiers()))
                continue;

            if(m.isAnnotationPresent(Disabled.class))
                continue;

            if(m.isAnnotationPresent(Loop.class)) {
                Loop loop = (Loop) m.getAnnotation(Loop.class);
                if(!loop.scope().equals(this.scope))
                    continue;

                this.loops.put(loop.order(), new Executor(m, configuration));
            }

            if(m.isAnnotationPresent(Step.class)) {
                Step step = (Step) m.getAnnotation(Step.class);
                if(!step.scope().equals(this.scope))
                    continue;

                this.steps.put(step.order(), new Executor(m, configuration));
            }

            if(m.isAnnotationPresent(End.class)) {
                End step = (End) m.getAnnotation(End.class);
                if(!step.scope().equals(this.scope))
                    continue;

                this.stops.put(step.order(), new Executor(m, configuration));
            }
        }
    }

    public void merge(ExecutionManager manager) {
        if(!manager.getScope().equals(this.scope))
            return;

        this.steps.putAll(manager.getSteps());
        this.loops.putAll(manager.getLoops());
        this.stops.putAll(manager.getStops());
    }

    public Object execute(Executor executor) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String[] tyeps = executor.getTypesAsLiteral();
        Method method = executor.getExecution();
        IConfiguration config = executor.getConfiguration();

        List<Object> arguments = new ArrayList<>();
        for (String type: tyeps) {
            arguments.add(ResourceManager.resolveResource(type, config));
        }
        return method.invoke(null, arguments.toArray());
    }

    public Object[] loop() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Object> results = new ArrayList<>();
        for(Map.Entry<Integer, Executor> entry: this.loops.entrySet()) {
            Executor executor = entry.getValue();
            results.add(this.execute(executor));
        }
        return results.toArray();
    }

    public Object[] step() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Object> results = new ArrayList<>();
        for(Map.Entry<Integer, Executor> entry: this.steps.entrySet()) {
            Executor executor = entry.getValue();
            results.add(this.execute(executor));
        }
        return results.toArray();
    }

    public Object[] stop() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Object> results = new ArrayList<>();
        for(Map.Entry<Integer, Executor> entry: this.stops.entrySet()) {
            Executor executor = entry.getValue();
            results.add(this.execute(executor));
        }
        return results.toArray();
    }
}
