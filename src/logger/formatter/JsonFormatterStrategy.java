package logger.formatter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import logger.model.LogMessage;

public class JsonFormatterStrategy implements LogFormatter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm:ss");

    @Override
    public String format(LogMessage message) {
        String formattedTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(message.getTimestamp()),
                ZoneId.systemDefault()).format(FORMATTER);

        return String.format("{\"timestamp\": %s, \"level\": %s, \"message\": %s}", formattedTime, message.getLevel(),
                message.getMessage());
    }
}
