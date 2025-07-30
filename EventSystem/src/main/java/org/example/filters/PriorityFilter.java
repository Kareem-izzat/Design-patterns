package org.example.filters;

import org.example.events.Event;
import org.example.events.Priority;

public class PriorityFilter <T extends Event> implements EventFilter<T>{
    private final Priority priority;
    public PriorityFilter(Priority priority) {
        this.priority = priority;
    }
    @Override
    public boolean matches (T event){
        return event.getPriority().equals(priority);
    }

}
