package edu.CalTestApp1.domain;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import edu.CalTestApp1.utils.Utils;


public class Meeting extends Event {

    private Date dateStart;

    private Date dateEnd;

    private String description;

    private Set<Task> tasks;


    public Meeting() {}

    public Meeting(long id, int day, int month, int year, int hourStart, int minuteStart, int hourEnd, int minuteEnd, String description) {
        super();
        super.eventType = EventType.EVENT_MEETING;
        this.id = id;
        Date d = Utils.createDate(day, month, year);
        this.dateStart = Utils.createHour(d, hourStart, minuteStart);
        this.dateEnd = Utils.createHour(d, hourEnd, minuteEnd);
        this.description = description;
        this.tasks = new LinkedHashSet<>();
    }

    public Meeting(int day, int month, int year, int hourStart, int minuteStart, int hourEnd, int minuteEnd, String description) {
        super();
        super.eventType = EventType.EVENT_MEETING;
        Date d = Utils.createDate(day, month, year);
        this.dateStart = Utils.createHour(d, hourStart, minuteStart);
        this.dateEnd = Utils.createHour(d, hourEnd, minuteEnd);
        this.description = description;
        this.tasks = new LinkedHashSet<>();
    }

    public Meeting(Date dateStart, Date dateEnd, String description) {
        super();
        super.eventType = EventType.EVENT_MEETING;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
        this.tasks = new LinkedHashSet<>();
    }

    public Meeting(long id, Date dateStart, Date dateEnd, String description) {
        super();
        super.eventType = EventType.EVENT_MEETING;
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
        this.tasks = new LinkedHashSet<>();
    }

    public int getDay() {
        return Utils.getDay(dateStart);
    }

    public int getMonth() {
        return Utils.getMonth(dateStart);
    }

    public int getYear() {
        return Utils.getYear(dateStart);
    }

    public int getStartHour() {
        return Utils.getHour(dateStart);
    }

    public int getStartMinute() {
        return Utils.getMinute(dateStart);
    }

    public int getEndHour() {
        return Utils.getHour(dateEnd);
    }

    public int getEndMinute() {
        return Utils.getMinute(dateEnd);
    }

    public Date getDate() {
        return dateStart;
    }

    public int getTotalStartMinute() {
        int hour = Utils.getHour(dateStart);
        int minute = Utils.getMinute(dateStart);
        return hour*60+minute;
    }

    public int getTotalEndMinute() {
        int hour = Utils.getHour(dateEnd);
        int minute = Utils.getMinute(dateEnd);
        return hour*60+minute;
    }

    public void setDate(int day, int month, int year) {
        int hourStart, minuteStart, hourEnd, minuteEnd;
        hourStart = Utils.getHour(dateStart);
        hourEnd = Utils.getMinute(dateEnd);
        minuteStart = Utils.getHour(dateStart);
        minuteEnd = Utils.getMinute(dateEnd);
        Date date = Utils.createDate(day, month, year);
        this.dateStart = Utils.createHour(date, hourStart, minuteStart);
        this.dateEnd = Utils.createHour(date, hourEnd, minuteEnd);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getHourStart() {
        return dateStart;
    }

    public void setHourStart(int hour, int minute) {
        this.dateStart = Utils.createHour(this.dateStart, hour, minute);
    }

    public Date getHourEnd() {
        return dateEnd;
    }

    public void setHourEnd(int hour, int minute) {
        this.dateEnd = Utils.createHour(this.dateEnd, hour, minute);
    }

    public void addTask(Task t) {
        tasks.add(t);
        t.setMeetingAssociated(description);
    }

    public void eraseTask(Task t) {
        tasks.remove(t);
        t.setMeetingAssociated(null);
    }

    @Override
    public String toString() {
        String result = "";
        result += "Date: " + Utils.dateToString(dateStart);
        result += "Description: " + this.description + "\n";
        result += "Start hour: " + Utils.hourToString(dateStart);
        result += "End hour: " + Utils.hourToString(dateEnd);
        return result;
    }
}
