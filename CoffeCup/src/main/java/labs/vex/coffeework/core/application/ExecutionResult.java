package labs.vex.coffeework.core.application;

public class ExecutionResult {
    private Object[] loops;
    private Object[] steps;

    public ExecutionResult(Object[] loops, Object[] steps) {
        this.loops = loops;
        this.steps = steps;
    }

    public Object[] getLoops() {
        return loops;
    }

    public Object[] getSteps() {
        return steps;
    }
}
