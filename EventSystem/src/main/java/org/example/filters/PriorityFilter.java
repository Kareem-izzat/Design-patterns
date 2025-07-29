package org.example.filters;

import org.example.events.Event;
import org.example.events.Priority;

public class PriorityFilter implements EventFilter{
    private final Priority priority;
    public PriorityFilter(Priority priority) {
        this.priority = priority;
    }
    @Override
    public boolean matches (Event event){
        return event.getPriority().equals(priority);
    }

}
