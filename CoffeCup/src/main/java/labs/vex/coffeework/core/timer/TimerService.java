package labs.vex.coffeework.core.timer;

import labs.vex.coffeework.core.service.AbstractService;
import labs.vex.coffeework.core.service.ServicePacket;

public class TimerService extends AbstractService {
    private long start;
    public long duration = 0;
    public long elapsed = 0;
    public boolean expired = false;

    public TimerService(long duration) {
        super("TimerTask");
        this.duration = duration;
    }

    @Override
    public ServicePacket init() {
        this.start = System.currentTimeMillis();
        return null;
    }

    @Override
    public ServicePacket killed() {
        this.expired = true;
        return null;
    }

    @Override
    public boolean tick() {
        this.elapsed = System.currentTimeMillis() - this.start;
        if(this.elapsed >= duration)
            return false;

        return true;
    }
}
