package labs.vex.coffeework.core.service;

public class Job {
    private IService service = null;
    private ServiceHandler handler = null;

    public Job(IService service, ServiceHandler handler) {
        this.service = service;
        this.handler = handler;
    }

    public IService getService() {
        return service;
    }

    public ServiceHandler getHandler() {
        return handler;
    }
}
