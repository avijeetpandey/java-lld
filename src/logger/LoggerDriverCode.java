package logger;

public class LoggerDriverCode {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.debug("This is a debug log");
        logger.error("This is an error log");
    }
}
