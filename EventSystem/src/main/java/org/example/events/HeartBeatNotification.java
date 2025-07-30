package org.example.events;

import java.time.LocalDateTime;

public class HeartBeatNotification implements Event {

    private final LocalDateTime timestamp;
    private final int sequenceNumber;
    private final String componentName;
    private final Priority priority;
    private final Long period;

    public HeartBeatNotification(int sequenceNumber, String componentName,Priority priority,Long period) {
        this.timestamp = LocalDateTime.now();
        this.sequenceNumber = sequenceNumber;
        this.componentName = componentName;
        this.period = period;
        if (priority == null) {
            this.priority = Priority.LOW;
        }
        else {
            this.priority = priority;
        }
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
        return priority;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }
    public String getComponentName() {
        return componentName;
    }

    public Long getPeriod() {
        return period;
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
