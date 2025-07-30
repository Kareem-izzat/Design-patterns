package org.example.publisher;

import org.example.events.Event;
import org.example.subscribers.Subscriber;

import java.util.ArrayList;
import java.util.List;
public class EventPublisher implements Publisher {

    private final List<Subscriber<Event>> subscribers = new ArrayList<>();


    @Override
    public synchronized void subscribe(Subscriber<? extends Event> subscriber) {// Safe cast because Subscriber<T extends Event> can be treated as Subscriber<Event>
        @SuppressWarnings("unchecked")
        Subscriber<Event> s = (Subscriber<Event>) subscriber;
        subscribers.add(s);
    }

    @Override
    public synchronized void publish(Event event) {
        for (Subscriber<Event> subscriber : subscribers) {
            if (subscriber.isInterestedIn(event)) {
                subscriber.notify(event);
            }
        }
    }
}
