package club.elitesocceracademy.elitesocceracademy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameTimer extends RosterActivity {

    private Button startButton;
    private Button stopButton;
    private Button resetButton;
    private int secondsPassed;
    int ageGroup;

    int minutes;
    int seconds;
    private TextView chronometer;
    static int tempTime;
    private static int startingTime = 1500;
    protected MyCustomAdapter adapter = new MyCustomAdapter(RosterActivity.list, this);
    public static int timeLeftInSeconds = startingTime;
    //When the timer is stopped the time remaining originalTime is used to reset the GUI label.
    public static int originalTime = timeLeftInSeconds;

    static String intialTimerLabel = timeLeftInSeconds / 60 + ":00";
    //Creates and instance of the timer
    public static Timer timer = new Timer();
    private static String updateTimerLabel;

    PlayerTimers playerTimers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gametimer);
        startButton = (Button) findViewById(R.id.start);
        resetButton = (Button) findViewById(R.id.reset);
        stopButton = (Button) findViewById(R.id.stop);
        chronometer  = (TextView)findViewById(R.id.chronometer);
        playerTimers = new PlayerTimers(chronometer,this,adapter);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle_btn);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                adapter.getTimers(selectedItem,position,playerTimers.timeLeftInSeconds);
                //adapter.getTotalTime(selectedItem);



            }
        });





        //mChronometer = (Chronometer) findViewById(R.id.chronometer);
        secondsPassed = 0;
        // Watch for button clicks.

        startButton.setOnClickListener(mStartListener);

        stopButton.setOnClickListener(mStopListener);

        resetButton.setOnClickListener(mResetListener);





    }

    View.OnClickListener mStartListener = new OnClickListener() {
        public void onClick(View v) {
            startButton.setVisibility(View.INVISIBLE);
            stopButton.setVisibility(View.VISIBLE);
            adapter.toggleButtons();
            playerTimers.startTimer();


        }
    };



    View.OnClickListener mStopListener = new OnClickListener() {
        public void onClick(View v) {
            stopButton.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            PlayerTimers.stopTimer();


        }
    };



    View.OnClickListener mResetListener = new OnClickListener() {
        public void onClick(View v) {
            stopButton.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            PlayerTimers.restartTimer();


            // mChronometer.setBase(SystemClock.elapsedRealtime());
        }
    };




}