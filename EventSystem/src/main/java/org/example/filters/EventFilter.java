package org.example.filters;

import org.example.events.Event;
// to implement  a strategy pattern depending in the type of filter needed
public interface EventFilter {
    boolean matches(Event event);
}
