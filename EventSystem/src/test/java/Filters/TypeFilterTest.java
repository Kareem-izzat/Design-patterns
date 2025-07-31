package Filters;

import org.example.events.*;
import org.example.filters.EventFilter;
import org.example.filters.EventTypeFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TypeFilterTest {
    private Event task;
    private Event reminder;
    private Event heartBeat;

    @BeforeEach
    void setUp() {
        task = new TaskEvent(1, null, null);
        reminder = new ReminderEvent(2, null, null);
        heartBeat = new HeartBeatNotification(3, null, null,1000L);
    }
    @Test
    void ReminderFilteringTest(){
        EventFilter<Event> reminderFilter = new EventTypeFilter<>(EventType.REMINDER);
        // will only accept reminder type events
        assertTrue(reminderFilter.matches(reminder));
        assertFalse(reminderFilter.matches(task));
        assertFalse(reminderFilter.matches(heartBeat));
    }
    @Test
    void HeartBeatFilteringTest(){
        EventFilter<Event> heartBeatFilter = new EventTypeFilter<>(EventType.HEARTBEAT);
        // should only take heartbeat notification
        assertTrue(heartBeatFilter.matches(heartBeat));
        assertFalse(heartBeatFilter.matches(reminder));
        assertFalse(heartBeatFilter.matches(task));
    }
    @Test
    void TaskFilteringTest(){
        EventFilter<Event> taskFilter = new EventTypeFilter<>(EventType.TASK);
        assertTrue(taskFilter.matches(task));
        assertFalse(taskFilter.matches(reminder));
        assertFalse(taskFilter.matches(heartBeat));
    }
}
