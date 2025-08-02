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
