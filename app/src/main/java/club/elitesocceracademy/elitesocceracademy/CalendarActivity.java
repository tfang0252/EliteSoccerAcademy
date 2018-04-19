package club.elitesocceracademy.elitesocceracademy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.CalendarView;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        CalendarView mCalendarView;
        mCalendarView = findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 + "/" + i;
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy: " + date);

                Intent intent = new Intent(CalendarActivity.this, CalendarShowDetail.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }
}


//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.CalendarView;

//
//public class CalendarActivity extends AppCompatActivity {
//
//    private static final String TAG = "CalendarActivity";
//
//    private CalendarView mCalendarView;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.calendar_layout);
//        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
//
//        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
//                String date = (i1 + 1) + "/" + i2 + "/" + i;
//                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy: " + date);
//
//                Intent intent = new Intent(CalendarActivity.this, CalendarShowDetail.class);
//                intent.putExtra("date", date);
//                startActivity(intent);
//            }
//        });
//    }
//}