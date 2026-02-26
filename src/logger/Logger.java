package logger;

import logger.enums.LogLevel;
import logger.handler.LogHandler;
import logger.model.LogMessage;

class Logger {
    private static final Logger INSTANCE = new Logger();
    private final LogHandler handler;

    private Logger() {
        handler = LogHandleConfiguration.build();
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    private void log(LogLevel level, String message) {
        LogMessage msg = new LogMessage(message, System.currentTimeMillis(), level);
        handler.handle(msg);
    }

    public void debug(String msg) {
        log(LogLevel.DEBUG, msg);
    }

    public void info(String msg) {
        log(LogLevel.INFO, msg);
    }

    public void warn(String msg) {
        log(LogLevel.WARN, msg);
    }

    public void error(String msg) {
        log(LogLevel.ERROR, msg);
    }
}
