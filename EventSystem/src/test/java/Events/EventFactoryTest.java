package Events;

import org.example.events.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventFactoryTest {

    @Test
    void createTaskEventTest(){
        Event event= EventFactory.createEvent(EventType.TASK, 1, "test task", Priority.HIGH);
        assertNotNull(event); // to check if event is created
        assertEquals(EventType.TASK, event.getType()); // check if correct type
        assertInstanceOf(TaskEvent.class, event);

    }
    @Test
    void createReminderEventTest(){
        Event event = EventFactory.createEvent(EventType.REMINDER, 2, "test reminder", Priority.LOW);
        assertNotNull(event);
        assertEquals(EventType.REMINDER, event.getType());
        assertInstanceOf(ReminderEvent.class, event);
    }
    @Test
    void createHeartbeatEventTest(){
        Event event = EventFactory.createEvent(EventType.HEARTBEAT, 3, "test heartbeat", Priority.MEDIUM,1000L);
        assertNotNull(event);
        assertEquals(EventType.HEARTBEAT, event.getType());
        assertInstanceOf(HeartBeatNotification.class, event);
    }
    @Test
    void nullTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EventFactory.createEvent(null);
        });

        assertTrue(exception.getMessage().contains("EventType cannot be null"));
    }

}
