package logger;

import logger.appender.ConsoleAppender;
import logger.appender.LogAppender;
import logger.enums.LogLevel;
import logger.formatter.PlainTextFormatter;
import logger.handler.LogHandler;
import logger.model.LogMessage;

class Logger {
    private static final Logger INSTANCE = new Logger();
    private final LogHandler handler;
    private final LogAppender logAppender;

    private Logger() {
        handler = LogHandleConfiguration.build();
        logAppender = new ConsoleAppender(new PlainTextFormatter());
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    private void log(LogLevel level, String message) {
        LogMessage msg = new LogMessage(message, System.currentTimeMillis(), level);
        LogHandleConfiguration.addAppenderForLevel(level, logAppender);
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
