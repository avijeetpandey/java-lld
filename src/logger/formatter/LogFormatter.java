package logger.formatter;

import logger.model.LogMessage;

public interface LogFormatter {
    String format(LogMessage message);
}
