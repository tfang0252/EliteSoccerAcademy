package edu.CalTestApp1.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.CalTestApp1.R;
import edu.CalTestApp1.domain.Event;
import edu.CalTestApp1.domain.Meeting;
import edu.CalTestApp1.domain.Task;
import edu.CalTestApp1.utils.MeetingButton;
import edu.CalTestApp1.utils.OnSwipeTouchListener;
import edu.CalTestApp1.utils.TaskButton;
import edu.CalTestApp1.utils.Utils;

public class MainActivity extends ActionBarActivity
{
    public static final String MY_ACCOUNT_NAME =            "calApp";
    public static final String CALENDAR_NAME =              "Calendar";
    public static final String EVENT_TO_ADD =               "EventToAdd";
    public static final String UNDONE_TASKS_TO_SHOW =       "UndoneTasksToShow";
    public static final String EVENTS_DAY_TO_SHOW =         "EventsDayToShow";
    public static final String DATE_IN_LONG_FORMAT =        "DateInLongFormat";
    public static final String LIST_MEETINGS =              "ListMeetings";
    public static final String TOAST_ERROR_FIND_ID =        "Error to find Calendar";
    public static final String TOAST_ERROR_FORMAT_DATE =    "Error to format Date";
    public static final String TOAST_DELETE_SUCCESSFULLY =  "All data have been deleted successfully";
    public static final String TOAST_DELETE_MEETING =       "This meeting has been deleted successfully";
    public static final String TOAST_TASK_MEETING =         "This task has been deleted successfully";
    public static final String TOAST_ADD_SUCCESSFULLY =     "Event has been added successfully";
    public static final String TOAST_SAME_SCHEDULE =        "It has already exists a meeting with same schedule";
    public static final String CALENDAR_TIME_ZONE =         "Europe/Berlin";
    public static final String OWNER_ACCOUNT =              "some.account@googlemail.com";
    public static final String SETTINGS_VARIABLE_KEY =      "settings";
    public static final String SETTINGS_VARIABLE_2_KEY =    "settings2";
    public static final String CALENDAR_ID_KEY =            "calendarId";
    public static final int UNDONE_TASK =                   0;
    public static final int DONE_TASK =                     1;
    public static final int MEETING =                       1;
    public static final int TASK =                          0;

    public static final String MONDAY =                     "monday";
    public static final String TUESDAY =                    "tuesday";
    public static final String WEDNESDAY =                  "wednesday";
    public static final String THURSDAY =                   "thursday";
    public static final String FRIDAY =                     "friday";
    public static final String SATURDAY =                   "saturday";
    public static final String SUNDAY =                     "sunday";

