package org.example.filters;

import org.example.events.Event;

import java.time.LocalDateTime;
// this filter take two period of time and give task between this period
public class TimeFilter <T extends Event> implements EventFilter <T>{
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public TimeFilter(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean matches(T event) {
        LocalDateTime eventTime = event.getTimestamp();
        return !eventTime.isBefore(startTime) && !eventTime.isAfter(endTime);
    }
}
