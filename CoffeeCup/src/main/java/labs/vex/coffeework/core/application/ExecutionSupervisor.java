package labs.vex.coffeework.core.application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecutionSupervisor {
    private static Map<String, ExecutionManager> currentExecutionSet = new HashMap<>();

    public static ExecutionManager scopeManager(String scope) {
        return currentExecutionSet.get(scope);
    }

    public static void scopeManager(String scope, ExecutionManager manager) {
        if(currentExecutionSet.containsKey(scope)) {
            currentExecutionSet.get(scope).merge(manager);
            return;
        } else
        currentExecutionSet.put(scope, manager);
    }

    public static ExecutionResult pulseScope(String scope) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(!currentExecutionSet.containsKey(scope))
            return null;

        Object[] steps = new Object[] {};
        Object[] loops = currentExecutionSet.get(scope).loop();
        return new ExecutionResult(loops, steps);
    }

    public static ExecutionResult executeScope(String scope) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(!currentExecutionSet.containsKey(scope))
            return null;

        Object[] steps = currentExecutionSet.get(scope).step();
        Object[] loops = new Object[] {};
        return new ExecutionResult(loops, steps);
    }

    public static void endScope(String scope) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(!currentExecutionSet.containsKey(scope))
            return;

        currentExecutionSet.get(scope).stop();
    }

    public static void execute() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<ExecutionResult> results = new ArrayList<ExecutionResult>();
        for(Map.Entry<String, ExecutionManager> entry: currentExecutionSet.entrySet()) {
            results.add(executeScope(entry.getKey()));
        }
    }

    public static void pulse() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<ExecutionResult> results = new ArrayList<ExecutionResult>();
        for(Map.Entry<String, ExecutionManager> entry: currentExecutionSet.entrySet()) {
            results.add(pulseScope(entry.getKey()));
        }
    }

    public static void end() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for(Map.Entry<String, ExecutionManager> entry: currentExecutionSet.entrySet()) {
            endScope(entry.getKey());
        }
    }
}
