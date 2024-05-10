package cs.vsu.event_ease.backend.utils;

import ch.qos.logback.core.pattern.color.ANSIConstants;
import org.springframework.boot.ansi.AnsiColor;

public class ColorPrint {
    private ColorPrint() {
    }

    public static void println(String string, AnsiColor color) {
        System.out.println("\u001B[" + color.toString() + "m" + string + "\u001B[0m");
    }
}
