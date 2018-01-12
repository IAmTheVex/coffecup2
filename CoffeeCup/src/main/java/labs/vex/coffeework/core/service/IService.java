package labs.vex.coffeework.core.service;

public interface IService {
    void setup(ServiceHandler handler);
    void kill();
    void inject(ServicePacket packet);
}
