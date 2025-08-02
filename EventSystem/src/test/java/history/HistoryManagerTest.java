package history;

import org.example.History.HistoryManager;
import org.example.events.Event;
import org.example.events.EventType;
import org.example.events.HeartBeatNotification;
import org.example.events.TaskEvent;
import org.example.publisher.LoggedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryManagerTest {

    private HistoryManager historyManager;

    @BeforeEach
    void setup() {
        historyManager = new HistoryManager();
    }

    @Test
    void testEventTimeFilter() {
        Event e1 = new TaskEvent(1, null, null);
        Event e2 = new TaskEvent(2, null, null);
        Event e3 = new TaskEvent(3, null, null);

        historyManager.recordEvent(new LoggedEvent(e1, List.of()));
        historyManager.recordEvent(new LoggedEvent(e2, List.of()));
        historyManager.recordEvent(new LoggedEvent(e3, List.of()));

        LocalDateTime now = LocalDateTime.now();
        List<LoggedEvent> inside = historyManager.getEventsFromBetween(now.minusSeconds(10), now.plusSeconds(10));
        List<LoggedEvent> outside = historyManager.getEventsFromBetween(now.plusMinutes(1), now.plusMinutes(5));

        assertEquals(3, inside.size());
        assertEquals(0, outside.size());
    }

    @Test
    void testEventTypeFilter() {
        Event e1 = new TaskEvent(1, null, null);
        Event e2 = new HeartBeatNotification(2, null, null, 1000L);
        Event e3 = new TaskEvent(3, null, null);

        historyManager.recordEvent(new LoggedEvent(e1, List.of()));
        historyManager.recordEvent(new LoggedEvent(e2, List.of()));
        historyManager.recordEvent(new LoggedEvent(e3, List.of()));

        List<LoggedEvent> listTask = historyManager.getEventByType(EventType.TASK);
        List<LoggedEvent> listHeart = historyManager.getEventByType(EventType.HEARTBEAT);
        List<LoggedEvent> listReminder = historyManager.getEventByType(EventType.REMINDER);

        assertEquals(2, listTask.size());
        assertEquals(1, listHeart.size());
        assertEquals(0, listReminder.size());
    }
}
