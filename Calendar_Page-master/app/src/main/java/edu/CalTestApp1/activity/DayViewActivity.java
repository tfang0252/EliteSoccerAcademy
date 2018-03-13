package edu.CalTestApp1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.CalTestApp1.R;
import edu.CalTestApp1.domain.Event;
import edu.CalTestApp1.domain.Meeting;
import edu.CalTestApp1.domain.Task;
import edu.CalTestApp1.utils.MeetingButton;
import edu.CalTestApp1.utils.TaskButton;

public class DayViewActivity extends Activity {

    private ArrayList<Event> eventsOfDay;
    private String dateInLongFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_view);
        eventsOfDay = (ArrayList<Event>) getIntent().getSerializableExtra(MainActivity.EVENTS_DAY_TO_SHOW);
        dateInLongFormat = getIntent().getStringExtra(MainActivity.DATE_IN_LONG_FORMAT);
        initializeView();
    }

    private void initializeView() {
        TextView title = (TextView) findViewById(R.id.dateTitle);
        title.setText(dateInLongFormat);

        LinearLayout tasksToAdd = (LinearLayout) findViewById(R.id.tasksLinearLayout);
        RelativeLayout meetingsToAdd = (RelativeLayout) findViewById(R.id.meetings);
        int numberOfTasks = 0;

        for (Event e : eventsOfDay) {
            if (e.isMeeting()) {
                Meeting m = (Meeting) e;
                int startMinute = m.getTotalStartMinute();
                int endMinute = m.getTotalEndMinute();
                int durationInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, endMinute - startMinute, getResources().getDisplayMetrics());
                int marginInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, startMinute, getResources().getDisplayMetrics());
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, durationInPx);
                params.setMargins(0, marginInPx, 0, 0);
                meetingsToAdd.addView(new MeetingButton(this, m.getDescription()), params);
            }
            else {
                Task t = (Task) e;
                tasksToAdd.addView(new TaskButton(this, t.getTitle(), t.isDone()));
                ++numberOfTasks;
            }
        }

        if (numberOfTasks == 0) findViewById(R.id.secondView).setVisibility(View.GONE);
    }

}
