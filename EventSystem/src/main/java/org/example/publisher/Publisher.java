package org.example.publisher;

import org.example.events.Event;
import org.example.subscribers.Subscriber;

public interface Publisher {
    void publish(Event event);
    void subscribe(Subscriber<? extends Event> subscriber);
}