package subscribers;

import org.example.events.Event;
import org.example.subscribers.Subscriber;

import java.util.ArrayList;
import java.util.List;

// so we can test function that return printstatments
// this is so we can see if things are received
// if notifed it will have true
public class TestSubscriber<T extends Event> implements Subscriber <T>{

    private boolean received = false;
    private T receivedEvent;
    private final List<T> receivedEvents = new ArrayList<>();

    @Override
    public void notify(T event) {
        this.received = true;
        receivedEvents.add(event);
    }

    @Override
    public boolean isInterestedIn(T event) {

        return true;
    }

    @Override
    public int getId() {
        return 999;
    }

    @Override
    public String getName() {
        return "TestSubscriber";
    }

    public boolean hasReceivedEvent() {
        return received;
    }
    public List<T> getReceivedEvents() {
        return receivedEvents;
    }

    public int getEventCount() {
        return receivedEvents.size();
    }
}
