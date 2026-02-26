package logger.appender;

import logger.model.LogMessage;

public interface LogAppender {
    public void append(LogMessage message);
}
