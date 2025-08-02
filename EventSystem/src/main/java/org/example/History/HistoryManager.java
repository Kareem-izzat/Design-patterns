package org.example.History;

import org.example.events.EventType;
import org.example.publisher.LoggedEvent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

// removed this from publisher to ensure single responsibility
public class HistoryManager {
    private final List<LoggedEvent> eventHistory = new CopyOnWriteArrayList<>();// thread safe
    // this class for saving and manipulating records

    public void recordEvent(LoggedEvent event) {
        eventHistory.add(event);
    }

    public List<LoggedEvent> getEventHistory() {
        return eventHistory;
    }
    // methods to get logs between two dates

    public List<LoggedEvent> getEventsFromBetween(LocalDateTime start, LocalDateTime end) {
        return eventHistory.stream()
                .filter(log -> !log.getEvent().getTimestamp().isBefore(start) &&
                        !log.getEvent().getTimestamp().isAfter(end))
                .collect(Collectors.toList());
    }
    // to get elogs by type

    public List<LoggedEvent> getEventByType(EventType eventType) {
        return eventHistory.stream()
                .filter(log -> log.getEvent().getType() == eventType)
                .collect(Collectors.toList());
    }

    public void clearHistory() {
        eventHistory.clear();
    }

    public void printHistory() {
        eventHistory.forEach(System.out::println);
    }
}
