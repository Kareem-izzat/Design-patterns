package org.example.filters;

import org.example.events.Event;

import java.util.List;


// this is a filter to apply more than one type of filter using streams
public class CompositeFilter<T> implements EventFilter<T>{
    private final List<EventFilter<T>> filters;
    public CompositeFilter(List<EventFilter<T>> filters) {
        this.filters = filters;
    }

    @Override
    public boolean matches(T event) {

        return filters.stream().allMatch(f -> f.matches(event));
    }
}
