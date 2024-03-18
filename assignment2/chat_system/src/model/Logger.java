package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

public class Logger {
    private static Logger instance;
    private Level logLevel = Level.INFO;
    private StringBuilder logContent = new StringBuilder();

    private Logger() {
        // Ensure that instance is only created if it's null
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Instance already exists. Use getInstance() method to access it.");
        }
    }


    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void logInfo(String message) {
        if (Level.INFO.intValue() >= logLevel.intValue()) {
            String logEntry = "[INFO] " + message;
            logContent.append(logEntry).append("\n");
            System.out.println(logEntry); // Console output, replace with your desired output mechanism
        }
    }
    public void logWarning(String message) {
        if (Level.WARNING.intValue() >= logLevel.intValue()) {
            String logEntry = "[WARNING] " + message;
            logContent.append(logEntry).append("\n");
            System.out.println(logEntry); // Console output, replace with your desired output mechanism
        }
    }
    public void logError(String message) {
        if (Level.SEVERE.intValue() >= logLevel.intValue()) {
            String logEntry = "[ERROR] " + message;
            logContent.append(logEntry).append("\n");
            System.err.println(logEntry); // Error console output, replace with your desired output mechanism
        }
    }
    public void setLogLevel(Level level) {
        logLevel = level;
    }

    public void clearLog() {
        logContent.setLength(0);
    }

    public void saveLogToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(logContent.toString());
        } catch (IOException e) {
            logError("Error while saving log to file: " + e.getMessage());
        }
    }

    public String getLogContent() {
        return logContent.toString();
    }
}
