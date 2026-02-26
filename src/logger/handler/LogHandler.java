package logger.handler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import logger.appender.LogAppender;
import logger.enums.LogLevel;
import logger.model.LogMessage;

public abstract class LogHandler {
    protected LogHandler next;
    protected final List<LogAppender> appenders = new CopyOnWriteArrayList<>();

    public void subscribe(LogAppender observer) {
        appenders.add(observer);
    }

    public void notifyObservers(LogMessage message) {
        for(LogAppender appender: appenders) {
            appender.append(message);
        }
    }

    public void handle(LogMessage message) {
        if(canHandle(message.getLevel())) {
            notifyObservers(message);
        } else if (next != null) {
            next.handle(message);
        }
    }

    protected abstract boolean canHandle(LogLevel level);

    public void setNext(LogHandler handler) {
        this.next = handler;
    }
}
