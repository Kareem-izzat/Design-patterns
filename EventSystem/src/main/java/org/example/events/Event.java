package org.example.events;

import java.time.LocalDateTime;
// base interface for all types of  events
public interface Event {
    LocalDateTime getTimestamp();
    EventType getType();
    Priority getPriority();
}
