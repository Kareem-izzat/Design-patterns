package org.example.filters;

import org.example.events.Event;

import java.util.List;


// this is a filter to apply more than one type of filter using streams
public class CompositeFilter implements EventFilter{
    private final List<EventFilter> filters;
    public CompositeFilter(List<EventFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean matches(Event event) {

        return filters.stream().allMatch(f -> f.matches(event));
    }
}
