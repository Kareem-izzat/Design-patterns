package org.example.publisher;


import org.example.History.HistoryManager;
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
    private final HistoryManager historyManager = new HistoryManager();

    private static final Logger logger = Logger.getLogger(EventPublisher.class.getName());
    // to add real logging instead of prints
    // made into singeltoon pattern because we only need one publisher also for thread safety
    public static EventPublisher getInstance() {
        return instance;
    }

    public List<Subscriber<Event>> getSubscribers() {
        return subscribers;
    }

    private EventPublisher() {}
    // this function to add a new subscriber , it is also thread safe the same thing for unsubbscribe
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
        historyManager.getEventHistory().clear();
    }
    // in this function that implement observer pattern the publisher will loop for each subsciber then
    // check if this event is allowed for him or not , it also log what happens
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
        historyManager.recordEvent(loggedEvent);

        logger.info("Event published: " + event + " to " + notifiedSubscribers.size() + " subscribers");
    }
    public List<LoggedEvent> getEventHistory() {
        return historyManager.getEventHistory();
    }
    // to get logged events in any time period

    public List<LoggedEvent> getEventsFromBetween(LocalDateTime start, LocalDateTime end) {
        return historyManager.getEventsFromBetween(start, end);
    }

    public List<LoggedEvent> getEventByType(EventType eventType) {
        return historyManager.getEventByType(eventType);
    }

}
