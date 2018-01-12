package coffecup.concretes.resources;

import labs.vex.coffeework.core.configuration.IConfiguration;
import labs.vex.coffeework.core.debugger.DebugMessage;
import labs.vex.coffeework.core.debugger.Debugger;
import labs.vex.coffeework.core.debugger.GenericDebugStream;
import labs.vex.coffeework.core.resource.IResource;

/**
 * Created by law on 08.01.2018.
 */

public class Debug extends GenericDebugStream implements IResource {
    private static Debug instance = null;

    public static Debug self(IConfiguration configuration){
        if(instance == null)
            instance = new Debug();

        return instance;
    }

    @Override
    public void eject() {
        for(DebugMessage message: this.messages) {
            System.out.println("[" + (message.type == Debugger.Type.MESSAGE ? "MESSAGE" : "ERROR") + "][" + message.time + "][" + message.className + ":" + message.line + "] " + message.text);
        }
        this.messages.clear();
    }
}
