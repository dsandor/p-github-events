package com.dsandor.github.models;

import javax.persistence.*;
import java.util.Comparator;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String eventName;

    @OneToOne
    private Actor actor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public static Comparator<Event> EventIdComparator = new Comparator<Event>() {
        public int compare(Event a, Event b) {
            // a compare to b is ascending, b compare to a is descending.
            return a.getId().compareTo(b.getId());
        }
    };
}
