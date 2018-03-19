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

public class GameTimer extends RosterActivity {
    Chronometer mChronometer;
    Button startButton;
    Button stopButton;
    Button resetButton;
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

        // Watch for button clicks.

        startButton.setOnClickListener(mStartListener);


        stopButton.setOnClickListener(mStopListener);


        resetButton.setOnClickListener(mResetListener);


    }

    View.OnClickListener mStartListener = new OnClickListener() {
        public void onClick(View v) {
            startButton.setVisibility(View.INVISIBLE);
            stopButton.setVisibility(View.VISIBLE);
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