package publisher;

import org.example.events.*;
import org.example.publisher.EventPublisher;
import org.example.publisher.LoggedEvent;
import org.example.publisher.Publisher;
import org.example.subscribers.FilteredSubscriber;
import org.example.subscribers.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import subscribers.TestSubscriber;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventPublisherTest {
   private EventPublisher eventPublisher;
   @BeforeEach
    void setUpPublisher(){
       eventPublisher=EventPublisher.getInstance();
       eventPublisher.clearSubscribers();
       eventPublisher.clearHistory();
   }
   @Test
    void AddSubscriberTest(){
       Subscriber<Event> subscriber = new FilteredSubscriber<>(1, "HeartbeatListener", null);
       eventPublisher.subscribe(subscriber);
       assertTrue(eventPublisher.getSubscribers().contains(subscriber));

   }
   @Test
    void RemoveSubscriberTest(){
       Subscriber<Event> subscriber = new FilteredSubscriber<>(1, "HeartbeatListener", null);
       eventPublisher.subscribe(subscriber);
       eventPublisher.unsubscribe(subscriber);
       assertFalse(eventPublisher.getSubscribers().contains(subscriber));
    }
    @Test
   void ClearSubscribersTest(){
        Subscriber<Event> subscriber = new FilteredSubscriber<>(1, "HeartbeatListener", null);
        eventPublisher.subscribe(subscriber);
        eventPublisher.clearSubscribers();
        assertTrue(eventPublisher.getSubscribers().isEmpty());
    }
    @Test
    void testEventTimeFilter(){
        Subscriber<Event> subscriber = new FilteredSubscriber<>(1, "HeartbeatListener", null);
        eventPublisher.subscribe(subscriber);
        Event e1 = new TaskEvent(1, null, null);
        Event e2 = new TaskEvent(2, null, null);
        Event e3 = new TaskEvent(3, null, null);
        eventPublisher.publish(e1);
        eventPublisher.publish(e2);
        eventPublisher.publish(e3);


        LocalDateTime now = LocalDateTime.now();
        // the first list will have all tests and the second none of them
        List<LoggedEvent> inside = eventPublisher.getEventsFromBetween(now.minusSeconds(10), now.plusSeconds(10));
        List<LoggedEvent> outside = eventPublisher.getEventsFromBetween(now.plusMinutes(1), now.plusMinutes(5));


        assertEquals(3, inside.size());
        assertEquals(0, outside.size());
    }
    @Test
    void testEventTypeFilter(){

       // in this test , we check if filtering history by type work
        Subscriber<Event> subscriber = new FilteredSubscriber<>(1, "HeartbeatListener", null);
        eventPublisher.subscribe(subscriber);
        Event e1 = new TaskEvent(1, null, null);
        Event e2 = new HeartBeatNotification(2, null, null,1000L);
        Event e3 = new TaskEvent(3, null, null);
        eventPublisher.publish(e1);
        eventPublisher.publish(e2);
        eventPublisher.publish(e3);
        List<LoggedEvent> listTask = eventPublisher.getEventByType(EventType.TASK); // should have 2 elemnt
        List<LoggedEvent> listHeart = eventPublisher.getEventByType(EventType.HEARTBEAT);// only one
        List<LoggedEvent> listReminder = eventPublisher.getEventByType(EventType.REMINDER); // zero


        assertEquals(2, listTask.size());
        assertEquals(1, listHeart.size());
        assertEquals(0, listReminder.size());
    }
    @Test
    void publishWithNoSubscribersTest(){
        Event e1 = new TaskEvent(1, null, null);
        eventPublisher.publish(e1);
        assertEquals(0, eventPublisher.getSubscribers().size());
    }
    @Test
    void puplishShoudbeReveivedForSubscriberTest(){
       // we will send a event and check if the sub received it
        TestSubscriber<Event> testSubscriber = new TestSubscriber<>();
        eventPublisher.subscribe(testSubscriber);
        Event event = new TaskEvent(1,"test task", null);
        eventPublisher.publish(event);
        // check if it received
        assertTrue(testSubscriber.hasReceivedEvent());

    }

}
