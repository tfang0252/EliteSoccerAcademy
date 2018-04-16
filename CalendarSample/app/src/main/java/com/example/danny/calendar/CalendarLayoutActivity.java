package com.example.danny.calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

public class CalendarLayoutActivity extends AppCompatActivity {


    private  static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_layout);


        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + month + "/"+ year ;
                Log.d(TAG, "onSelectedDayChange: dd/mm/yyyy:" + date);
                Intent intent = new Intent(CalendarLayoutActivity.this,CalendarActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);

            }
        });
    }
}
