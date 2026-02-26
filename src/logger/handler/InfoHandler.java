package logger.handler;

import logger.enums.LogLevel;

public class InfoHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.INFO;
    }
}
