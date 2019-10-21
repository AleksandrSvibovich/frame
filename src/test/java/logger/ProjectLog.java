package logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProjectLog {
    private static final Logger logger = LogManager.getLogger();

    public void fatal(String text) {
        logger.fatal(text);
    }

    public void error(String text) {
        logger.error(text);
    }

    public void warn(String text) {
        logger.warn(text);
    }

    public void info(String text) {
        logger.info(text);
    }

    public void debug(String text) {
        logger.debug(text);
    }

    public void trace(String text) {
        logger.trace(text);
    }

}
