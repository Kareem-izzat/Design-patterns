package org.example;

import org.example.events.*;
import org.example.filters.*;
import org.example.schedule.EventScheduler;
import org.example.schedule.ScheduleType;
import org.example.schedule.ScheduledEvent;
import org.example.subscribers.*;
import org.example.publisher.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Publisher publisher = EventPublisher.getInstance();

        // Subscriber that listens to all heartbeat events
        EventFilter<Event> heartbeatFilter = new EventTypeFilter<>(EventType.HEARTBEAT);
        FilteredSubscriber<Event> heartbeatSubscriber = new FilteredSubscriber<>(1, "HeartbeatListener", heartbeatFilter);

        publisher.subscribe(heartbeatSubscriber);

        EventScheduler scheduler = new EventScheduler(publisher);

        // Schedule HeartbeatEvent every 1 second
        ScheduledEvent<HeartBeatNotification> heartbeatEvent = new ScheduledEvent<>(
                new HeartBeatNotification(1, "Server-A",Priority.LOW, 10000L),
                ScheduleType.PERIODIC,
                1 // 1 second interval
        );

        scheduler.schedule(heartbeatEvent);

        // Let it run for 5 seconds to receive some heartbeats
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Shut down the scheduler
        scheduler.shutdown();

        System.out.println("Heartbeat scheduler test finished.");
    }
    }

