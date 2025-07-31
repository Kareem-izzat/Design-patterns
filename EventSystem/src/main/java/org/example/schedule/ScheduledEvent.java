package org.example.schedule;

import org.example.events.Event;
//  this is also a wraper function for the scheduled event
public class ScheduledEvent <T extends Event>{
    private final T event ;
    private final ScheduleType type;
    private final long period;
    public ScheduledEvent(T event, ScheduleType type, long period) {
        this.event = event;
        this.type = type;
        this.period = period;
    }

    public long getPeriod() {
        return period;
    }

    public ScheduleType getType() {
        return type;
    }

    public T getEvent() {
        return event;
    }
}
