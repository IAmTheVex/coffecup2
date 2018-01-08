package labs.vex.coffeework.core.application;

public class App {
    public static final String DEFAULT_SCOPE = "DEFAULT";
    public static final String AUTONOMOUS_SCOPE = "AUTO";
    public static final String MANUAL_SCOPE = "MANUAL";

    private ExecutionManager defaultManager = new ExecutionManager("DEFAULT");
    private ExecutionManager autoManager = new ExecutionManager("AUTO");
    private ExecutionManager manualManager = new ExecutionManager("MANUAL");

    public App() throws NoSuchFieldException, IllegalAccessException {
        defaultManager.load(this.getClass());
        autoManager.load(this.getClass());
        manualManager.load(this.getClass());

        ExecutionSupervisor.scopeManager(defaultManager.getScope(), defaultManager);
        ExecutionSupervisor.scopeManager(autoManager.getScope(), autoManager);
        ExecutionSupervisor.scopeManager(manualManager.getScope(), manualManager);
    }
}