    private Event eventAdded;
    private String descriptionAssociatedMeeting;
    private ArrayList<Task> undoneTasks;
    private long calendarId;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(MainActivity.this);
        initializeView();
        initializeProvider();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.add_event:
                Event event = new Event();
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                i.putExtra(EVENT_TO_ADD, event);
                i.putExtra(LIST_MEETINGS, listDescriptionsMeetings());
                startActivityForResult(i, 0);
                break;
            case R.id.search_day:
                final DatePicker datePicker = (DatePicker) inflater.inflate(getResources().getLayout(R.layout.date_picker), null);
                datePicker.updateDate(Utils.getCurrentYear(), Utils.getCurrentMonth() - 1, Utils.getCurrentFirstDay());
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Change week")
                        .setMessage("Select the day you want to go")
                        .setView(datePicker)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Utils.setDaysOfWeek(datePicker.getCalendarView().getDate());
                                updateView();
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(getResources().getDrawable(R.drawable.ic_menu_forward))
                        .show();
                break;
            case R.id.list_undone_tasks:
                Intent i2 = new Intent(MainActivity.this, UndoneTasksActivity.class);
                i2.putExtra(UNDONE_TASKS_TO_SHOW, findUndoneTasks());
                startActivityForResult(i2, 0);
                break;
            case R.id.delete:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete All Data")
                        .setMessage("Are you sure you want to delete the selected database?")
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteAll();
                                updateView();
                                Toast.makeText(MainActivity.this, TOAST_DELETE_SUCCESSFULLY, Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(getResources().getDrawable(R.drawable.ic_menu_close_clear_cancel))
                        .show();
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (data == null) return;
        eventAdded = (Event) data.getSerializableExtra(AddActivity.EVENT_TO_SEND);
        if (eventAdded != null) {
            if (!eventAdded.isMeeting()) {
                descriptionAssociatedMeeting = data.getStringExtra(AddActivity.ASSOCIATED_MEETING);
            }

            if (eventAdded.isMeeting()) {
                Meeting meeting = (Meeting) eventAdded;
                if (!existsMeetingWithSameSchedule(meeting)) addMeeting(meeting.getDescription(), meeting.getDay(), meeting.getMonth(), meeting.getYear(),
                                                                        meeting.getStartHour(), meeting.getStartMinute(), meeting.getEndHour(), meeting.getEndMinute());
                else {
                    Toast.makeText(MainActivity.this, TOAST_SAME_SCHEDULE, Toast.LENGTH_LONG).show();
                    return;
                }

            } else {
                Task task = (Task) eventAdded;
                task.setMeetingAssociated(descriptionAssociatedMeeting);
                addTask(task.getTitle(), task.getStartDay(), task.getStartMonth(), task.getStartYear(),
                        task.getEndDay(), task.getEndMonth(), task.getEndYear(), task.getMeetingAssociated());
            }
            updateView();
            Toast.makeText(MainActivity.this, TOAST_ADD_SUCCESSFULLY, Toast.LENGTH_LONG).show();
        }
        undoneTasks = (ArrayList<Task>) data.getSerializableExtra(UndoneTasksActivity.TASKS_TO_MODIFY);
        if (undoneTasks != null) {
            modifyTasks();
            updateView();
        }
    }

    private void deleteEventsView()
    {
        RelativeLayout parentView;
        // Meeting Buttons
        parentView = (RelativeLayout) findViewById(R.id.mondayEvents);
        for (int i = 0; i < parentView.getChildCount(); i++) {
            View childView = parentView.getChildAt(i);
            if (childView instanceof MeetingButton) childView.setVisibility(View.GONE);
        }
        parentView = (RelativeLayout) findViewById(R.id.tuesdayEvents);
        for (int i = 0; i < parentView.getChildCount(); i++) {
            View childView = parentView.getChildAt(i);
            if (childView instanceof MeetingButton) childView.setVisibility(View.GONE);
        }
        parentView = (RelativeLayout) findViewById(R.id.wednesdayEvents);
        for (int i = 0; i < parentView.getChildCount(); i++) {
            View childView = parentView.getChildAt(i);
            if (childView instanceof MeetingButton) childView.setVisibility(View.GONE);
        }
        parentView = (RelativeLayout) findViewById(R.id.thursdayEvents);
        for (int i = 0; i < parentView.getChildCount(); i++) {
            View childView = parentView.getChildAt(i);
            if (childView instanceof MeetingButton) childView.setVisibility(View.GONE);
        }
        parentView = (RelativeLayout) findViewById(R.id.fridayEvents);
        for (int i = 0; i < parentView.getChildCount(); i++) {
            View childView = parentView.getChildAt(i);
            if (childView instanceof MeetingButton) childView.setVisibility(View.GONE);
        }
        parentView = (RelativeLayout) findViewById(R.id.saturdayEvents);
        for (int i = 0; i < parentView.getChildCount(); i++) {
            View childView = parentView.getChildAt(i);
            if (childView instanceof MeetingButton) childView.setVisibility(View.GONE);
        }
        parentView = (RelativeLayout) findViewById(R.id.sundayEvents);
        for (int i = 0; i < parentView.getChildCount(); i++) {
            View childView = parentView.getChildAt(i);
            if (childView instanceof MeetingButton) childView.setVisibility(View.GONE);
        }

        //Tasks Buttons
        LinearLayout tasksLayout;
        tasksLayout = (LinearLayout) findViewById(R.id.mondayTasks);
        for (int i = 0; i < tasksLayout.getChildCount(); i++) tasksLayout.getChildAt(i).setVisibility(View.GONE);
        tasksLayout = (LinearLayout) findViewById(R.id.tuesdayTasks);
        for (int i = 0; i < tasksLayout.getChildCount(); i++) tasksLayout.getChildAt(i).setVisibility(View.GONE);
        tasksLayout = (LinearLayout) findViewById(R.id.wednesdayTasks);
        for (int i = 0; i < tasksLayout.getChildCount(); i++) tasksLayout.getChildAt(i).setVisibility(View.GONE);
        tasksLayout = (LinearLayout) findViewById(R.id.thursdayTasks);
        for (int i = 0; i < tasksLayout.getChildCount(); i++) tasksLayout.getChildAt(i).setVisibility(View.GONE);
        tasksLayout = (LinearLayout) findViewById(R.id.fridayTasks);
        for (int i = 0; i < tasksLayout.getChildCount(); i++) tasksLayout.getChildAt(i).setVisibility(View.GONE);
        tasksLayout = (LinearLayout) findViewById(R.id.saturdayTasks);
        for (int i = 0; i < tasksLayout.getChildCount(); i++) tasksLayout.getChildAt(i).setVisibility(View.GONE);
        tasksLayout = (LinearLayout) findViewById(R.id.sundayTasks);
        for (int i = 0; i < tasksLayout.getChildCount(); i++) tasksLayout.getChildAt(i).setVisibility(View.GONE);
    }

