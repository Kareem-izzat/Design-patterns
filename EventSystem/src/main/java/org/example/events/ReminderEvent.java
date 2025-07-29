package org.example.events;

import java.time.LocalDateTime;

public class ReminderEvent implements Event{
    private final int id;
    private final LocalDateTime timestamp;
    private final String reminderMessage;

    public ReminderEvent(int id, String reminderMessage) {
        this.id = id;
        this.timestamp = LocalDateTime.now();
        this.reminderMessage = reminderMessage;
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
        return "ReminderEvent";
    }

    public String getReminderMessage() {
        return reminderMessage;
    }

    @Override
    public String toString() {
        return "ReminderEvent{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", reminderMessage='" + reminderMessage + '\'' +
                '}';
    }
}
