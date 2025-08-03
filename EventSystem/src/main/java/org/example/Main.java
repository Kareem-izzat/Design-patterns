package org.example;

import org.example.events.*;
import org.example.filters.*;
import org.example.schedule.EventScheduler;
import org.example.schedule.ScheduleType;
import org.example.schedule.ScheduledEvent;
import org.example.subscribers.*;
import org.example.publisher.*;
import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Publisher publisher = EventPublisher.getInstance();
        EventScheduler scheduler = new EventScheduler(publisher);

        // Subscriber listens only to HEARTBEAT events
        EventFilter<Event> heartbeatFilter = new EventTypeFilter<>(EventType.HEARTBEAT);
        Subscriber<Event> heartbeatSubscriber = new FilteredSubscriber<>(1, "HeartbeatSubscriber", heartbeatFilter);
        publisher.subscribe(heartbeatSubscriber);

        // Subscriber listens only to HIGH priority events
        EventFilter<Event> highPriorityFilter = new PriorityFilter<>(Priority.HIGH);
        Subscriber<Event> highPrioritySubscriber = new FilteredSubscriber<>(2, "HighPrioritySubscriber", highPriorityFilter);
        publisher.subscribe(highPrioritySubscriber);

        // Subscriber listens to everything
        Subscriber<Event> generalSubscriber = new FilteredSubscriber<>(3, "GeneralSubscriber", null);
        publisher.subscribe(generalSubscriber);

        // Schedule HeartBeat every 2 seconds
        ScheduledEvent<HeartBeatNotification> heartbeatEvent = new ScheduledEvent<>(
                new HeartBeatNotification(10, "Server-1", Priority.MEDIUM, 1000L),
                ScheduleType.PERIODIC,
                2000
        );

        // Schedule High Priority Task every 5 seconds
        ScheduledEvent<TaskEvent> taskEvent = new ScheduledEvent<>(
                new TaskEvent(11, "Backup Task", Priority.HIGH),
                ScheduleType.PERIODIC,
                5000
        );

        // Schedule Reminder once after 3 seconds
        ScheduledEvent<ReminderEvent> reminderEvent = new ScheduledEvent<>(
                new ReminderEvent(12, "Water your plant", Priority.LOW),
                ScheduleType.ONETIME,
                3000
        );

        scheduler.schedule(heartbeatEvent);
        scheduler.schedule(taskEvent);
        scheduler.schedule(reminderEvent);


        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        scheduler.shutdown();



        System.out.println("\nEvent scheduling test finished.");
    }
    }

