package com.teamapp.util;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {
    public static void setup() throws IOException {
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger("");
        FileHandler fh = new FileHandler("app.log", true);
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        logger.setLevel(Level.INFO);
    }
}
