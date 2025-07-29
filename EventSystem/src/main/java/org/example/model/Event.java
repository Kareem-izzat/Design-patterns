package org.example.model;

import java.time.LocalDateTime;


// base interface for events with type , priority and date functions
public interface Event {
    EventType getType();
    EventPriority getPriority();
    LocalDateTime getTime();
}
