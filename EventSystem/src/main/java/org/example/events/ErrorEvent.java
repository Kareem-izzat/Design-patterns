package org.example.events;

import java.time.LocalDateTime;

public class ErrorEvent implements Event{
    private final int id;
    private final LocalDateTime timestamp;
    private final int errorCode;
    private final String errorMessage;

    public ErrorEvent(int id, int errorCode, String errorMessage) {
        this.id = id;
        this.timestamp = LocalDateTime.now();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getId() {
        return id;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String getType() {
        return "ErrorEvent";
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "SystemErrorEvent{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
