package model;

import model.Logger;

public class Test_Logger {
    public static void main(String[] args) {
        // Get the instance of the Logger
        Logger logger = Logger.getInstance();

        // Set log level (optional)
        // logger.setLogLevel(Level.WARNING);

        // Log some messages
        logger.logInfo("This is an informational message.");
        logger.logWarning("This is a warning message.");
        logger.logError("This is an error message.");

        // Save log to file
        logger.saveLogToFile("log.txt");

        // Get log content
        String logContent = logger.getLogContent();
        System.out.println("Log Content:");
        System.out.println(logContent);
    }
}

