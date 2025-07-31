package schedule;

import org.example.events.Event;
import org.example.events.HeartBeatNotification;
import org.example.events.TaskEvent;
import org.example.publisher.EventPublisher;
import org.example.publisher.Publisher;
import org.example.schedule.EventScheduler;
import org.example.schedule.ScheduleType;
import org.example.schedule.ScheduledEvent;
import org.junit.jupiter.api.Test;
import subscribers.TestSubscriber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventSchedulerTest {
    @Test
    void areOneTimeEventsRecievedTest() throws InterruptedException{
        // in this test we publish a task to a subscriber then we see if he recieve it
        Event event = new TaskEvent(1, "test task", null);
        TestSubscriber<Event> testSubscriber = new TestSubscriber<>();
        Publisher publisher = EventPublisher.getInstance();
        publisher.subscribe(testSubscriber);

        EventScheduler scheduler = new EventScheduler(publisher);
        ScheduledEvent<Event> scheduledEvent = new ScheduledEvent<>(event,  ScheduleType.ONETIME,0);

        scheduler.schedule(scheduledEvent);


        Thread.sleep(2000);

        assertTrue(testSubscriber.hasReceivedEvent());
    }
    @Test
    void ArePerioicTestReceived() throws InterruptedException{
        Event event = new HeartBeatNotification(1, "test task", null, 1000L);
        TestSubscriber<Event> testSubscriber = new TestSubscriber<>();
        Publisher publisher = EventPublisher.getInstance();
        publisher.subscribe(testSubscriber);
        EventScheduler scheduler = new EventScheduler(publisher);

        ScheduledEvent<Event> scheduledEvent = new ScheduledEvent<>(event, ScheduleType.PERIODIC, 1000); // every 1 second

        scheduler.schedule(scheduledEvent);

        Thread.sleep(3500); // to receive multiple periods

        int receivedCount = testSubscriber.getEventCount();
        assertTrue(receivedCount >= 2);// in 3 seconds it should have at least made 3 heartbeats

    }

}
