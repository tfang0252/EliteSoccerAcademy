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

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameTimer extends RosterActivity {
    private Chronometer mChronometer;
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
    //
    public static int timeLeftInSeconds = startingTime;
    //When the timer is stopped the time remaining originalTime is used to reset the GUI label.
    public static int originalTime = timeLeftInSeconds;

    static String intialTimerLabel = timeLeftInSeconds / 60 + ":00";
    //Creates and instance of the timer
    public static Timer timer = new Timer();
    private static String updateTimerLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gametimer);
        startButton = (Button) findViewById(R.id.start);
        resetButton = (Button) findViewById(R.id.reset);
        stopButton = (Button) findViewById(R.id.stop);
        chronometer  = (TextView)findViewById(R.id.chronometer);

        MyCustomAdapter adapter = new MyCustomAdapter(RosterActivity.list, this);
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle_btn);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);







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
            //mChronometer.start();
            //ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            //executor.scheduleAtFixedRate(halfTimeClock, 0, 1, TimeUnit.SECONDS);
            timer.scheduleAtFixedRate(new TimerTask() { // Below is the timerTask }, 0, 1000);
                // (i = minutes -1) so that the timer will not start with an extra minute
                int i = timeLeftInSeconds / 60; // minutes
                int j = timeLeftInSeconds % 60; // seconds


                public void run() {

                    updateTimerLabel = timeLeftInSeconds + "";
                    //GUI.updateLabel(updateTimerLabel);
                    j--;
                    timeLeftInSeconds--;

                    if (j < 0) {
                        i--;
                        j = 59;

                    }
                    // Updates the GUI's label with the time every second for second values of 10 or greater.
                    if (j >= 10) {
                        // Creates a string and saves it to the updateTimerLabel variable
                        updateTimerLabel = i + ":" + j;
                        // System.out.println(updateTimerLabel);
                        tempTime = timeLeftInSeconds;
                        chronometer.setText(updateTimerLabel);

                    }
                    // Updates the GUI's label with the time every second for second values less than 10.
                    //This basically formats the GUI label to have a double zero for seconds ex. :00.
                    else
                        // Creates a string and saves it to the updateTimerLabel variable
                        updateTimerLabel = i + ":0" + j;
                    tempTime = timeLeftInSeconds;
                    chronometer.setText(updateTimerLabel);

                    // System.out.println(i + ":0" + j);

                    // Updates the GUI's label when the timer's minutes and seconds are zero.
                    if (i == 0 && j == 0) {
                        // Stops the timer and purges it when the timer's minutes and seconds are both zero.
                        timer.cancel();
                        timer.purge();
                        updateTimerLabel = "00:00";
                        chronometer.setText(updateTimerLabel);



                        // System.out.println("End of the game.");
                    }

                }// End of run method


            }, 0, 1000);
        }
    };



    View.OnClickListener mStopListener = new OnClickListener() {
        public void onClick(View v) {
            stopButton.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            timer.cancel();
            timer.purge();
            timer = new Timer();
            tempTime = timeLeftInSeconds;


        }
    };



    View.OnClickListener mResetListener = new OnClickListener() {
        public void onClick(View v) {
            stopButton.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            timer.cancel();
            timer.purge();
            timer = new Timer();
            timeLeftInSeconds = originalTime;
            updateTimerLabel = originalTime / 60 + ":00";
            chronometer.setText(updateTimerLabel);


           // mChronometer.setBase(SystemClock.elapsedRealtime());
        }
    };



    View.OnClickListener mSetFormatListener = new OnClickListener() {
        public void onClick(View v) {
            //mChronometer.setFormat("Formatted time (%s)");
        }
    };

    View.OnClickListener mClearFormatListener = new OnClickListener() {
        public void onClick(View v) {
            //mChronometer.setFormat(null);
        }
    };


}