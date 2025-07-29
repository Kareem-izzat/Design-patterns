package org.example.subscribers;

import org.example.events.Event;

public interface Subscriber {
    int getId();
    void notify(Event event);
    boolean isIntrestedIn(Event event);
}
