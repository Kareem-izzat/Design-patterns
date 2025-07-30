package org.example.publisher;

import org.example.events.Event;
import org.example.subscribers.Subscriber;

public interface Publisher {
    void publish(Event event);
    <T extends Event> void subscribe(Subscriber<T> subscriber);
}
