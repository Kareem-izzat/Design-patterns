package org.example.subscribers;

import org.example.events.Event;
import org.example.filters.EventFilter;
// this is a subscriber that will take an event filter and filter based on it
public class FilteredSubscriber<T extends Event> implements Subscriber<T>{
    private final int id;
    private final EventFilter<T> filter; //subscriber will take the efilter he want to have
    private final String name;

    public FilteredSubscriber(int id, String name,EventFilter<T> filter) {
        this.id = id;
        this.filter = filter;
        this.name = name;
    }
    @Override
    public int getId() {
        return id;
    }


    @Override
    public void notify(T event) {
        System.out.println("Subscriber " + id + " (" + name + ") received event: " + event);
    }
    // this is to apply the filter
    // if no filter is added then it will allow everything
    @Override
    public boolean isInterestedIn(T event) {
        if (filter == null) {
            return true;
        }
        return filter.matches(event);
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "FilteredSubscriber{" +
                "id=" + id +
                ", filter=" + filter +
                ", name='" + name + '\'' +
                '}';
    }
}
