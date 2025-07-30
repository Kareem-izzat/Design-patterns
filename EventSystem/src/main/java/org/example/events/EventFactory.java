package org.example.events;

public class EventFactory {
    public static Event createEvent(EventType eventType , Object ... params) {
        switch (eventType) {
            case TASK :
                // it will receive int taskId, String description, Priority priority
                return new TaskEvent((int) params[0],(String) params[1],(Priority) params[2]);
            case REMINDER:
                // it will receive int id, String reminderMessage, Priority priority
                return new ReminderEvent(
                        (int) params[0],
                        (String) params[1],
                        (Priority) params[2]
                );
            case HEARTBEAT:
                // it will receive int sequenceNumber, String componentName, Priority priority, Long period
                return new HeartBeatNotification(
                        (int) params[0],
                        (String) params[1],
                        (Priority) params[2],
                        (Long) params[3]
                );
            default:
                throw new IllegalArgumentException("Unsupported event type: " + eventType);
        }
    }
}
