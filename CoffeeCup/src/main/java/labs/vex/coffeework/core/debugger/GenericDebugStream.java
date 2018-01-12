package labs.vex.coffeework.core.debugger;

import java.util.ArrayList;
import java.util.List;

public class GenericDebugStream implements IDebugStream {
    protected List<DebugMessage> messages = new ArrayList<>();

    @Override
    public void addMessage(DebugMessage message) {
        this.messages.add(message);
    }

    @Override
    public void eject() {
        for(DebugMessage message: this.messages) {
            System.out.println("[" + (message.type == Debugger.Type.MESSAGE ? "MESSAGE" : "ERROR") + "][" + message.time + "][" + message.className + ":" + message.line + "] " + message.text);
        }
        this.messages.clear();
    }
}
