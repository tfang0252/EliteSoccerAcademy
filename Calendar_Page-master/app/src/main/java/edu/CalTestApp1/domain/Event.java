package edu.CalTestApp1.domain;

import java.io.Serializable;

public class Event implements Serializable {

    public enum EventType {
        EVENT_MEETING,
        EVENT_TASK
    }

    protected long id;

    protected EventType eventType;

    protected Reminder reminder;

    public Reminder getReminder() {
        return reminder;
    }

    public Event() {

    }

    public long getId() {
        return id;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    public boolean isMeeting() {
        if (eventType == EventType.EVENT_MEETING) return true;
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
