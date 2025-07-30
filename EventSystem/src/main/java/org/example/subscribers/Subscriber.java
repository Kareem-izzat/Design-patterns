package org.example.subscribers;

import org.example.events.Event;

public interface Subscriber<T extends Event> {
    int getId();
    void notify(T event);
    boolean isInterestedIn(T event);
}
