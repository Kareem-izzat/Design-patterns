package org.example.publisher;

import org.example.events.Event;
import org.example.subscribers.Subscriber;

import java.util.List;

public class LoggedEvent {
    private final Event event;
    private final List<Subscriber<Event>> subscribers;

    public LoggedEvent(Event event,List<Subscriber<Event>> subscribers) {
        this.event = event;
        this.subscribers = List.copyOf(subscribers);
    }
    public Event getEvent() {
        return event;
    }
    public List<Subscriber<Event>> getSubscribers() {
        return subscribers;
    }
    @Override
    public String toString() {
        return "LoggedEvent{" +
                "event=" + event +
                ", subscribers=" + subscribers +
                 "Number of subscribers:"+ subscribers.size()+" }";
    }


}
