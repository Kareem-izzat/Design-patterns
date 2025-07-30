package org.example.publisher;


import org.example.events.Event;
import org.example.events.EventType;
import org.example.subscribers.Subscriber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventPublisher implements Publisher {
    private static final EventPublisher instance = new EventPublisher();
    private final List<Subscriber<Event>> subscribers = new ArrayList<>();
    private final List<Event> eventHistory = new CopyOnWriteArrayList<>(); //this is a safe list to store
    //history in a threading enviroment
    public static EventPublisher getInstance() {
        return instance;
    }

    private EventPublisher() {}
    @Override
    public synchronized void subscribe(Subscriber<? extends Event> subscriber) {
        @SuppressWarnings("unchecked")
        Subscriber<Event> s = (Subscriber<Event>) subscriber;
        subscribers.add(s);
    }
    @Override
    public synchronized void unsubscribe(Subscriber<? extends Event> subscriber) {
        @SuppressWarnings("unchecked")
        Subscriber<Event> s = (Subscriber<Event>) subscriber;
        subscribers.remove(s);
    }
    @Override
    public synchronized void clearSubscribers() {
        subscribers.clear();
    }

    @Override
    public synchronized void publish(Event event) {
        eventHistory.add(event);
        for (Subscriber<Event> subscriber : subscribers) {
            if (subscriber.isInterestedIn(event)) {
                subscriber.notify(event);
            }
        }
    }
    public List<Event> getEventHistory() {
        return eventHistory;
    }
    public List<Event> getEventsFromBetween(LocalDateTime start, LocalDateTime end) {

        return  eventHistory.stream()
                .filter(event-> !event.getTimestamp().isBefore(start) && !event.getTimestamp().isAfter(end))
                .collect(Collectors.toList());
    }
    public List<Event> getEventByType(EventType eventType) {
        return eventHistory.stream()
                .filter(event -> event.getType() == eventType)
                .collect(Collectors.toList());
    }

}
