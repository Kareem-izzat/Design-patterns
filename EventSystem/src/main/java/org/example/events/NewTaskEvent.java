package org.example.events;

import java.time.LocalDateTime;

public class NewTaskEvent implements Event{
    private final int id;
    private final LocalDateTime timestamp;
    private final String taskDetail;
    public NewTaskEvent(int id,  String taskDetail) {
        this.id = id;
        this.timestamp = LocalDateTime.now();
        this.taskDetail = taskDetail;
    }

    public int getId() {
        return id;
    }

    public String getTaskDetail() {
        return taskDetail;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    @Override
    public String getType() {
        return "NewTaskEvent";
    }
    @Override
    public String toString() {
        return "NewTaskEvent{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", taskDetail='" + taskDetail + '\'' +
                '}';
    }
}
