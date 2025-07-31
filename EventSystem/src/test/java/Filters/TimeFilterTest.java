package Filters;

import org.example.events.Event;
import org.example.events.Priority;
import org.example.events.TaskEvent;
import org.example.filters.EventFilter;

import org.example.filters.TimeFilter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TimeFilterTest {

    @Test
    void TimeFilteringInTimeTest(){
        // the event is in time bound
        LocalDateTime start = LocalDateTime.now().minusMinutes(10);
        LocalDateTime end = LocalDateTime.now().plusMinutes(10);

        EventFilter<Event> timeFilter = new TimeFilter<>(start, end);

        Event event1 = new TaskEvent(1, "Test task", Priority.HIGH);
        assertTrue(timeFilter.matches(event1));

    }
    @Test
    void TimeFilteringOutTimeTest(){
        // the event is not in time bound
        LocalDateTime start = LocalDateTime.now().plusMinutes(10);
        LocalDateTime end = LocalDateTime.now().plusMinutes(20);
        EventFilter<Event> timeFilter = new TimeFilter<>(start, end);
        Event event1 = new TaskEvent(1, "Test task", Priority.HIGH);
        assertFalse(timeFilter.matches(event1));
    }
}
