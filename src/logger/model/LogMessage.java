package logger.model;

import java.util.UUID;

import logger.enums.LogLevel;

public class LogMessage {
    private final String id;
    private String message;
    private long timestamp;
    private LogLevel level;

    public LogMessage(String message, long timestamp, LogLevel level) {
        this.id = UUID.randomUUID().toString();
        this.message = message;
        this.timestamp = timestamp;
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getId() {
        return id;
    }

    public LogLevel getLevel() {
        return level;
    }
}
