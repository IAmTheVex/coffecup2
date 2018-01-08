package labs.vex.coffeework.core.service;

public abstract class AbstractService extends Thread implements IService {
    private ServiceHandler handler = null;
    private ServicePacket currentPacket = null;

    public abstract ServicePacket init();
    public abstract ServicePacket killed();
    public abstract boolean tick();

    public AbstractService(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void setup(ServiceHandler handler) {
        this.handler = handler;

        if(this.handler == null)
            this.handler = new ServiceHandler() {};

        this.handler.onMount(this.init());
        this.start();
    }

    @Override
    public void kill() {
        this.handler.onDie(this.killed());
        this.stop();
    }

    @Override
    public void inject(ServicePacket packet) {
        this.currentPacket = packet;
    }

    protected void eject(ServicePacket packet) {
        this.handler.onData(packet);
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " abc");
        while(this.tick());
        this.killed();
    }
}
