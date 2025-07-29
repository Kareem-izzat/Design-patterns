package org.example.events;

import java.time.LocalDateTime;

public class TaskEvent implements Event {

    private final LocalDateTime timestamp;
    private final int taskId;
    private final String description;

    public TaskEvent(int taskId, String description) {
        this.timestamp = LocalDateTime.now();
        this.taskId = taskId;
        this.description = description;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public EventType getType() {
        return EventType.TASK;
    }

    @Override
    public Priority getPriority() {
        return Priority.HIGH;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "TaskEvent{" +
                "timestamp=" + timestamp +
                ", taskId='" + taskId + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + getPriority() +
                ", type=" + getType() +
                '}';
    }
}