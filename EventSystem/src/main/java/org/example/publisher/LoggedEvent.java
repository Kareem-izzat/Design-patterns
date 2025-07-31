package org.example.publisher;

import org.example.events.Event;

public class LoggedEvent {
    private final Event event;
    private final int numberOfSubscribers;
    public LoggedEvent(Event event, int numberOfSubscribers) {
        this.event = event;
        this.numberOfSubscribers = numberOfSubscribers;
    }
    public Event getEvent() {
        return event;
    }
    public int getNumberOfSubscribers() {
        return numberOfSubscribers;
    }
    @Override
    public String toString() {
        return "LoggedEvent{" +
                "event=" + event +
                ", numberOfSubscribers=" + numberOfSubscribers +
                '}';
    }
}
