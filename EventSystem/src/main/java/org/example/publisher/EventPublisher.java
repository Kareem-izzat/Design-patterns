package org.example.publisher;

import org.example.events.Event;
import org.example.subscribers.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher implements Publisher{
    private final List<Subscriber<? extends Event>> subscribers = new ArrayList<>();
    // synchronized is used to ensure safety when working with threads
    @Override
    public synchronized <T extends Event> void subscribe(Subscriber<T> subscriber) {
        subscribers.add(subscriber);
    }
    @Override
    public synchronized void publish(Event event) {
        for (Subscriber<? extends Event> subscriber : subscribers) {
            @SuppressWarnings("unchecked")
            Subscriber<Event> sub = (Subscriber<Event>) subscriber;

            if (sub.isInterestedIn(event)) {
                sub.notify(event);
            }
        }
    }
}