    private void updateView()
    {
        deleteEventsView();

        TextView monday = (TextView) findViewById(R.id.mondayText1);
        TextView tuesday = (TextView) findViewById(R.id.tuesdayText1);
        TextView wednesday = (TextView) findViewById(R.id.wednesdayText1);
        TextView thursday = (TextView) findViewById(R.id.thursdayText1);
        TextView friday = (TextView) findViewById(R.id.fridayText1);
        TextView saturday = (TextView) findViewById(R.id.saturdayText1);
        TextView sunday = (TextView) findViewById(R.id.sundayText1);
        TextView month = (TextView) findViewById(R.id.currentMonthTextView);
        TextView year = (TextView) findViewById(R.id.currentYearTextView);

        sunday.setText(Utils.days[0]);
        monday.setText(Utils.days[1]);
        tuesday.setText(Utils.days[2]);
        wednesday.setText(Utils.days[3]);
        thursday.setText(Utils.days[4]);
        friday.setText(Utils.days[5]);
        saturday.setText(Utils.days[6]);
        month.setText(Utils.compress(Utils.days[7]));
        year.setText(Utils.days[8]);

        String startDay = Utils.getFirstDayOfTheCurrentWeek();
        String endDay = Utils.getLastDayOfTheCurrentWeek();
        ArrayList<Event> events = findByWeek(startDay, endDay);
        for (Event e : events) {
            if (e.isMeeting()) {
                Meeting m = (Meeting) e;
                String day = Utils.getDayOfWeekInString(m.getDate());
                String text = m.getDescription();
                int startMinute = m.getTotalStartMinute();
                int endMinute = m.getTotalEndMinute();
                createMeetingButton(day, text, startMinute, endMinute, m.getId());
            }
            else {
                Task t = (Task) e;
                Date dSt = new Date();
                Date dEnd = new Date();
                Date dAnt = new Date();
                Date[] limits = Utils.getLimitsOfCurrentWeek();
                int count = 0;
                for (Date d : t.getAllDays()) {
                    if (Utils.compareTo(d, limits[0]) == 1 && Utils.compareTo(d, limits[1]) == -1) {
                        if (count == 0) dSt = d;
                        ++count;
                        dAnt = d;
                    }
                }
                if (count > 0) dEnd = dAnt;
                String startDate = Utils.getDayOfWeekInString(dSt);
                String endDate = Utils.getDayOfWeekInString(dEnd);
                int start = Utils.dayOfWeekToInteger(startDate);
                int end = Utils.dayOfWeekToInteger(endDate);
                for (int i = start; i <= end; i++) {
                    createTaskButton(Utils.integerToDayOfWeek(i), t.getTitle(), t.isDone(), t.getId());
                }
            }
        }

    }

