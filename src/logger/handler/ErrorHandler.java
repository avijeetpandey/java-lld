package logger.handler;

import logger.enums.LogLevel;

public class ErrorHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.ERROR;
    }
}
