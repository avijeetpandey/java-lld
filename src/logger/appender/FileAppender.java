package logger.appender;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import logger.formatter.LogFormatter;
import logger.model.LogMessage;

public class FileAppender implements LogAppender {
    private final LogFormatter formatter;
    private final BufferedWriter writer;

    public FileAppender(LogFormatter formatter, String filename) {
        this.formatter = formatter;

        try {
            this.writer = new BufferedWriter(new FileWriter(filename, true));
        } catch (IOException e) {
            throw new RuntimeException("Failed to open log file ", e);
        }
    }

    // synchronised makes it thread safe , but order is not maintained
    @Override
    public synchronized void append(LogMessage message) {
        try {
            writer.write(formatter.format(message));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
