package labs.vex.coffeework.core.debugger;

public class DebugMessage {
    public String time = "--:--:--.--- UTC";
    public int line = -1;
    public String className = "none";
    public Debugger.Type type = Debugger.Type.MESSAGE;
    public String text = "";

    public DebugMessage(String time, String className, int line, Debugger.Type type, String text) {
        this.time = time;
        this.className = className;
        this.line = line;
        this.type = type;
        this.text = text;
    }
}
