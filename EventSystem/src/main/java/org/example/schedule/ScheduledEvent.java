package org.example.schedule;

import org.example.events.Event;

public class ScheduledEvent <T extends Event>{
    private final T event ;
    private final SchduleType type;
    private final long period;
    public ScheduledEvent(T event, SchduleType type, long period) {
        this.event = event;
        this.type = type;
        this.period = period;
    }

    public long getPeriod() {
        return period;
    }

    public SchduleType getType() {
        return type;
    }

    public T getEvent() {
        return event;
    }
}
