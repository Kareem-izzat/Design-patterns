package org.example.events;

import java.time.LocalDateTime;


public class ReminderEvent implements Event {
    private final int id;
    private final LocalDateTime timestamp;
    private final String reminderMessage;
    private final Priority priority;

    public ReminderEvent(int id, String reminderMessage,Priority priority) {
        this.id = id;
        this.timestamp = LocalDateTime.now();
        this.reminderMessage = reminderMessage;
        if (priority == null) {
            this.priority = Priority.MEDIUM;
        } else {
            this.priority = priority;
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public EventType getType() {
        return EventType.REMINDER;  // Use enum instead of String
    }

    @Override
    public Priority getPriority() {
        return priority;     // Reminder events have medium priority
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
                ", priority=" + getPriority() +
                ", type=" + getType() +
                '}';
    }
}
