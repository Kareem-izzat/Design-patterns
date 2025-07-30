package org.example.filters;

import org.example.events.Event;
import org.example.events.Priority;

public class PriorityFilter<T extends Event> implements EventFilter<T> {
    private final Priority threshold;

    public PriorityFilter(Priority threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean matches(T event) {

        return event.getPriority().ordinal() <= threshold.ordinal();
        // this will take  any task with same or higher priority
    }
}
