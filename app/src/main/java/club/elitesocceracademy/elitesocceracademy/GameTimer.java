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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameTimer extends RosterActivity {
    private Chronometer mChronometer;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;
    private int minutesPassed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gametimer);
        startButton = (Button) findViewById(R.id.start);
        resetButton = (Button) findViewById(R.id.reset);
        stopButton = (Button) findViewById(R.id.stop);


        MyCustomAdapter adapter = new MyCustomAdapter(RosterActivity.list, this);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);



        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        minutesPassed = 0;
        // Watch for button clicks.

        startButton.setOnClickListener(mStartListener);


        stopButton.setOnClickListener(mStopListener);


        resetButton.setOnClickListener(mResetListener);





    }

    Runnable helloRunnable = new Runnable() {
        public void run() {
            while(minutesPassed==1){
                try {
                    startActivity(new Intent(GameTimer.this, FirstHalfNotes.class));
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
            System.out.println(minutesPassed);
            minutesPassed++;
        }
    };

    View.OnClickListener mStartListener = new OnClickListener() {
        public void onClick(View v) {
            startButton.setVisibility(View.INVISIBLE);
            stopButton.setVisibility(View.VISIBLE);
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(helloRunnable, 0, 60, TimeUnit.SECONDS);
            mChronometer.start();
        }
    };

    View.OnClickListener mStopListener = new OnClickListener() {
        public void onClick(View v) {
            stopButton.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            mChronometer.stop();
        }
    };

    View.OnClickListener mResetListener = new OnClickListener() {
        public void onClick(View v) {
            stopButton.setVisibility(View.INVISIBLE);
            startButton.setVisibility(View.VISIBLE);
            mChronometer.stop();
            mChronometer.setBase(SystemClock.elapsedRealtime());
        }
    };

    View.OnClickListener mSetFormatListener = new OnClickListener() {
        public void onClick(View v) {
            mChronometer.setFormat("Formatted time (%s)");
        }
    };

    View.OnClickListener mClearFormatListener = new OnClickListener() {
        public void onClick(View v) {
            mChronometer.setFormat(null);
        }
    };
}