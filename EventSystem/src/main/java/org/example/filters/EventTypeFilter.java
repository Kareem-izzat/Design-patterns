package org.example.filters;

import org.example.events.EventType;
import org.example.events.Event;
// filter based on filter type
public class EventTypeFilter<T extends Event> implements EventFilter<T> {
    private final EventType eventType;

    public EventTypeFilter(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean matches(T event) {
        return event.getType() == (eventType);
    }
}
