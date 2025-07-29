package org.example.filters;

import org.example.events.Event;

import java.time.LocalDateTime;

public class TimeFilter implements EventFilter {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public TimeFilter(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean matches(Event event) {
        LocalDateTime eventTime = event.getTimestamp();
        return !eventTime.isBefore(startTime) && !eventTime.isAfter(endTime);
    }
}
