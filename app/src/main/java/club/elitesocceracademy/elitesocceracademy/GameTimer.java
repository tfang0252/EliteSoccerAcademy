package club.elitesocceracademy.elitesocceracademy;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameTimer extends RosterActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private HashSet<Integer> gameIDSet = new HashSet<>();
    private Context context = this;
    private String GameID = "1";
    private String timeCSV = "PlayerTimes.csv";
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
        playerTimers = new PlayerTimers(chronometer,this,adapter,GameTimer.this);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle_btn);
        final ListView listView = (ListView) findViewById(R.id.listView);

        readCSV();



        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                // adapter.getTimers(selectedItem,position,playerTimers.timeLeftInSeconds);
                //adapter.getTotalTime(selectedItem);


            }
        });





        //mChronometer = (Chronometer) findViewById(R.id.chronometer);
        secondsPassed = 0;
        // Watch for button clicks.

        startButton.setOnClickListener(mStartListener);

        stopButton.setOnClickListener(mStopListener);

        resetButton.setOnClickListener(mResetListener);

        //System.out.print("***************" + playerTimers.doneOrNah);



    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public void writeToCSV() throws IOException {
        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath2 = baseDir + File.separator + timeCSV;
        File f2 = new File(filePath2);
        CSVWriter writer2;
        FileWriter mFileWriter2;
        if (f2.exists() && !f2.isDirectory()) {
            mFileWriter2 = new FileWriter(filePath2, true);
            writer2 = new CSVWriter(mFileWriter2);
        } else {
            writer2 = new CSVWriter(new FileWriter(filePath2));
            String[] time = {"PlayerName","GameID","TimePlayed"};
            writer2.writeNext(time);
        }
        //adapter.getTotalTime();
        System.out.println("***************" + adapter.totalPlayerTime.get("Ava Hick") + "***************");
        for(String players:list) {
            //if(adapter.totalPlayerTime.get(players)!=null) {
            String[] time = {players, GameID, Integer.toString(adapter.totalPlayerTime.get(players))};
            System.out.println(filePath2);
            writer2.writeNext(time);
            //}
        }


        writer2.close();
    }

    public void readCSV(){
        try{
            String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
            String filePath2 = baseDir + File.separator + "PlayerTimes.csv";
            File f2 = new File(filePath2);
            CSVReader reader = new CSVReader(new FileReader(f2));
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String player = nextLine[0];
                String gameID = nextLine[1];
                String gameTime = nextLine[2];
                if(!gameID.equals("GameID")) {
                    gameIDSet.add(Integer.parseInt(gameID));
                }
            }
            int newTime = Collections.max(gameIDSet) + 1;
            GameID = Integer.toString(newTime);
        }catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } {

        }




    }

    public void getTotalTime() {



        for(String selectedItem:list) {
            int total = 0;
            if(adapter.timeSwitchMap.get(selectedItem)==true){
                adapter.timeSwitchMap.put(selectedItem,false);
                adapter.offTimeMap.get(selectedItem).add(0);
            }
            for (int i = 0; i < adapter.onTimeMap.get(selectedItem).size(); i++) {
                if (adapter.offTimeMap.get(selectedItem).isEmpty()) {
                    adapter.offTimeMap.get(selectedItem).add(adapter.onTimeMap.get(selectedItem).getLast());
                }
                total = total + (adapter.onTimeMap.get(selectedItem).get(i) - adapter.offTimeMap.get(selectedItem).get(i));
                getTimers(selectedItem);
                System.out.println(selectedItem + "-- total: " + total);
            }
            adapter.totalPlayerTime.put(selectedItem, total);
        }

        verifyStoragePermissions(GameTimer.this);
        try {
            writeToCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //S3Upload s3Upload2 = new S3Upload(context,timeCSV,view);

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

    public void getTimers(String selectedItem) {
        //System.out.println(selectedItem + " " + timeSwitchMap.get(selectedItem));
        //System.out.println(selectedItem + " " + playerTimeMap.get(selectedItem));
        System.out.println(selectedItem + " on times:" + adapter.onTimeMap.get(selectedItem));
        System.out.println(selectedItem + " off times:" + adapter.offTimeMap.get(selectedItem));
    }




}