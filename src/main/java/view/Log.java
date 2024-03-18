package view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    private static final Logger logger = LogManager.getLogger();

    public static void info(String className, String message) {
        logger.info(className + ": " + message);
    }

    public static void warn(String className, String message) {
        logger.warn(className + ": " + message);
    }

    public static void error(String className, String message) {
        logger.error(className + ": " + message);
    }
}
