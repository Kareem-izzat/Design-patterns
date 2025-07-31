package subscribers;

import org.example.events.Event;
import org.example.events.Priority;
import org.example.events.TaskEvent;
import org.example.filters.EventFilter;
import org.example.filters.PriorityFilter;
import org.example.subscribers.FilteredSubscriber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilteredSubscriberTest {
    @Test
    void testIfSubReviveHisPrefrencedEvent(){
        // test filtering in subscriber
        EventFilter<Event> HighFilter = new PriorityFilter<>(Priority.HIGH);
        FilteredSubscriber<Event> subscriber = new FilteredSubscriber<>(1, "TestSubscriber",HighFilter );
        Event highPriority = new TaskEvent(1, null, Priority.HIGH);
        Event lowPriority = new TaskEvent(2, null, Priority.LOW);
        boolean acceptsHigh = subscriber.isInterestedIn(highPriority);
        boolean acceptsLow = subscriber.isInterestedIn(lowPriority);


        assertTrue(acceptsHigh);
        assertFalse(acceptsLow);
    }
    @Test
    void IfSubRecieveNoFilterITAcceptEverythingTest(){

        // if there is no filter the suscriber will receive all events
        FilteredSubscriber<Event> subscriber = new FilteredSubscriber<>(1, "TestSubscriber",null);
        Event highPriority = new TaskEvent(1, null, Priority.HIGH);
        Event lowPriority = new TaskEvent(2, null, Priority.LOW);
        boolean acceptsHigh = subscriber.isInterestedIn(highPriority);
        boolean acceptsLow = subscriber.isInterestedIn(lowPriority);


        assertTrue(acceptsHigh);
        assertTrue(acceptsLow);
    }
}
