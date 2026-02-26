package logger.appender;

import logger.formatter.LogFormatter;
import logger.model.LogMessage;

public class ConsoleAppender implements LogAppender {
    private final LogFormatter formatter;

    public ConsoleAppender(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(LogMessage message) {
        System.out.println(formatter.format(message));
    }
}
