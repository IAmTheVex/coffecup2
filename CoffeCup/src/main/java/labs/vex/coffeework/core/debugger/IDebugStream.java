package labs.vex.coffeework.core.debugger;

public interface IDebugStream {
    void addMessage(DebugMessage message);
    void eject();
}
