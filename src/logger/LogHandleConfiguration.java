package logger;

import logger.appender.LogAppender;
import logger.enums.LogLevel;
import logger.handler.DebugHandler;
import logger.handler.ErrorHandler;
import logger.handler.InfoHandler;
import logger.handler.LogHandler;
import logger.handler.WarnHandler;

public class LogHandleConfiguration {
    private static final LogHandler debug = new DebugHandler();
    private static final LogHandler info = new InfoHandler();
    private static final LogHandler warn = new WarnHandler();
    private static final LogHandler error = new ErrorHandler();


    public static LogHandler build() {
        debug.setNext(info);
        info.setNext(warn);
        warn.setNext(error);
        error.setNext(debug);

        return debug;
    }

    public static void addAppenderForLevel(LogLevel level, LogAppender appender) {
        switch (level) {
            case DEBUG -> debug.subscribe(appender);
            case INFO -> info.subscribe(appender);
            case WARN -> warn.subscribe(appender);
            case ERROR -> error.subscribe(appender);
        }
    }
}
