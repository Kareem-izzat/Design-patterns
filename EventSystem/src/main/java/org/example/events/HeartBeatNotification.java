package org.example.events;

import java.time.LocalDateTime;

public class HeartBeatNotification implements Event {
    private final LocalDateTime timestamp;
    private final int sequenceNumber;
    public HeartBeatNotification(int sequenceNumber) {
        this.timestamp = LocalDateTime.now();
        this.sequenceNumber = sequenceNumber;
    }
    @Override
    public String toString() {
        return "HeartBeatNotification{" +
                "timestamp=" + timestamp +
                ", sequenceNumber=" + sequenceNumber +
                '}';
    }
    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    @Override
    public String getType() {
        return "HeartBeatNotification";
    }
    public int getSequenceNumber() {
        return sequenceNumber;
    }
}
