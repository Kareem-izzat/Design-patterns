package org.example.filters;

import org.example.events.Event;
// to implement  a strategy pattern depending on the type of filter needed
public interface EventFilter <T extends Event> {
    boolean matches(T event);
}
