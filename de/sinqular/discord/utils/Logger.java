package de.sinqular.discord.utils;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Logger {

    private PrintStream output;
    private boolean time;

    public Logger(PrintStream output, boolean time) {
        this.output = output;
        this.time = time;
    }
    public void log(LogLevel level, String message) {
        if(time) {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", new Locale("de", "DE"));
            output.println("[" + format.format(new Date()) + "][" + level + "]: " + message);
        } else {
            output.println(level + ": " + message);
        }
    }

}
