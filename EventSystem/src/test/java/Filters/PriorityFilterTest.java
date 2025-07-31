package Filters;

import org.example.events.Event;
import org.example.events.Priority;
import org.example.events.TaskEvent;
import org.example.filters.EventFilter;
import org.example.filters.PriorityFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PriorityFilterTest {
    private Event highPriority;
    private Event mediumPriority;
    private Event lowPriority;

    @BeforeEach
    void setUp() {
        highPriority = new TaskEvent(1, null, Priority.HIGH);
        mediumPriority = new TaskEvent(2, null, Priority.MEDIUM);
        lowPriority = new TaskEvent(3, null, Priority.LOW);
    }
    @Test
    void lowFiltering(){
        EventFilter<Event> lowFilter = new PriorityFilter<>(Priority.LOW);
        // low filter should accept all priority
        assertTrue(lowFilter.matches(lowPriority));
        assertTrue(lowFilter.matches(mediumPriority));
        assertTrue(lowFilter.matches(highPriority));

    }
    @Test
    void mediumFiltering(){
        EventFilter<Event> mediumFilter = new PriorityFilter<>(Priority.MEDIUM);
        // mediumfilter acccepet medium and high only
        assertFalse(mediumFilter.matches(lowPriority));
        assertTrue(mediumFilter.matches(mediumPriority));
        assertTrue(mediumFilter.matches(highPriority));

    }
    @Test
    void highFiltering(){
        EventFilter<Event> highFilter = new PriorityFilter<>(Priority.HIGH);
        // high will only accept high priority
        assertFalse(highFilter.matches(lowPriority));
        assertFalse(highFilter.matches(mediumPriority));
        assertTrue(highFilter.matches(highPriority));
    }
}
