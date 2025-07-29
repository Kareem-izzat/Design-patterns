package org.example.filters;

import jdk.jfr.EventType;
import org.example.events.Event;

public class EventTypeFilter implements EventFilter {
    private final EventType eventType;

    public EventTypeFilter(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean matches(Event event) {
        return event.getType().equals(eventType);
    }
}