    private void initializeView()
    {
        TextView monday = (TextView) findViewById(R.id.mondayText1);
        TextView tuesday = (TextView) findViewById(R.id.tuesdayText1);
        TextView wednesday = (TextView) findViewById(R.id.wednesdayText1);
        TextView thursday = (TextView) findViewById(R.id.thursdayText1);
        TextView friday = (TextView) findViewById(R.id.fridayText1);
        TextView saturday = (TextView) findViewById(R.id.saturdayText1);
        TextView sunday = (TextView) findViewById(R.id.sundayText1);
        TextView month = (TextView) findViewById(R.id.currentMonthTextView);
        TextView year = (TextView) findViewById(R.id.currentYearTextView);

        String[] days = Utils.getDaysOfWeek();

        sunday.setText(days[0]);
        monday.setText(days[1]);
        tuesday.setText(days[2]);
        wednesday.setText(days[3]);
        thursday.setText(days[4]);
        friday.setText(days[5]);
        saturday.setText(days[6]);
        month.setText(Utils.compress(days[7]));
        year.setText(days[8]);

        // Initialize swipes
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        mainLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeRight() {
                Utils.previousWeek();
                updateView();
            }

            public void onSwipeLeft() {
                Utils.nextWeek();
                updateView();
            }

        });
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeRight() {
                Utils.previousWeek();
                updateView();
            }

            public void onSwipeLeft() {
                Utils.nextWeek();
                updateView();
            }
        });

        LinearLayout sundayLayout = (LinearLayout) findViewById(R.id.sunday);
        sundayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Event> eventsOfDay = findByDay(Utils.getDateInStringOfCurrentWeek(0));
                Intent i = new Intent(MainActivity.this, DayViewActivity.class);
                i.putExtra(EVENTS_DAY_TO_SHOW, eventsOfDay);
                i.putExtra(DATE_IN_LONG_FORMAT, Utils.getDateInLongFormat(Utils.getDateInStringOfCurrentWeek(0)));
                startActivityForResult(i, 0);
            }
        });
        LinearLayout mondayLayout = (LinearLayout) findViewById(R.id.monday);
        mondayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Event> eventsOfDay = findByDay(Utils.getDateInStringOfCurrentWeek(1));
                Intent i = new Intent(MainActivity.this, DayViewActivity.class);
                i.putExtra(EVENTS_DAY_TO_SHOW, eventsOfDay);
                i.putExtra(DATE_IN_LONG_FORMAT, Utils.getDateInLongFormat(Utils.getDateInStringOfCurrentWeek(1)));
                startActivityForResult(i, 0);
            }
        });
        LinearLayout tuesdayLayout = (LinearLayout) findViewById(R.id.tuesday);
        tuesdayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Event> eventsOfDay = findByDay(Utils.getDateInStringOfCurrentWeek(2));
                Intent i = new Intent(MainActivity.this, DayViewActivity.class);
                i.putExtra(EVENTS_DAY_TO_SHOW, eventsOfDay);
                i.putExtra(DATE_IN_LONG_FORMAT, Utils.getDateInLongFormat(Utils.getDateInStringOfCurrentWeek(2)));
                startActivityForResult(i, 0);
            }
        });
        LinearLayout wednesdayLayout = (LinearLayout) findViewById(R.id.wednesday);
        wednesdayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Event> eventsOfDay = findByDay(Utils.getDateInStringOfCurrentWeek(3));
                Intent i = new Intent(MainActivity.this, DayViewActivity.class);
                i.putExtra(EVENTS_DAY_TO_SHOW, eventsOfDay);
                i.putExtra(DATE_IN_LONG_FORMAT, Utils.getDateInLongFormat(Utils.getDateInStringOfCurrentWeek(3)));
                startActivityForResult(i, 0);
            }
        });
        LinearLayout thursdayLayout = (LinearLayout) findViewById(R.id.thursday);
        thursdayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Event> eventsOfDay = findByDay(Utils.getDateInStringOfCurrentWeek(4));
                Intent i = new Intent(MainActivity.this, DayViewActivity.class);
                i.putExtra(EVENTS_DAY_TO_SHOW, eventsOfDay);
                i.putExtra(DATE_IN_LONG_FORMAT, Utils.getDateInLongFormat(Utils.getDateInStringOfCurrentWeek(4)));
                startActivityForResult(i, 0);
            }
        });
        LinearLayout fridayLayout = (LinearLayout) findViewById(R.id.friday);
        fridayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Event> eventsOfDay = findByDay(Utils.getDateInStringOfCurrentWeek(5));
                Intent i = new Intent(MainActivity.this, DayViewActivity.class);
                i.putExtra(EVENTS_DAY_TO_SHOW, eventsOfDay);
                i.putExtra(DATE_IN_LONG_FORMAT, Utils.getDateInLongFormat(Utils.getDateInStringOfCurrentWeek(5)));
                startActivityForResult(i, 0);
            }
        });
        LinearLayout saturdayLayout = (LinearLayout) findViewById(R.id.saturday);
        saturdayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Event> eventsOfDay = findByDay(Utils.getDateInStringOfCurrentWeek(6));
                Intent i = new Intent(MainActivity.this, DayViewActivity.class);
                i.putExtra(EVENTS_DAY_TO_SHOW, eventsOfDay);
                i.putExtra(DATE_IN_LONG_FORMAT, Utils.getDateInLongFormat(Utils.getDateInStringOfCurrentWeek(6)));
                startActivityForResult(i, 0);
            }
        });
    }

    private void initializeProvider()
    {
        SharedPreferences settings = getSharedPreferences(SETTINGS_VARIABLE_KEY, 0);
        boolean silent = settings.getBoolean(SETTINGS_VARIABLE_2_KEY, false);
        if (!silent) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(SETTINGS_VARIABLE_2_KEY, true);
            editor.commit();
            createNewCalendar();
            calendarId = getCalendarId();
            if (calendarId == -1) Toast.makeText(MainActivity.this, TOAST_ERROR_FIND_ID, Toast.LENGTH_LONG).show();
            editor.putLong(CALENDAR_ID_KEY, calendarId);
            editor.commit();
        }
        else {
            calendarId = settings.getLong(CALENDAR_ID_KEY, -1);
            updateView();
        }
    }

    private void createNewCalendar()
    {
        // Object that represents the values we want to add
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Calendars.ACCOUNT_NAME,             MY_ACCOUNT_NAME);
        values.put(CalendarContract.Calendars.ACCOUNT_TYPE,             CalendarContract.ACCOUNT_TYPE_LOCAL);
        values.put(CalendarContract.Calendars.NAME,                     CALENDAR_NAME);
        values.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,    CALENDAR_NAME);
        values.put(CalendarContract.Calendars.CALENDAR_COLOR, 0xFFFF0000);
        values.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        values.put(CalendarContract.Calendars.OWNER_ACCOUNT, OWNER_ACCOUNT);
        values.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, CALENDAR_TIME_ZONE);
        values.put(CalendarContract.Calendars.SYNC_EVENTS, 1);

        // Additional query parameters
        Uri.Builder builder = CalendarContract.Calendars.CONTENT_URI.buildUpon();
        builder.appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, MY_ACCOUNT_NAME);
        builder.appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL);
        builder.appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true");

        Uri uri = getContentResolver().insert(builder.build(), values);
    }

    private long getCalendarId()
    {
        String[] projection = new String[]{CalendarContract.Calendars._ID};
        String selection = CalendarContract.Calendars.ACCOUNT_NAME + " = ? " ;
        String[] selArgs = new String[]{MY_ACCOUNT_NAME};
        Cursor cursor =
                getContentResolver().
                        query(
                                CalendarContract.Calendars.CONTENT_URI,
                                projection,
                                selection,
                                selArgs,
                                null);
        if (cursor.moveToFirst()) return cursor.getLong(0);
        return -1;
    }

    private long addTask(String title, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear, String associatedMeeting)
    {
        // SET start date
        long startTime = Utils.createDate(startDay, startMonth, startYear, 0, 0);
        // SET end date
        long endTime = Utils.createDate(endDay, endMonth, endYear, 0, 0);

        // Initialize the object that represents the values we want to add
        ContentValues values = new ContentValues();
        values.put(Events.DTSTART,                  startTime);
        values.put(Events.DTEND,                    endTime);
        // How to indicate undone tasks -> In the title
        values.put(Events.TITLE,                    title);
        values.put(Events.CALENDAR_ID,              calendarId);
        values.put(Events.EVENT_TIMEZONE,           CALENDAR_TIME_ZONE);
        // How to indicate the optional associated meeting -> In the description
        values.put(Events.DESCRIPTION,              associatedMeeting);
        values.put(Events.ACCESS_LEVEL,             Events.ACCESS_PRIVATE);
        values.put(Events.SELF_ATTENDEE_STATUS,     Events.STATUS_CONFIRMED);
        values.put(Events.ALL_DAY,                  0);
        values.put(Events.ORGANIZER,                OWNER_ACCOUNT);
        values.put(Events.GUESTS_CAN_INVITE_OTHERS, TASK);
        values.put(Events.GUESTS_CAN_MODIFY,        UNDONE_TASK);
        values.put(Events.AVAILABILITY,             Events.AVAILABILITY_BUSY);

        Uri uri = getContentResolver().insert(Events.CONTENT_URI, values);
        return new Long(uri.getLastPathSegment());
    }

    private long addMeeting(String description, int day, int month, int year, int startHour, int startMinute, int endHour, int endMinute)
    {
        // SET start date
        long startTime = Utils.createDate(day, month, year, startHour, startMinute);
        // SET end date
        long endTime = Utils.createDate(day, month, year, endHour, endMinute);

        // Initialize the object that represents the values we want to add
        ContentValues values = new ContentValues();
        values.put(Events.DTSTART,                  startTime);
        values.put(Events.DTEND,                    endTime);
        values.put(Events.RRULE,                    "FREQ=DAILY;COUNT=20;BYDAY=MO,TU,WE,TH,FR;WKST=MO");
        values.put(Events.TITLE,                    description);
        values.put(Events.CALENDAR_ID,              calendarId);
        values.put(Events.EVENT_TIMEZONE,           CALENDAR_TIME_ZONE);
        values.put(Events.ACCESS_LEVEL,             Events.ACCESS_PRIVATE);
        values.put(Events.SELF_ATTENDEE_STATUS,     Events.STATUS_CONFIRMED);
        values.put(Events.ALL_DAY,                  0);
        values.put(Events.ORGANIZER,                OWNER_ACCOUNT);
        values.put(Events.GUESTS_CAN_INVITE_OTHERS, MEETING);
        values.put(Events.GUESTS_CAN_MODIFY,        1);
        values.put(Events.AVAILABILITY,             Events.AVAILABILITY_BUSY);

        Uri uri = getContentResolver().insert(Events.CONTENT_URI, values);
        return new Long(uri.getLastPathSegment());
    }

    private ArrayList<Event> findByWeek(String firstDayString, String lastDayString)
    {
        ArrayList<Event> result = new ArrayList<>();
        ArrayList<Event> events = list();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date firstDay = dateFormat.parse(firstDayString);
            Date lastDay = dateFormat.parse(lastDayString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lastDay);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            lastDay = calendar.getTime();
            for (Event e : events) {
                if (e.isMeeting()) {
                    Meeting m = (Meeting) e;
                    if (Utils.compareTo(m.getDate(), firstDay) == 1 && Utils.compareTo(m.getDate(), lastDay) == -1) {
                        result.add(m);
                    }
                } else {
                    Task t = (Task) e;
                    for (Date d : t.getAllDays()) {
                        if (Utils.compareTo(d, firstDay) == 1 && Utils.compareTo(d, lastDay) == -1) {
                            result.add(t);
                            break;
                        }
                    }
                }
            }
        }
        catch (ParseException pe) {
            Toast.makeText(MainActivity.this, TOAST_ERROR_FORMAT_DATE, Toast.LENGTH_LONG).show();
        }
        return result;
    }

    private ArrayList<Event> findByDay(String day)
    {
        ArrayList<Event> all = list();
        ArrayList<Event> result = new ArrayList<>();
        for (Event e : all) {
            if (e.isMeeting()) {
                Meeting m = (Meeting) e;
                if (Utils.dateToString(m.getDate()).equals(day)) result.add(m);
            } else {
                Task t = (Task) e;
                for (Date date : t.getAllDays()) {
                    if (Utils.dateToString(date).equals(day)) result.add(t);
                }
            }
        }
        return result;
    }

    private ArrayList<Task> findUndoneTasks()
    {
        ArrayList<Event> list = list();
        ArrayList<Task> undoneTasks = new ArrayList<>();
        for (Event e : list) {
            if (!e.isMeeting()) {
                Task t = (Task) e;
                if (!t.isDone()) undoneTasks.add(t);
            }
        }
        return undoneTasks;
    }

    private ArrayList<String> listDescriptionsMeetings()
    {
        ArrayList<Event> all = list();
        ArrayList<String> result = new ArrayList<>();
        for (Event e : all) {
            if (e.isMeeting()) {
                Meeting m = (Meeting) e;
                result.add(m.getDescription());
            }
        }
        return result;
    }

    private ArrayList<Event> list()
    {
        ArrayList<Event> events = new ArrayList<>();
        String[] projection = new String[]{
                Events.GUESTS_CAN_INVITE_OTHERS,
                Events._ID,
                Events.TITLE,
                Events.DTSTART,
                Events.DTEND,
                Events.GUESTS_CAN_MODIFY,
                Events.DESCRIPTION
        };
        String selection = Events.CALENDAR_ID + " = ? " ;
        String[] selArgs = new String[]{Long.toString(calendarId)};
        Cursor cursor =
                getContentResolver().
                        query(
                                Events.CONTENT_URI,
                                projection,
                                selection,
                                selArgs,
                                null);
        while (cursor.moveToNext()) {
            int type = cursor.getInt(0);
            if (type == MEETING)
                events.add(new Meeting(cursor.getLong(1), new Date(cursor.getLong(3)), new Date(cursor.getLong(4)), cursor.getString(2)));
            else if (type == TASK) {
                boolean done = (cursor.getInt(5) == DONE_TASK);
                events.add(new Task(cursor.getLong(1), cursor.getString(2), new Date(cursor.getLong(3)), new Date(cursor.getLong(4)), done, cursor.getString(6)));
            }
        }
        cursor.close();
        return events;
    }

    private void deleteAll()
    {
        String[] selArgs =
                new String[]{Long.toString(calendarId)};
        String selection = Events.CALENDAR_ID + " = ? " ;
        int deleted =
                getContentResolver().
                        delete(
                                Events.CONTENT_URI,
                                selection,
                                selArgs);
    }

    private void deleteEventById(long id)
    {
        String[] selArgs =
                new String[]{Long.toString(id)};
        String selection = Events._ID + " = ? " ;
        int deleted =
                getContentResolver().
                        delete(
                                Events.CONTENT_URI,
                                selection,
                                selArgs);
    }

    private void setDoneTaskById(long id)
    {
        ContentValues values = new ContentValues();
        values.put(Events.GUESTS_CAN_MODIFY, DONE_TASK);
        String selection = Events._ID + " = ? " ;
        String[] selArgs = new String[]{Long.toString(id)};
        int updated =
                getContentResolver().
                        update(
                                Events.CONTENT_URI,
                                values,
                                selection,
                                selArgs);
    }

    private void createMeetingButton(String day, String text, int startTime, int endTime, final long id)
    {
        RelativeLayout dayLayout;
        if (day.equals(MONDAY)) dayLayout = (RelativeLayout) findViewById(R.id.mondayEvents);
        else if (day.equals(TUESDAY)) dayLayout = (RelativeLayout) findViewById(R.id.tuesdayEvents);
        else if (day.equals(WEDNESDAY)) dayLayout = (RelativeLayout) findViewById(R.id.wednesdayEvents);
        else if (day.equals(THURSDAY)) dayLayout = (RelativeLayout) findViewById(R.id.thursdayEvents);
        else if (day.equals(FRIDAY)) dayLayout = (RelativeLayout) findViewById(R.id.fridayEvents);
        else if (day.equals(SATURDAY)) dayLayout = (RelativeLayout) findViewById(R.id.saturdayEvents);
        else if (day.equals(SUNDAY)) dayLayout = (RelativeLayout) findViewById(R.id.sundayEvents);
        else return;

        int duration = endTime - startTime;
        int durationInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, duration, getResources().getDisplayMetrics());
        int marginInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, startTime, getResources().getDisplayMetrics());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, durationInPx);
        params.setMargins(0, marginInPx, 0, 0);
        MeetingButton mButton = new MeetingButton(this, text);
        dayLayout.addView(mButton, params);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete a Meeting")
                        .setMessage("Are you sure you want to delete this meeting?")
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteEventById(id);
                                updateView();
                                Toast.makeText(MainActivity.this, TOAST_DELETE_MEETING, Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(getResources().getDrawable(R.drawable.ic_menu_close_clear_cancel))
                        .show();
            }
        });
    }

    private void createTaskButton(String day, String text, boolean isDone, final long id)
    {
        LinearLayout dayLayout;
        if (day.equals(MONDAY)) dayLayout = (LinearLayout) findViewById(R.id.mondayTasks);
        else if (day.equals(TUESDAY)) dayLayout = (LinearLayout) findViewById(R.id.tuesdayTasks);
        else if (day.equals(WEDNESDAY)) dayLayout = (LinearLayout) findViewById(R.id.wednesdayTasks);
        else if (day.equals(THURSDAY)) dayLayout = (LinearLayout) findViewById(R.id.thursdayTasks);
        else if (day.equals(FRIDAY)) dayLayout = (LinearLayout) findViewById(R.id.fridayTasks);
        else if (day.equals(SATURDAY)) dayLayout = (LinearLayout) findViewById(R.id.saturdayTasks);
        else if (day.equals(SUNDAY)) dayLayout = (LinearLayout) findViewById(R.id.sundayTasks);
        else return;

        TaskButton tButton = new TaskButton(this, text, isDone);
        dayLayout.addView(tButton);
        tButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete a Task")
                        .setMessage("Are you sure you want to delete this task?")
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteEventById(id);
                                updateView();
                                Toast.makeText(MainActivity.this, TOAST_TASK_MEETING, Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(getResources().getDrawable(R.drawable.ic_menu_close_clear_cancel))
                        .show();
            }
        });
    }

    private boolean existsMeetingWithSameSchedule(Meeting meeting)
    {
        ArrayList<Event> all = list();
        for (Event e : all) {
            if (e.isMeeting()) {
                Meeting m = (Meeting) e;
                if (Utils.isSameDay(m.getDate(), meeting.getDate()) &&
                        ((Utils.compareTo(m.getHourEnd(), meeting.getHourStart()) >= 0 && Utils.compareTo(m.getHourEnd(), meeting.getHourEnd()) <= 0) ||
                                (Utils.compareTo(m.getHourStart(), meeting.getHourEnd()) <= 0 && Utils.compareTo(m.getHourStart(), meeting.getHourStart()) >= 0)))
                    return  true;
            }
        }
        return false;
    }

    private void modifyTasks()
    {
        for (Task t : undoneTasks) {
            if (t.isDone()) setDoneTaskById(t.getId());
        }
    }
}