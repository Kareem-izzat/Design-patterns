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
import java.util.logging.Logger;


public class EventPublisher implements Publisher {

    private static final EventPublisher instance = new EventPublisher();
    private final List<Subscriber<Event>> subscribers = new ArrayList<>();
    private final List<LoggedEvent> eventHistory = new CopyOnWriteArrayList<>(); //this is a safe list to store
    //history in a threading enviroment
    private static final Logger logger = Logger.getLogger(EventPublisher.class.getName());
    // to add real logging instead of prints
    public static EventPublisher getInstance() {
        return instance;
    }

    public List<Subscriber<Event>> getSubscribers() {
        return subscribers;
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
        if (subscribers.contains(s)) {
            subscribers.remove(s);
        }else {
            System.out.println("Subscriber not found");
        }

    }
    @Override
    public synchronized void clearSubscribers() {
        subscribers.clear();
    }
    public synchronized int getSubscriberCount() {
        return subscribers.size();
    }
    public synchronized  void clearHistory(){
        eventHistory.clear();
    }

    @Override
    public synchronized void publish(Event event) {

        List<Subscriber<Event>> notifiedSubscribers = new ArrayList<>();
        if (!subscribers.isEmpty()) {
            for (Subscriber<Event> subscriber : subscribers) {
                if (subscriber.isInterestedIn(event)) {
                    subscriber.notify(event);
                    notifiedSubscribers.add(subscriber);
                }
            }
        } else {
            logger.warning("No subscribers to notify for event: " + event);
        }

        LoggedEvent loggedEvent = new LoggedEvent(event, notifiedSubscribers);
        eventHistory.add(loggedEvent);

        logger.info("Event published: " + event + " to " + notifiedSubscribers.size() + " subscribers");
    }
    public List<LoggedEvent> getEventHistory() {
        return eventHistory;
    }
    public List<LoggedEvent> getEventsFromBetween(LocalDateTime start, LocalDateTime end) {

        return  eventHistory.stream()
                .filter(log-> !log.getEvent().getTimestamp().isBefore(start) && !log.getEvent().getTimestamp().isAfter(end))
                .collect(Collectors.toList());
    }
    public List<LoggedEvent> getEventByType(EventType eventType) {
        return eventHistory.stream()
                .filter(Log -> Log.getEvent().getType() == eventType)
                .collect(Collectors.toList());
    }
    public void printHistory() {
        for (LoggedEvent event : eventHistory) {
            System.out.println(event);
        }
    }

}
