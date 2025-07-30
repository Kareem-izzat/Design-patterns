package org.example.events;

import java.time.LocalDateTime;

public class HeartBeatNotification implements Event {

    private final LocalDateTime timestamp;
    private final int sequenceNumber;
    private final String componentName;

    public HeartBeatNotification(int sequenceNumber, String componentName) {
        this.timestamp = LocalDateTime.now();
        this.sequenceNumber = sequenceNumber;
        this.componentName = componentName;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public EventType getType() {
        return EventType.HEARTBEAT;
    }

    @Override
    public Priority getPriority() {
        return Priority.LOW;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }
    public String getComponentName() {
        return componentName;
    }

    @Override
    public String toString() {
        return "HeartBeatNotification{" +
                "timestamp=" + timestamp +
                ", sequenceNumber=" + sequenceNumber +
                ", priority=" + getPriority() +
                ", type=" + getType() +
                '}';
    }
}
