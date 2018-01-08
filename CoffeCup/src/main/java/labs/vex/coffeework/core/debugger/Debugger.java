package labs.vex.coffeework.core.debugger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Debugger {
    private static IDebugStream stream = new GenericDebugStream();

    public static enum Type {
      ERROR,
      MESSAGE
    }

    public static void installStream(IDebugStream s) {
        stream = s;
    }

    public static void append(Type type, String message) {
        stream.addMessage(new DebugMessage(getTime(), getCallerClassName(), getCallerLineNumber(), type, message));
    }

    public static void eject(Type type, String message) {
        append(type, message);
        eject();
    }

    public static void eject() {
        stream.eject();
    }

    private static String getFormattedTime(String pattern, Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        return sdf.format(time) + " UTC";
    }

    private static String getTime() {
        return getTime(new Date());
    }

    private static String getTime(Date time) {
        return getFormattedTime("HH:mm:ss.SSS", time);
    }

    private static int getCallerLineNumber() {
        StackTraceElement[] trace = new Exception().getStackTrace();
        if(trace != null) {
            if(trace.length >= 3){
                return trace[2].getLineNumber();
            }
        }
        return -1;
    }

    private static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(Debugger.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
                return ste.getClassName();
            }
        }
        return null;
    }
}
