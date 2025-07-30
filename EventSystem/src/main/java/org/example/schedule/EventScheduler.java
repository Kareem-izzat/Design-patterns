package org.example.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class EventScheduler {
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
    // i will use two thread for now because the system doesnt excpect many events in the current state


}
