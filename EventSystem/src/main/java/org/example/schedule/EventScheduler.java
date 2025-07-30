package org.example.schedule;

import org.example.events.Event;
import org.example.publisher.Publisher;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EventScheduler {
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
    // i will use two thread for now because the system doesnt excpect many events in the current state
    private final Publisher publisher;
    public EventScheduler(Publisher publisher) {
        this.publisher = publisher;
    }
    public <T extends Event> void schedule(ScheduledEvent<T> scheduledEvent) {
        Runnable task = () -> publisher.publish(scheduledEvent.getEvent());

        if (scheduledEvent.getType() == ScheduleType.ONETIME) {
            executor.schedule(task, scheduledEvent.getPeriod(), TimeUnit.SECONDS);
        } else if (scheduledEvent.getType() == ScheduleType.PERIODIC) {
            executor.scheduleAtFixedRate(task, 0, scheduledEvent.getPeriod(), TimeUnit.SECONDS);
        }
    }
    public void shutdown() {
        executor.shutdown();
    }


}
