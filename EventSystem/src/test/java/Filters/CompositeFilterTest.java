package Filters;
import org.example.events.Event;
import org.example.events.EventType;
import org.example.events.Priority;
import org.example.events.TaskEvent;
import org.example.filters.CompositeFilter;
import org.example.filters.EventFilter;
import org.example.filters.EventTypeFilter;
import org.example.filters.PriorityFilter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeFilterTest {
    Event testEvent = new TaskEvent(1, "Important task", Priority.LOW);

    @Test
    void allFilterMatchThenCompositeMatch(){
        // the two filters shouls match the composoite will match
        EventFilter<Event> highFilter = new PriorityFilter<>(Priority.LOW);
        EventFilter<Event> typeFilter = new EventTypeFilter<>(EventType.TASK);
        EventFilter<Event> compositeFilter = new CompositeFilter<>(List.of(highFilter, typeFilter));
        assertTrue(compositeFilter.matches(testEvent));
    }
    @Test
    void notAllFilterMatchTest(){
        EventFilter<Event> lowFilter = new PriorityFilter<>(Priority.HIGH);
        // this will not match thus the test must assert false
        EventFilter<Event> typeFilter = new EventTypeFilter<>(EventType.TASK);
        EventFilter<Event> compositeFilter = new CompositeFilter<>(List.of(lowFilter, typeFilter));
        assertFalse(compositeFilter.matches(testEvent));
    }
    @Test
    void EmptyFilterTest(){
        // empty will return true
        EventFilter<Event> compositeFilter = new CompositeFilter<>(List.of());
        assertTrue(compositeFilter.matches(testEvent));
    }
}
