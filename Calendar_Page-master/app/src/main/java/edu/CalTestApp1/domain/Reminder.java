package edu.CalTestApp1.domain;

import java.util.LinkedHashSet;
import java.util.Set;


public class Reminder {

    private Set<Event> events;


    public Reminder() {
        events = new LinkedHashSet<>();
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public void addEvent(Event e) {
        events.add(e);
        e.setReminder(this);
    }

    public void eraseEvent(Event e) {
        events.remove(e);
        e.setReminder(null);
    }

    public Set<Task> getUndoneTasks() {
        Set<Task> result = new LinkedHashSet<>();
        for (Event e : events) {
            if (!e.isMeeting()) {
                Task t = (Task) e;
                if (!t.isDone()) result.add(t);
            }
        }
        return result;
    }

    public String printUndoneTasks() {
        String result = "";
        for (Task t : getUndoneTasks()) {
            result += t.toString() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        int cont = 1;
        String s = "";
        for (Event e : events) {
            s += "Event number " + cont + ":\n";
            s += (e.toString()) + "\n";
            ++cont;
        }
        return s;
    }
}